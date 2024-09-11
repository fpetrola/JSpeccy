package com.fpetrola.z80.instructions.base;

import com.fpetrola.z80.cpu.DefaultInstructionFetcher;
import com.fpetrola.z80.cpu.InstructionExecutor;
import com.fpetrola.z80.instructions.Call;
import com.fpetrola.z80.instructions.Pop;
import com.fpetrola.z80.instructions.Ret;
import com.fpetrola.z80.instructions.ReturnAddressWordNumber;
import com.fpetrola.z80.jspeccy.MutableOpcodeConditions;
import com.fpetrola.z80.minizx.emulation.MockedMemory;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.decoder.table.FetchNextOpcodeInstructionFactory;
import com.fpetrola.z80.opcodes.references.ConditionAlwaysTrue;
import com.fpetrola.z80.opcodes.references.OpcodeConditions;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.spy.InstructionSpy;
import com.fpetrola.z80.spy.MemorySpy;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import static com.fpetrola.z80.opcodes.references.WordNumber.createValue;

public class SymbolicExecutionHelper<T extends WordNumber> {
  public static Stack<Object> stackFrames = new Stack<>();
  public static Map<Integer, RoutineExecution> routineExecutions = new HashMap<>();

  DefaultInstructionFetcher<T> createInstructionFetcher(InstructionSpy spy, State<T> state, RealCodeBytecodeCreationTestsBase realCodeBytecodeCreationTestsBase) {
    return new DefaultInstructionFetcher<T>(state, createOpcodeConditions(state), new FetchNextOpcodeInstructionFactory(spy, state), (InstructionExecutor<T>) realCodeBytecodeCreationTestsBase.transformerInstructionExecutor, createInstructionFactory(state));
  }

  InstructionFactory createInstructionFactory(final State state) {
    return new InstructionFactory<T>(state) {
      @Override
      public Pop Pop(OpcodeReference target) {
        return new Pop<T>(target, sp, memory, flag) {
          public int execute() {
            final T read = (T) Memory.read16Bits(memory, sp.read());
            if (read instanceof ReturnAddressWordNumber) {
              RoutineExecution routineExecution = getRoutineExecution();

              if (routineExecution.branchPoints.isEmpty()) {
                memoryReadOnly(false, state);
                stackFrames.pop();
                setNextPC(read);
              } else {
                routineExecution.executedPoints.add(pc.read().intValue());
                routineExecution.isNoConditionRet = true;
                routineExecution.retInstruction = pc.read().intValue();
              }

              target.write(read);
              return 0;
            } else
              return super.execute();
          }
        };
      }
    };
  }

  protected <T extends WordNumber> OpcodeConditions createOpcodeConditions(State<T> state) {
    return new MutableOpcodeConditions(state, (instruction, alwaysTrue, doBranch) -> {
      int pcValue = state.getPc().read().intValue();

      if (pcValue == 36337) {

      } else {
        RoutineExecution routineExecution = getRoutineExecution();
        boolean known = routineExecution.branchPoints.contains(pcValue);
        if (known) if (routineExecution.branchPoints.peek() == pcValue) routineExecution.branchPoints.poll();
        else System.out.println("error!");

        if (pcValue == 35703)
          System.out.print("");

        if (!alwaysTrue && !known)
          routineExecution.branchPoints.offer(pcValue);

        if (false && !known && alwaysTrue && instruction instanceof Call call) {
          routineExecution.branchPoints.offer(pcValue);
          return false;
        } else {
          if (instruction instanceof Ret ret) {
            routineExecution.retInstruction = pcValue;
            routineExecution.isNoConditionRet = ret.getCondition() instanceof ConditionAlwaysTrue;
            if (routineExecution.branchPoints.isEmpty()) {
              memoryReadOnly(false, state);
              stackFrames.pop();
              return true;
            } else {
              routineExecution.retInstruction = pcValue;
              return false;
            }
          } else if (doBranch) {
            memoryReadOnly(false, state);
            if (instruction instanceof Call call) {
              int jumpAddress = call.getJumpAddress().intValue();
              createRoutineExecution(jumpAddress);
            }
          }
        }
      }
      return doBranch;
    });
  }

  public void createRoutineExecution(int jumpAddress) {
    // if (jumpAddress == 35211) System.out.println("start routine: " + jumpAddress);
    stackFrames.push(jumpAddress);
    RoutineExecution routineExecution = routineExecutions.get(jumpAddress);
    if (routineExecution == null) {
      routineExecutions.put(jumpAddress, routineExecution = new RoutineExecution());
    } else
      System.err.print("");

    routineExecution.start = jumpAddress;
  }

  void stepUntilComplete(RealCodeBytecodeCreationTestsBase realCodeBytecodeCreationTestsBase) {
    State state = realCodeBytecodeCreationTestsBase.state;
    memoryReadOnly(true, state);

    int firstAddress = realCodeBytecodeCreationTestsBase.firstAddress;

    createRoutineExecution(firstAddress);
    Register<T> pc = state.getPc();
    pc.write(createValue(firstAddress));
    boolean ready = false;
    int pcValue;
    int lastPcValue;
    while (!ready) {
      memoryReadOnly(false, state);

      pcValue = pc.read().intValue();

      if (pcValue == 38094)
        System.err.println("");
      if (pcValue < 16384)
        ready = true;

      lastPcValue = pcValue;
      if (stackFrames.isEmpty())
        return;
      RoutineExecution routineExecution = getRoutineExecution();

      if (routineExecution.isNoConditionRet) {
        routineExecution.isNoConditionRet = false;
        if (routineExecution.branchPoints.isEmpty()) {
          if (stackFrames.size() == 1) ready = true;
          else {
            pc.write(createValue(routineExecution.retInstruction));
            realCodeBytecodeCreationTestsBase.step();
          }
        } else {
          pc.write(createValue(routineExecution.branchPoints.peek()));
          realCodeBytecodeCreationTestsBase.step();
        }
      } else if (routineExecution.executedPoints.contains(pcValue) || pcValue < 16384 + 4096) {
        if (routineExecution.branchPoints.isEmpty()) {
          T value = createValue(routineExecution.retInstruction);
          if (value.intValue() == 0) ready = true;
          else {
            pc.write(value);
            realCodeBytecodeCreationTestsBase.step();
            if (stackFrames.isEmpty()) ready = true;
          }
        } else {
          pc.write(createValue(routineExecution.branchPoints.peek()));
          realCodeBytecodeCreationTestsBase.step();
        }
      } else {
        routineExecution.executedPoints.add(pcValue);
        realCodeBytecodeCreationTestsBase.step();
      }
    }

    routineExecutions.entrySet().stream().forEach(e -> {
      if (!e.getValue().branchPoints.isEmpty()) {
        System.err.println("");
      }
    });
    return;
  }

  public RoutineExecution getRoutineExecution() {
    return routineExecutions.get(stackFrames.peek());
  }

  protected void memoryReadOnly(boolean readOnly, State state) {
    MockedMemory<T> memory = (MockedMemory<T>) ((MemorySpy<T>) state.getMemory()).getMemory();
    memory.enableReadyOnly(readOnly);
  }
}

package com.fpetrola.z80.instructions.base;

import com.fpetrola.z80.cpu.DefaultInstructionFetcher;
import com.fpetrola.z80.cpu.InstructionExecutor;
import com.fpetrola.z80.cpu.InstructionFetcher;
import com.fpetrola.z80.instructions.*;
import com.fpetrola.z80.jspeccy.MutableOpcodeConditions;
import com.fpetrola.z80.minizx.emulation.MockedMemory;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.decoder.table.FetchNextOpcodeInstructionFactory;
import com.fpetrola.z80.opcodes.references.*;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.spy.InstructionSpy;
import com.fpetrola.z80.spy.MemorySpy;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import static com.fpetrola.z80.opcodes.references.WordNumber.createValue;

public class SymbolicExecutionAdapter<T extends WordNumber> {
  private final State<? extends WordNumber> state;

  public <T extends WordNumber> SymbolicExecutionAdapter(State<T> state) {
    this.state = state;
  }

  public Stack<Object> stackFrames = new Stack<>();

  public Map<Integer, RoutineExecution> routineExecutions = new HashMap<>();

  InstructionFetcher createInstructionFetcher(InstructionSpy spy, State<T> state, InstructionExecutor<T> instructionExecutor) {
    return new DefaultInstructionFetcher<T>(state, createOpcodeConditions(state), new FetchNextOpcodeInstructionFactory(spy, state), instructionExecutor, createInstructionFactory(state));
  }

  public DefaultInstructionFactory createInstructionFactory(final State state) {
    return new DefaultInstructionFactory<T>(state) {
      @Override
      public Pop Pop(OpcodeReference target) {
        return new PopReturnAddress(target, sp, memory, flag, pc);
      }

      @Override
      public Call Call(Condition condition, ImmutableOpcodeReference positionOpcodeReference) {
        return new Call<T>(positionOpcodeReference, condition, pc, sp, this.state.getMemory()) {
          public T beforeJump(T jumpAddress) {
            T value = pc.read().plus(length);
            value = (T) new ReturnAddressWordNumber(value.intValue(), pc.read().intValue());
            Push.doPush(value, sp, memory);
            return jumpAddress;
          }

          protected String getName() {
            return "Call_";
          }
        };
      }
    };
  }

  public <T extends WordNumber> OpcodeConditions createOpcodeConditions(State<T> state) {
    return new MutableOpcodeConditions(state, (instruction, alwaysTrue, doBranch) -> {
      int pcValue = state.getPc().read().intValue();

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

  public void stepUntilComplete(Z80InstructionDriver z80InstructionDriver, State state1, int firstAddress1, int minimalValidCodeAddress) {
    State state = state1;
    memoryReadOnly(true, state);

    int firstAddress = firstAddress1;

    createRoutineExecution(firstAddress);
    Register<T> pc = state.getPc();
    pc.write(createValue(firstAddress));
    boolean ready = false;
    int lastPcValue;
    int pcValue;
    while (!ready) {
      memoryReadOnly(false, state);

      pcValue = pc.read().intValue();

      if (pcValue == 37047)
        System.err.println("");
      if (pcValue < minimalValidCodeAddress)
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
            z80InstructionDriver.step();
          }
        } else {
          pc.write(createValue(routineExecution.branchPoints.peek()));
          z80InstructionDriver.step();
        }
      } else if (routineExecution.executedPoints.contains(pcValue) || pcValue < minimalValidCodeAddress) {
        if (routineExecution.branchPoints.isEmpty()) {
          T value = createValue(routineExecution.retInstruction);
          if (value.intValue() == 0) ready = true;
          else {
            pc.write(value);
            z80InstructionDriver.step();
            if (stackFrames.isEmpty()) ready = true;
          }
        } else {
          pc.write(createValue(routineExecution.branchPoints.peek()));
          z80InstructionDriver.step();
        }
      } else {
        routineExecution.executedPoints.add(pcValue);
        z80InstructionDriver.step();
        if (stackFrames.isEmpty() && pcValue == routineExecution.retInstruction)
          ready = true;
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

  public abstract class SymbolicInstructionFactoryDelegator implements InstructionFactoryDelegator {
    private InstructionFactory instructionFactory;

    public SymbolicInstructionFactoryDelegator() {
      this.instructionFactory = createInstructionFactory(state);
    }

    @Override
    public InstructionFactory getDelegate() {
      return instructionFactory;
    }
  }

  public class PopReturnAddress extends Pop<T> {
    private final Register<T> pc;

    public ReturnAddressWordNumber getReturnAddress() {
      return returnAddress;
    }

    private ReturnAddressWordNumber returnAddress;

    public PopReturnAddress(OpcodeReference target, Register<T> sp, Memory<T> memory, Register<T> flag, Register<T> pc) {
      super(target, sp, memory, flag);
      this.pc = pc;
    }

    public int execute() {
      returnAddress = null;
      T read = doPop(memory, sp);
      target.write(read);

      if (read instanceof ReturnAddressWordNumber returnAddressWordNumber) {
        returnAddress = returnAddressWordNumber;
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

        return 0;
      } else
        return super.execute();
    }

    protected String getName() {
      return "Pop_";
    }
  }
}































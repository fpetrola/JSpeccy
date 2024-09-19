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
  private int lastPc;

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
      public Push Push(OpcodeReference target) {
        return new PushReturnAddress(target, sp, memory);
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
        addBranch(pcValue, routineExecution);

      if (false && !known && alwaysTrue && instruction instanceof Call call) {
        addBranch(pcValue, routineExecution);
        return false;
      } else {
        if (instruction instanceof Ret ret) {
          routineExecution.retInstruction = pcValue;
          routineExecution.isFinalRet = ret.getCondition() instanceof ConditionAlwaysTrue;
          if (!routineExecution.hasPendingPoints()) {
            memoryReadOnly(false, state);
            stackFrames.pop();
            return true;
          } else {
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

  public void stepUntilComplete(Z80InstructionDriver z80InstructionDriver, State state, int firstAddress, int minimalValidCodeAddress) {
    memoryReadOnly(false, state);

    createRoutineExecution(firstAddress);
    Register<T> pc = state.getPc();
    pc.write(createValue(firstAddress));

    executeAllCode(z80InstructionDriver, minimalValidCodeAddress, pc);

    routineExecutions.entrySet().stream().forEach(e -> {
      if (!e.getValue().branchPoints.isEmpty()) {
        System.err.println("");
      }
    });
    return;
  }

  private void executeAllCode(Z80InstructionDriver z80InstructionDriver, int minimalValidCodeAddress, Register<T> pc) {
    boolean ready = false;

    while (!ready) {
      int pcValue = pc.read().intValue();
      if (pcValue < minimalValidCodeAddress)
        ready = true;

      int next;
      RoutineExecution routineExecution = getRoutineExecution();
      if (!routineExecution.executedPoints.contains(pcValue) && pcValue >= minimalValidCodeAddress) {
        next = pcValue;
        routineExecution.executedPoints.add(pcValue);
      } else
        next = !routineExecution.hasPendingPoints() ? routineExecution.retInstruction : routineExecution.getNextPending();

      pc.write(createValue(next));
      z80InstructionDriver.step();
      if (routineExecution.retInstruction == next && routineExecution.hasPendingPoints())
        pc.write(createValue(routineExecution.getNextPending()));

      ready |= stackFrames.isEmpty();
    }
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
      setNextPC(null);
      returnAddress = null;
      final T read = Memory.read16Bits(memory, sp.read());

      if (read instanceof ReturnAddressWordNumber returnAddressWordNumber) {
        RoutineExecution routineExecution = routineExecutions.get(stackFrames.get(stackFrames.size() - 2));
        addPending(returnAddressWordNumber.intValue(), routineExecution);
        addPending(pc.read().intValue() + 1, routineExecution);

        RoutineExecution lastRoutineExecution = getRoutineExecution();
        if (lastRoutineExecution.hasPendingPoints()) {
          addPending(pc.read().intValue(), lastRoutineExecution);
          setNextPC(createValue(lastRoutineExecution.getNextPending()));
        } else {
          returnAddress = returnAddressWordNumber;
          T read1 = doPop(memory, sp);
          target.write(read1);
          stackFrames.pop();
          setNextPC(createValue(routineExecution.getNextPending()));
        }
        lastRoutineExecution.retInstruction = pc.read().intValue();
      }
      return 0;
    }

    protected String getName() {
      return "Pop_";
    }
  }

  private void addPending(int returnAddressWordNumber, RoutineExecution execution) {
    if (!execution.pendingPoints.contains(returnAddressWordNumber))
      execution.pendingPoints.offer(returnAddressWordNumber);
  }

  private void addBranch(int returnAddressWordNumber, RoutineExecution execution) {
    if (!execution.branchPoints.contains(returnAddressWordNumber))
      execution.branchPoints.offer(returnAddressWordNumber);
  }

  public class PushReturnAddress extends Push<T> {
    public PushReturnAddress(OpcodeReference target, Register<T> sp, Memory<T> memory) {
      super(target, sp, memory);
    }

    public int execute() {
      doPush(createValue(target.read().intValue()), sp, memory);
      return 5 + cyclesCost;
    }

    protected String getName() {
      return "Push_";
    }
  }
}































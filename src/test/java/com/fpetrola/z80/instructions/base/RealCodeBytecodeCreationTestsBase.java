package com.fpetrola.z80.instructions.base;

import com.fpetrola.z80.cpu.DefaultInstructionFetcher;
import com.fpetrola.z80.cpu.InstructionExecutor;
import com.fpetrola.z80.cpu.InstructionFetcher;
import com.fpetrola.z80.cpu.RandomAccessInstructionFetcher;
import com.fpetrola.z80.instructions.Call;
import com.fpetrola.z80.instructions.Ret;
import com.fpetrola.z80.jspeccy.MutableOpcodeConditions;
import com.fpetrola.z80.jspeccy.RegistersBase;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.decoder.table.FetchNextOpcodeInstructionFactory;
import com.fpetrola.z80.opcodes.references.*;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.spy.InstructionSpy;
import com.fpetrola.z80.spy.MemorySpy;
import com.fpetrola.z80.transformations.InstructionTransformer;
import com.fpetrola.z80.transformations.RegisterTransformerInstructionSpy;
import com.fpetrola.z80.transformations.TransformerInstructionExecutor;
import com.fpetrola.z80.transformations.VirtualRegisterFactory;
import jmce.util.RingBuffer;
import snapshots.*;

import java.io.File;
import java.util.*;

import static com.fpetrola.z80.opcodes.references.WordNumber.createValue;
import static org.junit.Assert.assertEquals;

@SuppressWarnings("ALL")
public class RealCodeBytecodeCreationTestsBase<T extends WordNumber> extends DefaultZ80InstructionDriver<T> implements BytecodeGenerationTest {
  protected TransformerInstructionExecutor<T> transformerInstructionExecutor;
  protected int endAddress;
  protected int startAddress;
  protected int firstAddress;
  private String classFile;
  private RandomAccessInstructionFetcher randomAccessInstructionFetcher;
  private Map<Integer, RoutineExecution> routineExecutions = new HashMap<>();
  private Stack<Object> routineStart = new Stack<>();

  public RealCodeBytecodeCreationTestsBase() {
    super(new RegisterTransformerInstructionSpy());
    randomAccessInstructionFetcher = (address) -> transformerInstructionExecutor.clonedInstructions.get(address);
  }

  public void setUpMemory(String fileName) {
    WordNumber[] data = new WordNumber[0x10000];

    try {
      File file = new File(fileName);
      SnapshotFile snap = SnapshotFactory.getSnapshot(file);
      SpectrumState snapState = snap.load(file);

      RegistersBase<T> registersBase = new RegistersBase<>(state) {
        public VirtualRegisterFactory getVirtualRegisterFactory() {
          return virtualRegisterFactory;
        }
      };

      registersBase.setZ80State(snapState.getZ80State());

      MemoryState memoryState = snapState.getMemoryState();

      byte[][] ram = memoryState.getRam();

      int position = 16384;
      position = copyPage(ram, 5, position);
      position = copyPage(ram, 2, position);
      copyPage(ram, 0, position);
    } catch (SnapshotException e) {
      throw new RuntimeException(e);
    }
  }

  private int copyPage(byte[][] ram, int page, int position) {
    Memory<T> memory = state.getMemory();
    for (int i = 0; i < ram[page].length; i++) {
      memory.write(createValue(position++), createValue(ram[page][i]));
    }
    return position;
  }

  @Override
  protected InstructionSpy createSpy() {
    return registerTransformerInstructionSpy;
  }

  @Override
  protected InstructionFetcher createInstructionFetcher(InstructionSpy spy, State<T> state, InstructionExecutor instructionExecutor) {
    transformerInstructionExecutor = new TransformerInstructionExecutor(state.getPc(), instructionExecutor, true, (InstructionTransformer) instructionCloner);
    return new DefaultInstructionFetcher<>(state, createOpcodeConditions(state), new FetchNextOpcodeInstructionFactory(spy, state), transformerInstructionExecutor);
  }

  protected OpcodeConditions createOpcodeConditions(State<T> state) {
    return new MutableOpcodeConditions(state, (instruction, alwaysTrue, doBranch) -> {
      int pcValue = state.getPc().read().intValue();

      RoutineExecution routineExecution = getRoutineExecution();
      boolean known = routineExecution.branchPoints.contains(pcValue);
      if (known) if (routineExecution.branchPoints.peek() == pcValue) routineExecution.branchPoints.pop();
      else System.out.println("error!");

      if (!alwaysTrue && !known) routineExecution.branchPoints.push(pcValue);

      if (instruction instanceof Ret ret) {
        routineExecution.retInstruction = pcValue;
        routineExecution.isNoConditionRet = ret.getCondition() instanceof ConditionAlwaysTrue;
        if (routineExecution.branchPoints.isEmpty()) {
          memoryReadOnly(false);
          popStart();
          return true;
        } else {
          routineExecution.retInstruction = pcValue;
          return false;
        }
      }

      if (doBranch) {
        memoryReadOnly(false);
        if (instruction instanceof Call call) {
          int jumpAddress = call.getJumpAddress().intValue();
          createRoutineExecution(jumpAddress);
        }
      }
      return doBranch;
    });
  }

  private void popStart() {
    routineStart.pop();
  }

  private void createRoutineExecution(int jumpAddress) {
   // if (jumpAddress == 35211) System.out.println("start routine: " + jumpAddress);
    routineStart.push(jumpAddress);
    RoutineExecution routineExecution = routineExecutions.get(jumpAddress);
    if (routineExecution == null) {
      routineExecutions.put(jumpAddress, routineExecution = new RoutineExecution());
    }

    routineExecution.start = jumpAddress;
  }

  private RoutineExecution getRoutineExecution() {
    return routineExecutions.get(routineStart.peek());
  }

  @Override
  public int add(Instruction<T> instruction) {
    return 0;
  }

  @Override
  public Instruction getInstructionAt(int i) {
    return randomAccessInstructionFetcher.getInstructionAt(i);
  }

  @Override
  public Instruction getTransformedInstructionAt(int i) {
    return null;
  }

  protected void stepUntilComplete() {
    createRoutineExecution(firstAddress);
    Register<T> pc = state.getPc();
    pc.write(createValue(firstAddress));
    boolean ready = false;
    while (!ready) {
      memoryReadOnly(false);

      int pcValue = pc.read().intValue();

      if (pcValue < 16384) ready = true;

      int lastPcValue = pcValue;
      RoutineExecution routineExecution = getRoutineExecution();

      if (routineExecution.isNoConditionRet) {
        routineExecution.isNoConditionRet = false;
        if (routineExecution.branchPoints.isEmpty()) {
          if (routineStart.size() == 1) ready = true;
          else {
            pc.write(createValue(routineExecution.retInstruction));
            step();
          }
        } else {
          pc.write(createValue(routineExecution.branchPoints.peek()));
          step();
        }
      } else if (routineExecution.executedPoints.contains(pcValue) || pcValue < 16384 + 4096) {
        if (routineExecution.branchPoints.isEmpty()) {
          T value = createValue(routineExecution.retInstruction);
          if (value.intValue() == 0) ready = true;
          else {
            pc.write(value);
            step();
            if (routineStart.isEmpty()) ready = true;
          }
        } else {
          pc.write(createValue(routineExecution.branchPoints.peek()));
          step();
        }
      } else {
        routineExecution.executedPoints.add(pcValue);
        step();
      }
    }

    return;

  }


  public String generateAndDecompile() {
    classFile = "JSW.class";
    return getDecompiledSource(startAddress, endAddress, state.getPc(), randomAccessInstructionFetcher, getRegisterTransformerInstructionSpy(), classFile);
  }

  protected void memoryReadOnly(boolean readOnly) {
    MockedMemory<T> memory = (MockedMemory<T>) ((MemorySpy<T>) state.getMemory()).getMemory();
    memory.enableReadyOnly(readOnly);
  }

  private class RoutineExecution {
    public int retInstruction;
    public int start;
    public boolean isNoConditionRet;
    protected Stack<Integer> branchPoints = new Stack<>();
    protected Map<Integer, Integer> executions = new HashMap<>();
    private Set<Integer> executedPoints = new HashSet<>();

    {
      int pcValue = 0;
      Integer i = executions.get(pcValue);
      if (i == null) executions.put(pcValue, i = 0);

      executions.put(pcValue, i + 1);
    }

    private boolean executionsAreComplete() {
      return (!executions.isEmpty()) && executions.entrySet().stream().allMatch(e -> e.getValue() > 1);
    }
  }
}

package com.fpetrola.z80.instructions.base;

import com.fpetrola.z80.cpu.DefaultInstructionFetcher;
import com.fpetrola.z80.cpu.InstructionExecutor;
import com.fpetrola.z80.cpu.InstructionFetcher;
import com.fpetrola.z80.instructions.tests.BytecodeGenerationTest;
import com.fpetrola.z80.jspeccy.MutableOpcodeConditions;
import com.fpetrola.z80.jspeccy.RegistersBase;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.decoder.table.FetchNextOpcodeInstructionFactory;
import com.fpetrola.z80.opcodes.references.OpcodeConditions;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.spy.InstructionSpy;
import com.fpetrola.z80.transformations.InstructionTransformer;
import com.fpetrola.z80.transformations.TransformerInstructionExecutor;
import com.fpetrola.z80.transformations.VirtualRegisterFactory;
import snapshots.*;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@SuppressWarnings("ALL")
public class RealCodeBytecodeCreationTestsBase<T extends WordNumber> extends DefaultZ80InstructionDriver<T> implements BytecodeGenerationTest {
  protected TransformerInstructionExecutor<T> transformerInstructionExecutor;
  protected Map<Integer, Integer> executions = new HashMap<>();
  protected int endAddress;
  protected int startAddress;
  protected int firstAddress;
  private String classFile;

  public RealCodeBytecodeCreationTestsBase() {
    super(new RegisterTransformerInstructionSpy());
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
      memory.write(WordNumber.createValue(position++), WordNumber.createValue(ram[page][i]));
    }
    return position;
  }

  @Override
  protected InstructionSpy createSpy() {
    return registerTransformerInstructionSpy;
  }

  @Override
  protected InstructionFetcher createInstructionFetcher(InstructionSpy spy, State<T> state, InstructionExecutor instructionExecutor) {
    transformerInstructionExecutor = new TransformerInstructionExecutor(state.getPc(), instructionExecutor, (InstructionTransformer) instructionCloner);
    return new DefaultInstructionFetcher<>(state, createOpcodeConditions(state), new FetchNextOpcodeInstructionFactory(spy, state), transformerInstructionExecutor);
  }

  protected OpcodeConditions createOpcodeConditions(State<T> state) {
//    return new OpcodeConditions(state.getFlag());
    return new MutableOpcodeConditions(state, () -> {
      T read = state.getPc().read();
      int pcValue = read.intValue();
      Integer i = executions.get(pcValue);
      if (i == null)
        executions.put(pcValue, i = 0);

      executions.put(pcValue, i + 1);
    });
  }

  @Override
  public int add(Instruction<T> instruction) {
    return 0;
  }

  @Override
  public Instruction getInstructionAt(int i) {
    return null;
  }

  @Override
  public Instruction getTransformedInstructionAt(int i) {
    return null;
  }

  protected void stepUntilComplete() {
    Register<T> pc = state.getPc();
    pc.write(WordNumber.createValue(firstAddress));

    while (!executionsAreComplete()) {
      pc.write(WordNumber.createValue(firstAddress));
      int i = pc.read().intValue();
      do {
        step();
        i = pc.read().intValue();
      } while (i >= startAddress && i <= endAddress);
    }

    return;

  }

  private boolean executionsAreComplete() {
    return (!executions.isEmpty()) && executions.entrySet().stream().allMatch(e -> e.getValue() > 1);
  }

  public String generateAndDecompile() {
    classFile = "JSW.class";
    return getDecompiledSource(
        startAddress, endAddress, state.getPc(),
        (address) -> transformerInstructionExecutor.clonedInstructions.get(address),
        getRegisterTransformerInstructionSpy(), classFile);
  }
}

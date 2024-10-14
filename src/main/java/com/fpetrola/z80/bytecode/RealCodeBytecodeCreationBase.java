package com.fpetrola.z80.bytecode;

import com.fpetrola.z80.bytecode.examples.RemoteZ80Translator;
import com.fpetrola.z80.bytecode.se.SymbolicExecutionAdapter;
import com.fpetrola.z80.cpu.InstructionExecutor;
import com.fpetrola.z80.cpu.InstructionFetcher;
import com.fpetrola.z80.cpu.RandomAccessInstructionFetcher;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.instructions.base.InstructionFactory;
import com.fpetrola.z80.jspeccy.RegistersBase;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.routines.Routine;
import com.fpetrola.z80.spy.InstructionSpy;
import com.fpetrola.z80.transformations.*;
import snapshots.*;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static com.fpetrola.z80.opcodes.references.WordNumber.createValue;

@SuppressWarnings("ALL")
public class RealCodeBytecodeCreationBase<T extends WordNumber> extends DefaultZ80InstructionDriver<T> implements BytecodeGeneration {
  protected TransformerInstructionExecutor<T> transformerInstructionExecutor;
  private RandomAccessInstructionFetcher randomAccessInstructionFetcher;
  private static SymbolicExecutionAdapter symbolicExecutionAdapter;

  public RealCodeBytecodeCreationBase() {
    super(new RegisterTransformerInstructionSpy());
    randomAccessInstructionFetcher = (address) -> transformerInstructionExecutor.clonedInstructions.get(address);
  }

  public static SymbolicExecutionAdapter getSymbolicExecutionAdapter(State state1) {
    if (symbolicExecutionAdapter == null)
      symbolicExecutionAdapter = new SymbolicExecutionAdapter(state1);

    return symbolicExecutionAdapter;
  }

  public void setupStateWithSnapshot(String fileName) {
    WordNumber[] data = new WordNumber[0x10000];

    try {
      File file = new File(fileName);

      SnapshotFile snap = new SnapshotZ80();
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
    if (ram[page] != null)
      for (int i = 0; i < ram[page].length; i++) {
        memory.write(createValue(position++), createValue(ram[page][i]));
      }
    return position;
  }

  protected String getBase64Memory() {
    WordNumber[] data1 = state.getMemory().getData();
    int ramEnd = 0x10000;
    byte[] data = new byte[ramEnd];
    Arrays.fill(data, (byte) 0);

    for (int i = 0; i < ramEnd; i++) {
      WordNumber wordNumber = data1[i];
      int i1 = wordNumber == null ? 0 : wordNumber.intValue();
      data[i] = (byte) i1;
    }
    return Base64Utils.gzipArrayCompressToBase64(data);
  }

  @Override
  protected InstructionSpy createSpy() {
    return registerTransformerInstructionSpy;
  }

  @Override
  protected InstructionFetcher createInstructionFetcher(InstructionSpy spy, State<T> state, InstructionExecutor instructionExecutor) {
    transformerInstructionExecutor = new TransformerInstructionExecutor(state.getPc(), instructionExecutor, true, (InstructionTransformer) instructionCloner);
    return getSymbolicExecutionAdapter(state).createInstructionFetcher(spy, state, (InstructionExecutor) this.transformerInstructionExecutor);
  }

  @Override
  protected InstructionFactory createInstructionFactory(State<T> state) {
    return getSymbolicExecutionAdapter(state).createInstructionFactory(this.state);
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

  protected void stepUntilComplete(int startAddress) {
    getSymbolicExecutionAdapter(state).stepUntilComplete(this, this.state, startAddress, 16384 + 4096);
  }

  public String generateAndDecompile() {
    return generateAndDecompile("", RemoteZ80Translator.getRoutines(), ".", "JetSetWilly");
  }

  @Override
  public String generateAndDecompile(String base64Memory, List<Routine> routines, String targetFolder, String className) {
    return getDecompiledSource(state.getPc(), randomAccessInstructionFetcher, className, base64Memory, routines, targetFolder);
  }


  public void translateToJava(String className, String memoryInBase64, String startMethod, List<Routine> routines) {
    BytecodeGeneration.super.translateToJava(state.getPc(), randomAccessInstructionFetcher, className, memoryInBase64, startMethod, routines);
  }
}

package com.fpetrola.z80.transformations;

import com.fpetrola.z80.cpu.InstructionExecutor;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.instructions.base.InstructionVisitor;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Plain8BitRegister;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Virtual8BitsRegister<T extends WordNumber> extends Plain8BitRegister<T> implements IVirtual8BitsRegister<T> {
  private final InstructionExecutor instructionExecutor;
  private Instruction<T> instruction;
  private VirtualFetcher<T> virtualFetcher;

  private List<VirtualRegister<T>> previousVersions = new ArrayList<>();

  protected T lastData;
  protected int reads;
  public IVirtual8BitsRegister<T> lastVersionRead;

  public Virtual8BitsRegister(InstructionExecutor instructionExecutor, String name, Instruction<T> instruction, IVirtual8BitsRegister<T> previousVersion, VirtualFetcher<T> virtualFetcher) {
    super(name);
    this.instructionExecutor = instructionExecutor;
    this.instruction = instruction;
    this.virtualFetcher = virtualFetcher;

    addPreviousVersion(previousVersion);

    if (instruction == null)
      this.instruction = new VirtualAssignmentInstruction(this, () -> this.getCurrentPreviousVersion());
  }

  @Override
  public boolean usesMultipleVersions() {
    return lastVersionRead != null && previousVersions.size() > 1;
  }

  public IVirtual8BitsRegister<T> getCurrentPreviousVersion() {
    return previousVersions.isEmpty() ? null : (IVirtual8BitsRegister<T>) previousVersions.get(previousVersions.size() - 1);
  }

  public T read() {
    T t = virtualFetcher.readFromVirtual(() -> instructionExecutor.isExecuting(instruction), () -> instructionExecutor.execute(instruction), () -> data, () -> (lastVersionRead = getCurrentPreviousVersion()).readPrevious());
    if (data == t)
      reads++;
//    if (reads > 1)
//      System.out.println("uu");
    lastData = null;
    data = t;

    return t;
  }

  public void decrement() {
    read();
    super.decrement();
  }

  public void increment() {
    read();
    super.increment();
  }

  public void reset() {
    data = null;
    reads = 0;
  }

  @Override
  public List<VirtualRegister<T>> getPreviousVersions() {
    return previousVersions;
  }

  public void addPreviousVersion(IVirtual8BitsRegister previousVersion) {
    if (previousVersion != null) {
      //  if (!previousVersions.isEmpty() &&  !previousVersions.contains(previousVersion))
      previousVersions.remove(previousVersion);
      previousVersions.add(previousVersion);
    }
    if (previousVersions.size() > 1) {
      previousVersion.saveData();
    }
  }

  public void saveData() {
    lastData = data;
    // data = null;
  }

  public T readPrevious() {
    StackWalker walker = StackWalker.getInstance();
    List<StackWalker.StackFrame> walk = walker.walk(s -> s.filter(f -> true).collect(Collectors.toList()));
    if (walk.size() > 100)
      System.out.println("dssdg");

    if (data == null && lastData == null && reads == 0) {
      for (VirtualRegister<T> v1 : previousVersions) {
        if (v1 != this)
          return getCurrentPreviousVersion().readPrevious();
      }
    }

    T result = lastData != null ? lastData : read();
    return result;
  }

  public void accept(InstructionVisitor instructionVisitor) {
    //instruction.accept(instructionVisitor);
    instructionVisitor.visitRegister(this);
  }
}

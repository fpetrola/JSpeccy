package com.fpetrola.z80.opcodes.references;

import com.fpetrola.z80.helpers.Helper;
import com.fpetrola.z80.instructions.base.InstructionVisitor;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.registers.Register;

public class MemoryPlusRegister8BitReference<T extends WordNumber> implements OpcodeReference<T> {

  private Memory<T> memory;

  public ImmutableOpcodeReference<T> getTarget() {
    return target;
  }

  private ImmutableOpcodeReference<T> target;
  private int valueDelta;
  protected T fetchedRelative;
  private Register<T> pc;

  public MemoryPlusRegister8BitReference() {
  }

  public MemoryPlusRegister8BitReference(ImmutableOpcodeReference target, Memory memory, Register pc, int valueDelta) {
    this.target = target;
    this.memory = memory;
    this.pc = pc;
    this.valueDelta = valueDelta;
  }

  public T read() {
    T address = target.read().plus(fetchRelative());
    return memory.read(address);
  }

  public void write(T value) {
    T address = target.read().plus(fetchRelative());
    memory.write(address, value);
  }

  public byte fetchRelative() {
    T dd = memory.read(pc.read().plus(valueDelta));
    fetchedRelative = dd;
    return (byte) fetchedRelative.intValue();
  }

  public String toString() {
    byte dd = fetchRelative();
    String string2 = (dd > 0 ? "+" : "-") + Helper.convertToHex(Math.abs(dd));
    return "(" + target.toString() + string2 + ")";
  }

  public int getLength() {
    return 1;
  }

  public Object clone() throws CloneNotSupportedException {
    T lastFetchedRelative = fetchedRelative;
    return new MyMemoryPlusRegister8BitReference(lastFetchedRelative, (ImmutableOpcodeReference) target.clone(),memory, pc, valueDelta);
  }

  public void accept(InstructionVisitor instructionVisitor) {
    instructionVisitor.visitMemoryPlusRegister8BitReference(this);
  }

  private class MyMemoryPlusRegister8BitReference extends MemoryPlusRegister8BitReference {
    public MyMemoryPlusRegister8BitReference(T lastFetchedRelative, ImmutableOpcodeReference target, Memory<T> memory, Register<T> pc, int valueDelta) {
      super(target, memory, pc, valueDelta);
      fetchedRelative= lastFetchedRelative;
    }

    public byte fetchRelative() {
      return (byte) fetchedRelative.intValue();
    }
  }
}

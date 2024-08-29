package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.AbstractInstruction;
import com.fpetrola.z80.instructions.base.InstructionVisitor;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;

public class Push<T extends WordNumber> extends AbstractInstruction<T> {
  public OpcodeReference<T> getTarget() {
    return target;
  }

  public void setTarget(OpcodeReference<T> target) {
    this.target = target;
  }

  private  OpcodeReference<T> target;
  private final Register<T> sp;
  private final Memory<T> memory;

  public Push(OpcodeReference target, Register<T> sp, Memory<T> memory) {
    this.target = target;
    this.sp = sp;
    this.memory = memory;
  }

  public int execute() {
    doPush(target.read(), sp, memory);
    return 5 + cyclesCost;
  }

  public static <T extends WordNumber> void doPush(T value, Register<T> sp, Memory<T> memory) {
    memory.disableWriteListener();
    sp.decrement();
    sp.decrement();
    T address = sp.read();
    Memory.write16Bits(memory, value, address);
    memory.enableWriteListener();
  }

  @Override
  public void accept(InstructionVisitor visitor) {
    visitor.visitPush(this);
  }
}

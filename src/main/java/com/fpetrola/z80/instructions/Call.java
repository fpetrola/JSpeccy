package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.ConditionalInstruction;
import com.fpetrola.z80.instructions.base.InstructionVisitor;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.opcodes.references.Condition;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;

public class Call<T extends WordNumber> extends ConditionalInstruction<T, Condition> {
  private final Register<T> sp;
  protected final Memory<T> memory;

  public Call(ImmutableOpcodeReference positionOpcodeReference, Condition condition, Register<T> pc, Register<T> sp, Memory<T> memory) {
    super(positionOpcodeReference, condition, pc);
    this.sp = sp;
    this.memory = memory;
  }

  public T beforeJump(T jumpAddress) {
    T value = pc.read().plus(length);
    Push.doPush(value, sp, memory);
    return jumpAddress;
  }

  @Override
  public int execute() {
    return super.execute();
  }

  @Override
  public void accept(InstructionVisitor visitor) {
    if (!visitor.visitingCall(this))
      super.accept(visitor);
  }
}

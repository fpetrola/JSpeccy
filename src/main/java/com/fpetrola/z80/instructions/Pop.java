package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.DefaultTargetFlagInstruction;
import com.fpetrola.z80.instructions.base.InstructionVisitor;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;

public class Pop<T extends WordNumber> extends DefaultTargetFlagInstruction<T> {
  protected final Register<T> sp;
  protected final Memory<T> memory;

  public Pop(OpcodeReference target, Register<T> sp, Memory<T> memory, Register<T> flag) {
    super(target, flag);
    this.sp = sp;
    this.memory = memory;
  }

  public int execute() {
    T value = doPop(memory, sp);
    target.write(value);
    return 5 + 3 + 3;
  }

  public static <T extends WordNumber> T doPop(Memory<T> memory, Register<T> sp) {
    memory.disableReadListener();
    final T value = Memory.read16Bits(memory, sp.read());
    sp.increment();
    sp.increment();
    memory.enableReadListener();
//    System.out.println("pop: " + value);
    return value;
  }

  public void accept(InstructionVisitor visitor) {
    visitor.visitingPop(this);
  }
}

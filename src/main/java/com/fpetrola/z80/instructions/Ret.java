package com.fpetrola.z80.instructions;

import com.fpetrola.z80.blocks.ByteCodeGenerator;
import com.fpetrola.z80.instructions.base.ConditionalInstruction;
import com.fpetrola.z80.instructions.base.InstructionVisitor;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.opcodes.references.Condition;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import org.cojen.maker.MethodMaker;

public class Ret<T extends WordNumber> extends ConditionalInstruction<T> {
  private final Memory<T> memory;
  private Register<T> sp;

  Ret(Condition condition, Register<T> sp, Memory<T> memory, Register<T> pc) {
    super(sp, condition, pc);
    this.memory = memory;
    this.sp = sp;
  }

  protected T beforeJump(T jumpAddress) {
    return Pop.doPop(memory, sp);
  }

  public String toString() {
    String conditionStr = condition.toString();
    return "RET" + ((conditionStr.length() > 0) ? " " + conditionStr : "");
  }

  @Override
  public void accept(InstructionVisitor visitor) {
    visitor.visitingRet(this);
  }
}

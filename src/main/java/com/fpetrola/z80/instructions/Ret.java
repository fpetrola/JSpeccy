package com.fpetrola.z80.instructions;

import com.fpetrola.z80.blocks.ByteCodeGenerator;
import com.fpetrola.z80.instructions.base.AbstractInstruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.Condition;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.RegisterName;
import org.cojen.maker.Field;
import org.cojen.maker.MethodMaker;

public class Ret<T extends WordNumber> extends AbstractInstruction<T> {
  private final Condition condition;
  private T jumpAddress;

  public Ret(State state, Condition condition) {
    super(state);
    this.condition = condition;
  }

  public int execute() {
    if (condition.conditionMet()) {
      jumpAddress = Pop.doPop(memory, sp);
      setNextPC(jumpAddress);
    } else setNextPC(null);
    return 11;
  }

  public String toString() {
    String conditionStr = condition.toString();
    return "RET" + ((conditionStr.length() > 0) ? " " + conditionStr : "");
  }

  public Condition getCondition() {
    return condition;
  }

  @Override
  public int createBytecode(MethodMaker mm, int label, ByteCodeGenerator byteCodeGenerator) {
    hereLabel(label, byteCodeGenerator);
    Runnable runnable = () -> mm.return_();
    executeConditional(byteCodeGenerator, runnable, condition);
    return 0;
  }
}

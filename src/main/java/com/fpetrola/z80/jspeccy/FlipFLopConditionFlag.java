package com.fpetrola.z80.jspeccy;

import com.fpetrola.z80.instructions.base.InstructionVisitor;
import com.fpetrola.z80.opcodes.references.ConditionFlag;
import com.fpetrola.z80.registers.Register;

public class FlipFLopConditionFlag extends ConditionFlag {
  public Runnable getExecutionsListener() {
    return executionsListener;
  }

  private boolean state = false;
  private Runnable executionsListener;

  public FlipFLopConditionFlag(Register register, int flag, boolean negate, Runnable executionsListener1) {
    super(register, flag, negate);
    this.executionsListener = executionsListener1;
  }

  public boolean conditionMet() {
    boolean result = state;
    state = !state;
//    return Math.random() * 100 > 50;
    executionsListener.run();
    return result;
  }

  public void accept(InstructionVisitor visitor) {
    boolean b = visitor.visitingFlipFlopConditionFlag(this);
    if (!b)
      super.accept(visitor);
  }

  @Override
  public String toString() {
    return "FlipFlop: " + super.toString();
  }
}

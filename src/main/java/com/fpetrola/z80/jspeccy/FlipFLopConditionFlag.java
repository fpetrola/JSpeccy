package com.fpetrola.z80.jspeccy;

import com.fpetrola.z80.instructions.base.InstructionVisitor;
import com.fpetrola.z80.opcodes.references.ConditionFlag;
import com.fpetrola.z80.registers.Register;

import java.util.function.DoublePredicate;
import java.util.function.Predicate;

public class FlipFLopConditionFlag {
  public final FlipFlopPredicate isConditionMet;

  public Runnable getExecutionsListener() {
    return isConditionMet.executionsListener;
  }


  public FlipFLopConditionFlag(Runnable executionsListener1) {
    isConditionMet = new FlipFlopPredicate(executionsListener1);
  }

  public class FlipFlopPredicate implements Predicate<Boolean> {
    private boolean state = false;
    public Runnable executionsListener;

    public FlipFlopPredicate(Runnable executionsListener) {
      this.executionsListener = executionsListener;
    }

    public boolean test(Boolean b) {
      boolean result = state;
      state = !state;
//    return Math.random() * 100 > 50;
      executionsListener.run();
      return result;
    }
  }
}

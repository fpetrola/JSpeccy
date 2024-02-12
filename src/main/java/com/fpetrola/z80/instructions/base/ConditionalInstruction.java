package com.fpetrola.z80.instructions.base;

import com.fpetrola.z80.blocks.ByteCodeGenerator;
import com.fpetrola.z80.helpers.Helper;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.Condition;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.RegisterName;
import org.cojen.maker.Field;
import org.cojen.maker.Label;
import org.cojen.maker.MethodMaker;

public abstract class ConditionalInstruction<T extends WordNumber> extends TargetInstruction<T> {
  protected Condition condition;

  public T getJumpAddress() {
    return jumpAddress;
  }

  public void setJumpAddress(T jumpAddress) {
    this.jumpAddress = jumpAddress;
  }

  protected T jumpAddress;

  public ConditionalInstruction(State state, OpcodeReference<T> target, Condition condition) {
    super(state, target);
    this.condition = condition;
  }

  public String toString() {
    return spy.executeInPause(() -> getClass().getSimpleName() + " " + ((condition.toString().length() > 0) ? condition.toString() + ", " : "") + jumpAddress);
  }

  public Condition getCondition() {
    return condition;
  }

  protected void jumpRelativeIfMatchCondition() {
    T jumpAddress = calculateAddress();
    setNextPC(condition.conditionMet() ? jumpAddress : null);
  }

  private T calculateAddress() {
    byte by = target.read().byteValue();
    jumpAddress = pc.read().plus(length + by);
    return jumpAddress;
  }

  @Override
  public int getJumpLabel() {
    return jumpAddress != null ? jumpAddress.intValue() : -1;
  }

  @Override
  public int createBytecode(MethodMaker mm, int label, ByteCodeGenerator byteCodeGenerator) {
    hereLabel(label, byteCodeGenerator);

    int jumpLabel = jumpAddress.intValue();
    Label label1 = byteCodeGenerator.getLabel(jumpLabel);
    if (label1 != null) {
      Field a = byteCodeGenerator.registers.get(RegisterName.F.name());
      if (condition.toString().equals("NZ")) a.ifNe(0, label1);
      else if (condition.toString().equals("Z")) a.ifEq(0, label1);
      else if (condition.toString().equals("NC")) a.ifGe(0, label1);
      else if (condition.toString().equals("C")) a.ifLt(0, label1);
      else label1.goto_();
    }
    return 0;
  }
}
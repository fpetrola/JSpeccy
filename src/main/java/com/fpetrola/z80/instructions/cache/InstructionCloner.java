package com.fpetrola.z80.instructions.cache;

import com.fpetrola.z80.blocks.DummyInstructionVisitor;
import com.fpetrola.z80.instructions.*;
import com.fpetrola.z80.instructions.base.AbstractInstruction;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.instructions.base.InstructionFactory;
import com.fpetrola.z80.instructions.base.ParameterizedUnaryAluInstruction;
import com.fpetrola.z80.opcodes.references.*;
import com.fpetrola.z80.transformations.InstructionTransformerBase;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.function.Predicate;

public class InstructionCloner<T extends WordNumber> extends DummyInstructionVisitor<T> {
  InstructionFactory instructionFactory;
  protected AbstractInstruction cloned;

  public InstructionCloner(InstructionFactory instructionFactory) {
    this.instructionFactory = instructionFactory;
  }

  public Instruction<T> clone(Instruction<T> instruction) {
    instruction.accept(this);
    if (cloned == null)
      throw new RuntimeException("clone not supported for: " + instruction.getClass());
    return cloned;
  }

  public void setCloned(AbstractInstruction cloned, AbstractInstruction instruction) {
    this.cloned = cloned;
    this.cloned.setLength(instruction.getLength());
  }

  public <R extends PublicCloneable> R clone(R cloneable) {
    try {
      return (R) cloneable.clone();
    } catch (CloneNotSupportedException e) {
      throw new RuntimeException(e);
    }
  }

  public <R extends PublicCloneable> R clone(OpcodeReference opcodeReference) {
    try {
      return (R) opcodeReference.clone();
    } catch (CloneNotSupportedException e) {
      throw new RuntimeException(e);
    }
  }

  public <R extends PublicCloneable> R clone(ImmutableOpcodeReference immutableOpcodeReference) {
    try {
      return (R) immutableOpcodeReference.clone();
    } catch (CloneNotSupportedException e) {
      throw new RuntimeException(e);
    }
  }

  public void visitingInc16(Inc16 inc16) {
    setCloned(instructionFactory.Inc16(clone(inc16.getTarget())), inc16);
  }

  public boolean visitingSet(SET set) {
    setCloned(instructionFactory.SET(clone(set.getTarget()), set.getN()), set);
    return false;
  }

  public boolean visitingRes(RES res) {
    setCloned(instructionFactory.RES(clone(res.getTarget()), res.getN()), res);
    return false;
  }

  public void visitingBit(BIT bit) {
    setCloned(instructionFactory.BIT(clone(bit.getTarget()), bit.getN()), bit);
  }

  public void visitingDjnz(DJNZ<T> djnz) {
    setCloned(instructionFactory.DJNZ(clone(djnz.getCondition()), djnz.getPositionOpcodeReference()), djnz);
  }

  public void visitingLd(Ld ld) {
    setCloned(instructionFactory.Ld(clone(ld.getTarget()), clone(ld.getSource())), ld);
  }

  public boolean visitingInc(Inc inc) {
    setCloned(instructionFactory.Inc(clone(inc.getTarget())), inc);
    return false;
  }

  public void visitingRla(RLA rla) {
    setCloned(instructionFactory.RLA(), rla);
  }

  public void visitingRl(RL rl) {
    setCloned(instructionFactory.RL(rl.getTarget()), rl);
  }

  public boolean visitingRet(Ret ret) {
    setCloned(instructionFactory.Ret(ret.getCondition()), ret);
    return false;
  }

  public void visitingAnd(And and) {
    setCloned(instructionFactory.And(and.getSource()), and);
  }

  public void visitingOr(Or or) {
    setCloned(instructionFactory.Or(or.getSource()), or);
  }

  public void visitingXor(Xor xor) {
    setCloned(instructionFactory.Xor(xor.getSource()), xor);
  }

  public void visitingRst(RST rst) {
    setCloned(instructionFactory.RST(rst.getP()), rst);
  }

  public void visitingIm(IM im) {
    setCloned(instructionFactory.IM(im.getMode()), im);
  }

  public boolean visitingDec(Dec dec) {
    setCloned(instructionFactory.Dec(clone(dec.getTarget())), dec);
    return false;
  }

  public void visitingJR(JR jr) {
    setCloned(instructionFactory.JR(clone(jr.getCondition()), clone(jr.getPositionOpcodeReference())), jr);
  }

  public <S extends Condition> S clone(S condition) {
    ConditionCloner visitor = new ConditionCloner();
    condition.accept(visitor);
    return (S) visitor.result;
  }

  @Override
  public boolean visitingParameterizedUnaryAluInstruction(ParameterizedUnaryAluInstruction parameterizedUnaryAluInstruction) {
    Constructor<?>[] constructors = parameterizedUnaryAluInstruction.getClass().getConstructors();
    try {
      cloned = (AbstractInstruction) constructors[0].newInstance(clone(parameterizedUnaryAluInstruction.getTarget()), parameterizedUnaryAluInstruction.getFlag());
    } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
      throw new RuntimeException(e);
    }
    return false;
  }

  private class ConditionCloner extends DummyInstructionVisitor {
    public Condition result;

    public ConditionCloner() {
    }

    public void visitingConditionFlag(ConditionFlag conditionFlag) {
      result = new ConditionFlag<>(InstructionCloner.this.clone(conditionFlag.getRegister()), conditionFlag.getFlag(), conditionFlag.isNegate(), conditionFlag.isConditionMet);
    }

    public void visitBNotZeroCondition(BNotZeroCondition bNotZeroCondition) {
      result = new BNotZeroCondition<>(InstructionCloner.this.clone(bNotZeroCondition.getB()), InstructionTransformerBase.clone(bNotZeroCondition.isConditionMet));
    }
  }
}

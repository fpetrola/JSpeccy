package com.fpetrola.z80.instructions.base;

import com.fpetrola.z80.blocks.ByteCodeGenerator;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.opcodes.references.MutableOpcodeReference;
import com.fpetrola.z80.registers.flag.IFlagRegister;
import org.cojen.maker.MethodMaker;
import org.cojen.maker.Variable;

import static com.fpetrola.z80.registers.RegisterName.F;
import static com.fpetrola.z80.registers.RegisterName.HL;

public abstract class TargetInstruction<T extends WordNumber> extends AbstractInstruction<T> {

  protected final OpcodeReference<T> target;

  public TargetInstruction(State state, OpcodeReference<T> target) {
    super(state, state.getRegister(HL), (IFlagRegister) state.getRegister(F));
    this.target = target;
    incrementLengthBy(target.getLength());
  }

  public MutableOpcodeReference<T> getTarget() {
    return target;
  }

  public String toString() {
    return super.toString() + " " + target.toString();
  }

  @Override
  public int createBytecode(MethodMaker mm, int label, ByteCodeGenerator byteCodeGenerator) {
    hereLabel(label, byteCodeGenerator);

    Object targetVariable = getSourceVariableOf(byteCodeGenerator, target, true);
    doOperation(targetVariable, 0);
    return 0;
  }

  protected void doOperation(Object targetVariable, Object sourceVariable) {
    if (targetVariable instanceof Variable)
      ((Variable) targetVariable).set(sourceVariable);
  }
}
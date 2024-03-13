package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.AbstractInstruction;
import com.fpetrola.z80.instructions.base.InstructionVisitor;
import com.fpetrola.z80.instructions.base.TargetInstruction;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class Inc16<T extends WordNumber> extends AbstractInstruction<T> implements TargetInstruction<T> {
  private  OpcodeReference<T> target;

  public Inc16(OpcodeReference target) {
    this.target = target;
  }

  public int execute() {
    target.write(target.read().plus1());
    return cyclesCost;
  }

  @Override
  public void accept(InstructionVisitor visitor) {
    visitor.visitingInc16(this);
  }

  public OpcodeReference<T> getTarget() {
    return target;
  }

  public void setTarget(OpcodeReference<T> target) {
    this.target = target;
  }
}

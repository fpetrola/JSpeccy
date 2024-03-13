package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.AbstractInstruction;
import com.fpetrola.z80.instructions.base.InstructionVisitor;
import com.fpetrola.z80.instructions.base.TargetInstruction;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class Dec16<T extends WordNumber> extends AbstractInstruction<T> implements TargetInstruction<T> {
  private OpcodeReference<T> target;

  public Dec16(OpcodeReference target) {
    super();
    this.target = target;
  }

  public int execute() {
    target.write(target.read().minus1());
    return cyclesCost;
  }

  public OpcodeReference<T> getTarget() {
    return target;
  }

  @Override
  public void setTarget(OpcodeReference<T> target) {
    this.target = target;
  }

  @Override
  public void accept(InstructionVisitor visitor) {
    visitor.visitingDec16(this);
  }
}

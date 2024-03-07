package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.InstructionVisitor;
import com.fpetrola.z80.instructions.base.TargetInstruction;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import org.cojen.maker.Variable;

public class Dec16<T extends WordNumber> extends TargetInstruction<T> {

  Dec16(OpcodeReference target) {
    super(target);
  }

  public int execute() {
    target.write(target.read().minus1());
    return cyclesCost;
  }

  @Override
  public void accept(InstructionVisitor visitor) {
    visitor.visitingDec16(this);
  }
}

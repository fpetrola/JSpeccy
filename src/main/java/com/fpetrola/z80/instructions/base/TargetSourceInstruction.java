package com.fpetrola.z80.instructions.base;

import com.fpetrola.z80.blocks.ByteCodeGenerator;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import org.cojen.maker.MethodMaker;
import org.cojen.maker.Variable;

public abstract class TargetSourceInstruction<T extends WordNumber, S extends ImmutableOpcodeReference<T>> extends TargetInstruction<T> {
  protected S source;

  public TargetSourceInstruction(OpcodeReference<T> target, S source) {
    super(target);
    this.source = source;
    incrementLengthBy(source.getLength());
    cyclesCost += 1;
  }

  public String toString() {
    return super.toString() + ", " + source;
  }

  public S getSource() {
    return source;
  }

  public void setSource(S source) {
    this.source = source;
  }

  public void accept(InstructionVisitor visitor) {
    visitor.visitingTargetSourceInstruction(this);
  }
}
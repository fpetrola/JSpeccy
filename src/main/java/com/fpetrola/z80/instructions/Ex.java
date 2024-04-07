package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.AbstractInstruction;
import com.fpetrola.z80.instructions.base.InstructionVisitor;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class Ex<T extends WordNumber> extends AbstractInstruction<T> {
  private  OpcodeReference<T> target;

  private  OpcodeReference<T> source;
  public Ex(OpcodeReference<T> target, OpcodeReference<T> source) {
    this.target = target;
    this.source = source;
  }

  public int execute() {
    final T v1 = target.read();
    final T v2 = source.read();

    target.write(v2);
    source.write(v1);
    return cyclesCost;
  }

  public OpcodeReference<T> getTarget() {
    return target;
  }

  public void setTarget(OpcodeReference<T> target) {
    this.target = target;
  }

  public void setSource(OpcodeReference<T> source) {
    this.source = source;
  }

  public OpcodeReference<T> getSource() {
    return source;
  }

  public void accept(InstructionVisitor visitor) {
    visitor.visitEx(this);
  }
}

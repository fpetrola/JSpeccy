package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.InstructionVisitor;
import com.fpetrola.z80.instructions.base.TargetInstruction;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.flag.FlagRegister;
import org.cojen.maker.Variable;

public class Inc<T extends WordNumber> extends TargetInstruction<T> {
  private final FlagRegister<T> flag;

  Inc(OpcodeReference target, FlagRegister<T> flag) {
    super(target);
    this.flag = flag;
  }

  public int execute() {
    T alu8BitInc = flag.ALU8BitInc(target.read());
    target.write(alu8BitInc);
    return cyclesCost;
  }

  @Override
  public void accept(InstructionVisitor visitor) {
    visitor.visitingInc(this);
  }
}

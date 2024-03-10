package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.InstructionVisitor;
import com.fpetrola.z80.instructions.base.TargetInstruction;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.flag.FlagRegister;
import org.cojen.maker.Variable;

public class Dec<T extends WordNumber> extends TargetInstruction<T> {
  private  FlagRegister<T> flag;

  public Dec(OpcodeReference target, FlagRegister<T> flag) {
    super(target);
    this.flag = flag;
  }

  public int execute() {
    final T value = target.read();
    T alu8BitDec = flag.ALU8BitDec(value);
    target.write(alu8BitDec);

    return cyclesCost;
  }

  public FlagRegister<T> getFlag() {
    return flag;
  }

  public void setFlag(FlagRegister<T> flag) {
    this.flag = flag;
  }

  @Override
  public void accept(InstructionVisitor visitor) {
    visitor.visitingDec(this);
  }
}

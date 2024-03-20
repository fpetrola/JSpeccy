package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.InstructionVisitor;
import com.fpetrola.z80.instructions.base.ParameterizedBinaryAluInstruction;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.flag.Alu8BitResult;
import com.fpetrola.z80.registers.flag.FlagRegister;
import com.fpetrola.z80.registers.flag.TableAluOperation;

public class And<T extends WordNumber> extends ParameterizedBinaryAluInstruction<T> {
  protected static final TableAluOperation andTableAluOperation = new TableAluOperation() {
    public Alu8BitResult execute(int result, int value, int carry) {
      data = 0x10;
      setS((result & 0x0080) != 0);
      setZ(result == 0);
      setPV(parity[result]);
      setUnusedFlags(result);
      return new Alu8BitResult(result, data);
    }
  };

  public And(OpcodeReference target, ImmutableOpcodeReference source, FlagRegister<T> flag) {
    super(target, source, flag, null);
  }

  @Override
  public int execute() {
    final T value1 = source.read();
    final T value2 = target.read();

    T result = value1.and(value2);

    T i = andTableAluOperation.executeWithoutCarry(value1.intValue(), result.intValue(), flag);

    target.write(i);

    target.write(binaryAluOperation.execute(flag, value1, value2));
    return cyclesCost;
  }

  @Override
  public void accept(InstructionVisitor visitor) {
    visitor.visitingAnd(this);
  }
}

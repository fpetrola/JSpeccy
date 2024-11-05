package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.InstructionVisitor;
import com.fpetrola.z80.instructions.base.ParameterizedBinaryAluInstruction;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.flag.TableAluOperation;

public class And<T extends WordNumber> extends ParameterizedBinaryAluInstruction<T> {
  protected static final TableAluOperation andTableAluOperation = new TableAluOperation() {
    public int execute(int result, int value, int carry) {
      data = 0x10;
      setS((result & 0x0080) != 0);
      setZ(result == 0);
      setPV(parity[result]);
      setUnusedFlags(result);
      return result;
    }
  };

  public And(OpcodeReference target, ImmutableOpcodeReference source, Register<T> flag) {
    super(target, source, flag, null);
  }

  @Override
  public int execute() {
    final T value1 = source.read();
    final T value2 = target.read();

    T result = value1.and(value2);

    T i = andTableAluOperation.executeWithoutCarry(value1, result, flag);

    target.write(i);
    return cyclesCost;
  }

  @Override
  public void accept(InstructionVisitor visitor) {
    super.accept(visitor);
    visitor.visitingAnd(this);
  }
}

package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.InstructionVisitor;
import com.fpetrola.z80.instructions.base.ParameterizedBinaryAluInstruction;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.flag.AluOperation;

public class Add16<T extends WordNumber> extends ParameterizedBinaryAluInstruction<T> {
  public static final AluOperation add16TableAluOperation = new AluOperation() {
    public AluResult execute(int value2, int value, int carry) {
      data = carry;
      int operand = value;
      int result = value2 + value; // ADD HL,rr
      resetN(); // N = 0;
      //
      int temp = (value2 & 0x0FFF) + (operand & 0x0FFF);
      if ((temp & 0xF000) != 0)
        setH();
      else
        resetH();
      if (result > lsw) // overflow ?
      {
        setC();
        return new AluResult((result & lsw), data);
      } else {
        resetC();
        return new AluResult(result, data);
      }
    }
  };

  public Add16(OpcodeReference target, ImmutableOpcodeReference source, Register<T> flag) {
    super(target, source, flag, (tFlagRegister, value2, value) -> add16TableAluOperation.executeWithCarry2(value2, value, tFlagRegister.read().intValue(), tFlagRegister));
  }

  @Override
  public void accept(InstructionVisitor visitor) {
    visitor.visitingAdd16(this);
  }
}

package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.InstructionVisitor;
import com.fpetrola.z80.instructions.base.ParameterizedBinaryAluInstruction;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.flag.AluOperation;

public class Adc16<T extends WordNumber> extends ParameterizedBinaryAluInstruction<T> {
  public static final AluOperation adc16TableAluOperation = new AluOperation() {
    public int execute(int b, int a, int carry) {
      data = carry;
      int c = carry;
      int lans = a + b + c;
      int ans = lans & 0xffff;
      setS((ans & (FLAG_S << 8)) != 0);
      setZ(ans == 0);
      setC(lans > 0xFFFF);
      // setPV( ((a ^ b) & (a ^ value) & 0x8000)!=0 );
      setOverflowFlagAdd16(a, b, c);
      if ((((a & 0x0fff) + (b & 0x0fff) + c) & 0x1000) != 0)
        setH();
      else
        resetH();
      resetN();

      return ans;
    }
  };

  public Adc16(OpcodeReference target, ImmutableOpcodeReference source, Register<T> flag) {
    super(target, source, flag, (tFlagRegister, a, b) -> adc16TableAluOperation.executeWithCarry(a, b, tFlagRegister));
  }

  @Override
  public void accept(InstructionVisitor visitor) {
    super.accept(visitor);
    visitor.visitingAdc16(this);
  }
}

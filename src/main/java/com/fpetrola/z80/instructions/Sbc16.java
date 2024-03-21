package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.ParameterizedBinaryAluInstruction;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.flag.AluOperation;

public class Sbc16<T extends WordNumber> extends ParameterizedBinaryAluInstruction<T> {
  public static final AluOperation sbc16TableAluOperation = new AluOperation() {
    public int execute(int HL, int DE, int carry) {
      data = carry;
      int a = HL;
      int b = DE;
      int c = getC() ? 1 : 0;
      int lans = (a - b) - c;
      int ans = lans & 0xffff;
      setS((ans & (FLAG_S << 8)) != 0);
      setZ(ans == 0);
      setC(lans < 0);
      // setPV( ((a ^ b) & (a ^ value) & 0x8000)!=0 );
      setOverflowFlagSub16(a, b, c);
      if ((((a & 0x0fff) - (b & 0x0fff) - c) & 0x1000) != 0)
        setH();
      else
        resetH();
      setN();

      return ans;
    }
  };

  public Sbc16(OpcodeReference<T> target, ImmutableOpcodeReference<T> source, Register<T> flag) {
    super(target, source, flag, (tFlagRegister, DE, HL) -> sbc16TableAluOperation.executeWithCarry(DE, HL, tFlagRegister));
  }
}

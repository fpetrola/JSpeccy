package com.fpetrola.z80.opcodes.table;

import static com.fpetrola.z80.registers.RegisterName.A;
import static com.fpetrola.z80.registers.RegisterName.C;
import static com.fpetrola.z80.registers.RegisterName.HL;
import static com.fpetrola.z80.registers.RegisterName.I;
import static com.fpetrola.z80.registers.RegisterName.R;

import com.fpetrola.z80.State;
import com.fpetrola.z80.instructions.Adc16;
import com.fpetrola.z80.instructions.IM;
import com.fpetrola.z80.instructions.In;
import com.fpetrola.z80.instructions.Ld;
import com.fpetrola.z80.instructions.Neg;
import com.fpetrola.z80.instructions.Nop;
import com.fpetrola.z80.instructions.OpCode;
import com.fpetrola.z80.instructions.Out;
import com.fpetrola.z80.instructions.RLD;
import com.fpetrola.z80.instructions.RRD;
import com.fpetrola.z80.instructions.RetN;
import com.fpetrola.z80.instructions.Sbc16;
import com.fpetrola.z80.instructions.SpyInterface;

public class EDPrefixTableOpCodeGenerator extends TableOpCodeGenerator {

  public EDPrefixTableOpCodeGenerator(State state, SpyInterface opcodesSpy) {
    super(state, opcodesSpy);
  }

  protected OpCode getOpcode(int i) {
    switch (x) {
    case 1:
      switch (z) {
      case 0:
        return y == 6 ? new In(s, r(A), r(C)) : new In(s, r[y], r(C));
      case 1:
        return y == 6 ? new Out(s, r(C), c(0)) : new Out(s, r(C), r[y]);
      case 2:
        return q == 0 ? new Sbc16(s, r(HL), rp[p]) : new Adc16(s, r(HL), rp[p]);
      case 3:
        return q == 0 ? new Ld(s, iinn(), rp[p]) : new Ld(s, rp[p], iinn());
      case 4:
        return new Neg(s, r(A));
      case 5:
        return y != 1 ? new RetN(s, opc.t()) : new RetN(s, opc.t());
      case 6:
        return new IM(s, im[y]);
      case 7:
        return new OOSwitch(new Ld(s, r(I), r(A)), new Ld(s, r(R), r(A)), new Ld(s, r(A), r(I)), new Ld(s, r(A), r(R)), new RRD(s), new RLD(s), new Nop(s), new Nop(s)).getCase(y);
      }
    case 2:
      if (z <= 3 && y >= 4)
        return bli[y][z];
    }
    return null;
  }
}

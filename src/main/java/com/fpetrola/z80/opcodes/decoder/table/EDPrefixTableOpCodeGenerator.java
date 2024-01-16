package com.fpetrola.z80.opcodes.decoder.table;

import com.fpetrola.z80.State;
import com.fpetrola.z80.opcodes.instructions.Adc16;
import com.fpetrola.z80.opcodes.instructions.IM;
import com.fpetrola.z80.opcodes.instructions.In;
import com.fpetrola.z80.opcodes.instructions.Ld;
import com.fpetrola.z80.opcodes.instructions.LdAR;
import com.fpetrola.z80.opcodes.instructions.Neg;
import com.fpetrola.z80.opcodes.instructions.Nop;
import com.fpetrola.z80.opcodes.instructions.Out;
import com.fpetrola.z80.opcodes.instructions.RLD;
import com.fpetrola.z80.opcodes.instructions.RRD;
import com.fpetrola.z80.opcodes.instructions.RetN;
import com.fpetrola.z80.opcodes.instructions.Sbc16;
import com.fpetrola.z80.opcodes.references.Instruction;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.spy.SpyInterface;

import static com.fpetrola.z80.registers.RegisterName.*;

public class EDPrefixTableOpCodeGenerator extends TableOpCodeGenerator {

  public EDPrefixTableOpCodeGenerator(State state, SpyInterface opcodesSpy, OpcodeReference a) {
    super(state, opcodesSpy, HL, H, L, a);
  }

  protected Instruction getOpcode(int i) {
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
        return q == 0 ? new Ld(s, iinn(2), rp[p]) : new Ld(s, rp[p], iinn(2));
      case 4:
        return new Neg(s, r(A));
      case 5:
        return y != 1 ? new RetN(s, opc.t()) : new RetN(s, opc.t());
      case 6:
        return new IM(s, im[y]);
      case 7:
        return select(new Ld(s, r(I), r(A)), new Ld(s, r(R), r(A)), new Ld(s, r(A), r(I)), new LdAR(s, r(A), r(R)), new RRD(s), new RLD(s), new Nop(s), new Nop(s)).get(y);
      }
    case 2:
      if (z <= 3 && y >= 4)
        return bli[y][z];
    }
    return null;
  }
}

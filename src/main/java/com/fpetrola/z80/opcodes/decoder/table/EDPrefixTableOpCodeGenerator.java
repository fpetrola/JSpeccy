package com.fpetrola.z80.opcodes.decoder.table;

import com.fpetrola.z80.instructions.Adc16;
import com.fpetrola.z80.instructions.IM;
import com.fpetrola.z80.instructions.In;
import com.fpetrola.z80.instructions.Ld;
import com.fpetrola.z80.instructions.LdAR;
import com.fpetrola.z80.instructions.Neg;
import com.fpetrola.z80.instructions.Nop;
import com.fpetrola.z80.instructions.Out;
import com.fpetrola.z80.instructions.RLD;
import com.fpetrola.z80.instructions.RRD;
import com.fpetrola.z80.instructions.RetN;
import com.fpetrola.z80.instructions.Sbc16;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.OpcodeConditions;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.spy.InstructionSpy;

import static com.fpetrola.z80.registers.RegisterName.*;

public class EDPrefixTableOpCodeGenerator<T> extends TableOpCodeGenerator<T> {

  public EDPrefixTableOpCodeGenerator(State state, OpcodeReference a, OpcodeConditions opc1) {
    super(state, HL, H, L, a, opc1);
  }

  protected Instruction<T> getOpcode(int i) {
    switch (x) {
    case 1:
      switch (z) {
      case 0:
        return y == 6 ? new In(s, r(A), r(C)) : new In(s, r[y], r(C));
      case 1:
        return y == 6 ? new Out(s, r(C), c(WordNumber.createValue(0))) : new Out(s, r(C), r[y]);
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

package com.fpetrola.z80.opcodes.decoder.table;

import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.instructions.base.InstructionFactory;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.OpcodeConditions;
import com.fpetrola.z80.opcodes.references.OpcodeReference;

import static com.fpetrola.z80.registers.RegisterName.*;

public class EDPrefixTableOpCodeGenerator<T> extends TableOpCodeGenerator<T> {

  public EDPrefixTableOpCodeGenerator(State state, OpcodeReference a, OpcodeConditions opc1, InstructionFactory instructionFactory) {
    super(state, HL, H, L, a, opc1, instructionFactory);
  }

  protected Instruction<T> getOpcode() {
    switch (x) {
    case 1:
      switch (z) {
      case 0:
        return y == 6 ? i.In(r(A), r(C)) : i.In(r[y], r(C));
      case 1:
        return y == 6 ? i.Out(r(C), c(0)) : i.Out(r(C), r[y]);
      case 2:
        return q == 0 ? i.Sbc16(r(HL), rp[p]) : i.Adc16(r(HL), rp[p]);
      case 3:
        return q == 0 ? i.Ld(iinn(2), rp[p]) : i.Ld(rp[p], iinn(2));
      case 4:
        return i.Neg(r(A));
      case 5:
        return y != 1 ? i.RetN(opc.t()) : i.RetN(opc.t());
      case 6:
        return i.IM(im[y]);
      case 7:
        return select(i.Ld(r(I), r(A)), i.Ld(r(R), r(A)), i.Ld(r(A), r(I)), i.LdAR(r(A), r(R)), i.RRD(), i.RLD(), i.Nop(), i.Nop()).get(y);
      }
    case 2:
      if (z <= 3 && y >= 4)
        return bli[y][z];
    }
    return null;
  }
}

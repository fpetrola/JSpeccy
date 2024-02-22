package com.fpetrola.z80.opcodes.decoder.table;

import com.fpetrola.z80.instructions.*;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.OpcodeConditions;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;

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
        return y == 6 ? InstructionFactory.createIn(r(A), r(C)) : InstructionFactory.createIn(r[y], r(C));
      case 1:
        return y == 6 ? InstructionFactory.createOut(r(C), c(WordNumber.createValue(0))) : InstructionFactory.createOut(r(C), r[y]);
      case 2:
        return q == 0 ? InstructionFactory.createSbc16(r(HL), rp[p]) : InstructionFactory.createAdc16(r(HL), rp[p]);
      case 3:
        return q == 0 ? InstructionFactory.createLd(iinn(2), rp[p]) : InstructionFactory.createLd(rp[p], iinn(2));
      case 4:
        return InstructionFactory.createNeg(r(A));
      case 5:
        return y != 1 ? InstructionFactory.createRetN(opc.t()) : InstructionFactory.createRetN(opc.t());
      case 6:
        return InstructionFactory.createIM(im[y]);
      case 7:
        return select(InstructionFactory.createLd(r(I), r(A)), InstructionFactory.createLd(r(R), r(A)), InstructionFactory.createLd(r(A), r(I)), InstructionFactory.createLdAR(r(A), r(R)), InstructionFactory.createRRD(), InstructionFactory.createRLD(), InstructionFactory.createNop(), InstructionFactory.createNop()).get(y);
      }
    case 2:
      if (z <= 3 && y >= 4)
        return bli[y][z];
    }
    return null;
  }
}

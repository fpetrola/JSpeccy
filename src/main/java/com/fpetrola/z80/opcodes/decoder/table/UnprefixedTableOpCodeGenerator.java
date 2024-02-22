package com.fpetrola.z80.opcodes.decoder.table;

import static com.fpetrola.z80.instructions.InstructionFactory.*;
import static com.fpetrola.z80.registers.RegisterName.*;

import com.fpetrola.z80.instructions.*;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.OpcodeConditions;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.registers.RegisterName;

public class UnprefixedTableOpCodeGenerator<T> extends TableOpCodeGenerator<T> {
  private Instruction<T> cbOpcode;
  private Instruction<T> ddOpcode;
  private Instruction<T> edOpcode;
  private Instruction<T> fdOpcode;
  private int delta;

  public UnprefixedTableOpCodeGenerator(int delta, State state, Instruction<T> cbOpcode, Instruction<T> ddOpcode, Instruction<T> edOpcode, Instruction<T> fdOpcode, RegisterName main16BitRegister, RegisterName mainHigh8BitRegister, RegisterName mainLow8BitRegister, OpcodeReference main16BitRegisterReference, OpcodeConditions opc1) {
    super(state, main16BitRegister, mainHigh8BitRegister, mainLow8BitRegister, main16BitRegisterReference, opc1);
    this.delta = delta;
    this.cbOpcode = cbOpcode;
    this.ddOpcode = ddOpcode;
    this.edOpcode = edOpcode;
    this.fdOpcode = fdOpcode;
  }

  protected Instruction<T> getOpcode(int i) {
    OpcodeReference hlRegister = r(main16BitRegister);
    switch (x) {
    case 0:
      switch (z) {
      case 0:
        switch (y) {
        case 0:
          return createNop();
        case 1:
          return createEx(r(AF), r(AFx));
        case 2:
          return createDJNZ(n(delta));
        case 3:
          return createJR(n(delta), opc.t());
        case 4, 5, 6, 7:
          return createJR(n(delta), cc[y - 4]);
        }
      case 1:
        return select(InstructionFactory.createLd(rp[p], nn(delta)), createAdd16(hlRegister, rp[p])).get(q);
      case 2:
        switch (q) {
        case 0:
          return select(InstructionFactory.createLd(iRR(BC), r(A)), InstructionFactory.createLd(iRR(DE), r(A)), InstructionFactory.createLd(iinn(delta), hlRegister), InstructionFactory.createLd(inn(delta), r(A))).get(p);
        case 1:
          return select(InstructionFactory.createLd(r(A), iRR(BC)), InstructionFactory.createLd(r(A), iRR(DE)), InstructionFactory.createLd(hlRegister, iinn(delta)), InstructionFactory.createLd(r(A), inn(delta))).get(p);
        }
      case 3:
        return select(createInc16(rp[p]), createDec16(rp[p])).get(q);
      case 4:
        return createInc(r[y]);
      case 5:
        return createDec(r[y]);
      case 6:
        return createLd1();
      case 7:
        return select(createRLCA(r(A)), createRRCA(r(A)), createRLA(r(A)), createRRA(r(A)), createDAA(r(A)), createCPL(r(A)), createSCF(), createCCF()).get(y);
      }
      return null;
    case 1:
      if (z == 6 && y == 6)
        return createHalt();
      else
        return createLd();
    case 2:
      return alu.get(y).apply(r[z]);
    case 3:
      switch (z) {
      case 0:
        return createRet(cc[y]);
      case 1:
        switch (q) {
        case 0:
          return createPop(rp2[p]);
        case 1:
          return select(createRet(opc.t()), createExx(), createJP(hlRegister, opc.t()), InstructionFactory.createLd(r(SP), hlRegister)).get(p);
        }
      case 2:
        return createJP(nn(delta), cc[y]);
      case 3:
        return select(createJP(nn(delta), opc.t()), cbOpcode, createOut(n(delta), r(A)), createIn(r(A), n(delta)), createEx(iiRR(SP), hlRegister), createEx(r(DE), r(HL)), createDI(), createEI()).get(y);
      case 4:
        return createCall(nn(delta), cc[y]);
      case 5:
        switch (q) {
        case 0:
          return createPush(rp2[p]);
        case 1:
          return select(createCall(nn(delta), opc.t()), ddOpcode, edOpcode, fdOpcode).get(p);
        }
      case 6:
        return alu.get(y).apply(n(delta));
      case 7:
        return createRST(y * 8);
      }
      return null;
    }
    return null;
  }

  protected Ld createLd1() {
    return InstructionFactory.createLd(r[y], n(delta));
  }

  protected Ld createLd() {
    return InstructionFactory.createLd(r[y], r[z]);
  }
}

package com.fpetrola.z80.opcodes.decoder.table;

import static com.fpetrola.z80.registers.Flags.CARRY_FLAG;
import static com.fpetrola.z80.registers.Flags.PARITY_FLAG;
import static com.fpetrola.z80.registers.Flags.SIGNIFICANT_FLAG;
import static com.fpetrola.z80.registers.Flags.ZERO_FLAG;
import static com.fpetrola.z80.registers.RegisterName.A;
import static com.fpetrola.z80.registers.RegisterName.AF;
import static com.fpetrola.z80.registers.RegisterName.B;
import static com.fpetrola.z80.registers.RegisterName.BC;
import static com.fpetrola.z80.registers.RegisterName.C;
import static com.fpetrola.z80.registers.RegisterName.D;
import static com.fpetrola.z80.registers.RegisterName.DE;
import static com.fpetrola.z80.registers.RegisterName.E;
import static com.fpetrola.z80.registers.RegisterName.SP;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import com.fpetrola.z80.instructions.Adc;
import com.fpetrola.z80.instructions.Add;
import com.fpetrola.z80.instructions.And;
import com.fpetrola.z80.instructions.Cp;
import com.fpetrola.z80.instructions.Cpi;
import com.fpetrola.z80.instructions.Cpir;
import com.fpetrola.z80.instructions.Ini;
import com.fpetrola.z80.instructions.Ldd;
import com.fpetrola.z80.instructions.Lddr;
import com.fpetrola.z80.instructions.Ldi;
import com.fpetrola.z80.instructions.Ldir;
import com.fpetrola.z80.instructions.Or;
import com.fpetrola.z80.instructions.Outi;
import com.fpetrola.z80.instructions.RL;
import com.fpetrola.z80.instructions.RLC;
import com.fpetrola.z80.instructions.RR;
import com.fpetrola.z80.instructions.RRC;
import com.fpetrola.z80.instructions.SLA;
import com.fpetrola.z80.instructions.SLL;
import com.fpetrola.z80.instructions.SRA;
import com.fpetrola.z80.instructions.SRL;
import com.fpetrola.z80.instructions.Sbc;
import com.fpetrola.z80.instructions.Sub;
import com.fpetrola.z80.instructions.Xor;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.Condition;
import com.fpetrola.z80.opcodes.references.OpcodeConditions;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.OpcodeTargets;
import com.fpetrola.z80.registers.RegisterName;
import com.fpetrola.z80.spy.InstructionSpy;

public abstract class TableOpCodeGenerator extends OpcodeTargets {

  protected OpcodeConditions opc;

  protected abstract Instruction getOpcode(int i);

  protected OpcodeReference[] r;
  protected OpcodeReference[] rp;
  protected OpcodeReference[] rp2;
  protected Condition[] cc;
  protected Instruction[][] bli;
  protected List<Function<OpcodeReference, Instruction>> alu;
  protected List<Function<OpcodeReference, Instruction>> rot;
  protected State s;
  protected int[] im;
  protected int x;
  protected int y;
  protected int z;
  protected int p;
  protected int q;
  protected RegisterName mainHigh8BitRegister;
  protected RegisterName mainLow8BitRegister;
  protected RegisterName main16BitRegister;

  public TableOpCodeGenerator(State state, InstructionSpy opcodesSpy, RegisterName main16BitRegister, RegisterName mainHigh8BitRegister, RegisterName mainLow8BitRegister, OpcodeReference main16BitRegisterReference) {
    super(state, opcodesSpy);

    this.s = state;
    this.main16BitRegister = main16BitRegister;
    this.mainHigh8BitRegister = mainHigh8BitRegister;
    this.mainLow8BitRegister = mainLow8BitRegister;
    this.opc = new OpcodeConditions(state);

    r = new OpcodeReference[] { r(B), r(C), r(D), r(E), r(mainHigh8BitRegister), r(mainLow8BitRegister), main16BitRegisterReference, r(A) };
    rp = new OpcodeReference[] { r(BC), r(DE), r(main16BitRegister), r(SP) };
    rp2 = new OpcodeReference[] { r(BC), r(DE), r(main16BitRegister), r(AF) };
    cc = new Condition[] { opc.nf(ZERO_FLAG), opc.f(ZERO_FLAG), opc.nf(CARRY_FLAG), opc.f(CARRY_FLAG), opc.nf(PARITY_FLAG), opc.f(PARITY_FLAG), opc.nf(SIGNIFICANT_FLAG), opc.f(SIGNIFICANT_FLAG) };
    im = new int[] { 0, 0, 1, 2, 0, 0, 1, 2 };
    createALUTable(state);
    createROTTable(state);
    createBLITable(state);
  }

  protected void createBLITable(State state) {
    bli = new Instruction[8][4];
    bli[4][0] = new Ldi(state);
    bli[4][1] = new Cpi(state);
    bli[4][2] = new Ini(state);
    bli[4][3] = new Outi(state);

    bli[5][0] = new Ldd(state);
    // tableBLI[5][1] = new Cpd(state);
    // tableBLI[5][2] = new Ind(state);
    // tableBLI[5][3] = new Outd(state);

    bli[6][0] = new Ldir(state);
    bli[6][1] = new Cpir(state);
    // tableBLI[6][2] = new Inir(state);
    // tableBLI[6][3] = new Outir(state);

    bli[7][0] = new Lddr(state);
    // tableBLI[7][1] = new Cpdr(state);
    // tableBLI[7][2] = new Indr(state);
    // tableBLI[7][3] = new Outdr(state);
  }

  protected void createALUTable(State state) {
    alu = new ArrayList<>();
    alu.add(r -> new Add(state, r(A), r));
    alu.add(r -> new Adc(state, r(A), r));
    alu.add(r -> new Sub(state, r(A), r));
    alu.add(r -> new Sbc(state, r(A), r));
    alu.add(r -> new And(state, r(A), r));
    alu.add(r -> new Xor(state, r(A), r));
    alu.add(r -> new Or(state, r(A), r));
    alu.add(r -> new Cp(state, r(A), r));
  }

  protected void createROTTable(State state) {
    rot = new ArrayList<>();
    rot.add(r -> new RLC(state, r));
    rot.add(r -> new RRC(state, r));
    rot.add(r -> new RL(state, r));
    rot.add(r -> new RR(state, r));
    rot.add(r -> new SLA(state, r));
    rot.add(r -> new SRA(state, r));
    rot.add(r -> new SLL(state, r));
    rot.add(r -> new SRL(state, r));
  }

  public Instruction[] getOpcodesTable() {
    Instruction[] opcodes = new Instruction[0x100];
    for (int i = 0; i < 0x100; i++) {
      x = i >> 6;
      y = (i & 0x38) >> 3;
      z = (i & 0x07);
      p = (i & 0x30) >> 4;
      q = (i & 0x08) >> 3;

      Instruction opcode = getOpcode(i);
      opcodes[i] = opcode;
    }
    return opcodes;
  }

  protected Selector select(Instruction... opcodes) {
    return new Selector(opcodes);
  }

}
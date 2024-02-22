package com.fpetrola.z80.opcodes.decoder.table;

import com.fpetrola.z80.instructions.*;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.*;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterName;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static com.fpetrola.z80.registers.Flags.*;
import static com.fpetrola.z80.registers.RegisterName.*;

public abstract class TableOpCodeGenerator<T> extends OpcodeTargets<T> {

  protected OpcodeConditions opc;

  protected abstract Instruction<T> getOpcode(int i);

  protected OpcodeReference[] r;
  protected Register[] rp;
  protected OpcodeReference[] rp2;
  protected Condition[] cc;
  protected Instruction<T>[][] bli;
  protected List<Function<ImmutableOpcodeReference, Instruction<T>>> alu;
  protected List<RotFactory> rot;
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

  public TableOpCodeGenerator(State state, RegisterName main16BitRegister, RegisterName mainHigh8BitRegister, RegisterName mainLow8BitRegister, OpcodeReference main16BitRegisterReference, OpcodeConditions opcodeConditions) {
    super(state);

    this.s = state;
    this.main16BitRegister = main16BitRegister;
    this.mainHigh8BitRegister = mainHigh8BitRegister;
    this.mainLow8BitRegister = mainLow8BitRegister;
    this.opc = opcodeConditions;

    r = new OpcodeReference[]{r(B), r(C), r(D), r(E), r(mainHigh8BitRegister), r(mainLow8BitRegister), main16BitRegisterReference, r(A)};
    rp = new Register[]{r(BC), r(DE), r(main16BitRegister), r(SP)};
    rp2 = new OpcodeReference[]{r(BC), r(DE), r(main16BitRegister), r(AF)};
    cc = new Condition[]{opc.nf(ZERO_FLAG), opc.f(ZERO_FLAG), opc.nf(CARRY_FLAG), opc.f(CARRY_FLAG), opc.nf(PARITY_FLAG), opc.f(PARITY_FLAG), opc.nf(SIGNIFICANT_FLAG), opc.f(SIGNIFICANT_FLAG)};
    im = new int[]{0, 0, 1, 2, 0, 0, 1, 2};
    createALUTable(state);
    createROTTable(state);
    createBLITable(state);
  }

  protected void createBLITable(State state) {
    bli = new Instruction[8][4];
    bli[4][0] = new Ldi(state);
    bli[4][1] = InstructionFactory.createCpi();
    bli[4][2] = new Ini(state);
    bli[4][3] = new Outi(state);

    bli[5][0] = new Ldd(state);
    bli[5][1] = InstructionFactory.createCpd();
    bli[5][2] = new Ind(state);
    bli[5][3] = new Outd(state);

    bli[6][0] = new Ldir(state);
    bli[6][1] = new Cpir(state);
    bli[6][2] = new Inir(state);
    bli[6][3] = new Outir(state);

    bli[7][0] = new Lddr(state);
    bli[7][1] = new Cpdr(state);
    bli[7][2] = new Indr(state);
    bli[7][3] = new Outdr(state);
  }

  protected void createALUTable(State state) {
    alu = new ArrayList<>();
    alu.add(r -> InstructionFactory.createAdd(r(A), r));
    alu.add(r -> InstructionFactory.createAdc(r(A), r));
    alu.add(r -> InstructionFactory.createSub(r(A), r));
    alu.add(r -> InstructionFactory.createSbc(r(A), r));
    alu.add(r -> InstructionFactory.createAnd(r(A), r));
    alu.add(r -> InstructionFactory.createXor(r(A), r));
    alu.add(r -> InstructionFactory.createOr(r(A), r));
    alu.add(r -> InstructionFactory.createCp(r(A), r));
  }

  protected void createROTTable(State state) {
    rot = new ArrayList<>();
    rot.add((r, valueDelta) -> new RLC(state, r, valueDelta));
    rot.add((r, valueDelta) -> new RRC(state, r, valueDelta));
    rot.add((r, valueDelta) -> new RL(state, r, valueDelta));
    rot.add((r, valueDelta) -> new RR(state, r, valueDelta));
    rot.add((r, valueDelta) -> new SLA(state, r, valueDelta));
    rot.add((r, valueDelta) -> new SRA(state, r, valueDelta));
    rot.add((r, valueDelta) -> new SLL(state, r, valueDelta));
    rot.add((r, valueDelta) -> new SRL(state, r, valueDelta));
  }

  public Instruction[] getOpcodesTable() {
    Instruction[] opcodes = new Instruction[0x100];
    for (int i = 0; i < 0x100; i++) {
      x = i >> 6;
      y = (i & 0x38) >> 3;
      z = (i & 0x07);
      p = (i & 0x30) >> 4;
      q = (i & 0x08) >> 3;

      Instruction<T> opcode = getOpcode(i);
      opcodes[i] = opcode;
    }
    return opcodes;
  }

  protected Selector select(Instruction<T>... opcodes) {
    return new Selector(opcodes);
  }

}
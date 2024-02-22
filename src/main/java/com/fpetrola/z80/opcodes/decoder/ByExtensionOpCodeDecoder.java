package com.fpetrola.z80.opcodes.decoder;

import static com.fpetrola.z80.registers.RegisterName.A;
import static com.fpetrola.z80.registers.RegisterName.B;
import static com.fpetrola.z80.registers.RegisterName.C;
import static com.fpetrola.z80.registers.RegisterName.D;
import static com.fpetrola.z80.registers.RegisterName.E;
import static com.fpetrola.z80.registers.RegisterName.H;
import static com.fpetrola.z80.registers.RegisterName.HL;
import static com.fpetrola.z80.registers.RegisterName.IX;
import static com.fpetrola.z80.registers.RegisterName.IXH;
import static com.fpetrola.z80.registers.RegisterName.IXL;
import static com.fpetrola.z80.registers.RegisterName.IY;
import static com.fpetrola.z80.registers.RegisterName.IYH;
import static com.fpetrola.z80.registers.RegisterName.IYL;
import static com.fpetrola.z80.registers.RegisterName.L;
import static com.fpetrola.z80.registers.RegisterName.PC;
import static com.fpetrola.z80.registers.RegisterName.R;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

import com.fpetrola.z80.instructions.*;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.*;
import com.fpetrola.z80.registers.Plain16BitRegister;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterName;
import com.fpetrola.z80.spy.InstructionSpy;

public class ByExtensionOpCodeDecoder<T extends WordNumber> extends OpcodeTargets implements OpCodeDecoder<T> {
  private Instruction[] opcodeLookupTable;
  private Instruction[] opcodeCBLookupTable;
  private Instruction[] opcodeDDLookupTable;
  private Instruction[] opcodeEDLookupTable;
  private Instruction[] opcodeFDLookupTable;
  //
  // Fast references
  //
  private OpcodeTargets opt;
  private OpcodeConditions opc;
  private State s;
  static Register registerR;

  public ByExtensionOpCodeDecoder(State state, InstructionSpy spy) {
    super(state);
    this.s = state;
    this.opt = this;
    this.opc = new OpcodeConditions(state);
    registerR = state.getRegister(R);
    fillOpcodeLookupTable();
  }

  private void fillOpcodeLookupTable() {
    fillEDTable();
    fillCBTable();
    opcodeDDLookupTable = new Instruction[0x100];
    fillDDFD(opcodeDDLookupTable, IX, IXH, IXL);
    opcodeFDLookupTable = new Instruction[0x100];
    fillDDFD(opcodeFDLookupTable, IY, IYH, IYL);

//    opcodeLookupTable = new OpCode[0x100];
//    opcodeLookupTable[0x00] = new Nop(s);
//    opcodeLookupTable[0x01] = new Ld(s, r(BC), nn());
//    opcodeLookupTable[0x02] = new Ld(s, iRR(BC), r(A));
//    opcodeLookupTable[0x03] = new Inc16(s, r(BC));
//    opcodeLookupTable[0x04] = new Inc(s, r(B));
//    opcodeLookupTable[0x05] = new Dec(s, r(B));
//    opcodeLookupTable[0x06] = new Ld(s, r(B), n());
//    opcodeLookupTable[0x07] = new RLCA(s, r(A));
//    opcodeLookupTable[0x08] = new Ex(s, r(AF), opt._r(AF));
//
//    opcodeLookupTable[0x09] = new Add16(s, r(HL), r(BC));
//    opcodeLookupTable[0x0A] = new Ld(s, r(A), iRR(BC));
//    opcodeLookupTable[0x0B] = new Dec16(s, r(BC));
//    opcodeLookupTable[0x0C] = new Inc(s, r(C));
//    opcodeLookupTable[0x0D] = new Dec(s, r(C));
//    opcodeLookupTable[0x0E] = new Ld(s, r(C), n());
//
//    opcodeLookupTable[0x0F] = new RRCA(s, r(A));
//    opcodeLookupTable[0x10] = new DJNZ(s, n());
//    opcodeLookupTable[0x11] = new Ld(s, r(DE), nn());
//    opcodeLookupTable[0x12] = new Ld(s, iRR(DE), r(A));
//    opcodeLookupTable[0x13] = new Inc16(s, r(DE));
//    opcodeLookupTable[0x14] = new Inc(s, r(D));
//    opcodeLookupTable[0x15] = new Dec(s, r(D));
//    opcodeLookupTable[0x16] = new Ld(s, r(D), n());
//    opcodeLookupTable[0x17] = new RLA(s, r(A));
//    opcodeLookupTable[0x18] = new JR(s, n(), opc.t());
//
//    opcodeLookupTable[0x19] = new Add16(s, r(HL), r(DE));
//    opcodeLookupTable[0x1A] = new Ld(s, r(A), iRR(DE));
//    opcodeLookupTable[0x1B] = new Dec16(s, r(DE));
//    opcodeLookupTable[0x1C] = new Inc(s, r(E));
//    opcodeLookupTable[0x1D] = new Dec(s, r(E));
//    opcodeLookupTable[0x1E] = new Ld(s, r(E), n());
//
//    opcodeLookupTable[0x1F] = new RRA(s, r(A));
//    opcodeLookupTable[0x20] = new JR(s, n(), opc.nf(Flags.ZERO_FLAG));
//    opcodeLookupTable[0x21] = new Ld(s, r(HL), nn());
//    opcodeLookupTable[0x22] = new Ld(s, iinn(), r(HL));
//    opcodeLookupTable[0x23] = new Inc16(s, r(HL));
//    opcodeLookupTable[0x24] = new Inc(s, r(H));
//    opcodeLookupTable[0x25] = new Dec(s, r(H));
//    opcodeLookupTable[0x26] = new Ld(s, r(H), n());
//    opcodeLookupTable[0x27] = new DAA(s, r(A));
//    opcodeLookupTable[0x28] = new JR(s, n(), opc.f(Flags.ZERO_FLAG));
//
//    opcodeLookupTable[0x29] = new Add16(s, r(HL), r(HL));
//    opcodeLookupTable[0x2A] = new Ld(s, r(HL), iinn());
//    opcodeLookupTable[0x2B] = new Dec16(s, r(HL));
//    opcodeLookupTable[0x2C] = new Inc(s, r(L));
//    opcodeLookupTable[0x2D] = new Dec(s, r(L));
//    opcodeLookupTable[0x2E] = new Ld(s, r(L), n());
//
//    opcodeLookupTable[0x2F] = new CPL(s, r(A));
//    opcodeLookupTable[0x30] = new JR(s, n(), opc.nf(Flags.CARRY_FLAG));
//    opcodeLookupTable[0x31] = new Ld(s, r(SP), nn());
//    opcodeLookupTable[0x32] = new Ld(s, opt.inn(), r(A));
//    opcodeLookupTable[0x33] = new Inc16(s, r(SP));
//    opcodeLookupTable[0x34] = new Inc(s, iRR(HL));
//    opcodeLookupTable[0x35] = new Dec(s, iRR(HL));
//    opcodeLookupTable[0x36] = new Ld(s, iRR(HL), n());
//    opcodeLookupTable[0x37] = new SCF(s);
//    opcodeLookupTable[0x38] = new JR(s, n(), opc.f(Flags.CARRY_FLAG));
//    opcodeLookupTable[0x39] = new Add16(s, r(HL), r(SP));
//    opcodeLookupTable[0x3A] = new Ld(s, r(A), opt.inn());
//    opcodeLookupTable[0x3B] = new Dec16(s, r(SP));
//    opcodeLookupTable[0x3C] = new Inc(s, r(A));
//    opcodeLookupTable[0x3D] = new Dec(s, r(A));
//    opcodeLookupTable[0x3E] = new Ld(s, r(A), n());
//    opcodeLookupTable[0x3F] = new CCF(s);
//
//    int i = 0x40;
//    i = fillCB(i, opcodeLookupTable, op -> new Ld(s, r(B), op));
//    i = fillCB(i, opcodeLookupTable, op -> new Ld(s, r(C), op));
//    i = fillCB(i, opcodeLookupTable, op -> new Ld(s, r(D), op));
//    i = fillCB(i, opcodeLookupTable, op -> new Ld(s, r(E), op));
//    i = fillCB(i, opcodeLookupTable, op -> new Ld(s, r(H), op));
//    i = fillCB(i, opcodeLookupTable, op -> new Ld(s, r(L), op));
//
//    opcodeLookupTable[0x70] = new Ld(s, iRR(HL), r(B));
//    opcodeLookupTable[0x71] = new Ld(s, iRR(HL), r(C));
//    opcodeLookupTable[0x72] = new Ld(s, iRR(HL), r(D));
//    opcodeLookupTable[0x73] = new Ld(s, iRR(HL), r(E));
//    opcodeLookupTable[0x74] = new Ld(s, iRR(HL), r(H));
//    opcodeLookupTable[0x75] = new Ld(s, iRR(HL), r(L));
//
//    opcodeLookupTable[0x76] = new Halt(s);
//    opcodeLookupTable[0x77] = new Ld(s, iRR(HL), r(A));
//
//    i = 0x78;
//    i = fillCB(i, opcodeLookupTable, op -> new Ld(s, r(A), op));
//    i = fillCB(i, opcodeLookupTable, op -> new Add(s, r(A), op));
//    i = fillCB(i, opcodeLookupTable, op -> new Adc(s, r(A), op));
//    i = fillCB(i, opcodeLookupTable, op -> new Sub(s, r(A), op));
//    i = fillCB(i, opcodeLookupTable, op -> new Sbc(s, r(A), op));
//    i = fillCB(i, opcodeLookupTable, op -> new And(s, r(A), op));
//    i = fillCB(i, opcodeLookupTable, op -> new Xor(s, r(A), op));
//    i = fillCB(i, opcodeLookupTable, op -> new Or(s, r(A), op));
//    i = fillCB(i, opcodeLookupTable, op -> new Cp(s, r(A), op));
//
//    opcodeLookupTable[0xC0] = new Ret(s, opc.nf(Flags.ZERO_FLAG));
//    opcodeLookupTable[0xC1] = new Pop(s, r(BC));
//    opcodeLookupTable[0xC2] = new JP(s, nn(), opc.nf(Flags.ZERO_FLAG));
//    opcodeLookupTable[0xC3] = new JP(s, nn(), opc.t());
//    opcodeLookupTable[0xC4] = new Call(s, nn(), opc.nf(Flags.ZERO_FLAG));
//    opcodeLookupTable[0xC5] = new Push(s, r(BC));
//    opcodeLookupTable[0xC6] = new Add(s, r(A), n());
//    opcodeLookupTable[0xC7] = new RST(s, 0x00);
//    opcodeLookupTable[0xC8] = new Ret(s, opc.f(Flags.ZERO_FLAG));
//    opcodeLookupTable[0xC9] = new Ret(s, opc.t());
//    opcodeLookupTable[0xCA] = new JP(s, nn(), opc.f(Flags.ZERO_FLAG));
//    opcodeLookupTable[0xCB] = new FlipOpcodeImpl(s, this.opcodeCBLookupTable, 1, "CB", spy);
//    opcodeLookupTable[0xCC] = new Call(s, nn(), opc.f(Flags.ZERO_FLAG));
//    opcodeLookupTable[0xCD] = new Call(s, nn(), opc.t());
//    opcodeLookupTable[0xCE] = new Adc(s, r(A), n());
//    opcodeLookupTable[0xCF] = new RST(s, 0x08);
//    opcodeLookupTable[0xD0] = new Ret(s, opc.nf(Flags.CARRY_FLAG));
//    opcodeLookupTable[0xD1] = new Pop(s, r(DE));
//    opcodeLookupTable[0xD2] = new JP(s, nn(), opc.nf(Flags.CARRY_FLAG));
//    opcodeLookupTable[0xD3] = new Out(s, n(), r(A));
//    opcodeLookupTable[0xD4] = new Call(s, nn(), opc.nf(Flags.CARRY_FLAG));
//    opcodeLookupTable[0xD5] = new Push(s, r(DE));
//    opcodeLookupTable[0xD6] = new Sub(s, r(A), n());
//    opcodeLookupTable[0xD7] = new RST(s, 0x10);
//    opcodeLookupTable[0xD8] = new Ret(s, opc.f(Flags.CARRY_FLAG));
//    opcodeLookupTable[0xD9] = new Exx(s);
//    opcodeLookupTable[0xDA] = new JP(s, nn(), opc.f(Flags.CARRY_FLAG));
//    opcodeLookupTable[0xDB] = new In(s, r(A), n());
//    opcodeLookupTable[0xDC] = new Call(s, nn(), opc.f(Flags.CARRY_FLAG));
//    opcodeLookupTable[0xDD] = new FlipOpcodeImpl(s, this.opcodeDDLookupTable, 1, "DD", spy);
//    opcodeLookupTable[0xDE] = new Sbc(s, r(A), n());
//    opcodeLookupTable[0xDF] = new RST(s, 0x18);
//    opcodeLookupTable[0xE0] = new Ret(s, opc.nf(Flags.PARITY_FLAG));
//    opcodeLookupTable[0xE1] = new Pop(s, r(HL));
//    opcodeLookupTable[0xE2] = new JP(s, nn(), opc.nf(Flags.PARITY_FLAG));
//    opcodeLookupTable[0xE3] = new Ex(s, opt.iiRR(SP), r(HL));
//    opcodeLookupTable[0xE4] = new Call(s, nn(), opc.nf(Flags.PARITY_FLAG));
//    opcodeLookupTable[0xE5] = new Push(s, r(HL));
//    opcodeLookupTable[0xE6] = new And(s, r(A), n());
//    opcodeLookupTable[0xE7] = new RST(s, 0x20);
//    opcodeLookupTable[0xE8] = new Ret(s, opc.f(Flags.PARITY_FLAG));
//    opcodeLookupTable[0xE9] = new JP(s, r(HL), opc.t());
//    opcodeLookupTable[0xEA] = new JP(s, nn(), opc.f(Flags.PARITY_FLAG));
//    opcodeLookupTable[0xEB] = new Ex(s, r(DE), r(HL));
//    opcodeLookupTable[0xEC] = new Call(s, nn(), opc.f(Flags.PARITY_FLAG));
//    opcodeLookupTable[0xED] = new FlipOpcodeImpl(s, this.opcodeEDLookupTable, 1, "ED", spy);
//    opcodeLookupTable[0xEE] = new Xor(s, r(A), n());
//    opcodeLookupTable[0xEF] = new RST(s, 0x28);
//    opcodeLookupTable[0xF0] = new Ret(s, opc.nf(Flags.SIGNIFICANT_FLAG));
//    opcodeLookupTable[0xF1] = new Pop(s, r(AF));
//    opcodeLookupTable[0xF2] = new JP(s, nn(), opc.nf(Flags.SIGNIFICANT_FLAG));
//    opcodeLookupTable[0xF3] = new DI(s);
//    opcodeLookupTable[0xF4] = new Call(s, nn(), opc.nf(Flags.SIGNIFICANT_FLAG));
//    opcodeLookupTable[0xF5] = new Push(s, r(AF));
//    opcodeLookupTable[0xF6] = new Or(s, r(A), n());
//    opcodeLookupTable[0xF7] = new RST(s, 0x30);
//    opcodeLookupTable[0xF8] = new Ret(s, opc.f(Flags.SIGNIFICANT_FLAG));
//    opcodeLookupTable[0xF9] = new Ld(s, r(SP), r(HL));
//    opcodeLookupTable[0xFA] = new JP(s, nn(), opc.f(Flags.SIGNIFICANT_FLAG));
//    opcodeLookupTable[0xFB] = new EI(s);
//    opcodeLookupTable[0xFC] = new Call(s, nn(), opc.f(Flags.SIGNIFICANT_FLAG));
//    opcodeLookupTable[0xFD] = new FlipOpcodeImpl(s, this.opcodeFDLookupTable, 1, "FD", spy);
//    opcodeLookupTable[0xFE] = new Cp(s, r(A), n());
//    opcodeLookupTable[0xFF] = new RST(s, 0x38);
//
//    /*
//     * #DD (Be aware of missing opcodes in this list) 70 LD (IX+d),B 71 LD (IX+d),C
//     * 72 LD (IX+d),D 73 LD (IX+d),E 74 LD (IX+d),H 75 LD (IX+d),L 76 77 LD (IX+d),A
//     * 78 79 7A 7B 7C 7D 7E LD A,(IX+d) 7F 80 81 82 83 84 85 86 ADD A,(IX+d) 87 88
//     * 89 8A 8B 8C 8D 8E ADC A,(IX+d) 8F 90 91 92 93 94 95 96 SUB (IX+d) 97 98 99 9A
//     * 9B 9C 9D 9E SBC (IX+d) 9F A0 A1 A2 A3 A4 A5 A6 AND (IX+d) A7 A8 A9 AA AB AC
//     * AD AE XOR (IX+d) AF B0 B1 B2 B3 B4 B5 B6 OR (IX+d) B7 B8 B9 BA BB BC BD BE CP
//     * (IX+d) BF C0 C1 C2 C3 C4 C5 C6 C7 C8 C9 CA
//     */

  }

  private void fillEDTable() {
//    opcodeEDLookupTable = new OpCode[0x100];
//    opcodeEDLookupTable[0x42] = new Out(s, r(C), r(B));
//    opcodeEDLookupTable[0x42] = new Sbc16(s, r(HL), r(BC));
//    opcodeEDLookupTable[0x43] = new Ld(s, iinn(), r(BC));
//    opcodeEDLookupTable[0x44] = new Neg(s, r(A));
//    opcodeEDLookupTable[0x47] = new Ld(s, r(I), r(A));
//    opcodeEDLookupTable[0x49] = new Out(s, r(C), r(C));
//    opcodeEDLookupTable[0x4B] = new Ld(s, r(BC), iinn());
//    opcodeEDLookupTable[0x4D] = new Ret(s, opc.t());
//    opcodeEDLookupTable[0x51] = new Out(s, r(C), r(D));
//    opcodeEDLookupTable[0x52] = new Sbc16(s, r(HL), r(DE));
//    opcodeEDLookupTable[0x53] = new Ld(s, iinn(), r(DE));
//    opcodeEDLookupTable[0x56] = new IM(s, 1);
//    opcodeEDLookupTable[0x58] = new In(s, r(E), r(C));
//    opcodeEDLookupTable[0x59] = new Out(s, r(C), r(E));
//    opcodeEDLookupTable[0x5A] = new Adc16(s, r(HL), r(DE));
//    opcodeEDLookupTable[0x5B] = new Ld(s, r(DE), iinn());
//    opcodeEDLookupTable[0x5F] = new LdAR(s, r(A), r(R));
//    opcodeEDLookupTable[0x61] = new Out(s, r(C), r(H));
//    opcodeEDLookupTable[0x69] = new Out(s, r(C), r(L));
//    opcodeEDLookupTable[0xA0] = new Ldi(s);
//    opcodeEDLookupTable[0xA1] = new Cpi(s);
//    opcodeEDLookupTable[0xA3] = new Outi(s);
//    opcodeEDLookupTable[0xA8] = new Ldd(s);
//    opcodeEDLookupTable[0xB0] = new Ldir(s);
//    opcodeEDLookupTable[0xB1] = new Cpir(s);
//    opcodeEDLookupTable[0xB8] = new Lddr(s);
//    opcodeEDLookupTable[0x71] = new Out(s, r(C), c(0));
//    opcodeEDLookupTable[0x72] = new Sbc16(s, r(HL), r(SP));
//    opcodeEDLookupTable[0x73] = new Ld(s, iinn(), r(SP));
//    opcodeEDLookupTable[0x78] = new In(s, r(A), r(C));
//    opcodeEDLookupTable[0x79] = new Out(s, r(C), r(A));
//    opcodeEDLookupTable[0x7B] = new Ld(s, r(SP), iinn());
  }

  private void fillCBTable() {
    opcodeCBLookupTable = new Instruction[0x100];

    int i;
    //
    // CB Instructions
    //

    i = 0;
    i = fillCB(i, opcodeCBLookupTable, op -> new RLC(s, op, 1));
    i = fillCB(i, opcodeCBLookupTable, op -> new RRC(s, op, 1));
    i = fillCB(i, opcodeCBLookupTable, op -> new RL(s, op, 1));
    i = fillCB(i, opcodeCBLookupTable, op -> new RR(s, op, 1));
    i = fillCB(i, opcodeCBLookupTable, op -> new SLA(s, op, 1));
    i = fillCB(i, opcodeCBLookupTable, op -> new SRA(s, op, 1));
    i = fillCB(i, opcodeCBLookupTable, op -> new SLL(s, op, 1));
    i = fillCB(i, opcodeCBLookupTable, op -> new SRL(s, op, 1));

    for (int j = 0; j < 8; j++) {
      int j2 = j;
      i = fillCB(i, opcodeCBLookupTable, op -> InstructionFactory.createBIT(op, j2, 0));
    }

    for (int j = 0; j < 8; j++) {
      int j2 = j;
      i = fillCB(i, opcodeCBLookupTable, op -> InstructionFactory.createRES(op, j2, 0));
    }

    for (int j = 0; j < 8; j++) {
      int j2 = j;
      i = fillCB(i, opcodeCBLookupTable, op -> InstructionFactory.createSET(op, j2, 0));
    }
  }

  private int fillCB(int i, Instruction<T>[] opCodes, Function<OpcodeReference, Instruction<T>> opcodeSupplier) {
    List<OpcodeReference> asList = Arrays.asList(r(B), r(C), r(D), r(E), r(H), r(L), iRR(HL), r(A));
    for (int j = 0; j < asList.size(); j++) {
      opCodes[j + i] = opcodeSupplier.apply(asList.get(j));
    }
    return i + asList.size();
  }

  private int fill07(int i, int inc, Instruction<T>[] opCodes, Function<Integer, Instruction<T>> opcodeSupplier) {
    for (int j = 0; j < 8; j++) {
      opCodes[(j * inc) + i] = opcodeSupplier.apply(j);
    }
    return i + 8;
  }

  private void fillDDFD(Instruction<T>[] table, RegisterName ixy, RegisterName ixyh, RegisterName ixyl) {
//    table[0x09] = new Add16(s, r(ixy), r(BC));
//    table[0x19] = new Add16(s, r(ixy), r(DE));
//    table[0x21] = new Ld(s, r(ixy), nn());
//    table[0x22] = new Ld(s, iinn(), r(ixy));
//    table[0x23] = new Inc16(s, r(ixy));
//    table[0x24] = new Inc(s, r(ixyh));
//    table[0x25] = new Dec(s, r(ixyh));
//    table[0x26] = new Ld(s, r(ixyh), n());
//    table[0x29] = new Add16(s, r(ixy), r(ixy));
//    table[0x2A] = new Ld(s, r(ixy), iinn());
//    table[0x2B] = new Dec16(s, r(ixy));
//    table[0x2C] = new Inc(s, r(ixyl));
//    table[0x2D] = new Dec(s, r(ixyl));
//    table[0x2E] = new Ld(s, r(ixyl), n());
//    table[0x34] = new Inc(s, iRRn(ixy, true, 0));
//    table[0x35] = new Dec(s, iRRn(ixy, true, 0));
//    table[0x36] = new Ld(s, iRRn(ixy, false, -1), n(1));
//    table[0x39] = new Add16(s, r(ixy), r(SP));
//    table[0x44] = new Ld(s, r(B), r(ixyh));
//    table[0x45] = new Ld(s, r(B), r(ixyl));
//    table[0x46] = new Ld(s, r(B), iRRn(ixy, false, 0));
//    table[0x4C] = new Ld(s, r(C), r(ixyh));
//    table[0x4D] = new Ld(s, r(C), r(ixyl));
//    table[0x4E] = new Ld(s, r(C), iRRn(ixy, false, 0));
//    table[0x54] = new Ld(s, r(D), r(ixyh));
//    table[0x55] = new Ld(s, r(D), r(ixyl));
//    table[0x56] = new Ld(s, r(D), iRRn(ixy, false, 0));
//    table[0x5C] = new Ld(s, r(E), r(ixyh));
//    table[0x5D] = new Ld(s, r(E), r(ixyl));
//    table[0x5E] = new Ld(s, r(E), iRRn(ixy, false, 0));
//    table[0x60] = new Ld(s, r(ixyh), r(B));
//    table[0x61] = new Ld(s, r(ixyh), r(C));
//    table[0x62] = new Ld(s, r(ixyh), r(D));
//    table[0x63] = new Ld(s, r(ixyh), r(E));
//    table[0x64] = new Ld(s, r(ixyh), r(ixyh));
//    table[0x65] = new Ld(s, r(ixyh), r(ixyl));
//    table[0x66] = new Ld(s, r(H), iRRn(ixy, false, 0));
//    table[0x67] = new Ld(s, r(ixyh), r(A));
//    table[0x68] = new Ld(s, r(ixyl), r(B));
//    table[0x69] = new Ld(s, r(ixyl), r(C));
//    table[0x6A] = new Ld(s, r(ixyl), r(D));
//    table[0x6B] = new Ld(s, r(ixyl), r(E));
//    table[0x6C] = new Ld(s, r(ixyl), r(ixyh));
//    table[0x6D] = new Ld(s, r(ixyl), r(ixyl));
//    table[0x6E] = new Ld(s, r(L), iRRn(ixy, false, 0));
//    table[0x6F] = new Ld(s, r(ixyl), r(A));
//    table[0x70] = new Ld(s, iRRn(ixy, false, 0), r(B));
//    table[0x71] = new Ld(s, iRRn(ixy, false, 0), r(C));
//    table[0x72] = new Ld(s, iRRn(ixy, false, 0), r(D));
//    table[0x73] = new Ld(s, iRRn(ixy, false, 0), r(E));
//    table[0x74] = new Ld(s, iRRn(ixy, false, 0), r(H));
//    table[0x75] = new Ld(s, iRRn(ixy, false, 0), r(L));
//    table[0x77] = new Ld(s, iRRn(ixy, false, 0), r(A));
//    table[0x7E] = new Ld(s, r(A), iRRn(ixy, false, 0));
//    table[0x86] = new Add(s, r(A), iRRn(ixy, false, 0));
//    table[0x8E] = new Adc(s, r(A), iRRn(ixy, false, 0));
//    table[0x96] = new Sub(s, r(A), iRRn(ixy, false, 0));
//    table[0xA6] = new And(s, r(A), iRRn(ixy, false, 0));
//    table[0xAE] = new Xor(s, r(A), iRRn(ixy, false, 0));
//    table[0xB6] = new Or(s, r(A), iRRn(ixy, false, 0));
//    table[0xBE] = new Cp(s, r(A), iRRn(ixy, false, 0));
//    table[0xE1] = new Pop(s, r(ixy));
//    table[0xE5] = new Push(s, r(ixy));
//    table[0xE9] = new JP(s, r(ixy), opc.t());

    Instruction[] IXCBTable = new Instruction[0x100];

    fill07(0x46, 8, IXCBTable, index -> InstructionFactory.createBIT(iRRn(ixy, true, -2), index, 0));
    fill07(0xC6, 8, IXCBTable, index -> InstructionFactory.createSET(iRRn(ixy, true, -2), index, 0));
    fill07(0x86, 8, IXCBTable, index -> InstructionFactory.createRES(iRRn(ixy, true, -2), index, 0));

//    table[0x7E] = new BIT(s, iRRn(ixy, true, 0), index, -2);

    table[0xCB] = new DefaultFetchNextOpcodeInstruction(s, IXCBTable, 2, "DDFDCB", spy);
  }

  public Instruction<T>[] getOpcodeLookupTable() {
    return opcodeLookupTable;
  }

  public void compareOpcodesGenerators(State state2, InstructionSpy spy2, OpCodeDecoder decoder1) {
    OpCodeDecoder decoder2 = this;
    Instruction<T>[] opcodeLookupTable = decoder1.getOpcodeLookupTable();
    Instruction<T>[] opcodeLookupTable2 = decoder2.getOpcodeLookupTable();
    int comparison = compareOpcodes(opcodeLookupTable, opcodeLookupTable2);
  }

  private int compareOpcodes(Instruction<T>[] opcodeLookupTable, Instruction<T>[] opcodeLookupTable2) {
    int compare = Arrays.compare(opcodeLookupTable, opcodeLookupTable2, new Comparator<Instruction<T>>() {
      public int compare(Instruction<T> o1, Instruction<T> o2) {
        if (o1 != null && o2 != null) {
          Plain16BitRegister<T> pc2 = new Plain16BitRegister<T>(PC);
          pc2.write(WordNumber.createValue(0));
          Plain16BitRegister<T> pc3 = new Plain16BitRegister<T>(PC);
          pc3.write(WordNumber.createValue(0));
//          o1.setPC(pc2);
//          o2.setPC(pc3);

          if (o1 instanceof FetchNextOpcodeInstruction) {
            FetchNextOpcodeInstruction fetchNextOpcodeInstruction = (FetchNextOpcodeInstruction) o1;
            FetchNextOpcodeInstruction flipOpcode2 = (FetchNextOpcodeInstruction) o2;
            return compareOpcodes(fetchNextOpcodeInstruction.getTable(), flipOpcode2.getTable());
          } else {
            int compareTo = o1.toString().compareTo(o2.toString());
            if (compareTo != 0)
              System.out.println("dgadg");
            return compareTo;
          }
        } else
          return 0;
      }
    });
    return compare;
  }
}

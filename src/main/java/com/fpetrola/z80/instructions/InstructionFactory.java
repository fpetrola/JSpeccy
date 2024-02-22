package com.fpetrola.z80.instructions;

import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.Condition;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.flag.IFlagRegister;

import static com.fpetrola.z80.registers.RegisterName.*;

public class InstructionFactory {
  private static Register bc;
  private static Register de;
  private static Register hl;
  private static Memory memory;
  private static Register c;
  private static Register _bc;
  private static Register _de;
  private static Register _hl;

  public static void setState(State state) {
    InstructionFactory.state = state;
    pc = state.getPc();
    flag = (IFlagRegister) state.getRegister(F);
    a = state.getRegister(A);
    b = state.getRegister(B);
    c = state.getRegister(B);
    bc = state.getRegister(BC);
    de = state.getRegister(DE);
    hl = state.getRegister(HL);
    _bc = state.getRegister(BCx);
    _de = state.getRegister(DEx);
    _hl = state.getRegister(HLx);
    memory = state.getMemory();
  }

  private static Register a;
  private static Register b;
  private static State state;
  private static Register pc;
  private static IFlagRegister flag;

  public static <T extends WordNumber> DJNZ<T> createDJNZ(ImmutableOpcodeReference<T> target) {
    return new DJNZ<T>(target, InstructionFactory.state.getRegisterB(), pc);
  }

  public static JP createJP(ImmutableOpcodeReference target, Condition condition) {
    return new JP(target, condition, pc);
  }

  public static <T extends WordNumber> Call createCall(ImmutableOpcodeReference positionOpcodeReference, Condition condition) {
    return new Call<T>(positionOpcodeReference, condition, pc, state.getRegisterSP(), state.getMemory());
  }

  public static <T extends WordNumber> JR createJR(ImmutableOpcodeReference target, Condition condition) {
    return new JR<T>(target, condition, pc);
  }

  public static <T extends WordNumber> Adc<T> createAdc(OpcodeReference<T> target, ImmutableOpcodeReference<T> source) {
    return new Adc<T>(target, source, flag);
  }

  public static <T extends WordNumber> Cpd createCpd() {
    return new Cpd<T>(a, flag, bc, hl, memory);
  }

  public static <T extends WordNumber> CCF createCCF() {
    return new CCF<T>(flag, a);
  }

  public static <T extends WordNumber> Cpi createCpi() {
    return new Cpi<T>(a, flag, bc, hl, memory);
  }

  public static <T extends WordNumber> Adc16 createAdc16(OpcodeReference target, ImmutableOpcodeReference source) {
    return new Adc16<T>(target, source, flag);
  }

  public static <T extends WordNumber> Add createAdd(OpcodeReference target, ImmutableOpcodeReference source) {
    return new Add<T>(target, source, flag);
  }

  public static <T extends WordNumber> Add16 createAdd16(OpcodeReference target, ImmutableOpcodeReference source) {
    return new Add16<T>(target, source, flag);
  }

  public static <T extends WordNumber> And createAnd(OpcodeReference target, ImmutableOpcodeReference source) {
    return new And<T>(target, source, flag);
  }

  public static <T extends WordNumber> Or createOr(OpcodeReference target, ImmutableOpcodeReference source) {
    return new Or<T>(target, source, flag);
  }

  public static <T extends WordNumber> Sbc createSbc(OpcodeReference target, ImmutableOpcodeReference source) {
    return new Sbc<T>(target, source, flag);
  }

  public static <T extends WordNumber> Sbc16 createSbc16(OpcodeReference target, ImmutableOpcodeReference source) {
    return new Sbc16<T>(target, source, flag);
  }

  public static <T extends WordNumber> Sub createSub(OpcodeReference target, ImmutableOpcodeReference source) {
    return new Sub<T>(target, source, flag);
  }

  public static <T extends WordNumber> Cp createCp(OpcodeReference target, ImmutableOpcodeReference source) {
    return new Cp<T>(state, target, source);
  }

  public static <T extends WordNumber> Xor createXor(OpcodeReference target, ImmutableOpcodeReference source) {
    return new Xor<T>(target, source, flag);
  }

  public static <T extends WordNumber> BIT createBIT(OpcodeReference target, int n, int valueDelta) {
    return new BIT<T>(target, n, valueDelta, flag);
  }

  public static <T extends WordNumber> RES createRES(OpcodeReference target, int n, int valueDelta) {
    return new RES<T>(target, n, valueDelta);
  }

  public static <T extends WordNumber> SET createSET(OpcodeReference target, int n, int valueDelta) {
    return new SET<T>(target, n, valueDelta);
  }

  public static <T extends WordNumber> Cpir createCpir() {
    return new Cpir<T>(flag, bc, pc, b);
  }

  public static <T extends WordNumber> Cpdr createCpdr() {
    return new Cpdr<T>(pc, b, bc);
  }

  public static <T extends WordNumber> Indr createIndr() {
    return new Indr<T>(pc, b, bc);
  }

  public static <T extends WordNumber> Inir createInir() {
    return new Inir<T>(pc, b, bc);
  }

  public static <T extends WordNumber> Lddr createLddr() {
    return new Lddr<T>(state, pc, b, bc);
  }

  public static <T extends WordNumber> Outdr createOutdr() {
    return new Outdr<T>(state, pc, b, bc);
  }

  public static <T extends WordNumber> Ldir createLdir() {
    return new Ldir<T>(state, pc, b, bc);
  }

  public static <T extends WordNumber> Outir createOutir() {
    return new Outir<T>(pc, b, bc);
  }

  public static <T extends WordNumber> Ind createInd() {
    return new Ind<T>(b, c, hl, flag, memory);
  }

  public static <T extends WordNumber> Ini createIni() {
    return new Ini<T>(b, c, hl, flag, memory, state.getIo());
  }

  public static <T extends WordNumber> Outi createOuti() {
    return new Outi<T>(state, b, c, hl, flag, memory, state.getIo());
  }

  public static <T extends WordNumber> CPL createCPL(OpcodeReference target) {
    return new CPL<T>(target, flag);
  }

  public static <T extends WordNumber> DAA createDAA(OpcodeReference target) {
    return new DAA<T>(target, flag);
  }

  public static <T extends WordNumber> Dec createDec(OpcodeReference target) {
    return new Dec<T>(target, flag);
  }

  public static <T extends WordNumber> Dec16 createDec16(OpcodeReference target) {
    return new Dec16<T>(target);
  }

  public static <T extends WordNumber> DI createDI() {
    return new DI<T>(state);
  }

  public static <T extends WordNumber> EI createEI() {
    return new EI<T>(state);
  }

  public static <T extends WordNumber> Ex createEx(OpcodeReference target, ImmutableOpcodeReference source) {
    return new Ex<T>(target, source);
  }

  public static <T extends WordNumber> Exx createExx() {
    return new Exx<T>(bc, de, hl, _bc, _de, _hl);
  }

  public static <T extends WordNumber> Halt createHalt() {
    return new Halt<T>(state);
  }

  public static <T extends WordNumber> IM createIM(int mode) {
    return new IM<T>(state, mode);
  }

  public static <T extends WordNumber> In createIn(OpcodeReference target, ImmutableOpcodeReference source) {
    return new In<T>(target, source, a, bc, flag, state.getMemptr(), state.getIo());
  }

  public static <T extends WordNumber> Inc createInc(OpcodeReference target) {
    return new Inc<T>(target, flag);
  }

  public static <T extends WordNumber> Inc16 createInc16(OpcodeReference target) {
    return new Inc16<T>(target);
  }

  public static <T extends WordNumber> Ld<T> createLd(OpcodeReference<T> target, ImmutableOpcodeReference<T> source) {
    return new Ld<T>(target, source, flag);
  }

  public static <T extends WordNumber> LdAR<T> createLdAR(OpcodeReference<T> target, ImmutableOpcodeReference<T> source) {
    return new LdAR<T>(target, source, flag, state);
  }

  public static <T extends WordNumber> Ldd createLdd() {
    return new Ldd<T>(bc, de, hl, flag, memory);
  }
}

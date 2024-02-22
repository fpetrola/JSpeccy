package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.Instruction;
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
  private Register bc;
  private Register de;
  private Register hl;
  private Memory memory;
  private Register c;
  private Register _bc;
  private Register _de;
  private Register _hl;
  private Register sp;
  private Register r;
  private Register a;
  private Register b;
  private State state;
  private Register pc;
  private IFlagRegister flag;

  public void setState(State state) {
    this.state = state;
    pc = state.getPc();
    sp = state.getRegisterSP();
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
    r = state.getRegister(R);
    memory = state.getMemory();
  }

  public InstructionFactory(State state) {
    setState(state);
  }

  public <T extends WordNumber> DJNZ<T> DJNZ(ImmutableOpcodeReference<T> target) {
    return new DJNZ<T>(target, state.getRegisterB(), pc);
  }

  public JP JP(ImmutableOpcodeReference target, Condition condition) {
    return new JP(target, condition, pc);
  }

  public <T extends WordNumber> Call Call(ImmutableOpcodeReference positionOpcodeReference, Condition condition) {
    return new Call<T>(positionOpcodeReference, condition, pc, state.getRegisterSP(), state.getMemory());
  }

  public <T extends WordNumber> JR JR(ImmutableOpcodeReference target, Condition condition) {
    return new JR<T>(target, condition, pc);
  }

  public <T extends WordNumber> Adc<T> Adc(OpcodeReference<T> target, ImmutableOpcodeReference<T> source) {
    return new Adc<T>(target, source, flag);
  }

  public <T extends WordNumber> Cpd Cpd() {
    return new Cpd<T>(a, flag, bc, hl, memory);
  }

  public <T extends WordNumber> CCF CCF() {
    return new CCF<T>(flag, a);
  }

  public <T extends WordNumber> Cpi Cpi() {
    return new Cpi<T>(a, flag, bc, hl, memory);
  }

  public <T extends WordNumber> Adc16 Adc16(OpcodeReference target, ImmutableOpcodeReference source) {
    return new Adc16<T>(target, source, flag);
  }

  public <T extends WordNumber> Add Add(OpcodeReference target, ImmutableOpcodeReference source) {
    return new Add<T>(target, source, flag);
  }

  public <T extends WordNumber> Add16 Add16(OpcodeReference target, ImmutableOpcodeReference source) {
    return new Add16<T>(target, source, flag);
  }

  public <T extends WordNumber> And And(OpcodeReference target, ImmutableOpcodeReference source) {
    return new And<T>(target, source, flag);
  }

  public <T extends WordNumber> Or Or(OpcodeReference target, ImmutableOpcodeReference source) {
    return new Or<T>(target, source, flag);
  }

  public <T extends WordNumber> Sbc Sbc(OpcodeReference target, ImmutableOpcodeReference source) {
    return new Sbc<T>(target, source, flag);
  }

  public <T extends WordNumber> Sbc16 Sbc16(OpcodeReference target, ImmutableOpcodeReference source) {
    return new Sbc16<T>(target, source, flag);
  }

  public <T extends WordNumber> Sub Sub(OpcodeReference target, ImmutableOpcodeReference source) {
    return new Sub<T>(target, source, flag);
  }

  public <T extends WordNumber> Cp Cp(OpcodeReference target, ImmutableOpcodeReference source) {
    return new Cp<T>(target, source, flag);
  }

  public <T extends WordNumber> Xor Xor(OpcodeReference target, ImmutableOpcodeReference source) {
    return new Xor<T>(target, source, flag);
  }

  public <T extends WordNumber> BIT BIT(OpcodeReference target, int n, int valueDelta) {
    return new BIT<T>(target, n, valueDelta, flag);
  }

  public <T extends WordNumber> RES RES(OpcodeReference target, int n, int valueDelta) {
    return new RES<T>(target, n, valueDelta, flag);
  }

  public <T extends WordNumber> SET SET(OpcodeReference target, int n, int valueDelta) {
    return new SET<T>(target, n, valueDelta, flag);
  }

  public <T extends WordNumber> Cpir Cpir() {
    return new Cpir<T>(flag, bc, pc, b, this);
  }

  public <T extends WordNumber> Cpdr Cpdr() {
    return new Cpdr<T>(pc, b, bc, flag, this);
  }

  public <T extends WordNumber> Indr Indr() {
    return new Indr<T>(pc, b, bc, this);
  }

  public <T extends WordNumber> Inir Inir() {
    return new Inir<T>(pc, b, bc, this);
  }

  public <T extends WordNumber> Lddr Lddr() {
    return new Lddr<T>(pc, b, bc, this);
  }

  public <T extends WordNumber> Outdr Outdr() {
    return new Outdr<T>(pc, b, bc, this);
  }

  public <T extends WordNumber> Ldir Ldir() {
    return new Ldir<T>(pc, b, bc, this);
  }

  public <T extends WordNumber> Outir Outir() {
    return new Outir<T>(pc, b, bc, this);
  }

  public <T extends WordNumber> Ind Ind() {
    return new Ind<T>(b, c, hl, flag, memory, state.getIo());
  }

  public <T extends WordNumber> Ini Ini() {
    return new Ini<T>(b, c, hl, flag, memory, state.getIo());
  }

  public <T extends WordNumber> Outi Outi() {
    return new Outi<T>(b, c, hl, flag, memory, state.getIo());
  }

  public <T extends WordNumber> CPL CPL(OpcodeReference target) {
    return new CPL<T>(target, flag);
  }

  public <T extends WordNumber> DAA DAA(OpcodeReference target) {
    return new DAA<T>(target, flag);
  }

  public <T extends WordNumber> Dec Dec(OpcodeReference target) {
    return new Dec<T>(target, flag);
  }

  public <T extends WordNumber> Dec16 Dec16(OpcodeReference target) {
    return new Dec16<T>(target);
  }

  public <T extends WordNumber> DI DI() {
    return new DI<T>(state);
  }

  public <T extends WordNumber> EI EI() {
    return new EI<T>(state);
  }

  public <T extends WordNumber> Ex Ex(OpcodeReference target, ImmutableOpcodeReference source) {
    return new Ex<T>(target, source);
  }

  public <T extends WordNumber> Exx Exx() {
    return new Exx<T>(bc, de, hl, _bc, _de, _hl);
  }

  public <T extends WordNumber> Halt Halt() {
    return new Halt<T>(state);
  }

  public <T extends WordNumber> IM IM(int mode) {
    return new IM<T>(state, mode);
  }

  public <T extends WordNumber> In In(OpcodeReference target, ImmutableOpcodeReference source) {
    return new In<T>(target, source, a, bc, flag, state.getMemptr(), state.getIo());
  }

  public <T extends WordNumber> Inc Inc(OpcodeReference target) {
    return new Inc<T>(target, flag);
  }

  public <T extends WordNumber> Inc16 Inc16(OpcodeReference target) {
    return new Inc16<T>(target);
  }

  public <T extends WordNumber> Ld<T> Ld(OpcodeReference<T> target, ImmutableOpcodeReference<T> source) {
    return new Ld<T>(target, source, flag);
  }

  public <T extends WordNumber> LdAR<T> LdAR(OpcodeReference<T> target, ImmutableOpcodeReference<T> source) {
    return new LdAR<T>(target, source, flag, state);
  }

  public <T extends WordNumber> Ldd Ldd() {
    return new Ldd<T>(bc, de, hl, flag, memory);
  }

  public <T extends WordNumber> Ldi Ldi() {
    return new Ldi<T>(bc, de, hl, flag, memory);
  }

  public <T extends WordNumber> LdOperation<T> LdOperation(OpcodeReference target, Instruction<T> instruction) {
    return new LdOperation<T>(target, instruction);
  }

  public <T extends WordNumber> Neg Neg(OpcodeReference target) {
    return new Neg<T>(target, flag);
  }

  public <T extends WordNumber> Nop Nop() {
    return new Nop<T>();
  }

  public <T extends WordNumber> Out Out(ImmutableOpcodeReference target, ImmutableOpcodeReference source) {
    return new Out<T>(target, source, state.getIo());
  }

  public <T extends WordNumber> Outd Outd() {
    return new Outd<T>(b, c, hl, flag, memory, state.getIo());
  }

  public <T extends WordNumber> Pop Pop(OpcodeReference target) {
    return new Pop<T>(target, sp, memory);
  }

  public <T extends WordNumber> Push Push(OpcodeReference target) {
    return new Push<T>(target, sp, memory);
  }

  public <T extends WordNumber> Ret Ret(Condition condition) {
    return new Ret<T>(condition, sp, memory);
  }

  public RetN RetN(Condition condition) {
    return new RetN(condition, sp, memory, state);
  }

  public <T extends WordNumber> RL<T> RL(OpcodeReference target, int valueDelta) {
    return new RL<T>(target, valueDelta, flag);
  }

  public <T extends WordNumber> RLA RLA(OpcodeReference target) {
    return new RLA<T>(target, flag);
  }

  public <T extends WordNumber> RLC<T> RLC(OpcodeReference target, int valueDelta) {
    return new RLC<T>(target, valueDelta, flag);
  }

  public <T extends WordNumber> RLCA RLCA(OpcodeReference target) {
    return new RLCA<T>(target, flag);
  }

  public <T extends WordNumber> RLD RLD() {
    return new RLD<T>(a, hl, flag, r, memory);
  }

  public <T extends WordNumber> RR RR(OpcodeReference target, int valueDelta) {
    return new RR<T>(target, valueDelta, flag);
  }

  public <T extends WordNumber> RRA RRA(OpcodeReference target) {
    return new RRA<T>(target, flag);
  }

  public <T extends WordNumber> RRC RRC(OpcodeReference target, int valueDelta) {
    return new RRC<T>(target, valueDelta, flag);
  }

  public <T extends WordNumber> RRCA RRCA(OpcodeReference target) {
    return new RRCA<T>(target, flag);
  }

  public <T extends WordNumber> RRD RRD() {
    return new RRD<T>(a, hl, r, flag, memory);
  }

  public <T extends WordNumber> RST RST(int p) {
    return new RST<T>(p, pc, sp, memory);
  }

  public <T extends WordNumber> SCF SCF() {
    return new SCF<T>(flag);
  }

  public <T extends WordNumber> SLA SLA(OpcodeReference<T> target, int valueDelta) {
    return new SLA<T>(target, valueDelta, flag);
  }

  public <T extends WordNumber> SLL SLL(OpcodeReference target, int valueDelta) {
    return new SLL<T>(target, valueDelta, flag);
  }

  public <T extends WordNumber> SRA SRA(OpcodeReference target, int valueDelta) {
    return new SRA<T>(target, valueDelta, flag);
  }

  public <T extends WordNumber> SRL SRL(OpcodeReference target, int valueDelta) {
    return new SRL<T>(target, valueDelta, flag);
  }
}

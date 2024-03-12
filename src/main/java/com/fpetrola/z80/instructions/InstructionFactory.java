package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.mmu.IO;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.Condition;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterPair;
import com.fpetrola.z80.registers.flag.FlagRegister;

import static com.fpetrola.z80.registers.RegisterName.*;

@SuppressWarnings("ALL")
public class InstructionFactory<T extends WordNumber> {
  private RegisterPair<T> bc;
  private Register<T> de;
  private Register<T> hl;
  private Memory<T> memory;
  private Register<T> c;
  private Register<T> _bc;
  private Register<T> _de;
  private Register<T> _hl;
  private Register<T> sp;
  private Register<T> r;
  private Register<T> a;
  private Register<T> b;
  private State<T> state;
  private Register<T> pc;
  private FlagRegister<T> flag;
  private IO<T> io;

  public InstructionFactory() {
  }

  public InstructionFactory(State state) {
    setState(state);
  }

  public void setState(State<T> state) {
    this.state = state;
    io = state.getIo();
    pc = state.getPc();
    sp = state.getRegisterSP();
    flag = (FlagRegister<T>) state.getRegister(F);
    a = state.getRegister(A);
    b = state.getRegister(B);
    c = state.getRegister(B);
    bc = (RegisterPair<T>) state.getRegister(BC);
    de = state.getRegister(DE);
    hl = state.getRegister(HL);
    _bc = state.getRegister(BCx);
    _de = state.getRegister(DEx);
    _hl = state.getRegister(HLx);
    r = state.getRegister(R);
    memory = state.getMemory();
  }

  public DJNZ<T> DJNZ(ImmutableOpcodeReference<T> target) {
    return new DJNZ<T>(target, b, pc);
  }

  public JP JP(ImmutableOpcodeReference target, Condition condition) {
    return new JP(target, condition, pc);
  }

  public Call Call(Condition condition, ImmutableOpcodeReference positionOpcodeReference) {
    return new Call<T>(positionOpcodeReference, condition, pc, sp, state.getMemory());
  }

  public JR JR(Condition condition, ImmutableOpcodeReference target) {
    return new JR<T>(target, condition, pc);
  }

  public Adc<T> Adc(OpcodeReference<T> target, ImmutableOpcodeReference<T> source) {
    return new Adc<T>(target, source, flag);
  }

  public Cpd Cpd() {
    return new Cpd<T>(a, flag, bc, hl, memory, io);
  }

  public CCF CCF() {
    return new CCF<T>(flag, a);
  }

  public Cpi Cpi() {
    return new Cpi<T>(a, flag, bc, hl, memory, io);
  }

  public Adc16 Adc16(OpcodeReference target, ImmutableOpcodeReference source) {
    return new Adc16<T>(target, source, flag);
  }

  public Add Add(OpcodeReference target, ImmutableOpcodeReference source) {
    return new Add<T>(target, source, flag);
  }

  public Add16 Add16(OpcodeReference target, ImmutableOpcodeReference source) {
    return new Add16<T>(target, source, flag);
  }

  public And And(ImmutableOpcodeReference source) {
    return new And<T>(a, source, flag);
  }

  public Or Or(ImmutableOpcodeReference source) {
    return new Or<T>(a, source, flag);
  }

  public Sbc Sbc(OpcodeReference target, ImmutableOpcodeReference source) {
    return new Sbc<T>(target, source, flag);
  }

  public Sbc16 Sbc16(OpcodeReference target, ImmutableOpcodeReference source) {
    return new Sbc16<T>(target, source, flag);
  }

  public Sub Sub(ImmutableOpcodeReference source) {
    return new Sub<T>(a, source, flag);
  }

  public Cp Cp(ImmutableOpcodeReference source) {
    return new Cp<T>(a, source, flag);
  }

  public Xor Xor(ImmutableOpcodeReference source) {
    return new Xor<T>(a, source, flag);
  }

  public BIT BIT(OpcodeReference target, int n) {
    return new BIT<T>(target, n, flag);
  }

  public RES RES(OpcodeReference target, int n) {
    return new RES<T>(target, n, flag);
  }

  public SET SET(OpcodeReference target, int n) {
    return new SET<T>(target, n, flag);
  }

  public Cpir<T> Cpir() {
    return new Cpir<T>(flag, bc, pc, b, Cpi());
  }

  public Cpdr Cpdr() {
    return new Cpdr<T>(pc, b, bc, flag, Cpd());
  }

  public Indr Indr() {
    return new Indr<T>(pc, b, bc, Ind());
  }

  public Inir Inir() {
    return new Inir<T>(pc, b, bc, Ini());
  }

  public Lddr Lddr() {
    return new Lddr<T>(pc, b, bc, Ldd());
  }

  public Outdr Outdr() {
    return new Outdr<T>(pc, b, bc, Outd());
  }

  public Ldir Ldir() {
    return new Ldir<T>(pc, b, bc, Ldi());
  }

  public Outir Outir() {
    return new Outir<T>(pc, b, bc, Outi());
  }

  public Ind Ind() {
    return new Ind<T>(bc, hl, flag, memory, io);
  }

  public Ini Ini() {
    return new Ini<T>(bc, hl, flag, memory, io);
  }

  public Outi Outi() {
    return new Outi<T>(bc, hl, flag, memory, io);
  }

  public CPL CPL() {
    return new CPL<T>(a, flag);
  }

  public DAA DAA() {
    return new DAA<T>(a, flag);
  }

  public Dec Dec(OpcodeReference target) {
    return new Dec<T>(target, flag);
  }

  public Dec16 Dec16(OpcodeReference target) {
    return new Dec16<T>(target);
  }

  public DI DI() {
    return new DI<T>(state);
  }

  public EI EI() {
    return new EI<T>(state);
  }

  public Ex Ex(OpcodeReference target, OpcodeReference source) {
    return new Ex<T>(target, source);
  }

  public Exx Exx() {
    return new Exx<T>(bc, de, hl, _bc, _de, _hl);
  }

  public Halt Halt() {
    return new Halt<T>(state);
  }

  public IM IM(int mode) {
    return new IM<T>(state, mode);
  }

  public In In(OpcodeReference target, ImmutableOpcodeReference source) {
    return new In<T>(target, source, a, bc, flag, state.getMemptr(), io);
  }

  public Inc Inc(OpcodeReference target) {
    return new Inc<T>(target, flag);
  }

  public Inc16 Inc16(OpcodeReference target) {
    return new Inc16<T>(target);
  }

  public Ld<T> Ld(OpcodeReference<T> target, ImmutableOpcodeReference<T> source) {
    return new Ld<T>(target, source, flag);
  }

  public LdAR<T> LdAR(OpcodeReference<T> target, ImmutableOpcodeReference<T> source) {
    return new LdAR<T>(target, source, flag, state);
  }

  public Ldd Ldd() {
    return new Ldd<T>(de, bc, hl, flag, memory, io);
  }

  public Ldi Ldi() {
    return new Ldi<T>(de, bc, hl, flag, memory, io);
  }

  public LdOperation<T> LdOperation(OpcodeReference target, Instruction<T> instruction) {
    return new LdOperation<T>(target, instruction);
  }

  public Neg Neg(OpcodeReference target) {
    return new Neg<T>(target, flag);
  }

  public Nop Nop() {
    return new Nop<T>();
  }

  public Out Out(ImmutableOpcodeReference target, ImmutableOpcodeReference source) {
    return new Out<T>(source, new Out.OutPortOpcodeReference(io, target), flag);
  }

  public Outd Outd() {
    return new Outd<T>(bc, hl, flag, memory, io);
  }

  public Pop Pop(OpcodeReference target) {
    return new Pop<T>(target, sp, memory, flag);
  }

  public Push Push(OpcodeReference target) {
    return new Push<T>(target, sp, memory);
  }

  public Ret Ret(Condition condition) {
    return new Ret<T>(condition, sp, memory, pc);
  }

  public RetN RetN(Condition condition) {
    return new RetN(condition, sp, memory, state, pc);
  }

  public RL<T> RL(OpcodeReference target) {
    return new RL<T>(target, flag);
  }

  public RLA RLA() {
    return new RLA<T>(a, flag);
  }

  public RLC<T> RLC(OpcodeReference target) {
    return new RLC<T>(target, flag);
  }

  public RLCA RLCA() {
    return new RLCA<T>(a, flag);
  }

  public RLD RLD() {
    return new RLD<T>(a, hl, flag, r, memory);
  }

  public RR RR(OpcodeReference target) {
    return new RR<T>(target, flag);
  }

  public RRA RRA() {
    return new RRA<T>(a, flag);
  }

  public RRC RRC(OpcodeReference target) {
    return new RRC<T>(target, flag);
  }

  public RRCA RRCA() {
    return new RRCA<T>(a, flag);
  }

  public RRD RRD() {
    return new RRD<T>(a, hl, r, flag, memory);
  }

  public RST RST(int p) {
    return new RST<T>(p, pc, sp, memory);
  }

  public SCF SCF() {
    return new SCF<T>(flag);
  }

  public SLA SLA(OpcodeReference<T> target) {
    return new SLA<T>(target, flag);
  }

  public SLL SLL(OpcodeReference target) {
    return new SLL<T>(target, flag);
  }

  public SRA SRA(OpcodeReference target) {
    return new SRA<T>(target, flag);
  }

  public SRL SRL(OpcodeReference target) {
    return new SRL<T>(target, flag);
  }
}

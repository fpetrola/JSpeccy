package com.fpetrola.z80.instructions;

import static com.fpetrola.z80.registers.RegisterName.A;
import static com.fpetrola.z80.registers.RegisterName.AF;
import static com.fpetrola.z80.registers.RegisterName.B;
import static com.fpetrola.z80.registers.RegisterName.BC;
import static com.fpetrola.z80.registers.RegisterName.DE;
import static com.fpetrola.z80.registers.RegisterName.F;
import static com.fpetrola.z80.registers.RegisterName.HL;
import static com.fpetrola.z80.registers.RegisterName.MEMPTR;
import static com.fpetrola.z80.registers.RegisterName.PC;
import static com.fpetrola.z80.registers.RegisterName.R;
import static com.fpetrola.z80.registers.RegisterName.SP;

import com.fpetrola.z80.State;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.registers.Plain16BitRegister;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterPair;

import z80core.Timer;

public abstract class AbstractOpCode implements OpCode {

  protected State state;

  protected Register a;
  protected IFlagRegister flag;
  protected RegisterPair af;
  protected RegisterPair bc;
  protected RegisterPair de;
  protected RegisterPair hl;
  protected RegisterPair _af;
  protected RegisterPair _bc;
  protected RegisterPair _de;
  protected RegisterPair _hl;

  protected Register b;

  protected Plain16BitRegister pc;
  protected Plain16BitRegister sp;

  protected Register memptr;

  protected Memory memory;

  protected int length = 1;

  protected Register r;

  private int basePc;

  protected static Timer timer = new Timer("OpCode ");
  protected int cyclesCost = 4;

  protected AbstractOpCode(State state) {
    this.state = state;
    this.memory = state.getMemory();
    this.a = state.getRegister(A);
    this.flag = (IFlagRegister) state.getRegister(F);
    this.pc = (Plain16BitRegister) state.getRegister(PC);
    this.sp = (Plain16BitRegister) state.getRegister(SP);
    this.af = (RegisterPair) state.getRegister(AF);
    this.bc = (RegisterPair) state.getRegister(BC);
    this.de = (RegisterPair) state.getRegister(DE);
    this.hl = (RegisterPair) state.getRegister(HL);
    this._bc = (RegisterPair) state.getRegisterAlternate(BC);
    this._de = (RegisterPair) state.getRegisterAlternate(DE);
    this._hl = (RegisterPair) state.getRegisterAlternate(HL);
    this._af = (RegisterPair) state.getRegisterAlternate(AF);
    this.memptr = state.getRegister(MEMPTR);
    this.b = state.getRegister(B);
    this.r = state.getRegister(R);
  }

  public String toString() {
    return getClass().getSimpleName();
  }

  public int getLength() {
    return length;
  }

  public void incrementLengthBy(int by) {
    length += by;
  }

  public Plain16BitRegister getPC() {
    return pc;
  }

  public void setPC(Plain16BitRegister pc) {
    this.pc = pc;
  }

  public int getBasePc() {
    return basePc;
  }

  public void setBasePc(int basePc) {
    this.basePc = basePc;
  }

  public OpCode getInstruction() {
    return this;
  }

  public void setLength(int length) {
    this.length = length;
  }
}

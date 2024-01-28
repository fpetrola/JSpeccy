package com.fpetrola.z80.instructions.base;

import static com.fpetrola.z80.registers.RegisterName.A;
import static com.fpetrola.z80.registers.RegisterName.AF;
import static com.fpetrola.z80.registers.RegisterName.B;
import static com.fpetrola.z80.registers.RegisterName.BC;
import static com.fpetrola.z80.registers.RegisterName.BCx;
import static com.fpetrola.z80.registers.RegisterName.DE;
import static com.fpetrola.z80.registers.RegisterName.F;
import static com.fpetrola.z80.registers.RegisterName.HL;
import static com.fpetrola.z80.registers.RegisterName.MEMPTR;
import static com.fpetrola.z80.registers.RegisterName.PC;
import static com.fpetrola.z80.registers.RegisterName.R;
import static com.fpetrola.z80.registers.RegisterName.*;

import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.registers.Plain16BitRegister;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterPair;
import com.fpetrola.z80.registers.flag.IFlagRegister;
import com.fpetrola.z80.spy.InstructionSpy;

import z80core.Timer;

public abstract class AbstractInstruction implements Instruction {
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

  protected static Timer timer = new Timer("OpCode ");

  protected int cyclesCost = 4;
  protected InstructionSpy spy;
  protected AbstractInstruction(State state) {
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
    this._bc = (RegisterPair) state.getRegister(BCx);
    this._de = (RegisterPair) state.getRegister(DEx);
    this._hl = (RegisterPair) state.getRegister(HLx);
    this._af = (RegisterPair) state.getRegister(AFx);
    this.memptr = state.getRegister(MEMPTR);
    this.b = state.getRegister(B);
    this.r = state.getRegister(R);
  }

  public String toString() {
    return spy.executeInPause(() -> getClass().getSimpleName());
  }

  public int getLength() {
    return length;
  }

  public void incrementLengthBy(int by) {
    length += by;
  }

  public Instruction getBaseInstruction() {
    return this;
  }

  public void setLength(int length) {
    this.length = length;
  }

  public State getState() {
    return state;
  }
  
  public void setSpy(InstructionSpy spy) {
    this.spy = spy;
  }
}

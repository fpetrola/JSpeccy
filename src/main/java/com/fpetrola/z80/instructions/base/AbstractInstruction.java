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

import com.fpetrola.z80.blocks.ByteCodeGenerator;
import com.fpetrola.z80.helpers.Helper;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.Condition;
import com.fpetrola.z80.opcodes.references.MemoryPlusRegister8BitReference;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Plain16BitRegister;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterName;
import com.fpetrola.z80.registers.RegisterPair;
import com.fpetrola.z80.registers.flag.IFlagRegister;
import com.fpetrola.z80.spy.InstructionSpy;

import org.cojen.maker.*;
import z80core.Timer;

public abstract class AbstractInstruction<T extends WordNumber> implements Instruction<T> {
  protected State<T> state;
  protected Register<T> a;

  protected IFlagRegister<T> flag;
  protected RegisterPair<T> af;
  protected RegisterPair<T> bc;
  protected RegisterPair<T> de;
  protected RegisterPair<T> hl;
  protected RegisterPair<T> _af;
  protected RegisterPair<T> _bc;
  protected RegisterPair<T> _de;
  protected RegisterPair<T> _hl;
  protected Register<T> b;

  protected Plain16BitRegister<T> pc;

  protected Plain16BitRegister<T> sp;
  protected Register<T> memptr;

  protected Memory<T> memory;

  protected int length = 1;

  protected Register<T> r;

  protected static Timer timer = new Timer("OpCode ");

  protected int cyclesCost = 4;
  protected InstructionSpy<T> spy;
  private T nextPC = null;

  protected AbstractInstruction(State state) {
    this.state = state;
    this.memory = state.getMemory();
    this.a = state.getRegister(A);
    this.flag = (IFlagRegister) state.getRegister(F);
    this.pc = (Plain16BitRegister<T>) state.getRegister(PC);
    this.sp = (Plain16BitRegister<T>) state.getRegister(SP);
    this.af = (RegisterPair<T>) state.getRegister(AF);
    this.bc = (RegisterPair<T>) state.getRegister(BC);
    this.de = (RegisterPair<T>) state.getRegister(DE);
    this.hl = (RegisterPair<T>) state.getRegister(HL);
    this._bc = (RegisterPair<T>) state.getRegister(BCx);
    this._de = (RegisterPair<T>) state.getRegister(DEx);
    this._hl = (RegisterPair<T>) state.getRegister(HLx);
    this._af = (RegisterPair<T>) state.getRegister(AFx);
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

  public Instruction<T> getBaseInstruction() {
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

  public void setNextPC(T address) {
    this.nextPC = address;
  }

  public T getNextPC() {
    return nextPC;
  }

  @Override
  public int getJumpLabel() {
    return 0;
  }

  @Override
  public int createBytecode(MethodMaker mm, int label, ByteCodeGenerator byteCodeGenerator) {
    hereLabel(label, byteCodeGenerator);
    return 0;
  }

  protected void hereLabel(int label, ByteCodeGenerator byteCodeGenerator) {
    if (label != -1)
      byteCodeGenerator.hereLabel(label);
  }

  public static String createLabelName(int label) {
    return "$" + Helper.convertToHex(label);
  }

  protected <T extends WordNumber> Object getSourceVariableOf(ByteCodeGenerator byteCodeGenerator, OpcodeReference<T> source2, boolean isTarget) {
    Object sourceVariable2 = source2.read().intValue();

    if (source2 instanceof Register) {
      sourceVariable2 = byteCodeGenerator.getField(((Register<T>) source2));
    } else if (source2 instanceof MemoryPlusRegister8BitReference<T>) {
      MemoryPlusRegister8BitReference<T> source1 = (MemoryPlusRegister8BitReference<T>) source2;
      Field field = byteCodeGenerator.getField((Register) source1.getTarget());
      if (isTarget)
        sourceVariable2 = new WriteArrayVariable(byteCodeGenerator, field, source1);
      else
        sourceVariable2 = byteCodeGenerator.memory.aget(field.add(source1.fetchRelative()));
    }
    return sourceVariable2;
  }

  protected void executeConditional(ByteCodeGenerator byteCodeGenerator, Runnable runnable, Condition condition) {
    Field f = byteCodeGenerator.registers.get(RegisterName.F.name());
    if (condition.toString().equals("NZ")) f.ifNe(0, runnable);
    else if (condition.toString().equals("Z")) f.ifEq(0, runnable);
    else if (condition.toString().equals("NC")) f.ifGe(0, runnable);
    else if (condition.toString().equals("C")) f.ifLt(0, runnable);
    else runnable.run();
  }
}

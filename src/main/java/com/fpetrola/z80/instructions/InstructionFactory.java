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
  private static Register hl;
  private static Register bc;
  private static Memory memory;

  public static void setState(State state) {
    InstructionFactory.state = state;
    pc = state.getPc();
    flag = (IFlagRegister) state.getRegister(F);
    a = state.getRegister(A);
    hl = state.getRegister(HL);
    bc = state.getRegister(BC);
    memory= state.getMemory();
  }

  private static Register a;
  private static State state;
  private static Register pc;
  private static IFlagRegister flag;

  public static <T extends WordNumber> DJNZ<T> createDJNZ(ImmutableOpcodeReference<T> target) {
    return new DJNZ<T>(target, InstructionFactory.state.getRegisterB(), pc);
  }

  public static JP createJP(ImmutableOpcodeReference target, Condition condition) {
    return new JP(state, target, condition, pc);
  }

  public static <T extends WordNumber> Call createCall(ImmutableOpcodeReference positionOpcodeReference, Condition condition) {
    return new Call<T>(positionOpcodeReference, condition, pc, state.getRegisterSP(), state.getMemory());
  }

  public static <T extends WordNumber> JR createJR(ImmutableOpcodeReference target, Condition condition) {
    return new JR<T>(target, condition, pc);
  }

  public static <T extends WordNumber> Adc<T> createAdc(OpcodeReference<T> target, ImmutableOpcodeReference<T> source) {
    return new Adc<T>(state, target, source);
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
}

package com.fpetrola.z80.instructions;

import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.Condition;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;

public class InstructionFactory {
  public static void setState(State state) {
    InstructionFactory.state = state;
    pc = state.getPc();
  }

  private static State state;
  private static Register pc;

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
}

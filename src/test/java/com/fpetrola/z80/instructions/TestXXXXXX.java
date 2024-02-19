package com.fpetrola.z80.instructions;

import com.fpetrola.z80.cpu.InstructionFetcher;
import com.fpetrola.z80.cpu.OOZ80;
import com.fpetrola.z80.instructions.base.ConditionalInstruction;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.OpcodeTargets;
import com.fpetrola.z80.opcodes.references.TraceableWordNumber;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

import java.util.ArrayList;
import java.util.List;

import static com.fpetrola.z80.registers.RegisterName.*;

public class TestXXXXXX {


  @Before
  public void setUp() {
  }

  @Test
  public <T extends WordNumber> void test1() {
    State state = null;
    Register a = state.getRegister(A);
    Register b = state.getRegister(B);
    Register d = state.getRegister(D);
    Register h = state.getRegister(H);
    Register l = state.getRegister(L);
    Register hl = state.getRegister(HL);
    Register de = state.getRegister(DE);
    OpcodeTargets ot = new OpcodeTargets(state);

    InstructionFetcher<WordNumber> instructionFetcher = new InstructionFetcher<>(state);
    OOZ80<T> z80 = new OOZ80<>(state,
        instructionFetcher);

    List<Instruction<T>> instructions = new ArrayList<>();

    instructions.add(new Ld<T>(state, h, ot.c(7)));
    instructions.add(new Ld<T>(state, l, a));
    instructions.add(new Add16<T>(state, hl, hl));
    instructions.add(new Add16<T>(state, hl, hl));
    instructions.add(new Add16<T>(state, hl, hl));
    instructions.add(new Ld<T>(state, b, ot.c(8)));

    Ld<T> ld1 = new Ld<T>(state, a, ot.iiRR(HL));
    instructions.add(ld1);
    instructions.add(new Ld<T>(state, ot.iiRR(DE), a));
    instructions.add(new Inc<>(state, hl));
    instructions.add(new Inc<>(state, d));
    instructions.add(new DJNZ<>(state, ConditionalInstruction.createLabelFor(ld1)));


  }
}

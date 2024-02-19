package com.fpetrola.z80.instructions;

import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.OpcodeTargets;
import com.fpetrola.z80.opcodes.references.TraceableWordNumber;
import com.fpetrola.z80.registers.Register;
import org.junit.Before;
import org.junit.Test;

import static com.fpetrola.z80.registers.RegisterName.H;

public class TestXXXXXX {


  @Before
  public void setUp() {
  }

  @Test
  public void test1() {
    State state = null;
    Register h = state.getRegister(H);
    OpcodeTargets ot = new OpcodeTargets(state);
    new Ld<TraceableWordNumber>(state, h, ot.c(7));
  }
}

package com.fpetrola.z80.instructions;

import com.fpetrola.z80.opcodes.references.WordNumber;
import org.junit.Test;

import static com.fpetrola.z80.registers.RegisterName.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@SuppressWarnings("ALL")
public class TestBasicInstructionLoop<T extends WordNumber> extends TestFirstCPUInstructionLoop<T> {
  @Test
  public void testRegisterAssignmentUsingVirtualRegister() {
    useSecond();

    add(new Ld(r(H), c(7), f()));
    add(new Ld(r(B), r(H), f()));

    step();
    assertNotEquals(7, r(H).read().intValue());

    step();
    assertEquals(7, r(B).read().intValue());
  }

  @Test
  public void testRegisterAssignmentUsingVirtualRegisterTwice() {
    useSecond();

    add(new Ld(r(H), c(7), f()));
    add(new Ld(r(B), r(H), f()));
    add(new Ld(r(L), r(B), f()));

    step();
    assertNotEquals(7, r(H).read().intValue());

    step();
    assertEquals(7, r(B).read().intValue());

    step();
    assertEquals(7, r(L).read().intValue());
  }
}

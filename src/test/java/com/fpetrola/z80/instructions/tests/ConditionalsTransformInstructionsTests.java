package com.fpetrola.z80.instructions.tests;

import com.fpetrola.z80.instructions.*;
import com.fpetrola.z80.opcodes.references.WordNumber;
import org.junit.Test;

import static com.fpetrola.z80.registers.RegisterName.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@SuppressWarnings("ALL")
public class ConditionalsTransformInstructionsTests<T extends WordNumber> extends TransformInstructionsTests<T> {
  @Test
  public void testDjnzSimpleLoop() {
    add(new Ld(r(B), c(2), f()));
    add(new Ld(r(H), c(7), f()));
    add(new Inc(r(H), f()));
    add(new Ld(mm(c(memPosition)), r(H), f()));
    add(new DJNZ(c(-3), r(B), r(PC)));

    step(4);
    assertEquals(8, readMemAt(memPosition));
    step();
    assertEquals(2, r(PC).read().intValue());
    step(2);
    assertEquals(9, readMemAt(memPosition));
  }
}

package com.fpetrola.z80.instructions.tests;

import com.fpetrola.z80.instructions.*;
import com.fpetrola.z80.opcodes.references.WordNumber;
import org.junit.Test;

import java.util.List;

import static com.fpetrola.z80.registers.RegisterName.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@SuppressWarnings("ALL")
public class ConditionalsTransformInstructionsTests<T extends WordNumber> extends TransformInstructionsTests<T> {
  @Test
  public void testJRNZSimpleLoop() {
    add(new Ld(f(), c(20), f()));
    add(new Ld(r(B), c(3), f()));
    add(new Ld(r(H), c(7), f()));

    add(new Inc(r(H), f()));
    add(new Ld(mm(c(memPosition)), r(H), f()));
    add(new Dec(r(B), f()));
    add(new JR(c(-4), nz(), r(PC)));

    step(3);
    assertLoop0(0);
    assertLoop0(1);
    assertLoop0(2);

    List executedInstructions = registerTransformerInstructionSpy.getExecutedInstructions();
    executedInstructions.size();

    assertEquals(executedInstructions.get(0), executedInstructions.get(9));
    assertEquals(executedInstructions.get(9), executedInstructions.get(15));

    assertEquals(executedInstructions.get(2), executedInstructions.get(11));
    assertEquals(executedInstructions.get(11), executedInstructions.get(17));

    assertEquals(executedInstructions.get(7), executedInstructions.get(14));
    assertEquals(executedInstructions.get(14), executedInstructions.get(20));

    assertEquals(7, r(PC).read().intValue());
  }

  private void assertLoop0(int i) {
    assertEquals(3, r(PC).read().intValue());
    step(2);
    assertEquals(8 + i, readMemAt(memPosition));
    step(2);
  }


  @Test
  public void testDjnzSimpleLoop() {
    add(new Ld(f(), c(0), f()));

    add(new Ld(r(B), c(3), f()));
    add(new Ld(r(H), c(7), f()));
    add(new Inc(r(H), f()));
    add(new Ld(mm(c(memPosition)), r(H), f()));
    add(new DJNZ(c(-3), r(B), r(PC)));

    step(5);
    assertEquals(8, readMemAt(memPosition));
    assertDjnzSimpleLoop(1);
    assertDjnzSimpleLoop(2);

    step();
    assertEquals(6, r(PC).read().intValue());

    List executedInstructions = registerTransformerInstructionSpy.getExecutedInstructions();
    executedInstructions.size();
  }

  private void assertDjnzSimpleLoop(int i) {
    step();
    assertEquals(3, r(PC).read().intValue());
    step(2);
    assertEquals(8 + i, readMemAt(memPosition));
  }


  @Test
  public void testDjnzSimpleLoopIncHL() {
    add(new Ld(f(), c(0), f()));

    add(new Ld(r(B), c(3), f()));
    add(new Ld(r(HL), c(7), f()));
    add(new Inc16(r(HL)));
    add(new Ld(iRR(r(HL)), r(B), f()));
    add(new DJNZ(c(-3), r(B), r(PC)));

    step(5);
    assertEquals(3, readMemAt(8));
    assertDjnzSimpleLoopIncHL(1);
    assertDjnzSimpleLoopIncHL(2);

    step();
    assertEquals(6, r(PC).read().intValue());

    List executedInstructions = registerTransformerInstructionSpy.getExecutedInstructions();
    executedInstructions.size();
  }

  private void assertDjnzSimpleLoopIncHL(int i) {
    step();
    assertEquals(3, r(PC).read().intValue());
    step(2);
    assertEquals(3 - i, readMemAt(8 + i));
  }
}

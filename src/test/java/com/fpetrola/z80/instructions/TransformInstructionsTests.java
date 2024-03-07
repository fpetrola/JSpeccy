package com.fpetrola.z80.instructions;

import com.fpetrola.z80.opcodes.references.WordNumber;
import org.junit.Before;
import org.junit.Test;

import static com.fpetrola.z80.registers.RegisterName.*;
import static org.junit.Assert.*;

@SuppressWarnings("ALL")
public class TransformInstructionsTests<T extends WordNumber> extends BaseInstructionLoopTest<T> {

  private int memPosition = 1000;

  @Before
  public void setUp() {
    super.setUp();
    useSecond();
  }

  @Test
  public void testRegisterAssignmentUsingVirtualRegister() {
    add(new Ld(r(H), c(7), f()));
    add(new Ld(mm(c(memPosition)), r(H), f()));

    step();
    assertNotEquals(7, r(H).read().intValue());

    step();
    assertEquals(7, mem().read(WordNumber.createValue(memPosition)).intValue());
  }

  @Test
  public void testRegisterAssignmentUsingVirtualRegisterTwice() {
    add(new Ld(r(H), c(7), f()));
    add(new Ld(r(B), r(H), f()));
    add(new Ld(mm(c(memPosition)), r(B), f()));

    step();
    assertNotEquals(7, r(H).read().intValue());

    step();
    assertNotEquals(7, r(B).read().intValue());

    step();
    assertEquals(7, mem().read(WordNumber.createValue(memPosition)).intValue());
  }

  @Test
  public void testRegisterAssignmentWithInc() {
    add(new Ld(r(H), c(7), f()));
    add(new Inc(r(H), f()));
    add(new Ld(mm(c(memPosition)), r(H), f()));

    step();
    assertNotEquals(7, r(H).read().intValue());

    step();
    assertNotEquals(7, r(H).read().intValue());
    assertNotEquals(8, r(H).read().intValue());

    step();
    assertEquals(8, mem().read(WordNumber.createValue(memPosition)).intValue());
  }

  @Test
  public void testRegisterAssignmentWithRlaInc() {
    add(new Ld(r(A), c(4), f()));
    add(new RLA(r(A), f()));
    add(new Ld(r(B), r(A), f()));
    add(new Inc(r(B), f()));
    add(new RL(r(B), 0, f()));
    add(new Ld(mm(c(memPosition)), r(B), f()));

    step(6);
    assertEquals(20, mem().read(WordNumber.createValue(memPosition)).intValue());
  }

  @Test
  public void testRegisterUsedTwice() {
    add(new Ld(r(A), c(4), f()));
    add(new Ld(r(B), r(A), f()));
    add(new Inc(r(A), f()));
    add(new Ld(r(C), r(A), f()));
    add(new Ld(mm(c(memPosition)), r(B), f()));
    add(new Ld(mm(c(memPosition + 1)), r(C), f()));

    step(4);
    assertNotEquals(4, r(C).read().intValue());
    step();
    assertEquals(4, mem().read(WordNumber.createValue(memPosition)).intValue());
    step();
    assertEquals(5, mem().read(WordNumber.createValue(memPosition + 1)).intValue());
  }

  @Test
  public void test8BitRegisterAssignmentReflectedIn16Bits() {
    add(new Ld(r(H), c(1), f()));
    add(new Ld(r(C), c(7), f()));
    add(new Ld(iiRR(r(HL)), r(C), f()));

    step(3);
    int i = 255 + 256;
    T read = mem().read(WordNumber.createValue(i));
    assertNotNull(read);
    assertEquals(7, read.intValue());
  }
}

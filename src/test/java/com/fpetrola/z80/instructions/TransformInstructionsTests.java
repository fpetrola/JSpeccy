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
    add(new Ld(mm(c(memPosition)), r(A), f()));
    add(new Ld(r(B), r(A), f()));
    add(new Inc(r(B), f()));
    add(new Ld(mm(c(memPosition)), r(B), f()));
    add(new RL(r(B), 0, f()));
    add(new Ld(mm(c(memPosition)), r(B), f()));

    step(3);
    assertEquals(9, mem().read(WordNumber.createValue(memPosition)).intValue());

    step(2);
    step();
    assertEquals(10, mem().read(WordNumber.createValue(memPosition)).intValue());

    step(2);
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
    add(new Ld(r(L), c(255), f()));
    add(new Ld(r(C), c(7), f()));
    add(new Ld(iRR(r(HL)), r(C), f()));

    step(4);
    T read = mem().read(WordNumber.createValue(255 + 256));
    assertNotNull(read);
    assertEquals(7, read.intValue());
  }

  @Test
  public void test8BitRegisterAssignmentReflectedIn16BitsUpdatingL() {
    add(new Ld(r(H), c(1), f()));
    add(new Ld(r(L), c(255), f()));
    add(new Ld(r(C), c(7), f()));
    add(new Ld(iRR(r(HL)), r(C), f()));
    add(new Ld(r(L), c(2), f()));
    add(new Ld(r(C), c(10), f()));
    add(new Ld(iRR(r(HL)), r(C), f()));

    step(4);
    T read = mem().read(WordNumber.createValue(255 + 256));
    assertNotNull(read);
    assertEquals(7, read.intValue());

    step(3);
    T read2 = mem().read(WordNumber.createValue(2 + 256));
    assertNotNull(read2);
    assertEquals(10, read2.intValue());
  }

  @Test
  public void test8BitRegisterAssignmentReflectedIn16BitsIncHL() {
    add(new Ld(r(H), c(1), f()));
    add(new Ld(r(L), c(2), f()));
    add(new Ld(r(C), c(7), f()));
    add(new Ld(iRR(r(HL)), r(C), f()));
    add(new Inc16(r(HL)));
    add(new Ld(r(C), c(10), f()));
    add(new Ld(iRR(r(HL)), r(C), f()));

    step(4);
    T read = mem().read(WordNumber.createValue(256 + 2));
    assertNotNull(read);
    assertEquals(7, read.intValue());

    step();
    step(2);
    T read1 = mem().read(WordNumber.createValue(256 + 2));
    assertEquals(7, read1.intValue());

    T read2 = mem().read(WordNumber.createValue(256 + 2 + 1));
    assertNotNull(read2);
    assertEquals(10, read2.intValue());
  }

  @Test
  public void test8BitRegisterAssignmentReflectedIn16BitsIncHLTwice() {
    add(new Ld(r(H), c(1), f()));
    add(new Ld(r(L), c(2), f()));
    add(new Inc16(r(HL)));
    add(new Inc16(r(HL)));
    add(new Ld(r(C), c(10), f()));
    add(new Ld(iRR(r(HL)), r(C), f()));

    step(6);

    T read2 = mem().read(WordNumber.createValue(256 + 2 + 2));
    assertNotNull(read2);
    assertEquals(10, read2.intValue());
  }

  @Test
  public void test8BitRegisterAssignmentReflectedIn16BitsIncHLAndSetH() {
    add(new Ld(r(H), c(1), f()));
    add(new Ld(r(L), c(4), f()));
    add(new Inc16(r(HL)));
    add(new Ld(r(H), c(2), f()));
    add(new Ld(r(C), c(10), f()));
    add(new Ld(iRR(r(HL)), r(C), f()));
    add(new Ld(r(L), c(8), f()));
    add(new Ld(iRR(r(HL)), r(C), f()));
    add(new Inc16(r(HL)));
    add(new Ld(iRR(r(HL)), r(C), f()));

    step(6);

    T read2 = mem().read(WordNumber.createValue(512 + 4 + 1));
    assertNotNull(read2);
    assertEquals(10, read2.intValue());

    step(2);

    T read3 = mem().read(WordNumber.createValue(512 + 8));
    assertNotNull(read3);
    assertEquals(10, read3.intValue());

    step(2);

    T read4 = mem().read(WordNumber.createValue(512 + 8 + 1));
    assertNotNull(read4);
    assertEquals(10, read4.intValue());
  }

  @Test
  public void test16BitRegisterAssignedToOther() {
    add(new Ld(r(C), c(8), f()));

    add(new Ld(r(D), c(5), f()));
    add(new Ld(r(E), c(5), f()));

    add(new Ld(r(H), c(1), f()));
    add(new Ld(r(L), c(4), f()));

    add(new Ld(r(DE), r(HL), f()));

    add(new Ld(iRR(r(DE)), r(C), f()));

    step(7);

    T read2 = mem().read(WordNumber.createValue(256 + 4 ));
    assertNotNull(read2);
    assertEquals(8, read2.intValue());
  }

}

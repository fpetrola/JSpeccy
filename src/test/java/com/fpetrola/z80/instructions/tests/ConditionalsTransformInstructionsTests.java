package com.fpetrola.z80.instructions.tests;

import com.fpetrola.z80.instructions.*;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.transformations.Virtual8BitsRegister;
import org.junit.Test;

import java.util.List;
import java.util.function.Consumer;

import static com.fpetrola.z80.registers.RegisterName.*;
import static java.util.stream.IntStream.rangeClosed;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@SuppressWarnings("ALL")
public class ConditionalsTransformInstructionsTests<T extends WordNumber> extends TransformInstructionsTests<T> {
  @Test
  public void testIncJPInfiniteLoop() {
    add(new Ld(f(), c(20), f()));
    add(new Ld(r(H), c(7), f()));
    add(new Inc(r(H), f()));
    add(new Ld(mm(c(memPosition)), r(H), f()));
    add(new JP(c(2), t(), r(PC)));

    step(4);
    assertEquals(8, readMemAt(memPosition));
    step(1);
    assertEquals(2, r(PC).read().intValue());
    step(1);
    step(1);
    assertEquals(9, readMemAt(memPosition));
  }


  @Test
  public void testJRNZSimpleLoop() {
    add(new Ld(f(), c(20), f()));
    add(new Ld(r(B), c(3), f()));
    add(new Ld(r(H), c(7), f()));

    add(new Inc(r(H), f()));
    add(new Ld(mm(c(memPosition)), r(H), f()));
    add(new Dec(r(B), f()));
    add(new JR(c(-4), nz(), r(PC)));
    add(new Ld(mm(c(memPosition + 1)), r(H), f()));

    step(3);

    rangeClosed(0, 2).forEach(i -> {
      assertEquals(3, r(PC).read().intValue());
      step();
      step();
      assertEquals(8 + i, readMemAt(memPosition));
      step(2);
    });

    step();
    assertEquals(10, readMemAt(memPosition + 1));
    assertEquals(8, r(PC).read().intValue());


    List executedInstructions = registerTransformerInstructionSpy.getExecutedInstructions();
    executedInstructions.size();

    test3Equals(executedInstructions, 2, 9, 15);
    test3Equals(executedInstructions, 0, 10, 16);
    test3Equals(executedInstructions, 7, 12, 18);

    assertEquals(Ld.class, executedInstructions.get(21).getClass());

//    ByteCodeGenerator byteCodeGenerator = new ByteCodeGenerator((address) -> currentContext.getTransformedInstructionAt(address), 0, (address) -> true, 8, currentContext.pc());
//    byteCodeGenerator.generate(() -> ClassMaker.beginExternal("JSW").public_(), "JSW.class");
  }

  private void test3Equals(List executedInstructions, int index, int index1, int index2) {
    assertEquals(executedInstructions.get(index), executedInstructions.get(index1));
    assertEquals(executedInstructions.get(index1), executedInstructions.get(index2));
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

    rangeClosed(1, 2).forEach(i -> {
      step();
      assertEquals(3, r(PC).read().intValue());
      step();
      step();
      assertEquals(8 + i, readMemAt(memPosition));
    });

    step();
    assertEquals(6, r(PC).read().intValue());

    List executedInstructions = registerTransformerInstructionSpy.getExecutedInstructions();
    executedInstructions.size();
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

    rangeClosed(1, 2).forEach(i -> {
      step();
      assertEquals(3, r(PC).read().intValue());
      step();
      step();
      assertEquals(3 - i, readMemAt(8 + i));
    });

    step();
    assertEquals(6, r(PC).read().intValue());

    List executedInstructions = registerTransformerInstructionSpy.getExecutedInstructions();
    executedInstructions.size();
  }


  @Test
  public void testJRNZSimpleLoopJumpingToBegining() {
    add(new Ld(f(), c(20), f()));
    add(new Ld(r(B), c(3), f()));
    add(new Ld(r(H), c(7), f()));

    add(new Inc(r(H), f()));
    add(new Ld(mm(c(memPosition)), r(H), f()));
    add(new Dec(r(B), f()));
    add(new JR(c(-4), nz(), r(PC)));

    add(new JP(c(1), t(), r(PC)));

    step(3);

    Runnable assertLoop = () -> rangeClosed(0, 2).forEach(i -> {
      assertEquals(3, r(PC).read().intValue());
      step(2);
      assertEquals(8 + i, readMemAt(memPosition));
      step();
      step();
    });
    assertLoop.run();

    step();
    assertEquals(1, r(PC).read().intValue());
    step(2);

    assertLoop.run();

    assertEquals(7, r(PC).read().intValue());
    step();
    assertEquals(1, r(PC).read().intValue());
    step();

    List executedInstructions = registerTransformerInstructionSpy.getExecutedInstructions();
    executedInstructions.size();
    assertEquals(2, r(PC).read().intValue());
  }


  @Test
  public void stackOverflowBug() {
    setUpMemory();
    add(new Ld(r(F), c(0), f()));
    add(new Ld(r(B), c(3), f()));
    add(new Ld(r(D), c(4), f()));
    add(new Ld(r(E), c(8), f()));
    add(new Ld(r(A), c(0), f()));

    add(new Add(r(D), r(A), f()));
    add(new Add(r(E), r(A), f()));
    add(new Ld(iRR(r(E)), r(A), f()));
    add(new Inc(r(D), f()));
    add(new DJNZ(c(-5), r(B), r(PC)));

    step(10);
    step(2);
    step(1);

    List<Instruction<T>> executedInstructions = registerTransformerInstructionSpy.getExecutedInstructions();
    executedInstructions.size();
  }


  @Test
  public void bug2() {
    setUpMemory();
    add(new Ld(r(F), c(0), f()));
    add(new Ld(r(D), c(2), f()));
    add(new Ld(r(C), c(0), f()));
    add(new Ld(r(A), c(7), f()));

    add(new Ld(r(B), c(2), f()));

    add(new Ld(r(C), r(B), f()));
    add(new Add(r(A), r(C), f()));
    add(new Inc(r(A), f()));
    add(new DJNZ(c(-4), r(B), r(PC)));
    add(new Dec(r(D), f()));
    add(new JR(c(-7), nz(), r(PC)));
    add(new Ld(r(H), r(A), f()));
    add(new Ld(mm(c(memPosition)), r(H), f()));

    step(5);

    Runnable assertExternalLoop = () -> {
      rangeClosed(1, 2).forEach(i -> {
        assertEquals(5, r(PC).read().intValue());
        step(4);
      });
      assertEquals(9, r(PC).read().intValue());
      step(2);
    };

    assertExternalLoop.run();
    assertEquals(4, r(PC).read().intValue());
    step();

    assertExternalLoop.run();

    assertEquals(11, r(PC).read().intValue());
    step(2);

    assertEquals(17, readMemAt(1000));

    List<Instruction<T>> executedInstructions = registerTransformerInstructionSpy.getExecutedInstructions();
    executedInstructions.size();

    Virtual8BitsRegister target = (Virtual8BitsRegister) ((Ld) executedInstructions.get(5)).getTarget();
    assertNull(target.lastVersionRead);
  }
}

package com.fpetrola.z80.instructions.tests;

import com.fpetrola.z80.instructions.*;
import com.fpetrola.z80.opcodes.references.WordNumber;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.IntFunction;
import java.util.function.Supplier;

import static com.fpetrola.z80.registers.RegisterName.*;
import static java.util.stream.IntStream.rangeClosed;
import static org.junit.Assert.assertEquals;

@SuppressWarnings("ALL")
public class InlineRegisterTransformInstructionsTests<T extends WordNumber> extends TransformInstructionsTests<T> {
  @Test
  public void testJRNZSimpleLoop() {
    add(new Ld(r(F), c(20), f()));
    add(new Ld(r(B), c(3), f()));
    add(new Ld(r(A), c(2), f()));
    add(new Ld(r(H), c(7), f()));

    add(new Inc(r(H), f()));
    add(new Ld(mm(c(memPosition)), r(H), f()));
    add(new Add(r(A), r(H), f()));
    add(new Ld(mm(c(memPosition + 1)), r(A), f()));
    add(new Dec(r(B), f()));
    add(new JR(c(-6), nz(), r(PC)));
    add(new Ld(mm(c(memPosition + 100)), r(H), f()));

    step(4);

    rangeClosed(0, 2).forEach(i -> {
      assertEquals(4, r(PC).read().intValue());
      step();
      step();
      assertEquals(8 + i, readMemAt(memPosition));
      step();
      step();
      assertEquals(rangeClosed(0, i).map(i2 -> 8 + i2).sum() + 2, readMemAt(memPosition + 1));
      step(2);
    });

    step();
    assertEquals(29, readMemAt(memPosition + 1));
    assertEquals(11, r(PC).read().intValue());


    List executedInstructions = registerTransformerInstructionSpy.getExecutedInstructions();
    executedInstructions.size();
  }

}

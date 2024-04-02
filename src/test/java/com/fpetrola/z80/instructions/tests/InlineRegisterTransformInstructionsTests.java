package com.fpetrola.z80.instructions.tests;

import com.fpetrola.z80.blocks.ByteCodeGenerator;
import com.fpetrola.z80.instructions.*;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import org.cojen.maker.ClassMaker;
import org.junit.Test;

import java.util.List;
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
    add(new Ld(r(A), c(1), f()));
    add(new Ld(r(D), c(2), f()));
    add(new Ld(r(H), c(7), f()));

    add(new Inc(r(H), f()));
    add(new Ld(mm(c(memPosition)), r(H), f()));
    add(new Add(r(D), r(H), f()));
    add(new Xor(r(A), r(D), f()));
    add(new Add(r(A), r(B), f()));
    add(new Ld(mm(c(memPosition + 2)), r(A), f()));
    add(new Ld(mm(c(memPosition + 1)), r(D), f()));
    add(new Dec(r(B), f()));
    add(new JR(c(-9), nz(), r(PC)));
    add(new Ld(mm(c(memPosition + 100)), r(H), f()));

    step(5);

    int[] aValue = new int[]{1};
    rangeClosed(0, 2).forEach(i -> {
      assertEquals(5, r(PC).read().intValue());
      step();
      step();
      assertEquals(8 + i, readMemAt(memPosition));
      step();
      step();
      step();
      step();
      int dValue = rangeClosed(0, i).map(i2 -> 8 + i2).sum() + 2;
      aValue[0] = (dValue ^ aValue[0]) + 3 - i;
      assertEquals(aValue[0], readMemAt(memPosition + 2));
      step();
      assertEquals(dValue, readMemAt(memPosition + 1));

      step(2);
    });

    step();
    assertEquals(29, readMemAt(memPosition + 1));
    assertEquals(15, r(PC).read().intValue());


    List<Instruction<T>> executedInstructions = registerTransformerInstructionSpy.getExecutedInstructions();
    executedInstructions.size();

    ByteCodeGenerator byteCodeGenerator = new ByteCodeGenerator((address) -> currentContext.getTransformedInstructionAt(address), 0, (address) -> true, 15, currentContext.pc());
    byteCodeGenerator.generate(() -> ClassMaker.beginExternal("JSW").public_(), "JSW.class");
//
//    ByteCodeGeneratorVisitorLevel1 visitor = new ByteCodeGeneratorVisitorLevel1();
//    executedInstructions.forEach(i -> i.accept(visitor));
  }

  @Test
  public void createPlainExecution() {
    setUpMemory();
    add(new Ld(r(F), c(0), f()));
    add(new Ld(r(B), c(3), f()));
    add(new Ld(r(DE), c(520), f()));
    add(new Ld(r(A), c(0), f()));

    add(new Ld(r(H), c(7), f()));
    add(new Ld(r(L), r(A), f()));
    add(new Add(r(D), r(A), f()));
    add(new Add(r(E), r(A), f()));
    add(new Add(r(H), r(H), f()));

    add(new Ld(r(A), iRR(r(B)), f()));
    add(new Inc(r(A), f()));
    add(new Ld(mm(c(memPosition + 2)), r(A), f()));
    add(new Ld(iRR(r(E)), r(B), f()));
    add(new Inc(r(H), f()));
    add(new Inc(r(D), f()));
    add(new Ld(r(F), c(1), f()));
    add(new DJNZ(c(-12), r(B), r(PC)));
    //add(new Ret(t(), r(SP), mem(), r(PC)));

    step(39);

    List<Instruction<T>> executedInstructions = registerTransformerInstructionSpy.getExecutedInstructions();
    executedInstructions.size();

    Register<T> pc = currentContext.pc();
    int endAddress = 17;
    ByteCodeGenerator byteCodeGenerator2 = new ByteCodeGenerator((address) -> currentContext.getTransformedInstructionAt(address), 0, (address) -> true, endAddress, pc);
    byteCodeGenerator2.generate(() -> ClassMaker.beginExternal("JSW").public_(), "JSW.class");

    System.out.println("-----------------------------------");
    ByteCodeGenerator byteCodeGenerator = new ByteCodeGenerator((address) -> currentContext.getTransformedInstructionAt(address), 0, (address) -> true, endAddress, pc);
    ClassMakerForTest classMakerForTest = new ClassMakerForTest();
    Supplier<ClassMaker> classMakerSupplier = () -> classMakerForTest;
    byteCodeGenerator.generate(classMakerSupplier, "JSW1.class");
    List<FieldMakerForTest> fieldMakers = classMakerForTest.fieldMakers;

  }

  @Test
  public void createPlainExecution2() {
    setUpMemory();
    add(new Ld(r(F), c(0), f()));
    add(new Ld(r(D), c(100), f()));
    add(new Ld(r(A), iRR(r(D)), f()));
    add(new Inc(r(A), f()));
    add(new Ld(r(B), r(A), f()));
    add(new Ld(mm(c(memPosition + 2)), r(A), f()));
    add(new Ld(mm(c(memPosition + 3)), r(B), f()));

    int endAddress = 7;
    step(endAddress);

    List<Instruction<T>> executedInstructions = registerTransformerInstructionSpy.getExecutedInstructions();
    executedInstructions.size();

    Register<T> pc = currentContext.pc();
    ByteCodeGenerator byteCodeGenerator2 = new ByteCodeGenerator((address) -> currentContext.getTransformedInstructionAt(address), 0, (address) -> true, endAddress, pc);
    byteCodeGenerator2.generate(() -> ClassMaker.beginExternal("JSW").public_(), "JSW.class");

    System.out.println("-----------------------------------");
    ByteCodeGenerator byteCodeGenerator = new ByteCodeGenerator((address) -> currentContext.getTransformedInstructionAt(address), 0, (address) -> true, endAddress, pc);
    ClassMakerForTest classMakerForTest = new ClassMakerForTest();
    Supplier<ClassMaker> classMakerSupplier = () -> classMakerForTest;
    byteCodeGenerator.generate(classMakerSupplier, "JSW1.class");
    List<FieldMakerForTest> fieldMakers = classMakerForTest.fieldMakers;

  }

}

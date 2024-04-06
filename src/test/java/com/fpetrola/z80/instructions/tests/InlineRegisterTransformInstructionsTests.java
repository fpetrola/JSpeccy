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

import static com.fpetrola.z80.opcodes.references.WordNumber.createValue;
import static com.fpetrola.z80.registers.RegisterName.*;
import static java.util.stream.IntStream.rangeClosed;
import static org.junit.Assert.assertEquals;

@SuppressWarnings("ALL")

public class InlineRegisterTransformInstructionsTests<T extends WordNumber> extends TransformInstructionsTests<T> {
  private void finishTest(int endAddress) {
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
    add(new JR(c(-6), nz(), r(PC)));
    add(new Ld(mm(c(memPosition + 100)), r(H), f()));

    step(20);


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
    add(new Ld(r(C), c(0), f()));

    add(new Ld(r(H), c(7), f()));
    add(new Ld(r(L), r(A), f()));
    add(new Add(r(D), r(A), f()));
    add(new Add(r(E), r(A), f()));

    add(new Add(r(C), r(B), f()));
    add(new Add(r(C), r(B), f()));
    add(new Add(r(C), r(B), f()));
    add(new Ld(r(A), iRR(r(B)), f()));
    add(new Inc(r(A), f()));
    add(new Ld(mm(c(memPosition + 2)), r(A), f()));
    add(new Ld(iRR(r(E)), r(D), f()));
    add(new Inc(r(H), f()));
    add(new Inc(r(D), f()));
    add(new Ld(r(F), c(1), f()));
    add(new DJNZ(c(-8), r(B), r(PC)));
    //add(new Ret(t(), r(SP), mem(), r(PC)));

    step(30);
    int endAddress = addedInstructions;
    finishTest(endAddress);
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

    finishTest(endAddress);

  }


  @Test
  public void incrementDBeforeDJNZ() {
    setUpMemory();
    add(new Ld(r(F), c(0), f()));
    add(new Ld(r(B), c(3), f()));
    add(new Ld(r(D), c(4), f()));

    add(new Ld(iRR(r(B)), r(D), f()));
    add(new Inc(r(D), f()));
    add(new DJNZ(c(-3), r(B), r(PC)));

    step(10);

    int endAddress = addedInstructions;

    finishTest(endAddress);

  }

  @Test
  public void bug2() {
    setUpMemory();
    add(new Ld(r(F), c(0), f()));
    add(new Ld(r(B), c(3), f()));
    add(new Ld(r(C), c(0), f()));
    add(new Ld(r(A), c(10), f()));

    add(new Ld(r(C), r(B), f()));
    add(new Ld(r(A), r(C), f()));
    add(new Inc(r(A), f()));
    add(new DJNZ(c(-4), r(B), r(PC)));
    add(new Ld(r(D), r(C), f()));

    step(17);
    int endAddress = addedInstructions;
    finishTest(endAddress);
  }

  @Test
  public void firstLoop() {
    setUpMemory();
    add(new Ld(r(F), c(0), f()));
    add(new Ld(r(B), c(3), f()));
    add(new Ld(r(C), c(0), f()));
    add(new Ld(r(A), c(4), f()));

    add(new Ld(r(DE), c(520), f()));
    add(new Ld(r(H), c(7), f()));
    add(new Ld(r(L), r(A), f()));

    add(new Add16(r(HL), r(HL), f()));
    add(new Add16(r(HL), r(HL), f()));
    add(new Add16(r(HL), r(HL), f()));
    add(new Ld(r(B), c(3), f()));

    add(new Ld(r(A), iRR(r(HL)), f()));
    add(new Ld(iRR(r(DE)), r(A), f()));
    add(new Inc16(r(HL)));
    add(new Inc(r(D), f()));
    add(new DJNZ(c(-5), r(B), r(PC)));
//    add(new Inc(r(A), f()));
//    add(new Ld(iRR(r(HL)), r(A), f()));
//    add(new JP(c(2), t(), r(PC)));


    step(26);
    int endAddress = addedInstructions;
    finishTest(endAddress);
  }

}

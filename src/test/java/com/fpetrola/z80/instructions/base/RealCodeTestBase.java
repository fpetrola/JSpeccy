package com.fpetrola.z80.instructions.base;

import com.fpetrola.z80.bytecode.impl.ByteCodeGenerator;
import com.fpetrola.z80.cpu.RandomAccessInstructionFetcher;
import com.fpetrola.z80.instructions.tests.BytecodeGenerationTransformInstructionsTests;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import org.cojen.maker.ClassMaker;

import java.util.List;

public abstract class RealCodeTestBase<T extends WordNumber> extends RealCodeTransformationsInstructionsTestsBase<T> {
  protected int endAddress;
  protected int startAddress;
  protected int firstAddress;
  private String classFile;

  protected void stepUntilComplete() {
    Register<T> pc = state.getPc();
    pc.write(WordNumber.createValue(firstAddress));

    while (!executionsAreComplete()) {
      pc.write(WordNumber.createValue(firstAddress));
      int i = pc.read().intValue();
      do {
        step();
        i = pc.read().intValue();
      } while (i >= startAddress && i <= endAddress);
    }

    return;

  }

  private boolean executionsAreComplete() {
    return (!executions.isEmpty()) && executions.entrySet().stream().allMatch(e -> e.getValue() > 1);
  }

  private byte[] finishTest(int startAddress, int endAddress) {
    List<Instruction<T>> executedInstructions = getRegisterTransformerInstructionSpy().getExecutedInstructions();
    executedInstructions.size();

    Register<T> pc = state.getPc();
    RandomAccessInstructionFetcher instructionFetcher = (address) -> transformerInstructionExecutor.clonedInstructions.get(address);
    ByteCodeGenerator byteCodeGenerator2 = new ByteCodeGenerator(instructionFetcher, startAddress, (address) -> true, endAddress, pc);
    classFile = "JSW.class";
    byte[] bytecode = byteCodeGenerator2.generate(() -> ClassMaker.beginExternal("JSW").public_(), classFile);

//    System.out.println("-----------------------------------");
//    ByteCodeGenerator byteCodeGenerator = new ByteCodeGenerator(instructionFetcher, startAddress, (address) -> true, endAddress, pc);
//    ClassMakerForTest classMakerForTest = new ClassMakerForTest();
//    Supplier<ClassMaker> classMakerSupplier = () -> classMakerForTest;
//    byteCodeGenerator.generate(classMakerSupplier, "JSW1.class");
//    List<FieldMakerForTest> fieldMakers = classMakerForTest.fieldMakers;
    return bytecode;
  }

  protected String generateAndDecompile() {
    return BytecodeGenerationTransformInstructionsTests.decompile(finishTest(startAddress, endAddress), classFile);
  }
}

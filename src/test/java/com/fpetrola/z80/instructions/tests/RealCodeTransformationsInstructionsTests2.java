package com.fpetrola.z80.instructions.tests;

import com.fpetrola.z80.blocks.ByteCodeGenerator;
import com.fpetrola.z80.cpu.RandomAccessInstructionFetcher;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import org.cojen.maker.ClassMaker;
import org.junit.Test;

import java.util.List;
import java.util.function.Supplier;

import static org.junit.Assert.assertEquals;

@SuppressWarnings("ALL")
public class RealCodeTransformationsInstructionsTests2<T extends WordNumber> extends RealCodeTransformationsInstructionsTests<T> {

  private int endAddress;
  private int startAddress;

  @Test
  public void testJSW1() {

//    value4.setRecentFile0("/home/fernando/detodo/desarrollo/m/zx/zx/jsw.z80");
//    value4.setRecentFile1("/home/fernando/detodo/desarrollo/m/zx/zx/emlyn.z80");
//    value4.setRecentFile2("/home/fernando/detodo/desarrollo/m/zx/zx/tge.z80");
//
    startAddress = 37056;
    endAddress = 37309;

    setUpMemory("/home/fernando/detodo/desarrollo/m/zx/zx/jsw.z80");
    Register<T> pc = state.getPc();
    pc.write(WordNumber.createValue(startAddress));

    int i = endAddress - startAddress;
    stepUntilComplete();
//    assertEquals(9, readMemAt(memPosition));

    finishTest(startAddress, endAddress);
  }

  private void stepUntilComplete() {
    Register<T> pc = state.getPc();

    while (!executionsAreComplete()) {
      pc.write(WordNumber.createValue(startAddress));
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

  private void finishTest(int startAddress, int endAddress) {
    List<Instruction<T>> executedInstructions = getRegisterTransformerInstructionSpy().getExecutedInstructions();
    executedInstructions.size();

    Register<T> pc = state.getPc();
    RandomAccessInstructionFetcher instructionFetcher = (address) -> transformerInstructionExecutor.clonedInstructions.get(address);
    ByteCodeGenerator byteCodeGenerator2 = new ByteCodeGenerator(instructionFetcher, startAddress, (address) -> true, endAddress, pc);
    byteCodeGenerator2.generate(() -> ClassMaker.beginExternal("JSW").public_(), "JSW.class");

    System.out.println("-----------------------------------");
    ByteCodeGenerator byteCodeGenerator = new ByteCodeGenerator(instructionFetcher, startAddress, (address) -> true, endAddress, pc);
    ClassMakerForTest classMakerForTest = new ClassMakerForTest();
    Supplier<ClassMaker> classMakerSupplier = () -> classMakerForTest;
    byteCodeGenerator.generate(classMakerSupplier, "JSW1.class");
    List<FieldMakerForTest> fieldMakers = classMakerForTest.fieldMakers;
  }
}

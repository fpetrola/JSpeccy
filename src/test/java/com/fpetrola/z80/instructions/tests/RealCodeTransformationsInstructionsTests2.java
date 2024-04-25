package com.fpetrola.z80.instructions.tests;

import com.fpetrola.z80.blocks.ByteCodeGenerator;
import com.fpetrola.z80.cpu.RandomAccessInstructionFetcher;
import com.fpetrola.z80.instructions.*;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import org.cojen.maker.ClassMaker;
import org.junit.Assert;
import org.junit.Test;
import static com.fpetrola.z80.registers.RegisterName.*;

import java.util.List;

@SuppressWarnings("ALL")
public class RealCodeTransformationsInstructionsTests2<T extends WordNumber> extends RealCodeTransformationsInstructionsTests<T> {
  private int endAddress;
  private int startAddress;
  private String classFile;

  @Test
  public void testJSW1() {
//    value4.setRecentFile0("/home/fernando/detodo/desarrollo/m/zx/zx/jsw.z80");
//    value4.setRecentFile1("/home/fernando/detodo/desarrollo/m/zx/zx/emlyn.z80");
//    value4.setRecentFile2("/home/fernando/detodo/desarrollo/m/zx/zx/tge.z80");
//
    startAddress = 37056;
    endAddress = 37307;

    setUpMemory("/home/fernando/detodo/desarrollo/m/zx/zx/jsw.z80");

    stepUntilComplete();

    Assert.assertEquals("""
public class JSW {
   public int[] memory;

   public void $90C0() {
      // $FF: Couldn't be decompiled
   }
}
""", generateAndDecompile());
  }

  private void stepUntilComplete() {
    Register<T> pc = state.getPc();
    pc.write(WordNumber.createValue(startAddress));

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

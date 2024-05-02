package com.fpetrola.z80.instructions.tests;

import com.fpetrola.z80.bytecode.impl.ByteCodeGenerator;
import com.fpetrola.z80.cpu.RandomAccessInstructionFetcher;
import com.fpetrola.z80.instructions.base.BytecodeProviderForTest;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.instructions.base.RegisterTransformerInstructionSpy;
import com.fpetrola.z80.instructions.base.ResultSaverForTest;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import org.cojen.maker.ClassMaker;
import org.jetbrains.java.decompiler.main.Fernflower;
import org.jetbrains.java.decompiler.main.decompiler.PrintStreamLogger;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;

public interface BytecodeGenerationTest {
  default <T extends WordNumber> String getDecompiledSource(int startAddress, int endAddress, Register<T> pc1, RandomAccessInstructionFetcher randomAccessInstructionFetcher, RegisterTransformerInstructionSpy registerTransformerInstructionSpy1, String classFile1) {
    List<Instruction<T>> executedInstructions = registerTransformerInstructionSpy1.getExecutedInstructions();
    executedInstructions.forEach(i -> System.out.println(i));

    byte[] bytecode = new ByteCodeGenerator(randomAccessInstructionFetcher, startAddress, (_) -> true, endAddress, pc1)
        .generate(() -> ClassMaker.beginExternal("JSW").public_(), classFile1);

//    System.out.println("-----------------------------------");
//    ByteCodeGenerator byteCodeGenerator = new ByteCodeGenerator((address) -> currentContext.getTransformedInstructionAt(address), 0, (address) -> true, endAddress, pc);
//    ClassMakerForTest classMakerForTest = new ClassMakerForTest();
//    Supplier<ClassMaker> classMakerSupplier = () -> classMakerForTest;
//    byteCodeGenerator.generate(classMakerSupplier, "JSW1.class");
//    List<FieldMakerForTest> fieldMakers = classMakerForTest.fieldMakers;
    return decompile(bytecode, classFile1);
  }

  default String decompile(byte[] bytecode, String classFile) {
    ResultSaverForTest saver = new ResultSaverForTest();
    HashMap<String, Object> customProperties = new HashMap<>();
    customProperties.put("asc", "1");
    Fernflower fernflower = new Fernflower(new BytecodeProviderForTest(bytecode), saver, customProperties, new PrintStreamLogger(new PrintStream(new ByteArrayOutputStream())));
    fernflower.addSource(new File(classFile));
    fernflower.decompileContext();
    return saver.getContent();
  }

  String generateAndDecompile();
}

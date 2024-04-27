package com.fpetrola.z80.instructions.tests;

import com.fpetrola.z80.blocks.ByteCodeGenerator;
import com.fpetrola.z80.instructions.base.Instruction;
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
import java.util.Map;

public abstract class BytecodeGenerationTransformInstructionsTests<T extends WordNumber> extends TransformInstructionsTests<T> {
  protected String classFile = "JSW.class";

  protected String generateAndDecompile() {
    return finishTest(addedInstructions);
  }

  protected String finishTest(int endAddress) {
    List<Instruction<T>> executedInstructions = getRegisterTransformerInstructionSpy().getExecutedInstructions();
    executedInstructions.size();

    Register<T> pc = currentContext.pc();
    ByteCodeGenerator byteCodeGenerator2 = new ByteCodeGenerator((address) -> currentContext.getTransformedInstructionAt(address), 0, (address) -> true, endAddress, pc);
    byte[] bytecode = byteCodeGenerator2.generate(() -> ClassMaker.beginExternal("JSW").public_(), classFile);

    System.out.println("-----------------------------------");
//    ByteCodeGenerator byteCodeGenerator = new ByteCodeGenerator((address) -> currentContext.getTransformedInstructionAt(address), 0, (address) -> true, endAddress, pc);
//    ClassMakerForTest classMakerForTest = new ClassMakerForTest();
//    Supplier<ClassMaker> classMakerSupplier = () -> classMakerForTest;
//    byteCodeGenerator.generate(classMakerSupplier, "JSW1.class");
//    List<FieldMakerForTest> fieldMakers = classMakerForTest.fieldMakers;

    return decompile(bytecode, classFile);
  }

  public static String decompile(byte[] bytecode, String classFile) {
    ResultSaverForTest saver = new ResultSaverForTest();
    HashMap<String, Object> customProperties = new HashMap<>();
    customProperties.put("asc", "1");
    Fernflower fernflower = new Fernflower(new BytecodeProviderForTest(bytecode), saver, customProperties, new PrintStreamLogger(new PrintStream(new ByteArrayOutputStream())));
    fernflower.addSource(new File(classFile));

    fernflower.decompileContext();

    String content = saver.getContent();
    return content;
  }
}

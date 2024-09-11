package com.fpetrola.z80.instructions.base;

import com.fpetrola.z80.blocks.Block;
import com.fpetrola.z80.blocks.CodeBlockType;
import com.fpetrola.z80.blocks.ranges.RangeHandler;
import com.fpetrola.z80.bytecode.impl.ByteCodeGenerator;
import com.fpetrola.z80.cpu.RandomAccessInstructionFetcher;
import com.fpetrola.z80.minizx.SpectrumApplication;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.transformations.RegisterTransformerInstructionSpy;
import org.apache.commons.io.FileUtils;
import org.cojen.maker.ClassMaker;
import org.cojen.maker.MethodMaker;
import org.jetbrains.java.decompiler.main.Fernflower;
import org.jetbrains.java.decompiler.main.decompiler.PrintStreamLogger;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public interface BytecodeGenerationTest {
  default <T extends WordNumber> String getDecompiledSource(int startAddress, int endAddress, Register<T> pc1, RandomAccessInstructionFetcher randomAccessInstructionFetcher, RegisterTransformerInstructionSpy registerTransformerInstructionSpy1, String classFile1) {
    try {
      List<Instruction<T>> executedInstructions = registerTransformerInstructionSpy1.getExecutedInstructions();
//    executedInstructions.forEach(i -> System.out.println(i));

      ClassMaker classMaker = ClassMaker.beginExternal("JSW").public_();
      classMaker.extend(SpectrumApplication.class);
      HashMap<String, MethodMaker> methods = new HashMap<>();

      List<Block> blocks = RegisterTransformerInstructionSpy.routineFinder.blocksManager.getBlocks().stream()
          .filter(block -> block.getBlockType() instanceof CodeBlockType)
          .sorted(Comparator.comparingInt(b -> b.getRangeHandler().getStartAddress()))
          .toList();

      blocks.forEach(block -> {
        ByteCodeGenerator.findMethod(ByteCodeGenerator.createLabelName(block.getRangeHandler().getStartAddress()), methods, classMaker);
      });

//      blocks= new ArrayList<>();
//      blocks.add(new DefaultBlock(38026, 48621, "", RegisterTransformerInstructionSpy.routineFinder.blocksManager));

      blocks.forEach(block -> {
        RangeHandler rangeHandler = block.getRangeHandler();
        System.out.println(block);
        new ByteCodeGenerator(classMaker, rangeHandler.getStartAddress(), rangeHandler.getEndAddress(), randomAccessInstructionFetcher, (_) -> true, pc1, methods).generate();
      });

      byte[] bytecode = classMaker.finishBytes();
      FileUtils.writeByteArrayToFile(new File("target/" + classFile1), bytecode);

//    System.out.println("-----------------------------------");
//    ByteCodeGenerator byteCodeGenerator = new ByteCodeGenerator((address) -> currentContext.getTransformedInstructionAt(address), 0, (address) -> true, endAddress, pc);
//    ClassMakerForTest classMakerForTest = new ClassMakerForTest();
//    Supplier<ClassMaker> classMakerSupplier = () -> classMakerForTest;
//    byteCodeGenerator.generate(classMakerSupplier, "JSW1.class");
//    List<FieldMakerForTest> fieldMakers = classMakerForTest.fieldMakers;
      return decompile(bytecode, classFile1);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  default String decompile(byte[] bytecode, String classFile) {
    ResultSaverForTest saver = new ResultSaverForTest();
    HashMap<String, Object> customProperties = new HashMap<>();
    customProperties.put("lit", "1");
    customProperties.put("asc", "1");
    Fernflower fernflower = new Fernflower(new BytecodeProviderForTest(bytecode), saver, customProperties, new PrintStreamLogger(new PrintStream(new ByteArrayOutputStream())));
    fernflower.addSource(new File(classFile));
    fernflower.decompileContext();
    return saver.getContent();
  }

  String generateAndDecompile();
}

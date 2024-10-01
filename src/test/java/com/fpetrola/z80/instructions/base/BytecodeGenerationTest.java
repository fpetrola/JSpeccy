package com.fpetrola.z80.instructions.base;

import com.fpetrola.z80.bytecode.impl.ByteCodeGenerator;
import com.fpetrola.z80.cpu.RandomAccessInstructionFetcher;
import com.fpetrola.z80.minizx.MiniZX;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.routines.Routine;
import com.fpetrola.z80.routines.RoutineFinder;
import com.fpetrola.z80.transformations.RegisterTransformerInstructionSpy;
import org.apache.commons.io.FileUtils;
import org.cojen.maker.ClassMaker;
import org.cojen.maker.MethodMaker;
import org.cojen.maker.Variable;
import org.jetbrains.java.decompiler.main.Fernflower;
import org.jetbrains.java.decompiler.main.decompiler.PrintStreamLogger;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;

import static java.util.Comparator.comparingInt;

public interface BytecodeGenerationTest {
  default <T extends WordNumber> String getDecompiledSource(int startAddress, int endAddress, Register<?> pc1, RandomAccessInstructionFetcher randomAccessInstructionFetcher, RegisterTransformerInstructionSpy registerTransformerInstructionSpy1, String className, String memoryInBase64) {
    try {
      ClassMaker classMaker1 = createClass(pc1, randomAccessInstructionFetcher, className, memoryInBase64);
      byte[] bytecode = classMaker1.finishBytes();
      String classFile = className + ".class";
      FileUtils.writeByteArrayToFile(new File("target/" + classFile), bytecode);
      return decompile(bytecode, classFile);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private ClassMaker createClass(Register<?> pc1, RandomAccessInstructionFetcher randomAccessInstructionFetcher, String className, String memoryInBase64) {
    ClassMaker classMaker = ClassMaker.beginExternal(className).public_();
    classMaker.extend(MiniZX.class);

    MethodMaker methodMaker = classMaker.addConstructor().public_();
    methodMaker.invokeSuperConstructor();

//      createMainMethod(classMaker);

    MethodMaker getProgramBytesMaker = classMaker.addMethod(String.class, "getProgramBytes").public_();

    getProgramBytesMaker.return_(memoryInBase64);

    HashMap<String, MethodMaker> methods = new HashMap<>();

    List<Routine> routines = RoutineFinder.routineManager.getRoutines().stream()
        .sorted(comparingInt(Routine::getStartAddress))
        .toList();

    routines.forEach(routine -> {
      ByteCodeGenerator.findMethod(ByteCodeGenerator.createLabelName(routine.getStartAddress()), methods, classMaker);
    });

    routines.forEach(routine -> {
      System.out.println(routine);
      new ByteCodeGenerator(classMaker, randomAccessInstructionFetcher, (_) -> true, pc1, methods, routine).generate();
    });
    return classMaker;
  }

  private void createMainMethod(ClassMaker classMaker) {
    MethodMaker mainMethod = classMaker.addMethod(void.class, "main", String[].class);
    mainMethod.public_();
    Variable jetSetWilly = mainMethod.new_("JetSetWilly");
    jetSetWilly.invoke("$34762");
    mainMethod.return_();
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

  String generateAndDecompile(String base64Memory);

  default void translateToJava(Register<?> pc1, RandomAccessInstructionFetcher randomAccessInstructionFetcher, String className, String memoryInBase64) {
    try {
      ClassMaker classMaker = createClass(pc1, randomAccessInstructionFetcher, className, memoryInBase64);
      Class<?> finish = classMaker.finish();
      Object o = finish.getConstructors()[0].newInstance();
      o.getClass().getMethod("$34762").invoke(o);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}

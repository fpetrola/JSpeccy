package com.fpetrola.z80.bytecode;

import com.fpetrola.z80.bytecode.decompile.SimpleBytecodeProvider;
import com.fpetrola.z80.bytecode.decompile.SimpleResultSaverFor;
import com.fpetrola.z80.bytecode.impl.ByteCodeGenerator;
import com.fpetrola.z80.cpu.RandomAccessInstructionFetcher;
import com.fpetrola.z80.minizx.MiniZX;
import com.fpetrola.z80.minizx.SpectrumApplication;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.routines.Routine;
import org.apache.commons.io.FileUtils;
import org.cojen.maker.ClassMaker;
import org.cojen.maker.MethodMaker;
import org.jetbrains.java.decompiler.main.Fernflower;
import org.jetbrains.java.decompiler.main.decompiler.PrintStreamLogger;
import org.jetbrains.java.decompiler.util.InterpreterUtil;
import soot.Main;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;

public interface BytecodeGeneration {
  default <T extends WordNumber> String getDecompiledSource(Register<?> pc1, RandomAccessInstructionFetcher randomAccessInstructionFetcher, String className, String memoryInBase64, List<Routine> routines) {
    try {
      ClassMaker classMaker1 = createClass(pc1, randomAccessInstructionFetcher, className, memoryInBase64, routines);
      byte[] bytecode = classMaker1.finishBytes();
      String classFile = className + ".class";
      File file = new File("target2/" + classFile);
      FileUtils.writeByteArrayToFile(file, bytecode);
      extracted(className);
      return decompile(null, new File("sootOutput/" + classFile));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private void extracted(String className) {
    String[] args = {"-via-shimple", "-allow-phantom-refs", "-cp", "./rt.jar:target/classes:target2", "-O", "" + className};
    Main.main(args);
  }

  private ClassMaker createClass(Register<?> pc1, RandomAccessInstructionFetcher randomAccessInstructionFetcher, String className, String memoryInBase64, List<Routine> routines1) {
    boolean translation = !memoryInBase64.isBlank();
    ClassMaker classMaker = ClassMaker.beginExternal(className).public_();
    if (translation)
      classMaker.extend(MiniZX.class);
    else
      classMaker.extend(SpectrumApplication.class);

    MethodMaker methodMaker = classMaker.addConstructor().public_();
    methodMaker.invokeSuperConstructor();

    createMainMethod(classMaker);


    if (translation) {
      MethodMaker getProgramBytesMaker = classMaker.addMethod(String.class, "getProgramBytes").public_();
      getProgramBytesMaker.return_(memoryInBase64);
    }
    HashMap<String, MethodMaker> methods = new HashMap<>();

    routines1.forEach(routine -> {
      ByteCodeGenerator.findMethod(ByteCodeGenerator.createLabelName(routine.getStartAddress()), methods, classMaker);
    });

    routines1.forEach(routine -> {
      boolean syncEnabled = false;
      new ByteCodeGenerator(classMaker, randomAccessInstructionFetcher, (_) -> true, pc1, methods, routine, syncEnabled).generate();
    });
    return classMaker;
  }

  private void createMainMethod(ClassMaker classMaker) {
    MethodMaker mainMethod = classMaker.addMethod(void.class, "main", String[].class);
    mainMethod.public_();
//    Variable jetSetWilly = mainMethod.new_("JetSetWilly");
//    jetSetWilly.invoke("$34762");
//    mainMethod.return_();
  }

  default String decompile(byte[] bytecode, File source) {
    SimpleResultSaverFor saver = new SimpleResultSaverFor();
    HashMap<String, Object> customProperties = new HashMap<>();
    customProperties.put("lit", "1");
    customProperties.put("asc", "1");

    try {
      if (bytecode == null)
        bytecode = InterpreterUtil.getBytes(source);

      Fernflower fernflower = new Fernflower(new SimpleBytecodeProvider(bytecode), saver, customProperties, new PrintStreamLogger(new PrintStream(new ByteArrayOutputStream())));
      fernflower.addSource(source);
      fernflower.decompileContext();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return saver.getContent();
  }

  String generateAndDecompile();

  String generateAndDecompile(String base64Memory, List<Routine> routines);

  default void translateToJava(Register<?> pc1, RandomAccessInstructionFetcher randomAccessInstructionFetcher, String className, String memoryInBase64, String startMethod, List<Routine> routines) {
    try {
      ClassMaker classMaker = createClass(pc1, randomAccessInstructionFetcher, className, memoryInBase64, routines);
      Class<?> finish = classMaker.finish();
      Object o = finish.getConstructors()[0].newInstance();
      o.getClass().getMethod(startMethod).invoke(o);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}

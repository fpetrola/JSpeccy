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
import com.hypherionmc.jarmanager.JarManager;
import org.apache.commons.io.FileUtils;
import org.cojen.maker.ClassMaker;
import org.cojen.maker.ClassMaker2;
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
  default <T extends WordNumber> String getDecompiledSource(Register<?> pc1, RandomAccessInstructionFetcher randomAccessInstructionFetcher, String className, String memoryInBase64, List<Routine> routines, String targetFolder) {
    try {
      ClassMaker classMaker1 = createClass(pc1, randomAccessInstructionFetcher, className, memoryInBase64, routines);
      byte[] bytecode = classMaker1.finishBytes();
      String classFile = className + ".class";
      File source = new File(targetFolder + "/" + classFile);
      //FileUtils.writeByteArrayToFile(source, bytecode);

     // bytecode = optimize(className, "target/translation/", source, bytecode);
      return decompile(bytecode, source);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private byte[] optimize(String className, String targetFolder, File source, byte[] bytecode) throws IOException {
    String path = BytecodeGeneration.class.getProtectionDomain().getCodeSource().getLocation().getPath();

    int i1 = path.indexOf("nested:");
    boolean isExecutingFatJar = i1 != -1;
    if (isExecutingFatJar) {
      int i = path.indexOf(".jar");
      path = path.substring(i1 + "nested:".length(), i + ".jar".length());
      JarManager.getInstance().unpackJar(new File(path), new File("target/jar-content"));
    }

    if (!targetFolder.equals(".")) {
      FileUtils.writeByteArrayToFile(source, bytecode);
      String s = isExecutingFatJar ? "target/jar-content/BOOT-INF/classes/rt.jar:target/jar-content/BOOT-INF/classes:" : "target/classes/rt.jar:target/classes:";
      String pack = "com.fpetrola.z80.minizx.";
      pack= "";
      String[] args = {"-via-shimple", "-allow-phantom-refs", "-d", targetFolder, "-cp", s + targetFolder, "-W", pack + className};
      Main.main(args);
      bytecode = InterpreterUtil.getBytes(source);
    }
    return bytecode;
  }

  private ClassMaker createClass(Register<?> pc1, RandomAccessInstructionFetcher randomAccessInstructionFetcher, String className, String memoryInBase64, List<Routine> routines1) {
    boolean translation = !memoryInBase64.isBlank();

    ClassLoader classLoader = BytecodeGeneration.class.getClassLoader();
    if (!translation)
      classLoader= ClassLoader.getSystemClassLoader();
//    ClassMaker classMaker = ClassMaker.begin(className, classLoader).public_();

    ClassMaker classMaker = ClassMaker2.beginExternal(className, classLoader).public_();
    if (translation)
      classMaker.extend(MiniZX.class);
    else
      classMaker.extend(SpectrumApplication.class);

    MethodMaker methodMaker = classMaker.addConstructor().public_();
    methodMaker.invokeSuperConstructor();

    //createMainMethod(classMaker);

    if (translation) {
      MethodMaker getProgramBytesMaker = classMaker.addMethod(String.class, "getProgramBytes").public_();
      getProgramBytesMaker.return_(memoryInBase64);
    }
    HashMap<String, MethodMaker> methods = new HashMap<>();

    routines1.forEach(routine -> {
      ByteCodeGenerator.findMethod(ByteCodeGenerator.createLabelName(routine.getStartAddress()), methods, classMaker);
    });

    routines1.forEach(routine -> {
      boolean syncEnabled = true;
      new ByteCodeGenerator(classMaker, randomAccessInstructionFetcher, (x) -> true, pc1, methods, routine, syncEnabled).generate();
    });
    return classMaker;
  }

  private void createMainMethod(ClassMaker2 classMaker) {
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

    Fernflower fernflower = new Fernflower(new SimpleBytecodeProvider(bytecode), saver, customProperties, new PrintStreamLogger(new PrintStream(new ByteArrayOutputStream())));
    fernflower.addSource(source);
    fernflower.decompileContext();
    return saver.getContent();
  }

  String generateAndDecompile();

  String generateAndDecompile(String base64Memory, List<Routine> routines, String targetFolder, String className1);

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

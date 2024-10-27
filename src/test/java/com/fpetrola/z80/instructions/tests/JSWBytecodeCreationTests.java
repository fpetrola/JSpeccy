package com.fpetrola.z80.instructions.tests;

import com.fpetrola.z80.bytecode.RealCodeBytecodeCreationBase;
import com.fpetrola.z80.bytecode.examples.RemoteZ80Translator;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.routines.Routine;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@SuppressWarnings("ALL")
public class JSWBytecodeCreationTests<T extends WordNumber> extends RealCodeBytecodeCreationBase<T> {

  @Test
  public void testJSWMoveWilly() {
    setupStateWithSnapshot("/home/fernando/detodo/desarrollo/m/zx/zx/jsw.z80");
    String base64Memory = getBase64Memory();
    stepUntilComplete(35090);

    String actual = generateAndDecompile(base64Memory, RemoteZ80Translator.getRoutines(), ".", "JetSetWilly");
    actual = RemoteZ80Translator.improveSource(actual);
    List<Routine> routines = routineManager.getRoutines();

    Assert.assertEquals("""
        """, actual);
  }

  @Ignore
  @Test
  public void testTranslateWillyToJava() {
    try {
      String s = "http://torinak.com/qaop/bin/jetsetwilly";
      s = "file:///home/fernando/Downloads/jetsetwilly";

      String first = "/tmp/jsw.z80";
      Files.copy(new URL(s).openStream(), Paths.get(first), StandardCopyOption.REPLACE_EXISTING);

      String fileName = "/home/fernando/detodo/desarrollo/m/zx/zx/jsw.z80";
      setupStateWithSnapshot(first);
      String base64Memory = getBase64Memory();
      stepUntilComplete(35090);
      translateToJava("JetSetWilly", base64Memory, "$34762", RemoteZ80Translator.getRoutines());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}

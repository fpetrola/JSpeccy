package com.fpetrola.z80.bytecode.examples;

import com.fpetrola.z80.bytecode.RealCodeBytecodeCreationBase;
import com.fpetrola.z80.opcodes.references.WordNumber;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.net.URI.create;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class RemoteZ80Translator<T extends WordNumber> extends RealCodeBytecodeCreationBase<T> {
  public static void main(String[] args) {
    RemoteZ80Translator<WordNumber> remoteZ80Translator = new RemoteZ80Translator<>();
    remoteZ80Translator.translate("JetSetWilly", "http://torinak.com/qaop/bin/jetsetwilly", 35090, 34762);
  }

  public void translate(String gameName, String url, int firstAddress, int startRoutineAddress) {
    try {
      File tempFile = File.createTempFile("zx-game-", ".z80");
      Path target = tempFile.toPath();
      Files.copy(create(url).toURL().openStream(), target, REPLACE_EXISTING);

      setupStateWithSnapshot(tempFile.getAbsolutePath());
      String base64Memory = getBase64Memory();
      stepUntilComplete(firstAddress);
      translateToJava(gameName, base64Memory, STR."$\{startRoutineAddress}");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
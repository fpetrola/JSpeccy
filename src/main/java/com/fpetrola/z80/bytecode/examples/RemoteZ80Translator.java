package com.fpetrola.z80.bytecode.examples;

import com.fpetrola.z80.bytecode.RealCodeBytecodeCreationBase;
import com.fpetrola.z80.opcodes.references.WordNumber;
import io.korhner.asciimg.image.AsciiImgCache;
import io.korhner.asciimg.image.character_fit_strategy.StructuralSimilarityFitStrategy;
import io.korhner.asciimg.image.converter.AsciiToStringConverter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.net.URI.create;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class RemoteZ80Translator<T extends WordNumber> extends RealCodeBytecodeCreationBase<T> {
  public static void main(String[] args) {
    RemoteZ80Translator<WordNumber> remoteZ80Translator = new RemoteZ80Translator<>();

    String jetSetWilly = "JetSetWilly";
    String url = "http://torinak.com/qaop/bin/jetsetwilly";
    int firstAddress = 35090;
    int startRoutineAddress = 34762;
    String screenURL = "https://tcrf.net/images/3/3a/Jet_Set_Willy-ZX_Spectrum-title.png";


    if (args.length >= 4) {
      jetSetWilly = args[0];
      url = args[1];
      firstAddress = Integer.parseInt(args[2]);
      startRoutineAddress = Integer.parseInt(args[3]);
      if (args.length > 4)
        screenURL = args[4];
    }

    System.out.println(STR."\n\nTranslating: \{jetSetWilly} \{url} \{firstAddress} \{startRoutineAddress}\n\n");

    remoteZ80Translator.translate(jetSetWilly, url, firstAddress, startRoutineAddress, screenURL);
  }

  private void drawPicture(String url) {
    try {
      File input = getRemoteFile(url, "");

      AsciiImgCache cache = AsciiImgCache.create(new Font("Courier", Font.PLAIN, 2));
      BufferedImage portraitImage = ImageIO.read(input);
      AsciiToStringConverter stringConverter = new AsciiToStringConverter(cache, new StructuralSimilarityFitStrategy());
      System.out.println(stringConverter.convertImage(portraitImage));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public void translate(String gameName, String url, int firstAddress, int startRoutineAddress, String screeenURL) {
    drawPicture(screeenURL);

    File tempFile = getRemoteFile(url, ".z80");

    setupStateWithSnapshot(tempFile.getAbsolutePath());
    String base64Memory = getBase64Memory();
    stepUntilComplete(firstAddress);
    translateToJava(gameName, base64Memory, STR."$\{startRoutineAddress}");
  }

  private File getRemoteFile(String url, String suffix) {
    try {
      File tempFile = File.createTempFile("zx-game-", suffix);
      Path target = tempFile.toPath();
      Files.copy(create(url).toURL().openStream(), target, REPLACE_EXISTING);
      return tempFile;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
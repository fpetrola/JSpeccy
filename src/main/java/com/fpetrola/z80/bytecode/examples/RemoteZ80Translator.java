package com.fpetrola.z80.bytecode.examples;

import com.fpetrola.z80.bytecode.RealCodeBytecodeCreationBase;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.routines.Routine;
import com.fpetrola.z80.routines.RoutineFinder;
import io.korhner.asciimg.image.AsciiImgCache;
import io.korhner.asciimg.image.character_fit_strategy.StructuralSimilarityFitStrategy;
import io.korhner.asciimg.image.converter.AsciiToStringConverter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Pattern;

import static java.net.URI.create;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static java.util.Comparator.comparingInt;

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

    List<Routine> routines = getRoutines();

    String sourceCode = generateAndDecompile(base64Memory, routines);
    sourceCode = sourceCode.replace("this.", "").replace("super.", "");

    sourceCode = StringReplacer.replace(sourceCode, Pattern.compile("('\\\\u([0-9a-f]{4})')"), m -> {
      String group = m.group(2);
      return String.valueOf(Integer.parseInt(group,16));
    });

    try {
      String fileName = gameName + ".java";
      FileWriter fileWriter = new FileWriter(fileName);
      fileWriter.write(sourceCode);
      fileWriter.close();
      System.out.println("\n\nWritting java source code to: " + fileName + "\n\n");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    translateToJava(gameName, base64Memory, STR."$\{startRoutineAddress}", routines);
  }


  public static List<Routine> getRoutines() {
    List<Routine> routines = RoutineFinder.routineManager.getRoutines().stream()
        .sorted(comparingInt(Routine::getStartAddress))
        .toList();

    System.out.println("\n\nDetecting routines\n\n");
    routines.forEach(System.out::println);
    return routines;
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
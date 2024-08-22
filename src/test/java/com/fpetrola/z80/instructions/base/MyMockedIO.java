package com.fpetrola.z80.instructions.base;

import com.fpetrola.z80.opcodes.references.WordNumber;

import javax.sound.sampled.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.fpetrola.z80.opcodes.references.WordNumber.createValue;

class MyMockedIO extends MockedIO {
  private AudioFormat fmt;
  private SourceDataLine line;

  public MyMockedIO() {
    try {
      fmt = new AudioFormat(10000, 16, 1, true, true);
//                System.out.println(fmt);
      DataLine.Info infoDataLine = new DataLine.Info(SourceDataLine.class, fmt);
      line = (SourceDataLine) AudioSystem.getLine(infoDataLine);
      line.start();
    } catch (LineUnavailableException excpt) {
      Logger.getLogger(MyMockedIO.class.getName()).log(Level.SEVERE, null, excpt);
    }
  }

  public Object in(Object port) {
    if (((WordNumber) port).intValue() == 31) return createValue(31);
    else return createValue(255);
  }

  @Override
  public void out(Object port, Object value) {
    WordNumber v = (WordNumber) value;
    line.write(new byte[]{(byte) ((byte) v.intValue() * -10000), (byte) 255, 0, 0}, 0, 4);
    line.flush();
  }
}

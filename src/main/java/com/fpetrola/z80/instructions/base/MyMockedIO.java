package com.fpetrola.z80.instructions.base;

import com.fpetrola.z80.opcodes.references.WordNumber;

import java.awt.event.KeyEvent;
import java.util.Arrays;

import static com.fpetrola.z80.opcodes.references.WordNumber.createValue;

public class MyMockedIO extends MockedIO {
  private int[] ports = initPorts();

  public MyMockedIO() {

  }

  public synchronized Object in(Object port) {
    if (((WordNumber) port).intValue() == 31) {
      ports[31]|= 32;
    }
    int port1 = ports[((WordNumber) port).intValue()];
    if (port1 != 0) {
//      System.out.println(port1);
      return createValue(port1);
    } else {
      if (((WordNumber) port).intValue() == 31)
        return createValue(0);
      else
        return createValue(191);
    }
  }

  @Override
  public void out(Object port, Object value) {
  }

  public void setCurrentKey(int e, boolean pressed) {
    if (KeyEvent.VK_RIGHT == e) {
      activateKey(1, pressed);
    } else if (KeyEvent.VK_LEFT == e) {
      activateKey(2, pressed);
    } else if (KeyEvent.VK_UP == e) {
      activateKey(8, pressed);
    } else if (KeyEvent.VK_DOWN == e) {
      activateKey(4, pressed);
    } else if (KeyEvent.VK_SPACE == e) {
      activateKey(16, pressed);
    } else if (KeyEvent.VK_ENTER == e) {
      ports[49150] = pressed ? 255: 191;
      ports[45054] = pressed ? 255: 0;

    }
  }

  private void activateKey(int i, boolean pressed) {
    if (pressed)
      ports[31] |= i;
    else {
      int i1 = ~i;
      ports[31] &= i1;
    }

    ports[32510] = 255;
    ports[61438] = 255;
  }

  private int[] initPorts() {
    int[] ports = new int[0x10000];
    Arrays.fill(ports, 0);
//    ports[65278]= 191;
//    ports[32766]= 191;
//    ports[65022]= 191;
//    ports[49150]= 191;
//    ports[61438]= 191;
//    ports[64510]= 191;
//    ports[59390]= 191;
//    ports[59390]= 191;
//    ports[59390]= 191;
    return ports;
  }
}

package com.fpetrola.z80.bytecode.examples;

import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.transformations.Base64Utils;

import java.util.Arrays;

public class SnapshotHelper {
  public static <T extends WordNumber> String getBase64Memory(State<T> state1) {
    WordNumber[] data1 = state1.getMemory().getData();
    int ramEnd = 0x10000;
    byte[] data = new byte[ramEnd];
    Arrays.fill(data, (byte) 0);

    for (int i = 0; i < ramEnd; i++) {
      WordNumber wordNumber = data1[i];
      int i1 = wordNumber == null ? 0 : wordNumber.intValue();
      data[i] = (byte) i1;
    }
    return Base64Utils.gzipArrayCompressToBase64(data);
  }
}

package com.fpetrola.z80.helpers;

import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.opcodes.references.WordNumber;

import java.lang.reflect.Constructor;

public class Helper {
  public static String convertToHex(int routineAddress) {
    return Long.toHexString(routineAddress).toUpperCase();
  }

  public static <T extends WordNumber> String convertToHex(T value) {
    return value.toString();
  }

  public static <T> T createInstance(Class<T> type) {
    T instance;
    try {
      Constructor<T> constructor = type.getConstructor(null);
      instance = constructor.newInstance();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    return (T) instance;
  }

  public static <T extends WordNumber> T read16Bits(Memory<T> memory, T address) {
    return memory.read(address.plus(1)).left(8).and(0xff00).or(memory.read(address).and(0xff));
  }

  public static <T extends WordNumber> void write16Bits(T value, T address, Memory<T> memory) {
    memory.write(address, value.and(0xFF));
    memory.write(address.plus(1), (value.right(8)));
  }
}

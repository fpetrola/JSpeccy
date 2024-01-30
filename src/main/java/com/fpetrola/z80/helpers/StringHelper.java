package com.fpetrola.z80.helpers;

public class StringHelper {
  public static String convertToHex(int routineAddress) {
    return Long.toHexString(routineAddress).toUpperCase();
  }
}

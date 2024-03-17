package com.fpetrola.z80.registers.flag;

import org.apache.commons.lang3.function.TriFunction;

import java.util.function.BiFunction;

public class TableAluOperation {
  private final Integer8BitRegister register;
  protected int table[];

  public TableAluOperation(TriFunction<Integer, Integer, Integer, Alu8BitResult> triFunction, Integer8BitRegister register) {
    this.register = register;
    init(triFunction);
  }

  public TableAluOperation(BiFunction<Integer, Integer, Alu8BitResult> biFunction, Integer8BitRegister register) {
    this.register = register;
    init(biFunction);
  }

  private void init(BiFunction<Integer, Integer, Alu8BitResult> biFunction) {
    table = new int[256 * 2];
    for (int a = 0; a < 256; a++) {
      for (int c = 0; c < 2; c++) {
        Alu8BitResult alu8BitResult = biFunction.apply(a, c);
        table[((a & 0xff)) | (c << 8)] = ((alu8BitResult.ans() & 0xff) << 16) + alu8BitResult.flag();
      }
    }
  }

  public int[] getTable() {
    return table;
  }

  public void init(TriFunction<Integer, Integer, Integer, Alu8BitResult> triFunction) {
    table = new int[256 * 256 * 2];
    for (int a = 0; a < 256; a++) {
      for (int value = 0; value < 256; value++) {
        for (int c = 0; c < 2; c++) {
          Alu8BitResult alu8BitResult = triFunction.apply(a, value, c);
          table[((value & 0xff)) | (a << 8) | (c << 16)] = ((alu8BitResult.ans() & 0xff) << 16) + alu8BitResult.flag();
        }
      }
    }
  }

  public int executeWithCarry(int regA) {
    return (register.data = table[regA | (register.data & 0x01) << 8]) >> 16;
  }


  public int executeWithCarry(int value, int regA) {
    return (register.data = table[(regA << 8) | value | (register.data & 0x01) << 16]) >> 16;
  }

  public int executeWithoutCarry(int value, int regA) {
    return (register.data = table[(regA << 8) | value]) >> 16;
  }
}

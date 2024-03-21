package com.fpetrola.z80.registers.flag;

import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import org.apache.commons.lang3.function.TriFunction;

import java.util.function.BiFunction;

public class TableAluOperation extends AluOperation {
  protected int table[];

  protected void init(BiFunction<Integer, Integer, AluResult> biFunction) {
    table = new int[256 * 2];
    for (int a = 0; a < 256; a++) {
      for (int c = 0; c < 2; c++) {
        AluResult aluResult = biFunction.apply(a, c);
        table[((a & 0xff)) | (c << 8)] = ((aluResult.value() & 0xff) << 16) + aluResult.flag();
      }
    }
  }

  public void init(TriFunction<Integer, Integer, Integer, AluResult> triFunction) {
    table = new int[256 * 256 * 2];
    for (int a = 0; a < 256; a++) {
      for (int value = 0; value < 256; value++) {
        for (int c = 0; c < 2; c++) {
          AluResult aluResult = triFunction.apply(a, value, c);
          table[((value & 0xff)) | (a << 8) | (c << 16)] = ((aluResult.value() & 0xff) << 16) + aluResult.flag();
        }
      }
    }
  }

  public int[] getTable() {
    return table;
  }

  public <T extends WordNumber> T executeWithoutCarry(T value, T regA, Register<T> register) {
    int data1 = table[(regA.left(8)).or(value).intValue()];
    register.write(WordNumber.createValue(data1));
    return WordNumber.createValue(data1 >> 16);
  }

  public <T extends WordNumber> T executeWithCarry(T regA, Register<T> register1) {
    int data1 = table[((register1.read().intValue() & 0x01) << 8) | (regA.intValue() & 0xff)];
    register1.write(WordNumber.createValue(data1));
    return WordNumber.createValue(data1 >> 16);
  }
}

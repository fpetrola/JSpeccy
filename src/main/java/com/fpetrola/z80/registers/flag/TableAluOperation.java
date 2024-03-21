package com.fpetrola.z80.registers.flag;

import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import org.apache.commons.lang3.function.TriFunction;

import java.util.function.BiFunction;

public class TableAluOperation extends AluOperation {
  protected int table[];

  protected void init(BiFunction<Integer, Integer, Alu8BitResult> biFunction) {
    table = new int[256 * 2];
    for (int a = 0; a < 256; a++) {
      for (int c = 0; c < 2; c++) {
        Alu8BitResult alu8BitResult = biFunction.apply(a, c);
        table[((a & 0xff)) | (c << 8)] = ((alu8BitResult.ans() & 0xff) << 16) + alu8BitResult.flag();
      }
    }
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

  public int[] getTable() {
    return table;
  }

  @Override
  public int executeWithCarry(int regA, Integer8BitRegister register1) {
    int data1 = table[((register1.read() & 0x01) << 8) | (regA & 0xff)];
    register1.write(data1);
    return data1 >> 16;
  }

  public int executeWithCarry2(int value, int regA, int carry, Integer8BitRegister register1) {
    int data1 = table[(regA << 8) | value | (carry & 0x01) << 16];
    register1.write(data1);
    return data1 >> 16;
  }

  @Override
  public int executeWithoutCarry(int value, int regA, Integer8BitRegister register1) {
    int data1 = table[(regA << 8) | value];
    register1.write(data1);
    return data1 >> 16;
  }

  public <T extends WordNumber> T executeWithoutCarry(int value, int regA, Register<T> register) {
    int data1 = table[(regA << 8) | value];
    register.write(WordNumber.createValue(data1));
    return WordNumber.createValue(data1 >> 16);
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

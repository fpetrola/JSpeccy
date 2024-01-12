package com.fpetrola.z80.instructions;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class MatrixCachedFlagRegister extends FlagRegister {

  public MatrixCachedFlagRegister(String h, Consumer<Integer> consumer, Supplier<Integer> supplier) {
    super(h);
  }

  @Override
  public void LDI(int reg_A, int value, int bc) {
    super.LDI(reg_A, value, bc);
    System.out.println(data);
  }
}
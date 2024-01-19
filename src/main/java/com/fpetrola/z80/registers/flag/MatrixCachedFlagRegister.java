package com.fpetrola.z80.registers.flag;

import java.util.function.Consumer;
import java.util.function.Supplier;

import com.fpetrola.z80.registers.RegisterName;

public class MatrixCachedFlagRegister extends FlagRegister {

  public MatrixCachedFlagRegister(RegisterName h, Consumer<Integer> consumer, Supplier<Integer> supplier) {
    super(h);
  }

  @Override
  public void LDI(int reg_A, int value, int bc) {
    super.LDI(reg_A, value, bc);
    System.out.println(data);
  }
}
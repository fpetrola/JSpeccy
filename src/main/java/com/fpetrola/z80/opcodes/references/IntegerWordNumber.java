package com.fpetrola.z80.opcodes.references;

import java.util.Arrays;
import java.util.List;

public class IntegerWordNumber implements WordNumber {
  private int value;

  public IntegerWordNumber(int aValue) {
    this.value = aValue;
  }

  @Override
  public <T extends WordNumber> T plus(int i) {
    return (T) createInstance((value + i));
  }

  protected IntegerWordNumber createInstance(int value) {
    return new IntegerWordNumber(value & 0xFFFF);
  }

  @Override
  public <T extends WordNumber> T minus1() {
    return (T) createInstance((value - 1));
  }

  @Override
  public <T extends WordNumber> T left(int i) {
    return (T) createInstance((value << i));
  }

  @Override
  public <T extends WordNumber> T right(int i) {
    return (T) createInstance((value >>> i));
  }

  @Override
  public <T extends WordNumber> T or(int i) {
    return (T) createInstance((value | i));
  }

  public <T extends WordNumber> T xor(int i) {
    return (T) createInstance((value ^ i));
  }

  @Override
  public <T extends WordNumber> T xor(T wordNumber) {
    return xor(wordNumber.intValue() & 0xFFFF);
  }

  @Override
  public <T extends WordNumber> T and(T wordNumber) {
    return and(wordNumber.intValue() & 0xFFFF);
  }

  @Override
  public <T extends WordNumber> T and(int i) {
    return (T) createInstance((value & i));
  }

  @Override
  public <T extends WordNumber> T or(T wordNumber) {
    return or(wordNumber.intValue() & 0xFFFF);
  }

  @Override
  public boolean isNotZero() {
    return value != 0;
  }

  @Override
  public int intValue() {
    return value;
  }

  @Override
  public <T extends WordNumber> T set(T value) {
    this.value = value.intValue();
    return value;
  }

  @Override
  public WordNumber aluOperation2(WordNumber value1, WordNumber value2, String name) {
    return createInstance(value1.intValue());
  }

  @Override
  public WordNumber aluOperation(WordNumber value, String name) {
    return createInstance(value.intValue());
  }

  @Override
  public <T extends WordNumber> T readOperation(T address, T value) {
    return (T) createInstance(value.intValue());
  }

  @Override
  public <T extends WordNumber> List<T> getFirstReadOperation() {
    return (List<T>) Arrays.asList(this);
  }

  public String toString() {
    return value + "";
  }
}

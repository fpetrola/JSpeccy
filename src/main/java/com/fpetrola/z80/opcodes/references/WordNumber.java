package com.fpetrola.z80.opcodes.references;

import java.util.HashSet;
import java.util.Set;

public class WordNumber {
  private int value;

  public static <T> T createValue(int i) {
    return (T) new WordNumber(i);
  }

  public Set<Integer> getReads() {
    return reads;
  }

  protected Set<Integer> reads = new HashSet<>();

  public WordNumber(int value) {
    this.value = value;
  }

  public <T extends WordNumber> T and(int i) {
    return (T) createRelatedWordNumber(value & i);
  }

  private WordNumber createRelatedWordNumber(int value) {
    WordNumber wordNumber = new WordNumber(value);

    for (int r : reads) {
      wordNumber.addReadAccess(r);
      if (r >= 0xAB00 && r <= 0xC000) {
        int a = 1;
      }
    }
    return wordNumber;
  }

  public <T extends WordNumber> T plus(int i) {
    return (T) createRelatedWordNumber(value + i);
  }

  public <T extends WordNumber> T minus(int i) {
    return (T) createRelatedWordNumber(value - i);
  }

  public <T extends WordNumber> T plus(byte i) {
    return (T) createRelatedWordNumber(value + i);
  }


  public <T extends WordNumber> T left(int i) {
    return (T) createRelatedWordNumber(value << i);
  }

  public <T extends WordNumber> T right(int i) {
    return (T) createRelatedWordNumber(value >>> i);
  }


  public <T extends WordNumber> T or(T wordNumber) {
    WordNumber relatedWordNumber = createRelatedWordNumber(value | wordNumber.intValue());
    merge(wordNumber);
    return (T) relatedWordNumber;
  }

  public <T extends WordNumber> T or(int i) {
    return (T) createRelatedWordNumber(value | i);
  }

  public boolean notEquals(int i) {
    return i != value;
  }

  public <T extends WordNumber> byte byteValue() {
    return (byte) value;
  }

  public int intValue() {
    return value;
  }

  public <T extends WordNumber> void addReadAccess(int address) {
    reads.add(address);
  }

  public void merge(WordNumber trace) {
    for (int r : trace.reads) {
      addReadAccess(r);
    }
  }

  public <T extends WordNumber> T and(T wordNumber) {
    WordNumber relatedWordNumber = createRelatedWordNumber(value & wordNumber.intValue());
    merge(wordNumber);
    return (T) relatedWordNumber;
  }

  public <T extends WordNumber> T xor(T wordNumber) {
    WordNumber relatedWordNumber = createRelatedWordNumber(value ^ wordNumber.intValue());
    merge(wordNumber);
    return (T) relatedWordNumber;
  }

  public <T extends WordNumber> T plus(T wordNumber) {
    WordNumber relatedWordNumber = createRelatedWordNumber(value + wordNumber.intValue());
    merge(wordNumber);
    return (T) relatedWordNumber;
  }

  public void set(int read) {
    this.value= read;
  }
}

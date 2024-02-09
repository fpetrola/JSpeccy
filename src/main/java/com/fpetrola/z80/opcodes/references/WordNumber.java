package com.fpetrola.z80.opcodes.references;

import com.fpetrola.z80.spy.InstructionSpy;

import java.util.HashSet;
import java.util.Set;

public class WordNumber {
  private int value;

  public Set<Integer> getReads() {
    return reads;
  }

  protected Set<Integer> reads = new HashSet<>();
  protected Set<Integer> operationsAddresses = new HashSet<>();
  public static InstructionSpy instructionSpy;

  public static <T> T createValue(int i) {
    return (T) new WordNumber(i);
  }

  public WordNumber(int value) {
    this.value = value;
  }

  public WordNumber copyMetadataFromTo(WordNumber source, WordNumber target) {
//    for (int r : source.reads) {
//      target.addReadAccess(r);
//    }
//    for (int r : source.operationsAddresses) {
//      target.operationsAddresses.add(r);
//    }

    return source;
  }

  private WordNumber createRelatedWordNumber(int value) {
    WordNumber wordNumber = new WordNumber(value);
    copyMetadataFromTo(this, wordNumber);
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

  public <T extends WordNumber> T or(int i) {
    return (T) createRelatedWordNumber(value | i);
  }

  public <T extends WordNumber> T and(int i) {
    return (T) createRelatedWordNumber(value & i);
  }

  public <T extends WordNumber> T xor(int i) {
    return (T) createRelatedWordNumber(value ^ i);
  }

  public <T extends WordNumber> T or(T wordNumber) {
    return (T) or(copyMetadataFromTo(wordNumber, this).intValue());
  }

  public <T extends WordNumber> T and(T wordNumber) {
    return (T) and(copyMetadataFromTo(wordNumber, this).intValue());
  }

  public <T extends WordNumber> T xor(T wordNumber) {
    return (T) xor(copyMetadataFromTo(wordNumber, this).intValue());
  }

  public <T extends WordNumber> T plus(T wordNumber) {
    return (T) plus(copyMetadataFromTo(wordNumber, this).intValue());
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

  public void set(int read) {
    this.value = read;
  }

  public <T extends WordNumber> void addReadAccess(int address) {
    reads.add(address);
  }
}

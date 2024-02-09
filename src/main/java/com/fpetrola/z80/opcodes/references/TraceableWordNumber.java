package com.fpetrola.z80.opcodes.references;

import com.fpetrola.z80.spy.InstructionSpy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TraceableWordNumber implements WordNumber {
  public static InstructionSpy instructionSpy;

  private int value;

  public Set<Integer> getReads() {
    return reads;
  }

  protected Set<Integer> reads = new HashSet<>();
  protected List<Long> operationsAddresses = new ArrayList<>();

  public TraceableWordNumber(int value) {
    this.value = value;
    operationsAddresses.add(instructionSpy.getExecutionNumber());
  }

  WordNumber createRelatedWordNumber(int value) {
    TraceableWordNumber wordNumber = new TraceableWordNumber(value);
    copyMetadataFromTo(this, wordNumber);
    return wordNumber;
  }

  public <T extends WordNumber> T copyMetadataFromTo(TraceableWordNumber source, TraceableWordNumber target) {
    for (int r : source.reads) {
      target.addReadAccess(r);
    }

//    for (long r : source.operationsAddresses) {
//      target.operationsAddresses.add(r);
//    }

    return (T) source;
  }

  @Override
  public <T extends WordNumber> T plus(int i) {
    return (T) createRelatedWordNumber(value + i);
  }

  @Override
  public <T extends WordNumber> T minus(int i) {
    return (T) createRelatedWordNumber(value - i);
  }

  @Override
  public <T extends WordNumber> T plus(byte i) {
    return (T) createRelatedWordNumber(value + i);
  }

  @Override
  public <T extends WordNumber> T left(int i) {
    return (T) createRelatedWordNumber(value << i);
  }

  @Override
  public <T extends WordNumber> T right(int i) {
    return (T) createRelatedWordNumber(value >>> i);
  }

  @Override
  public <T extends WordNumber> T or(int i) {
    return (T) createRelatedWordNumber(value | i);
  }

  @Override
  public <T extends WordNumber> T and(int i) {
    return (T) createRelatedWordNumber(value & i);
  }

  @Override
  public <T extends WordNumber> T xor(int i) {
    return (T) createRelatedWordNumber(value ^ i);
  }

  @Override
  public <T extends WordNumber> T or(T wordNumber) {
    return (T) or(copyMetadataFromTo((TraceableWordNumber) wordNumber, this).intValue());
  }

  @Override
  public <T extends WordNumber> T and(T wordNumber) {
    return (T) and(copyMetadataFromTo((TraceableWordNumber) wordNumber, this).intValue());
  }

  @Override
  public <T extends WordNumber> T xor(T wordNumber) {
    return (T) xor(copyMetadataFromTo((TraceableWordNumber) wordNumber, this).intValue());
  }

  @Override
  public <T extends WordNumber> T plus(T wordNumber) {
    return (T) plus(copyMetadataFromTo((TraceableWordNumber) wordNumber, this).intValue());
  }

  @Override
  public boolean notEquals(int i) {
    return i != value;
  }

  @Override
  public <T extends WordNumber> byte byteValue() {
    return (byte) value;
  }

  @Override
  public int intValue() {
    return value;
  }

  @Override
  public void set(int read) {
    this.value = read;
  }

  public <T extends WordNumber> void addReadAccess(int address) {
    reads.add(address);
  }

  public <T extends WordNumber> T merge(T wordNumber1, T wordNumber2) {
    return copyMetadataFromTo((TraceableWordNumber) wordNumber1, (TraceableWordNumber) wordNumber2);
  }
}

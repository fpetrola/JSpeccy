package com.fpetrola.z80.opcodes.references;

import com.fpetrola.z80.spy.ExecutionStep;
import com.fpetrola.z80.spy.InstructionSpy;
import org.apache.commons.collections4.queue.CircularFifoQueue;

import java.util.*;

public class TraceableWordNumber implements WordNumber {
  public static InstructionSpy instructionSpy;

  private int value;

  public Set<Integer> getReads() {
    return reads;
  }

  protected Set<Integer> reads = new HashSet<>();
  private Queue<Long> operationsAddresses = new CircularFifoQueue<>(10);

  public TraceableWordNumber(int value) {
    set(value);
    long executionNumber = instructionSpy.getExecutionNumber();
    addOperationAddress(executionNumber);
  }

  private void addOperationAddress(long executionNumber) {
      operationsAddresses.add(executionNumber);
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

    for (long r : source.operationsAddresses) {
      target.addOperationAddress(r);
    }

    return (T) source;
  }

  public <T extends WordNumber> void clearMetadata() {
    reads.clear();
//    operationsAddresses.clear();
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
//    clearMetadata();
  }

  @Override
  public <T extends WordNumber> void set(T other) {
    set(other.intValue());
    copyMetadataFromTo((TraceableWordNumber) other, this);
  }

  public <T extends WordNumber> void addReadAccess(int address) {
    reads.add(address);
  }

  public <T extends WordNumber> T merge(T wordNumber1, T wordNumber2) {
    return copyMetadataFromTo((TraceableWordNumber) wordNumber1, (TraceableWordNumber) wordNumber2);
  }

  public String toString() {
    return value + "";
  }
}

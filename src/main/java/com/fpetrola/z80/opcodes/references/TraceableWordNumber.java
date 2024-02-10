package com.fpetrola.z80.opcodes.references;

import com.fpetrola.z80.instructions.base.Instruction;
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
  private TreeSet<ExecutionPoint> operationsAddresses = new TreeSet<>();

  public TraceableWordNumber(int value) {
    set(value);
    Instruction instruction = instructionSpy.getInstruction();
    long executionNumber = instructionSpy.getExecutionNumber();
    int pc = instructionSpy.getPc();
    if (instruction != null) {
      ExecutionPoint executionPoint = new ExecutionPoint(executionNumber, instruction, pc);
      addOperationAddress(executionPoint);
    }
  }

  private void addOperationAddress(ExecutionPoint executionPoint) {
    if (!operationsAddresses.contains(executionPoint))
      operationsAddresses.add(executionPoint);
  }

  WordNumber createRelatedWordNumber(int value) {
    TraceableWordNumber wordNumber = new TraceableWordNumber(value);
    copyMetadataFromTo(this, wordNumber);
    return wordNumber;
  }

  public <T extends WordNumber> T copyMetadataFromTo(TraceableWordNumber source, TraceableWordNumber target) {
    copyReadAccess(source, target);
    copyOperations(source, target);
    return (T) source;
  }

  public void copyOperations(TraceableWordNumber source, TraceableWordNumber target) {
    //    if (source.operationsAddresses.size() >= 20) {
//      int a = 0;
//    }
    int i = 0;
    for (Iterator<ExecutionPoint> iterator = source.operationsAddresses.descendingIterator(); iterator.hasNext(); ) {
      ExecutionPoint a = iterator.next();
      if (!target.operationsAddresses.contains(a)) {
        target.addOperationAddress(a);
      }
      if (++i == 20)
        break;
    }
  }

  public void copyReadAccess(TraceableWordNumber source, TraceableWordNumber target) {
    for (int r : source.reads) {
      target.addReadAccess(r);
    }
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

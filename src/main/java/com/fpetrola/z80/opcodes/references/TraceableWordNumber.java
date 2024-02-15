package com.fpetrola.z80.opcodes.references;

import com.fpetrola.z80.spy.InstructionSpy;

import java.util.*;

public class TraceableWordNumber implements WordNumber {
  public static InstructionSpy instructionSpy;

  private int value;
  private WordNumberOperation wordNumberOperation;
  private TraceableWordNumber previous;

  public Set<Integer> getReads() {
    return reads;
  }

  protected Set<Integer> reads = new HashSet<>();

  public TreeSet<ExecutionPoint> getOperationsAddresses() {
    return operationsAddresses;
  }

  private TreeSet<ExecutionPoint> operationsAddresses = new TreeSet<>();

  public TraceableWordNumber(int value) {
    this.value = value;
    addOperationAddress(instructionSpy.getLastExecutionPoint());
  }

  private void addOperationAddress(ExecutionPoint executionPoint) {
    if (instructionSpy.isStructureCapture() && instructionSpy.getLastExecutionPoint() != null)
      if (!operationsAddresses.contains(executionPoint)) operationsAddresses.add(executionPoint);
  }

  WordNumber createRelatedWordNumber(int value) {
    TraceableWordNumber wordNumber = new TraceableWordNumber(value & 0xFFFF);
    wordNumber.previous = this;
    copyMetadataFromTo(this, wordNumber);
    return wordNumber;
  }

  public <T extends WordNumber> T copyMetadataFromTo(TraceableWordNumber source, TraceableWordNumber target) {
    copyReadAccess(source, target);
    copyOperations(source, target);
    return (T) source;
  }

  public void copyOperations(TraceableWordNumber source, TraceableWordNumber target) {
    if (instructionSpy.isStructureCapture()) {
      int i = 0;
      for (Iterator<ExecutionPoint> iterator = source.operationsAddresses.descendingIterator(); iterator.hasNext(); ) {
        ExecutionPoint a = iterator.next();
        if (!target.operationsAddresses.contains(a)) {
          target.addOperationAddress(a);
        }
        if (++i == 20) break;
      }
    }
  }

  public void copyReadAccess(TraceableWordNumber source, TraceableWordNumber target) {
    if (instructionSpy.isReadAccessCapture()) for (int r : source.reads) {
      target.addReadAccess(r);
    }
  }

  public <T extends WordNumber> void clearMetadata() {
    reads.clear();
//    operationsAddresses.clear();
  }

  private <T extends WordNumber> T execute(WordNumberOperation wordNumberOperation) {
    this.wordNumberOperation = wordNumberOperation;
    return (T) createRelatedWordNumber(wordNumberOperation.execute());
  }

  @Override
  public <T extends WordNumber> T plus(int i) {
    return execute(new PlusOperation(this, i));
  }

  @Override
  public <T extends WordNumber> T minus1() {
    return execute(new MinusOperation(this, 1));
  }

  @Override
  public <T extends WordNumber> T left(int i) {
    return execute(new LeftOperation(this, i));
  }

  @Override
  public <T extends WordNumber> T right(int i) {
    return execute(new RightOperation(this, i));
  }

  @Override
  public <T extends WordNumber> T or(int i) {
    return execute(new OrOperation(this, i));
  }

  @Override
  public <T extends WordNumber> T and(int i) {
    return execute(new AndOperation(this, i));
  }

  @Override
  public <T extends WordNumber> T or(T wordNumber) {
    return (T) or(copyMetadataFromTo((TraceableWordNumber) wordNumber, this).intValue());
  }

  @Override
  public boolean isNotZero() {
    return intValue() != 0;
  }

  @Override
  public int intValue() {
    return value;
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

  private class PlusOperation extends DefaultWordNumberOperation {
    public PlusOperation(TraceableWordNumber traceableWordNumber, int i) {
      super(traceableWordNumber, i);
    }

    public int execute() {
      return value + i;
    }
  }

  private class MinusOperation extends DefaultWordNumberOperation {
    public MinusOperation(TraceableWordNumber traceableWordNumber, int i) {
      super(traceableWordNumber, i);
    }

    public int execute() {
      return value - i;
    }
  }

  private class OrOperation extends DefaultWordNumberOperation {
    public OrOperation(TraceableWordNumber traceableWordNumber, int i) {
      super(traceableWordNumber, i);
    }

    public int execute() {
      return value | i;
    }
  }

  private class AndOperation extends DefaultWordNumberOperation {
    public AndOperation(TraceableWordNumber traceableWordNumber, int i) {
      super(traceableWordNumber, i);
    }

    public int execute() {
      return value & i;
    }
  }

  private class LeftOperation extends DefaultWordNumberOperation {
    public LeftOperation(TraceableWordNumber traceableWordNumber, int i) {
      super(traceableWordNumber, i);
    }

    public int execute() {
      return value << i;
    }
  }

  private class RightOperation extends DefaultWordNumberOperation {
    public RightOperation(TraceableWordNumber traceableWordNumber, int i) {
      super(traceableWordNumber, i);
    }

    public int execute() {
      return value >>> i;
    }
  }
}

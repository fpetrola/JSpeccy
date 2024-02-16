package com.fpetrola.z80.opcodes.references;

import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.spy.InstructionSpy;

import java.util.*;

public class TraceableWordNumber implements WordNumber {
  public static InstructionSpy instructionSpy;

  private int value;
  public WordNumberOperation operation;
  public TraceableWordNumber previous;

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

  private <T extends WordNumber> T execute(WordNumberOperation operation) {
    this.operation = operation;
    operation.setExecutionPoint(instructionSpy.getLastExecutionPoint());
    return (T) createRelatedWordNumber(operation.execute());
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

  public TraceableWordNumber nullOperation(TraceableWordNumber value) {
    return execute(new NullOperation((TraceableWordNumber) value));
  }

  public TraceableWordNumber readOperation(WordNumber address, TraceableWordNumber value) {
    return execute(new ReadOperation(address, value));
  }

  public <T extends WordNumber> T merge(T wordNumber1, T wordNumber2) {
    return copyMetadataFromTo((TraceableWordNumber) wordNumber1, (TraceableWordNumber) wordNumber2);
  }

  public String printTrace() {
    TraceableWordNumber current = this;
    ExecutionPoint lastExecutionPoint = null;
    while (current.previous != null) {
      if (current.operation != null) {
        ExecutionPoint executionPoint = current.operation.getExecutionPoint();
        if (lastExecutionPoint != executionPoint) {
          Instruction instruction = executionPoint.instruction;
          System.out.println(instruction);
        }
        lastExecutionPoint = executionPoint;
      }
      current = current.previous;
    }

    return value + "";
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

  private class NullOperation extends DefaultWordNumberOperation {
    public NullOperation(TraceableWordNumber traceableWordNumber) {
      super(traceableWordNumber, traceableWordNumber.value);
    }

    public int execute() {
      return traceableWordNumber.value;
    }
  }

  public class ReadOperation extends DefaultWordNumberOperation {
    public WordNumber address;

    public ReadOperation(WordNumber address, TraceableWordNumber value) {
      super(value, value.value);
      this.address = address;
    }

    public int execute() {
      return traceableWordNumber.value;
    }
  }
}

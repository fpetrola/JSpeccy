package com.fpetrola.z80.opcodes.references;

import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.spy.InstructionSpy;

import java.util.*;

public class TraceableWordNumber implements WordNumber {
  public static InstructionSpy instructionSpy;

  private int value;
  public WordNumberOperation operation;
  public TraceableWordNumber previous;

  public TraceableWordNumber(int value) {
    this.value = value;
  }

  WordNumber createRelatedWordNumber(int value) {
    TraceableWordNumber wordNumber = new TraceableWordNumber(value & 0xFFFF);
    wordNumber.previous = this;
    return wordNumber;
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
    return (T) or(wordNumber.intValue());
  }

  @Override
  public boolean isNotZero() {
    return intValue() != 0;
  }

  @Override
  public int intValue() {
    return value;
  }

  public TraceableWordNumber aluOperation(TraceableWordNumber value) {
    return execute(new AluOperation(value));
  }

  public TraceableWordNumber readOperation(WordNumber address, TraceableWordNumber value) {
    return execute(new ReadOperation(address, value));
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

  public TreeSet<ExecutionPoint> getOperationsAddresses() {
    TreeSet<ExecutionPoint> operationsAddresses = new TreeSet<>();
    TraceableWordNumber current = this;
    ExecutionPoint lastExecutionPoint = null;
    while (current.previous != null) {
      if (current.operation != null) {
        ExecutionPoint executionPoint = current.operation.getExecutionPoint();
        if (lastExecutionPoint != executionPoint) {
          operationsAddresses.add(executionPoint);
        }
        lastExecutionPoint = executionPoint;
      }
      current = current.previous;
    }

    return operationsAddresses;
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

  private class AluOperation extends DefaultWordNumberOperation {
    public AluOperation(TraceableWordNumber traceableWordNumber) {
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

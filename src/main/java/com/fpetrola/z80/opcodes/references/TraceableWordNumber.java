package com.fpetrola.z80.opcodes.references;

import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.spy.InstructionSpy;

import java.util.*;

public class TraceableWordNumber implements WordNumber {
  public static InstructionSpy instructionSpy;
  private TraceableWordNumber previous;
  private TraceableWordNumber previous2;

  private int value;
  public WordNumberOperation operation;

  public TraceableWordNumber(int value) {
    this.value = value;
  }

  WordNumber createRelatedWordNumber(WordNumberOperation operation) {
    TraceableWordNumber wordNumber = new TraceableWordNumber(operation.execute() & 0xFFFF);
    wordNumber.operation = operation;
    wordNumber.previous = null;
    wordNumber.previous2 = null;
    return wordNumber;
  }

  private <T extends WordNumber> T execute(WordNumberOperation operation) {
    operation.setExecutionPoint(instructionSpy.getLastExecutionPoint());
    TraceableWordNumber relatedWordNumber = (TraceableWordNumber) createRelatedWordNumber(operation);

    relatedWordNumber.previous= previous;
    relatedWordNumber.previous2= previous2;
    previous= relatedWordNumber;
    previous2= null;
    return (T) relatedWordNumber;
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

  @Override
  public <T extends WordNumber> T set(T value) {
    TraceableWordNumber value1 = (TraceableWordNumber) value;
    TraceableWordNumber execute = execute(new AssignmentOperation(this, value1));
    previous2 = value1;
    return (T) execute;
  }

  public TooLargeTraceOperation createTooLargeTraceOperation() {
    TooLargeTraceOperation tooLargeTraceOperation = new TooLargeTraceOperation();
    tooLargeTraceOperation.setExecutionPoint(instructionSpy.getLastExecutionPoint());
    return tooLargeTraceOperation;
  }

  public AluOperation createAluOperation(TraceableWordNumber value, String name) {
    AluOperation aluOperation = new AluOperation(name, value);
    aluOperation.setExecutionPoint(instructionSpy.getLastExecutionPoint());
    return aluOperation;
  }

  public TraceableWordNumber readOperation(WordNumber address, TraceableWordNumber value) {
    return execute(createReadOperation(address, value));
  }

  public ReadOperation createReadOperation(WordNumber address, TraceableWordNumber value) {
    ReadOperation readOperation = new ReadOperation(address, value);
    readOperation.setExecutionPoint(instructionSpy.getLastExecutionPoint());
    return readOperation;
  }

  public String printTrace() {
    printPrevious(this, 2, instructionSpy.getExecutionNumber());
    return value + "";
  }

  private void printPrevious(TraceableWordNumber current, int indent, long executionNumber) {
    String str = "%" + indent + "s";
    String tab = str.formatted("");
    if (current != null) {
      if (current.operation != null) {
        ExecutionPoint executionPoint = current.operation.getExecutionPoint();
        if (executionPoint != null) {
          Instruction instruction = executionPoint.instruction;
          String s = executionPoint.pc + " : " + instruction + " (" + (executionPoint.executionNumber - executionNumber) + ") " + current.operation.toString();
          if (executionNumber == 0) {
            executionNumber = executionPoint.executionNumber;
          }

          System.out.println(tab + s);
        }
      } else {
        System.out.println(tab + "value: " + current.value);
      }

      //System.out.println(tab + "previous ---> ");
      printPrevious(current.previous, indent + 2, executionNumber);
      //System.out.println(tab + "previous2 ---> ");
      printPrevious(current.previous2, indent + 2, executionNumber);
    }
  }

  public String printTrace2() {
    TraceableWordNumber current = this;
    ExecutionPoint lastExecutionPoint = null;
    while (current.previous != null || current.previous2 != null) {
      if (current.operation != null) {
        ExecutionPoint executionPoint = current.operation.getExecutionPoint();
        if (lastExecutionPoint != executionPoint) {
          Instruction instruction = executionPoint.instruction;
          System.out.println(executionPoint.pc + " : " + instruction);
        }
        lastExecutionPoint = executionPoint;
      }
      if (current.previous == null)
        current = current.previous2;
      else
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

  public void purgeTooOlderPrevious() {
    purgeDeepPrevious(this, 0);
  }

  private void purgeDeepPrevious(TraceableWordNumber current, int i) {
    if (current != null) {
      int maxDepth = 10;
      if (i == maxDepth) {
        if (current.previous != null)
          current.previous = (TraceableWordNumber) current.previous.createRelatedWordNumber(this.createTooLargeTraceOperation());
        if (current.previous2 != null)
          current.previous2 = (TraceableWordNumber) current.previous2.createRelatedWordNumber(this.createTooLargeTraceOperation());
      } else {
        purgeDeepPrevious(current.previous, i + 1);
        purgeDeepPrevious(current.previous2, i + 1);
      }
    }
  }

  public TraceableWordNumber getPrevious() {
    return previous;
  }

  public void setPrevious(TraceableWordNumber previous) {
//    if (operation instanceof TooLargeTraceOperation)
//      System.out.println("oh!");
//    else
      this.previous = previous;
  }

  public TraceableWordNumber getPrevious2() {
    return previous2;
  }

  public void setPrevious2(TraceableWordNumber previous2) {
//    if (operation instanceof TooLargeTraceOperation)
//      System.out.println("oh!");
//    else
      this.previous2 = previous2;
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

  private class AssignmentOperation extends DefaultWordNumberOperation {
    private final TraceableWordNumber traceableWordNumber1;

    public AssignmentOperation(TraceableWordNumber traceableWordNumber, TraceableWordNumber traceableWordNumber1) {
      super(traceableWordNumber, traceableWordNumber1.value);
      this.traceableWordNumber1 = traceableWordNumber1;
    }

    public int execute() {
      return traceableWordNumber.value = i;
    }
  }

  public static class AluOperation extends DefaultWordNumberOperation {
    public String name;

    public AluOperation(String name, TraceableWordNumber traceableWordNumber) {
      super(traceableWordNumber, traceableWordNumber.value);
      this.name = name;
    }

    public int execute() {
      return traceableWordNumber.value;
    }

    public String toString() {
      return getClass().getSimpleName() + " - " + name + ": " + i;
    }
  }

  public static class TooLargeTraceOperation extends DefaultWordNumberOperation {

    public TooLargeTraceOperation() {
      super(null, 0);
    }

    public int execute() {
      return 0;
    }
  }

  public static class ReadOperation extends DefaultWordNumberOperation {
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

package com.fpetrola.z80.opcodes.references;

import com.fpetrola.z80.instructions.base.Instruction;

import java.util.Objects;

public class ExecutionPoint implements Comparable {
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ExecutionPoint that = (ExecutionPoint) o;
    return executionNumber == that.executionNumber;
  }

  @Override
  public int hashCode() {
    return Objects.hash(executionNumber);
  }

  public long executionNumber;
  public Instruction instruction;
  public int pc;

  public ExecutionPoint(long executionNumber, Instruction instruction, int pc) {
    this.executionNumber = executionNumber;
    this.instruction = instruction;
    this.pc = pc;
  }

  @Override
  public String toString() {
    return "ExecutionPoint{" +
        "executionNumber=" + executionNumber +
        ", instruction=" + instruction +
        '}';
  }

  @Override
  public int compareTo(Object o) {
    return (int) (executionNumber - ((ExecutionPoint) o).executionNumber);
  }
}

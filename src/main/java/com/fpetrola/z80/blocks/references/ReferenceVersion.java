package com.fpetrola.z80.blocks.references;

import java.util.Objects;

public class ReferenceVersion {
  public final int cycle;
  public final long executionNumber;

  public ReferenceVersion(int cycle, long executionNumber) {
    this.cycle = cycle;
    this.executionNumber = executionNumber;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ReferenceVersion that = (ReferenceVersion) o;
    return cycle == that.cycle;
  }

  @Override
  public int hashCode() {
    return Objects.hash(cycle);
  }

  @Override
  public String toString() {
    return "ReferenceVersion{" +
        "cycle=" + cycle +
        ", executionNumber=" + executionNumber +
        '}';
  }
}

package com.fpetrola.z80.blocks.references;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BlockRelation {
  private int sourceAddress;
  private int targetAddress;

  private List<Integer> versions = new ArrayList<>();

  public BlockRelation(int sourceAddress, int targetAddress) {
    this.sourceAddress = sourceAddress;
    this.targetAddress = targetAddress;
  }

  public static BlockRelation createBlockRelation(int sourceAddress, int targetAddress) {
    return new BlockRelation(sourceAddress, targetAddress);
  }

  public int getSourceAddress() {
    return sourceAddress;
  }

  public int getTargetAddress() {
    return targetAddress;
  }

  public void addInCycle(int cycle) {
    if (cycle > 0)
      versions.add(cycle);
  }

  public List<Integer> getVersions() {
    return versions;
  }

  public void setVersions(List<Integer> versions) {
    this.versions = versions;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    BlockRelation that = (BlockRelation) o;
    return Objects.equals(sourceAddress, that.sourceAddress) && Objects.equals(targetAddress, that.targetAddress);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sourceAddress, targetAddress);
  }
}

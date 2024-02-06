package com.fpetrola.z80.blocks.references;

import com.fpetrola.z80.blocks.Block;

import java.util.Objects;

public class BlockReference {
  private Block block;
  private int address;

  public BlockReference(Block block, int address) {
    this.block = block;
    this.address = address;
  }

  public Block getBlock() {
    return block;
  }

  public void setBlock(Block block) {
    this.block = block;
  }

  public int getAddress() {
    return address;
  }

  public void setAddress(int address) {
    this.address = address;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    BlockReference that = (BlockReference) o;
    return address == that.address && Objects.equals(block, that.block);
  }

  @Override
  public int hashCode() {
    return Objects.hash(block, address);
  }
}

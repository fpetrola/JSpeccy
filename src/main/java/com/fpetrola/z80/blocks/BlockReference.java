package com.fpetrola.z80.blocks;

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
}

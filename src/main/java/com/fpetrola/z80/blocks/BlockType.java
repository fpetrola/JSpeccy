package com.fpetrola.z80.blocks;

public interface BlockType {
  Block getBlock();

  void accept(BlockRoleVisitor blockRoleVisitor);

  void setBlock(Block block);
}

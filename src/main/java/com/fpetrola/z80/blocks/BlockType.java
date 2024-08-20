package com.fpetrola.z80.blocks;

public interface BlockType {
  Block getBlock();

  void accept(BlockRoleVisitor blockRoleVisitor);

  void setBlock(Block block);

  default String getName() {
    return getClass().getSimpleName().toString();
  }

  default boolean canSplit() {
    return true;
  }

  default boolean canBeJoined() {
    return true;
  }
}

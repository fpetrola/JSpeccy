package com.fpetrola.z80.blocks;

public interface BlockVisitor {
  default void visitingBlock(Block block) {
  }

  default void visitingCodeBlock(CodeBlock codeBlock) {
  }

  default void visitingUnknownBlock(UnknownBlock unknownBlock) {
  }

  default void visitingDataBlock(DataBlock dataBlock) {
  }
}

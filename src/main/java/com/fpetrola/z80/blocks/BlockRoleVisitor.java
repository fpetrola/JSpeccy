package com.fpetrola.z80.blocks;

public interface BlockRoleVisitor {
  default void visiting(CodeBlockType codeBlockType) {
  }

  default void visiting(UnknownBlockType unknownBlockType) {
  }

  default void visiting(DataBlockType dataBlockType) {
  }

  default void visiting(RoutineBlockType routineBlockType) {
  }
}

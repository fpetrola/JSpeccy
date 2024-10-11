package com.fpetrola.z80.spy;

import com.fpetrola.z80.cpu.Z80Cpu;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.opcodes.references.ExecutionPoint;

import java.util.function.Supplier;

public interface ComplexInstructionSpy<T> extends InstructionSpy<T> {

  default boolean isReadAccessCapture() {
    return false;
  }

  default long getExecutionNumber() {
    return 0;
  }

  default boolean[] getBitsWritten() {
    return new boolean[0];
  }


  default Instruction getFetchedAt(int address) {
    return null;
  }


  default boolean wasFetched(int address) {
    return false;
  }


  default boolean isIndirectReference() {
    return false;
  }

  default void setSpritesArray(boolean[] bitsWritten) {

  }


  default void undo() {

  }


  default boolean isStructureCapture() {
    return false;
  }


  default void enableStructureCapture() {
  }


  default void switchToIndirectReference() {
  }


  default void switchToDirectReference() {
  }


  default <T> T executeInPause(Supplier<T> object) {
    return object.get();
  }


  default void setSecondZ80(Z80Cpu z802) {

  }


  default ExecutionPoint getLastExecutionPoint() {
    return null;
  }


  default void export() {

  }


  default void enableReadAccessCapture() {

  }


  default void setGameName(String gameName) {

  }
}
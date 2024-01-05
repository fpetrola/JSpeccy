package com.fpetrola.z80.instructions;

import java.util.ArrayList;
import java.util.List;

public class ExecutionStepData {

  List<WriteOpcodeReference> writeReferences = new ArrayList<>();
  List<ReadOpcodeReference> readReferences = new ArrayList<>();
  List<WriteMemoryReference> writeMemoryReferences = new ArrayList<>();
  List<ReadMemoryReference> readMemoryReferences = new ArrayList<>();
  OpCode opcode;

  public ExecutionStepData() {
  }

  public void addWriteReference(OpcodeReference opcodeReference, int value) {
    writeReferences.add(new WriteOpcodeReference(opcodeReference, value));
  }

  public void addReadReference(OpcodeReference opcodeReference, int value) {
    readReferences.add(new ReadOpcodeReference(opcodeReference, value));
  }

  protected void clear() {
    writeReferences.clear();
    readReferences.clear();
    writeMemoryReferences.clear();
    readMemoryReferences.clear();
  }

  public void addWriteMemoryReference(int address, int value) {
    writeMemoryReferences.add(new WriteMemoryReference(address, value));
  }

  public void addReadMemoryReference(int address, int value) {
    readMemoryReferences.add(new ReadMemoryReference(address, value));
  }
}
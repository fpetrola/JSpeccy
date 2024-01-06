package com.fpetrola.z80.instructions;

import java.util.ArrayList;
import java.util.List;

public class ExecutionStepData {

  List<WriteOpcodeReference> writeReferences = new ArrayList<>();
  List<ReadOpcodeReference> readReferences = new ArrayList<>();
  List<WriteMemoryReference> writeMemoryReferences = new ArrayList<>();
  List<ReadMemoryReference> readMemoryReferences = new ArrayList<>();
  List<Object> accessReferences = new ArrayList<>();
  OpCode opcode;
  public int opcodeInt;
  public int pcValue;

  public ExecutionStepData() {
  }

  public void addWriteReference(OpcodeReference opcodeReference, int value) {
    WriteOpcodeReference e = new WriteOpcodeReference(opcodeReference, value);
    writeReferences.add(e);
    accessReferences.add(e);
  }

  public void addReadReference(OpcodeReference opcodeReference, int value) {
    ReadOpcodeReference e = new ReadOpcodeReference(opcodeReference, value);
    readReferences.add(e);
    accessReferences.add(e);
  }

  protected void clear() {
    writeReferences.clear();
    readReferences.clear();
    writeMemoryReferences.clear();
    readMemoryReferences.clear();
  }

  public void addWriteMemoryReference(int address, int value) {
    WriteMemoryReference e = new WriteMemoryReference(address, value);
    writeMemoryReferences.add(e);
    accessReferences.add(e);

  }

  public void addReadMemoryReference(int address, int value) {
    ReadMemoryReference e = new ReadMemoryReference(address, value);
    readMemoryReferences.add(e);
    accessReferences.add(e);
  }
  
  
}
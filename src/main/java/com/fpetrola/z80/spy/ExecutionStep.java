package com.fpetrola.z80.spy;

import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.opcodes.references.WordNumber;

import java.util.ArrayList;
import java.util.List;

public class ExecutionStep<T extends WordNumber> {
  public List<WriteOpcodeReference> writeReferences = new ArrayList<>();
  public List<ReadOpcodeReference> readReferences = new ArrayList<>();
  public List<WriteMemoryReference<T>> writeMemoryReferences = new ArrayList<>();
  public List<ReadMemoryReference<T>> readMemoryReferences = new ArrayList<>();
  transient public List<Object> accessReferences = new ArrayList<>();
  transient public Instruction<T> instruction;
  public String description;
  public int pcValue;
  transient private Memory memory;
  public int i;

  public ExecutionStep(Memory memory) {
    this.memory = memory;
  }

  public WriteOpcodeReference addWriteReference(String opcodeReference, T value, boolean isIncrement, boolean indirectReference) {
    WriteOpcodeReference e = new WriteOpcodeReference(opcodeReference, value, isIncrement, indirectReference);
    writeReferences.add(e);
    addAccessReference(e);
    return e;
  }

  private void addAccessReference(Undoable e) {
    accessReferences.add(e);
  }

  public ReadOpcodeReference addReadReference(String opcodeReference, T value, boolean indirectReference) {
    ReadOpcodeReference e = new ReadOpcodeReference(opcodeReference, value, indirectReference);
    readReferences.add(e);
    addAccessReference(e);
    return e;
  }

  protected void clear() {
    writeReferences.clear();
    readReferences.clear();
    writeMemoryReferences.clear();
    readMemoryReferences.clear();
  }

  public WriteMemoryReference addWriteMemoryReference(T address, T value, boolean indirectReference) {
    WriteMemoryReference e = new WriteMemoryReference(address, value, memory, indirectReference);
    writeMemoryReferences.add(e);
    addAccessReference(e);
    return e;
  }

  public ReadMemoryReference<T> addReadMemoryReference(T address, T value, boolean indirectReference) {
    ReadMemoryReference<T> e = new ReadMemoryReference<T>(address, value, memory, indirectReference);
    readMemoryReferences.add(e);
    addAccessReference(e);
    return e;
  }

  public void undo() {
//    accessReferences.forEach(ar -> ar.undo());
  }

  public void setIndex(int i) {
    this.i = i;
  }
}
package com.fpetrola.z80.jspeccy;

import com.fpetrola.z80.mmu.Memory;

import com.fpetrola.z80.opcodes.references.TraceableWordNumber;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.spy.AbstractInstructionSpy;
import z80core.MemIoOps;

public class MemoryImplementation<T extends WordNumber> implements Memory<T> {
  private MemIoOps memory;
  private final AbstractInstructionSpy spy;
  int[] data = new int[0x10000];

  WordNumber[] traces = new WordNumber[0x10000];

  MemoryWriteListener memoryWriteListener;

  public MemoryImplementation(MemIoOps memory2, AbstractInstructionSpy spy) {
    this.memory = memory2;
    this.spy = spy;
  }

  public void update() {
    for (int i = 0; i < 0xFFFF; i++) {
      int j = memory.peek82(i) & 0xFF;
//      memory.poke82(i, j);
      data[i] = j;
    }
  }

  public T read(T address) {
    int i = address.intValue() & 0xFFFF;
    TraceableWordNumber value = WordNumber.createValue(data[i] & 0xff);

    if (!spy.wasFetched(i)) {
      WordNumber trace = traces[address.intValue()];
      if (trace != null) {
        if (trace instanceof TraceableWordNumber)
          value.copyReadAccess((TraceableWordNumber) trace, value);
      } else
        value.addReadAccess(i);

//      if (spy.getBitsWritten() != null)
//        for (int k = 0; k < 8; k++)
//          spy.getBitsWritten()[i * 8 + k] = true;
    }

    if (address instanceof TraceableWordNumber)
      value.merge(address, value);

    return (T) value;
  }

  @Override
  public void write(T address, T value) {
    byte b = (byte) (value.intValue() & 0xFF);
//    byte peek84 = (byte) memory.peek84(address);
//    if (peek84 != b) {
//      System.out.println("dsgadg");
//    }

    if (memoryWriteListener != null) {
      memoryWriteListener.writtingMemoryAt(address.intValue(), value.intValue());
    }

    int a = address.intValue() & 0xffff;
    data[a] = b;
//    if (memory.peek82(a) != (value.intValue() & 0xFF) )
//      System.out.println("upa!");
    memory.poke8(a, value.intValue());

    checkTrace(value, a);
  }

  private void checkTrace(T value, int a) {
    if (value instanceof TraceableWordNumber) {
      TraceableWordNumber traceableWordNumber = ((TraceableWordNumber) value);

      WordNumber trace = traces[a];
      if (trace != null)
        traceableWordNumber.copyReadAccess((TraceableWordNumber) trace, traceableWordNumber);
      traces[a] = value;

      if (spy.getBitsWritten() != null)
        if (a >= 0x4000 && a < 0x5800) {
          for (int r : traceableWordNumber.getReads()) {
//        if (r >= 0x8000 && r <= 0xC000) {
            for (int k = 0; k < 8; k++)
              spy.getBitsWritten()[r * 8 + k] = true;
            int a1 = 1;
//        }
          }
        }
    }
  }

  public boolean compare() {
    for (int i = 0; i < 0xFFFF; i++) {
      int j = memory.peek82(i) & 0xFF;
      if (data[i] != j)
        return false;
    }
    return true;
  }

  @Override
  public void setMemoryWriteListener(MemoryWriteListener memoryWriteListener) {
    this.memoryWriteListener = memoryWriteListener;
  }

  @Override
  public Memory getMemory() {
    return this;
  }

  @Override
  public void reset() {
    traces = new WordNumber[0x10000];
  }
}
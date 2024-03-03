package com.fpetrola.z80.jspeccy;

import com.fpetrola.z80.mmu.Memory;

import com.fpetrola.z80.opcodes.references.TraceableWordNumber;
import com.fpetrola.z80.opcodes.references.TraceableWordNumber.ReadOperation;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.spy.AbstractInstructionSpy;
import z80core.MemIoOps;

import java.util.ArrayList;
import java.util.List;

public class MemoryImplementation<T extends WordNumber> implements Memory<T> {
  private MemIoOps memory;
  private final AbstractInstructionSpy spy;
  int[] data = new int[0x10000];

  WordNumber[] traces = new WordNumber[0x10000];

  MemoryWriteListener memoryWriteListener;
  private List<MemoryReadListener> memoryReadListeners = new ArrayList<>();

  public MemoryImplementation(MemIoOps memory2, AbstractInstructionSpy spy) {
    this.memory = memory2;
    this.spy = spy;
  }

  public void update() {
    for (int i = 0; i < 0xFFFF; i++) {
      int j = memory.peek82(i) & 0xFF;
      data[i] = j;
    }
  }

  public T read(T address) {
//    if (address.intValue() == 24686) System.out.println("dgadg");

    int i = address.intValue() & 0xFFFF;
    WordNumber value = WordNumber.createValue(data[i] & 0xff);

    WordNumber trace = traces[i];
    if (trace != null) {
      value= trace.readOperation(address, WordNumber.createValue(data[i] & 0xff));
    } else {
      value = value.readOperation(address, value);
    }

    if (!spy.wasFetched(i)) {
      WordNumber finalValue = value;
      new ArrayList<>(memoryReadListeners).forEach(l -> l.readingMemoryAt(address, finalValue));
    }
    return (T) value;
  }

  @Override
  public void write(T address, T value) {
    byte b = (byte) (value.intValue() & 0xFF);
    if (memoryWriteListener != null) {
      memoryWriteListener.writtingMemoryAt(address.intValue(), value.intValue());
    }

    int a = address.intValue() & 0xffff;
    data[a] = b;
    memory.poke8(a, value.intValue());
    checkTrace(value, a);
  }

  private void checkTrace(T value, int a) {
    if (value instanceof TraceableWordNumber) {
      TraceableWordNumber traceableWordNumber = ((TraceableWordNumber) value);

      WordNumber trace = traces[a];
      traces[a] = value;

      if (value instanceof TraceableWordNumber wordNumber)
        wordNumber.purgeTooOlderPrevious();

      if (spy.getBitsWritten() != null)
        if (a >= 0x4000 && a < 0x5800) {
          List<ReadOperation> readOperations = traceableWordNumber.getFirstReadOperation();

          for (ReadOperation readOperation : readOperations) {
            int r = readOperation.address.intValue();
//        if (r >= 0x8000 && r <= 0xC000) {
            for (int k = 0; k < 8; k++) {
//            if (r < 49000 & r > 43000)
//              System.out.println("AAAAA");
              spy.getBitsWritten()[r * 8 + k] = true;
            }
            int a1 = 1;
//        }
          }
        }
    }
  }

  public boolean compare() {
    for (int i = 0; i < 0xFFFF; i++) {
      int j = memory.peek82(i) & 0xFF;
      if (data[i] != j) return false;
    }
    return true;
  }

  @Override
  public void setMemoryWriteListener(MemoryWriteListener memoryWriteListener) {
    this.memoryWriteListener = memoryWriteListener;
  }

  @Override
  public void reset() {
    traces = new WordNumber[0x10000];
  }

  @Override
  public void addMemoryReadListener(MemoryReadListener memoryReadListener) {
    this.memoryReadListeners.add(memoryReadListener);
  }

  @Override
  public void removeMemoryReadListener(MemoryReadListener memoryReadListener) {
    this.memoryReadListeners.remove(memoryReadListener);
  }
}
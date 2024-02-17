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
    TraceableWordNumber value = WordNumber.createValue(data[i] & 0xff);

    if (!spy.wasFetched(i)) {
      WordNumber trace = traces[i];
      if (trace != null) {
        if (trace instanceof TraceableWordNumber traceableWordNumber) {
          value.previous = traceableWordNumber;
          value.operation = new ReadOperation(address, WordNumber.createValue(data[i] & 0xff));
        }
      } else {
        value = value.readOperation(address, value);
      }
    }

//    if (address instanceof TraceableWordNumber)
//      value.merge(address, value);

    TraceableWordNumber finalValue = value;
    new ArrayList<>(memoryReadListeners).forEach(l -> l.readingMemoryAt(address, finalValue));

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

      if (spy.getBitsWritten() != null) if (a >= 0x4000 && a < 0x5800) {
        List<ReadOperation> readOperations = getFirstReadOperation(traceableWordNumber);

        for (ReadOperation readOperation : readOperations) {
          int r = readOperation.address.intValue();
//        if (r >= 0x8000 && r <= 0xC000) {
          for (int k = 0; k < 8; k++) {
            if (r < 49000 & r > 43000)
              System.out.println("AAAAA");
            spy.getBitsWritten()[r * 8 + k] = true;
          }
          int a1 = 1;
//        }
        }
      }
    }
  }

  private List getFirstReadOperation(TraceableWordNumber traceableWordNumber) {
    return findReadOperation(new ArrayList<>(), traceableWordNumber);
  }

  private List findReadOperation(List result, TraceableWordNumber current) {
    if (current != null) {
      if (current.operation != null && current.operation instanceof ReadOperation readOperation)
        result.add(readOperation);

      findReadOperation(result, current.previous);
      findReadOperation(result, current.previous2);
    }
    return result;
  }

  private ReadOperation getFirstReadOperation2(TraceableWordNumber traceableWordNumber) {
    TraceableWordNumber current = traceableWordNumber;
    while (current.previous2 != null) {
      current = current.previous2;
    }

    if (current.operation instanceof ReadOperation readOperation)
      return readOperation;
    else
      return null;
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
  public Memory getMemory() {
    return this;
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
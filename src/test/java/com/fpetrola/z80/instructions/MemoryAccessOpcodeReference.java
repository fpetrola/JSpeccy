package com.fpetrola.z80.instructions;

import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;

class MemoryAccessOpcodeReference<T extends WordNumber> implements OpcodeReference<T> {
  private final CpuTest<T> testBasicInstructionLoop;
  private final ImmutableOpcodeReference<T> c;

  public MemoryAccessOpcodeReference(CpuTest testBasicInstructionLoop, ImmutableOpcodeReference<T> c) {
    this.testBasicInstructionLoop = testBasicInstructionLoop;
    this.c = c;
  }

  @Override
  public T read() {
    return testBasicInstructionLoop.mem().read(c.read());
  }

  @Override
  public int getLength() {
    return 0;
  }

  @Override
  public void write(T value) {
    testBasicInstructionLoop.mem().write(c.read(), value);
  }

  @Override
  public Object clone() throws CloneNotSupportedException {
    return new MemoryAccessOpcodeReference<>(testBasicInstructionLoop, c);
  }

  @Override
  public String toString() {
    return "[" + c + "]";
  }
}

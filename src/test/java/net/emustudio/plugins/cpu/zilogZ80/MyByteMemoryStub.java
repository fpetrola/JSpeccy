package net.emustudio.plugins.cpu.zilogZ80;

import com.fpetrola.z80.jspeccy.MemoryWriteListener;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.opcodes.references.WordNumber;
import net.emustudio.cpu.testsuite.memory.ByteMemoryStub;
import net.emustudio.emulib.runtime.helpers.NumberUtils;

public class MyByteMemoryStub extends ByteMemoryStub {

  private Memory<WordNumber> memory;

  public MyByteMemoryStub() {
    super(NumberUtils.Strategy.LITTLE_ENDIAN);
  }

  public void init(Memory<WordNumber> memory1) {
    memory = memory1;
    memory1.setMemoryWriteListener(new MemoryWriteListener() {
      public void writtingMemoryAt(int address, int value) {
        MyByteMemoryStub.super.write(address, (byte) value);
      }
    });
  }

  @Override
  public void setMemory(Byte[] memory) {
    super.setMemory(memory);
  }

  @Override
  public void setMemory(byte[] memory) {
    super.setMemory(memory);
  }

  @Override
  public void setMemory(short[] memory) {
    super.setMemory(memory);
    for (int i = 0; i < memory.length; i++) {
      getMemory().write(WordNumber.createValue(i), WordNumber.createValue(memory[i]));
    }
  }

  private Memory<WordNumber> getMemory() {
    return memory;
  }

  @Override
  public void write(int memoryPosition, Byte value) {
    getMemory().write(WordNumber.createValue(memoryPosition), WordNumber.createValue(value));
    super.write(memoryPosition, value);
  }

  @Override
  public void write(int memoryPosition, Byte[] values) {
    super.write(memoryPosition, values);
  }

  @Override
  public void write(int memoryPosition, Byte[] cells, int count) {
    super.write(memoryPosition, cells, count);
  }

  @Override
  public Byte read(int memoryPosition) {
    return (byte) getMemory().read(WordNumber.createValue(memoryPosition)).intValue();
  }
}

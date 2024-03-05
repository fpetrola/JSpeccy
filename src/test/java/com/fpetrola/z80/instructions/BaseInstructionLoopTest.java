package com.fpetrola.z80.instructions;

import com.fpetrola.z80.opcodes.references.WordNumber;

import static com.fpetrola.z80.opcodes.references.WordNumber.createValue;

public class BaseInstructionLoopTest<T extends WordNumber> extends CpuTest<T> {
  @Override
  protected void setUpMemory() {
    initMem(() -> {
      WordNumber[] data = new WordNumber[0x10000];
      int base = 3592 * 4;
      base= 14368;
      data[base] = createValue(16);
      data[base + 1] = createValue(8);
      data[base + 2] = createValue(4);
      data[base + 3] = createValue(2);
      data[0] = createValue(1);
      data[0xFFFF] = createValue(1);
      data[1000] = createValue(123);

      return (T[]) data;
    });
  }
}

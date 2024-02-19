package com.fpetrola.z80.instructions;

import com.fpetrola.z80.opcodes.references.TraceableWordNumber;
import com.fpetrola.z80.opcodes.references.WordNumber;
import org.junit.Assert;
import org.junit.Test;

import static com.fpetrola.z80.opcodes.references.WordNumber.createValue;
import static com.fpetrola.z80.registers.RegisterName.DE;
import static com.fpetrola.z80.registers.RegisterName.HL;

public class TestXXXXXX<T extends WordNumber> extends CpuTest<T> {

  @Test
  public <T extends WordNumber> void testPlainPath() {
    memory.init(() -> {
      WordNumber[] data = new TraceableWordNumber[0x10000];
      int base = 3592 * 4;
      data[base] = createValue(16);
      data[base + 1] = createValue(8);
      data[base + 2] = createValue(4);
      data[base + 3] = createValue(2);
      return data;
    });

    de.write(createValue(520));

    instructionFetcher.add(new Ld(state, h, ot.c(createValue(7))));
    instructionFetcher.add(new Ld(state, l, a));
    instructionFetcher.add(new Add16(state, hl, hl));
    instructionFetcher.add(new Add16(state, hl, hl));
    instructionFetcher.add(new Add16(state, hl, hl));
    instructionFetcher.add(new Ld(state, b, ot.c(createValue(3))));

    instructionFetcher.add(new Ld(state, a, ot.iRR(HL)));
    instructionFetcher.add(new Ld(state, ot.iiRR(DE), a));
    instructionFetcher.add(new Inc16(state, hl));
    instructionFetcher.add(new Inc(state, d));
    instructionFetcher.add(new DJNZ(state, ot.c(createValue(-5))));
    instructionFetcher.add(new Ret(state, opc.t()));

    assertLoopSetup();

    assertLoopNumber(0, 16);
    assertLoopNumber(1, 8);
    assertLoopNumber(2, 4);
    Assert.assertEquals(11, pc.read().intValue());
  }

  private void assertLoopNumber(int increment, int memoryValue) {
    Assert.assertEquals(6, pc.read().intValue());
    z80.execute();
    Assert.assertEquals(memoryValue, a.read().intValue());
    z80.execute();
    Assert.assertEquals(memoryValue, memory.read(de.read()).intValue());
    z80.execute();
    Assert.assertEquals(14369 + increment, hl.read().intValue());
    z80.execute();
    Assert.assertEquals(3 + increment, d.read().intValue());
    Assert.assertEquals(10, pc.read().intValue());
    z80.execute();
  }

  private void assertLoopSetup() {
    a.write(createValue(4));
    z80.execute();
    Assert.assertEquals(7, h.read().intValue());
    z80.execute();
    Assert.assertEquals(4, l.read().intValue());
    z80.execute();
    Assert.assertEquals(3592, hl.read().intValue());
    z80.execute();
    Assert.assertEquals(3592 * 2, hl.read().intValue());
    z80.execute();
    Assert.assertEquals(3592 * 4, hl.read().intValue());
    z80.execute();
    Assert.assertEquals(3, b.read().intValue());
  }

}

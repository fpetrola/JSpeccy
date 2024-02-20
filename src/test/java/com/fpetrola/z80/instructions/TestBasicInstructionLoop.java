package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.TraceableWordNumber;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Composed16BitRegister;
import com.fpetrola.z80.registers.Plain16BitRegister;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterName;
import org.junit.Test;

import static com.fpetrola.z80.opcodes.references.WordNumber.createValue;
import static com.fpetrola.z80.registers.RegisterName.*;
import static org.junit.Assert.assertEquals;

@SuppressWarnings("ALL")
public class TestBasicInstructionLoop<T extends WordNumber> extends CpuTest<T> {

  private void setUpTest() {
    memory.init(() -> {
      WordNumber[] data = new TraceableWordNumber[0x10000];
      int base = 3592 * 4;
      data[base] = createValue(16);
      data[base + 1] = createValue(8);
      data[base + 2] = createValue(4);
      data[base + 3] = createValue(2);
      return data;
    });
  }

  @Test
  public void testPlainPath() {
    setUpTest();
    de.write(createValue(520));

    add(new Ld(state, h, ot.c(createValue(7))));
    add(new Ld(state, l, a));
    add(new Add16(state, hl, hl));
    add(new Add16(state, hl, hl));
    add(new Add16(state, hl, hl));
    add(new Ld(state, b, ot.c(createValue(3))));

    add(new Ld(state, a, ot.iRR(hl)));
    add(new Ld(state, ot.iiRR(de), a));
    add(new Inc16(state, hl));
    add(new Inc(state, d));
    add(new DJNZ(state, ot.c(createValue(-5))));
    add(new Ret(state, opc.t()));

    assertLoopSetup();

    assertLoopNumber(0, 16);
    assertLoopNumber(1, 8);
    assertLoopNumber(2, 4);
    assertEquals(11, pc.read().intValue());
  }

  private void assertLoopNumber(int increment, int memoryValue) {
    assertEquals(6, pc.read().intValue());
    step();
    assertEquals(memoryValue, a.read().intValue());
    step();
    assertEquals(memoryValue, memory.read(de.read()).intValue());
    step();
    assertEquals(14369 + increment, hl.read().intValue());
    step();
    assertEquals(3 + increment, d.read().intValue());
    assertEquals(10, pc.read().intValue());
    step();
  }

  private void assertLoopSetup() {
    a.write(createValue(4));
    step();
    assertEquals(7, h.read().intValue());
    step();
    assertEquals(4, l.read().intValue());
    step();
    assertEquals(3592, hl.read().intValue());
    step();
    assertEquals(3592 * 2, hl.read().intValue());
    step();
    assertEquals(3592 * 4, hl.read().intValue());
    step();
    assertEquals(3, b.read().intValue());
  }


  @Test
  public void testComposite() {
    setUpTest();
    de.write(createValue(520));
    a.write(createValue(4));

    step(new Ld(state, b, ot.c(createValue(3))));
    assertEquals(3, b.read().intValue());


    Register<T> in1 = a;

    PipeRegister<T> vr1 = cr(p1 -> {
      Register<T> cr = cr(p2 -> {
        Register<T> cr1 = cr(p3 -> {
          OpcodeReference<T> pair = pair(cr(j1 -> new Ld(state, j1, ot.c(createValue(7)))), cr(j2 -> new Ld(state, j2, in1)));
          return new Add16(state, p3.r(pair), pair
          );
        });
        return new Add16(state, p2.r(cr1), cr1);
      });
      return new Add16(state, p1.r(cr), cr);
    });

    step(new Ld(state, ot.iiRR(de), cr(p1 -> new Ld(state, p1, ot.iRR(vr1)))));
    assertEquals(16, memory.read(createValue(520)).intValue());
    assertEquals(14368, vr1.read().intValue());

    step(new Inc16(state, vr1));
    assertEquals(14369, vr1.read().intValue());
    assertEquals(2, d.read().intValue());

    step(new Inc(state, d));
    assertEquals(3, d.read().intValue());


    step(new DJNZ(state, ot.c(createValue(-4))));
    assertEquals(1, pc.read().intValue());
    assertEquals(2, b.read().intValue());

    step(new Ret(state, opc.t()));

    assertEquals(2, b.read().intValue());
    assertEquals(8, memory.read(createValue(520 + 256)).intValue());
  }

  private OpcodeReference<T> pair(Register<T> cr, Register<T> cr1) {
    return new Composed16BitRegister<>(VIRTUAL, cr, cr1);
  }

  private PipeRegister<T> cr(InstructionAdapter ia) {
    PipeRegister<T> register = new PipeRegister<>();
    Instruction adapt = ia.adapt(register);
    register.setInstruction(adapt);
    return register;
  }

}

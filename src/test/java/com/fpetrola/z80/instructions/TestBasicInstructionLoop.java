package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.opcodes.references.*;
import com.fpetrola.z80.registers.*;
import org.junit.Test;

import static com.fpetrola.z80.opcodes.references.WordNumber.createValue;
import static org.junit.Assert.assertEquals;

@SuppressWarnings("ALL")
public class TestBasicInstructionLoop<T extends WordNumber> extends CpuTest<T> {
  private void setUpMemory() {
    memory.init(() -> {
      WordNumber[] data = new TraceableWordNumber[0x10000];
      int base = 3592 * 4;
      data[base] = createValue(16);
      data[base + 1] = createValue(8);
      data[base + 2] = createValue(4);
      data[base + 3] = createValue(2);
      data[0] = createValue(1);
      data[0xFFFF] = createValue(1);
      return data;
    });
  }

  @Test
  public void testPlainPath() {
    createPlainExecution();

    assertLoopSetup();

    assertLoopNumber(0, 16);
    assertLoopNumber(1, 8);
    assertLoopNumber(2, 4);
    assertEquals(11, pc.read().intValue());
    step();
    assertEquals(257, pc.read().intValue());
  }

  @Test
  public void testPlainPath2() {
    createPlainExecution();

    step();

    Instruction instructionAt = instructionFetcher.getInstructionAt(0);
    System.out.println(instructionAt);
    step();
    step();
    step();

  }


  private void createPlainExecution() {
    setUpMemory();
    de.write(createValue(520));

    add(new___.Ld(h, ot.c(createValue(7))));
    add(new___.Ld(l, a));
    // add(new SET(state, l, 7, 0));
    add(new___.Add16(hl, hl));
    add(new___.Add16(hl, hl));
    add(new___.Add16(hl, hl));
    add(new___.Ld(b, ot.c(createValue(3))));

    add(new___.Ld(a, ot.iRR(hl)));
    add(new___.Ld(ot.iiRR(de), a));
    add(new___.Inc16(hl));
    add(new___.Inc(d));
    add(new___.DJNZ(ot.c(createValue(-5))));
    add(new___.Ret(opc.t()));
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
    setUpMemory();

    de.write(createValue(520));
    a.write(createValue(4));

    Register<T> memoryWriterHigh = cr(j2 -> new___.Ld(j2, d));
    ImmutableOpcodeReference<T> pair2 = createPair(memoryWriterHigh, e);
    Register<T> memoryWriter = cr(p1 -> new___.Ld(p1.r(pair2), pair2));

    Register<T> counter = cr(j2 -> new___.Ld(j2, ot.c(createValue(3))));

    RegisterPair<T> pair = createPair(ot.c(createValue(7)), a);
    Register<T> cr1 = cr(hl1 -> new___.Add16(hl1.r(pair), pair));
    Register<T> cr2 = cr(hl1 -> new___.Add16(hl1.r(cr1), cr1));
    Register<T> memoryReader = cr(hl1 -> new___.Add16(hl1.r(cr2), cr2));

    add(new___.Ld(ot.iiRR(memoryWriter), cr(p1 -> new___.Ld(p1, ot.iRR(memoryReader)))));
    add(new___.Inc16(memoryReader));
    add(new___.Inc(memoryWriterHigh));
    add(new DJNZ(ot.c(createValue(-4)), counter, pc));
    add(new___.Ret(opc.t()));

    checkInstructionsStructure();

    checkExecution(counter, memoryReader, memoryWriterHigh);
  }

  private void checkExecution(Register<T> counter, Register<T> memoryReader, Register<T> memoryWriterHigh) {
    assertEquals(3, counter.read().intValue());

    assertCompositeLoop(memoryReader, counter, 3, 16, 14368, 2, 520, memoryWriterHigh);
    assertEquals(0, pc.read().intValue());
    assertCompositeLoop(memoryReader, counter, 2, 8, 14369, 3, 520 + 256, memoryWriterHigh);
    assertEquals(0, pc.read().intValue());
    assertCompositeLoop(memoryReader, counter, 1, 4, 14370, 4, 520 + 256 + 256, memoryWriterHigh);
    assertEquals(4, pc.read().intValue());

    step();
    assertEquals(257, pc.read().intValue());
  }

  private void checkInstructionsStructure() {
    Ld ld1 = assertTypeAndCast(Ld.class, instructionFetcher.getInstructionAt(0));
    IndirectMemory16BitReference iiRR1 = assertTypeAndCast(IndirectMemory16BitReference.class, ld1.getTarget());
    VirtualPlain8BitRegister vpr1 = assertTypeAndCast(VirtualPlain8BitRegister.class, iiRR1.target);
    Ld ld1_a = assertTypeAndCast(Ld.class, vpr1.getInstruction());

    PipeRegister ld1_a_pipe = assertTypeAndCast(PipeRegister.class, ld1_a.getTarget());

    Composed16BitRegister ld1_a_pipe_pair = assertTypeAndCast(Composed16BitRegister.class, ld1_a_pipe.readSupplier);
    assertEquals(2, ((T) ld1_a_pipe_pair.getHigh().read()).intValue());
    assertEquals(8, ((T) ld1_a_pipe_pair.getLow().read()).intValue());

    VirtualPlain8BitRegister ld1_a_pipe_pair_h = assertTypeAndCast(VirtualPlain8BitRegister.class, ld1_a_pipe_pair.getHigh());
    Ld ld4 = assertTypeAndCast(Ld.class, ld1_a_pipe_pair_h.getInstruction());
    VirtualPlain8BitRegister memoryWriterHighRef = assertTypeAndCast(VirtualPlain8BitRegister.class, ld4.getSource());

    VirtualPlain8BitRegister vpr2 = assertTypeAndCast(VirtualPlain8BitRegister.class, ld1.getSource());
    Ld ld2 = assertTypeAndCast(Ld.class, vpr2.getInstruction());
    IndirectMemory8BitReference iRR1 = assertTypeAndCast(IndirectMemory8BitReference.class, ld2.getSource());

    VirtualPlain8BitRegister memReader = assertTypeAndCast(VirtualPlain8BitRegister.class, iRR1.target);

    Instruction add16_1 = assertCompositeAdd16(memReader.getInstruction());
    Instruction add16_2 = assertCompositeAdd16(add16_1);
    Add16 add16_3 = (Add16) assertCompositeAdd16(add16_1);

    PipeRegister pipeRegister = assertTypeAndCast(PipeRegister.class, add16_3.getTarget());

    Composed16BitRegister add16_1_a = assertTypeAndCast(Composed16BitRegister.class, pipeRegister.readSupplier);
    assertEquals(7, ((T) add16_1_a.getHigh().read()).intValue());
    assertEquals(4, ((T) add16_1_a.getLow().read()).intValue());


    Inc16 inc16_a = assertTypeAndCast(Inc16.class, instructionFetcher.getInstructionAt(1));

    assertEquals(memReader, inc16_a.getTarget());

    Inc inc_a = assertTypeAndCast(Inc.class, instructionFetcher.getInstructionAt(2));
    VirtualPlain8BitRegister inc_a_target = assertTypeAndCast(VirtualPlain8BitRegister.class, inc_a.getTarget());

    assertEquals(memoryWriterHighRef, inc_a_target);


    DJNZ djnz = assertTypeAndCast(DJNZ.class, instructionFetcher.getInstructionAt(3));

    assertEquals(-4, ((T) djnz.getPositionOpcodeReference().read()).intValue());
    assertEquals(3, ((T) djnz.getB().read()).intValue());

    Ret ret = assertTypeAndCast(Ret.class, instructionFetcher.getInstructionAt(4));
  }

  private Instruction assertCompositeAdd16(Instruction instruction) {
    Add16 add16 = assertTypeAndCast(Add16.class, instruction);
    PipeRegister pipeRegister = assertTypeAndCast(PipeRegister.class, add16.getTarget());
    VirtualPlain8BitRegister add16_1_a = assertTypeAndCast(VirtualPlain8BitRegister.class, pipeRegister.readSupplier);
    assertEquals(pipeRegister.readSupplier, add16.getSource());
    return add16_1_a.getInstruction();
  }

  private <J> J assertTypeAndCast(Class<? extends J> expected, Object i1) {
    assertEquals(expected, i1.getClass());
    J ld1 = (J) i1;
    return ld1;
  }

  private RegisterPair<T> createPair(ImmutableOpcodeReference immutableOpcodeReference, Register<T> register) {
    return pair(cr(refHigh -> new___.Ld(refHigh, immutableOpcodeReference)), cr(refLow -> new___.Ld(refLow, register)));
  }

  private void assertCompositeLoop(Register<T> vr1, Register<T> counter, int bValue, int memoryReadValue, int indexValue, int dValue, int readAddress, Register<T> vr2A) {
    step();

    assertEquals(memoryReadValue, memory.read(createValue(readAddress)).intValue());
    assertEquals(indexValue, vr1.read().intValue());

    step();
    assertEquals(indexValue + 1, vr1.read().intValue());
    assertEquals(dValue, vr2A.read().intValue());

    step();
    assertEquals(dValue + 1, vr2A.read().intValue());

    step();
    assertEquals(bValue - 1, counter.read().intValue());
  }

  private RegisterPair<T> pair(Register<T> cr, Register<T> cr1) {
    return new Composed16BitRegister<>(RegisterName.VIRTUAL, cr, cr1);
  }

  private Register<T> cr(InstructionAdapter ia) {
    PipeRegister<T> register = new PipeRegister<>();
    Instruction instruction = ia.adapt(register);
    return new VirtualPlain8BitRegister(instruction, register, this.nestedInstructionExecutor);
  }
}

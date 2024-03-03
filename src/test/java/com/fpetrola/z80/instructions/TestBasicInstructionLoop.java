package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.opcodes.references.*;
import com.fpetrola.z80.registers.*;
import org.junit.Test;

import static com.fpetrola.z80.opcodes.references.OpcodeConditions.t;
import static com.fpetrola.z80.opcodes.references.WordNumber.createValue;
import static com.fpetrola.z80.registers.RegisterName.*;
import static org.junit.Assert.assertEquals;

@SuppressWarnings("ALL")
public class TestBasicInstructionLoop<T extends WordNumber> extends CpuTest<T> {

  private void setUpMemory() {
    mem().init(() -> {
      WordNumber[] data = new TraceableWordNumber[0x10000];
      int base = 3592 * 4;
      data[base] = createValue(16);
      data[base + 1] = createValue(8);
      data[base + 2] = createValue(4);
      data[base + 3] = createValue(2);
      data[0] = createValue(1);
      data[0xFFFF] = createValue(1);
      return (T[]) data;
    });
  }

  @Test
  public void testPlainPath() {
    createPlainExecution();

    assertLoopSetup();

    assertLoopNumber(0, 16);
    assertLoopNumber(1, 8);
    assertLoopNumber(2, 4);
    assertEquals(11, r(PC).read().intValue());
    step();
    assertEquals(257, r(PC).read().intValue());
  }

  @Test
  public void testPlainPath2() {
    createPlainExecution();

    step();

    Instruction instructionAt = getInstructionAt(0);
    System.out.println(instructionAt);
    step();
    step();
    step();
  }


  private void createPlainExecution() {
    setUpMemory();
    r(DE).write(createValue(520));

    add(new Ld(r(H), c(7), f()));
    add(new Ld(r(L), r(A), f()));
    // add(new SET(state, l, 7, 0));
    add(new Add16(r(HL), r(HL), f()));
    add(new Add16(r(HL), r(HL), f()));
    add(new Add16(r(HL), r(HL), f()));
    add(new Ld(r(B), c(3), f()));

    add(new Ld(r(A), iRR(r(HL)), f()));
    add(new Ld(iiRR(r(DE)), r(A), f()));
    add(new Inc16(r(HL)));
    add(new Inc(r(D), f()));
    add(new DJNZ(c(-5), r(B), r(PC)));
    add(new Ret(t(), r(SP), mem(), r(PC)));
  }

  private void assertLoopNumber(int increment, int memoryValue) {
    assertEquals(6, r(PC).read().intValue());
    step();
    assertEquals(memoryValue, r(A).read().intValue());
    step();
    assertEquals(memoryValue, mem().read(r(DE).read()).intValue());
    step();
    assertEquals(14369 + increment, r(HL).read().intValue());
    step();
    assertEquals(3 + increment, r(D).read().intValue());
    assertEquals(10, r(PC).read().intValue());
    step();
  }

  private void assertLoopSetup() {
    r(A).write(createValue(4));
    step();
    assertEquals(7, r(H).read().intValue());
    step();
    assertEquals(4, r(L).read().intValue());
    step();
    assertEquals(3592, r(HL).read().intValue());
    step();
    assertEquals(3592 * 2, r(HL).read().intValue());
    step();
    assertEquals(3592 * 4, r(HL).read().intValue());
    step();
    assertEquals(3, r(B).read().intValue());
  }


  @Test
  public void testComposite() {
    setUpMemory();

    r(DE).write(createValue(520));
    r(A).write(createValue(4));

    ChainedRegister<T> memoryWriterHigh = cr(j2 -> new Ld(j2, r(D), f()));
    ChainedRegister<T> pair2 = createPair(memoryWriterHigh, r(E));
    Register<T> memoryWriter = cr(p1 -> new Ld(p1.r(pair2), pair2, f()), pair2);

    Register<T> counter = cr(j2 -> new Ld(j2, c(3), f()));

    ChainedRegister<T> pair = createPair(c(7), r(A));
    ChainedRegister<T> cr1 = cr(hl1 -> new Add16(hl1.r(pair), pair, f()), pair);
    ChainedRegister<T> cr2 = cr(hl1 -> new Add16(hl1.r(cr1), cr1, f()), cr1);
    ChainedRegister<T> memoryReader = cr(hl1 -> new Add16(hl1.r(cr2), cr2, f()), cr2);

    add(new Ld(iiRR(memoryWriter), cr(p1 -> new Ld(p1, iRR(memoryReader), f()), memoryReader), f()));
    add(new Inc16(memoryReader));
    add(new Inc(memoryWriterHigh, f()));
    add(new DJNZ(c(-4), counter, r(PC)));
    add(new Ret(t(), r(SP), mem(), r(PC)));

    checkInstructionsStructure();
    checkExecution(counter, memoryReader, memoryWriterHigh);
  }

  private void checkExecution(Register<T> counter, Register<T> memoryReader, Register<T> memoryWriterHigh) {
    assertEquals(3, counter.read().intValue());

    assertCompositeLoop(memoryReader, counter, 3, 16, 14368, 2, 520, memoryWriterHigh);
    assertEquals(0, r(PC).read().intValue());
    assertCompositeLoop(memoryReader, counter, 2, 8, 14369, 3, 520 + 256, memoryWriterHigh);
    assertEquals(0, r(PC).read().intValue());
    assertCompositeLoop(memoryReader, counter, 1, 4, 14370, 4, 520 + 256 + 256, memoryWriterHigh);
    assertEquals(4, r(PC).read().intValue());

    step();
    assertEquals(257, r(PC).read().intValue());
  }

  private void checkInstructionsStructure() {
    Ld ld1 = assertTypeAndCast(Ld.class, getInstructionAt(0));
    IndirectMemory16BitReference iiRR1 = assertTypeAndCast(IndirectMemory16BitReference.class, ld1.getTarget());
    VirtualPlain8BitRegister vpr1 = assertTypeAndCast(VirtualPlain8BitRegister.class, iiRR1.target);
    Ld ld1_a = assertTypeAndCast(Ld.class, vpr1.getInstruction());

    PipeRegister ld1_a_pipe = assertTypeAndCast(PipeRegister.class, ld1_a.getTarget());

    ChainedComposed16BitRegister ld1_a_pipe_pair = assertTypeAndCast(ChainedComposed16BitRegister.class, ld1_a_pipe.readSupplier);
    assertEquals(2, ((T) ld1_a_pipe_pair.getHigh().read()).intValue());
    assertEquals(8, ((T) ld1_a_pipe_pair.getLow().read()).intValue());

    VirtualPlain8BitRegister memoryWriterHighRef = assertTypeAndCast(VirtualPlain8BitRegister.class, ld1_a_pipe_pair.getHigh());
//    Ld ld4 = assertTypeAndCast(Ld.class, ld1_a_pipe_pair_h.getInstruction());
//    VirtualPlain8BitRegister memoryWriterHighRef = assertTypeAndCast(VirtualPlain8BitRegister.class, ld4.getSource());

    VirtualPlain8BitRegister vpr2 = assertTypeAndCast(VirtualPlain8BitRegister.class, ld1.getSource());
    Ld ld2 = assertTypeAndCast(Ld.class, vpr2.getInstruction());
    IndirectMemory8BitReference iRR1 = assertTypeAndCast(IndirectMemory8BitReference.class, ld2.getSource());

    VirtualPlain8BitRegister memReader = assertTypeAndCast(VirtualPlain8BitRegister.class, iRR1.target);

    Instruction add16_1 = assertCompositeAdd16(memReader.getInstruction());
    Instruction add16_2 = assertCompositeAdd16(add16_1);
    Add16 add16_3 = (Add16) assertCompositeAdd16(add16_1);

    PipeRegister pipeRegister = assertTypeAndCast(PipeRegister.class, add16_3.getTarget());

    ChainedComposed16BitRegister add16_1_a = assertTypeAndCast(ChainedComposed16BitRegister.class, pipeRegister.readSupplier);
    assertEquals(7, ((T) add16_1_a.getHigh().read()).intValue());
    assertEquals(4, ((T) add16_1_a.getLow().read()).intValue());


    Inc16 inc16_a = assertTypeAndCast(Inc16.class, getInstructionAt(1));

    assertEquals(memReader, inc16_a.getTarget());

    Inc inc_a = assertTypeAndCast(Inc.class, getInstructionAt(2));
    VirtualPlain8BitRegister inc_a_target = assertTypeAndCast(VirtualPlain8BitRegister.class, inc_a.getTarget());

    assertEquals(memoryWriterHighRef, inc_a_target);


    DJNZ djnz = assertTypeAndCast(DJNZ.class, getInstructionAt(3));

    assertEquals(-4, ((T) djnz.getPositionOpcodeReference().read()).intValue());
    assertEquals(3, ((T) djnz.getB().read()).intValue());

    Ret ret = assertTypeAndCast(Ret.class, getInstructionAt(4));
  }

  private Instruction assertCompositeAdd16(Instruction instruction) {
    Add16 add16 = assertTypeAndCast(Add16.class, instruction);
    PipeRegister pipeRegister = assertTypeAndCast(PipeRegister.class, add16.getTarget());
    VirtualPlain8BitRegister add16_1_a = assertTypeAndCast(VirtualPlain8BitRegister.class, pipeRegister.readSupplier);
    assertEquals(pipeRegister.readSupplier, add16.getSource());
    return add16_1_a.getInstruction();
  }

  private void assertCompositeLoop(Register<T> vr1, Register<T> counter, int bValue, int memoryReadValue, int indexValue, int dValue, int readAddress, Register<T> vr2A) {
    step();

    assertEquals(memoryReadValue, mem().read(createValue(readAddress)).intValue());
    assertEquals(indexValue, vr1.read().intValue());

    step();
    assertEquals(indexValue + 1, vr1.read().intValue());
    assertEquals(dValue, vr2A.read().intValue());

    step();
    assertEquals(dValue + 1, vr2A.read().intValue());

    step();
    assertEquals(bValue - 1, counter.read().intValue());
  }


  @Test
  public void testStepFromFirstReflectedAtSecond() {
    setupCompositeTest();
    step();
    useSecond();

    assertEquals(7, r(H).read().intValue());
    assertEquals(Ld.class, getInstructionAt(0).getClass());
  }

  private void setupCompositeTest() {
    useFirst();
    setUpMemory();
    createPlainExecution();

    useSecond();
    setUpMemory();
    createPlainExecution();

    useFirst();
  }

}

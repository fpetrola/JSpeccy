package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.opcodes.references.*;
import com.fpetrola.z80.registers.*;
import com.fpetrola.z80.spy.MemorySpy;
import org.junit.Test;

import static com.fpetrola.z80.opcodes.references.OpcodeConditions.t;
import static com.fpetrola.z80.opcodes.references.WordNumber.createValue;
import static com.fpetrola.z80.registers.RegisterName.*;
import static org.junit.Assert.assertEquals;

@SuppressWarnings("ALL")
public class TestBasicInstructionLoop<T extends WordNumber> extends CpuTest<T> {

  private Register<T> _1(RegisterName registerName) {
    return state.r(registerName);
  }

  private void setUpMemory() {
    _m1().init(() -> {
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
    assertEquals(11, _1(PC).read().intValue());
    step();
    assertEquals(257, _1(PC).read().intValue());
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
    _1(DE).write(createValue(520));

    add(new___.Ld(_1(H), ot.c(7)));
    add(new___.Ld(_1(L), _1(A)));
    // add(new SET(state, l, 7, 0));
    add(new___.Add16(_1(HL), _1(HL)));
    add(new___.Add16(_1(HL), _1(HL)));
    add(new___.Add16(_1(HL), _1(HL)));
    add(new___.Ld(_1(B), ot.c(3)));

    add(new___.Ld(_1(A), ot.iRR(_1(HL))));
    add(new___.Ld(ot.iiRR(_1(DE)), _1(A)));
    add(new___.Inc16(_1(HL)));
    add(new___.Inc(_1(D)));
    add(new___.DJNZ(ot.c(-5)));
    add(new___.Ret(t()));
  }

  private void assertLoopNumber(int increment, int memoryValue) {
    assertEquals(6, _1(PC).read().intValue());
    step();
    assertEquals(memoryValue, _1(A).read().intValue());
    step();
    assertEquals(memoryValue, _m1().read(_1(DE).read()).intValue());
    step();
    assertEquals(14369 + increment, _1(HL).read().intValue());
    step();
    assertEquals(3 + increment, _1(D).read().intValue());
    assertEquals(10, _1(PC).read().intValue());
    step();
  }

  private MockedMemory<T> _m1() {
    return (MockedMemory<T>) ((MemorySpy<T>) z80.getState().getMemory()).getMemory();
  }

  private void assertLoopSetup() {
    _1(A).write(createValue(4));
    step();
    assertEquals(7, _1(H).read().intValue());
    step();
    assertEquals(4, _1(L).read().intValue());
    step();
    assertEquals(3592, _1(HL).read().intValue());
    step();
    assertEquals(3592 * 2, _1(HL).read().intValue());
    step();
    assertEquals(3592 * 4, _1(HL).read().intValue());
    step();
    assertEquals(3, _1(B).read().intValue());
  }


  @Test
  public void testComposite() {
    setUpMemory();

    _1(DE).write(createValue(520));
    _1(A).write(createValue(4));

    Register<T> memoryWriterHigh = cr(j2 -> new___.Ld(j2, _1(D)));
    ImmutableOpcodeReference<T> pair2 = createPair(memoryWriterHigh, _1(E));
    Register<T> memoryWriter = cr(p1 -> new___.Ld(p1.r(pair2), pair2));

    Register<T> counter = cr(j2 -> new___.Ld(j2, ot.c(3)));

    RegisterPair<T> pair = createPair(ot.c(7), _1(A));
    Register<T> cr1 = cr(hl1 -> new___.Add16(hl1.r(pair), pair));
    Register<T> cr2 = cr(hl1 -> new___.Add16(hl1.r(cr1), cr1));
    Register<T> memoryReader = cr(hl1 -> new___.Add16(hl1.r(cr2), cr2));

    add(new___.Ld(ot.iiRR(memoryWriter), cr(p1 -> new___.Ld(p1, ot.iRR(memoryReader)))));
    add(new___.Inc16(memoryReader));
    add(new___.Inc(memoryWriterHigh));
    add(new DJNZ(ot.c(-4), counter, _1(PC)));
    add(new___.Ret(t()));

    checkInstructionsStructure();

    checkExecution(counter, memoryReader, memoryWriterHigh);
  }

  private void checkExecution(Register<T> counter, Register<T> memoryReader, Register<T> memoryWriterHigh) {
    assertEquals(3, counter.read().intValue());

    assertCompositeLoop(memoryReader, counter, 3, 16, 14368, 2, 520, memoryWriterHigh);
    assertEquals(0, _1(PC).read().intValue());
    assertCompositeLoop(memoryReader, counter, 2, 8, 14369, 3, 520 + 256, memoryWriterHigh);
    assertEquals(0, _1(PC).read().intValue());
    assertCompositeLoop(memoryReader, counter, 1, 4, 14370, 4, 520 + 256 + 256, memoryWriterHigh);
    assertEquals(4, _1(PC).read().intValue());

    step();
    assertEquals(257, _1(PC).read().intValue());
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

    assertEquals(memoryReadValue, _m1().read(createValue(readAddress)).intValue());
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

package com.fpetrola.z80.instructions;

import com.fpetrola.z80.cpu.*;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.jspeccy.MemoryReadListener;
import com.fpetrola.z80.jspeccy.MemoryWriteListener;
import com.fpetrola.z80.mmu.IO;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.*;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.spy.NullInstructionSpy;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.fpetrola.z80.opcodes.references.WordNumber.createValue;
import static com.fpetrola.z80.registers.RegisterName.*;

public class TestXXXXXX<T extends WordNumber> {
  private Register<T> a;
  private Register<T> b;
  private Register<T> d;
  private Register<T> h;
  private Register<T> l;
  private Register<T> hl;
  private Register<T> de;
  private OpcodeTargets ot;

  private Register<T> pc;

  private State<T> state;
  private TestInstructionFetcher instructionFetcher;
  private OOZ80 z80;
  protected OpcodeConditions opc;
  private MyMemory memory;

  @Before
  public <T extends WordNumber> void setUp() {
    NullInstructionSpy spy = new NullInstructionSpy();
    TraceableWordNumber.instructionSpy = spy;

    memory = new MyMemory();
    state = new State(spy, memory, new MyIO());
    a = state.getRegister(A);
    b = state.getRegister(B);
    d = state.getRegister(D);
    h = state.getRegister(H);
    l = state.getRegister(L);
    hl = state.getRegister(HL);
    de = state.getRegister(DE);
    pc = state.getRegister(PC);
    ot = new OpcodeTargets(state);
    opc = new OpcodeConditions(state);

    instructionFetcher = new TestInstructionFetcher(state);
    z80 = new OOZ80(state, instructionFetcher, new DefaultInstructionExecutor(spy));
    z80.reset();
    instructionFetcher.reset();

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
  }

  @Test
  public <T extends WordNumber> void testPlainPath() {
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

  private static class TestInstructionFetcher<T extends WordNumber> implements InstructionFetcher<T> {
    List<Instruction<T>> instructions = new ArrayList<>();
    private int i;
    private Register<T> pc;

    public TestInstructionFetcher(State<T> state) {
      pc = state.getRegister(PC);
    }

    public int getOpcodeInt() {
      return 0;
    }

    public void fetchNextInstruction(InstructionExecutor<T> instructionExecutor) {
      T pcValue = pc.read();
      Instruction<T> instruction = instructions.get(pcValue.intValue());
      instructionExecutor.execute(instruction, -1, pcValue);
      if (instruction.getNextPC() == null)
        pc.write(pc.read().plus1());
      else
        pc.write(instruction.getNextPC());
    }

    public void reset() {
      pc.write(createValue(0));
    }

    public int add(Instruction<T> instruction) {
      instructions.add(instruction);
      return instructions.size();
    }
  }

  private static class MyMemory<T extends WordNumber> implements Memory<T> {
    private T[] data;

    public MyMemory() {
      this.data = (T[]) new TraceableWordNumber[0x10000];
      int base = 3592 * 4;
      data[base] = createValue(16);
      data[base + 1] = createValue(8);

      data[base + 2] = createValue(4);
      data[base + 3] = createValue(2);
    }

    @Override
    public T read(T address) {
      return data[address.intValue()];
    }

    @Override
    public void write(T address, T value) {
      data[address.intValue()] = value;
    }

    @Override
    public boolean compare() {
      return false;
    }

    @Override
    public void update() {

    }

    @Override
    public void setMemoryWriteListener(MemoryWriteListener memoryWriteListener) {

    }

    @Override
    public void reset() {

    }

    @Override
    public void addMemoryReadListener(MemoryReadListener memoryReadListener) {

    }

    @Override
    public void removeMemoryReadListener(MemoryReadListener memoryReadListener) {

    }
  }

  private static class MyIO implements IO {
    public Object in(Object port) {
      return null;
    }

    public void out(Object port, Object value) {
    }
  }
}

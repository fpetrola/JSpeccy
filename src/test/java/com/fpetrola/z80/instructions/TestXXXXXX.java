package com.fpetrola.z80.instructions;

import com.fpetrola.z80.cpu.*;
import com.fpetrola.z80.instructions.base.ConditionalInstruction;
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
  private State<T> state;
  private TestInstructionFetcher instructionFetcher;
  private OOZ80 z80;
  protected OpcodeConditions opc;

  @Before
  public <T extends WordNumber> void setUp() {
    NullInstructionSpy spy = new NullInstructionSpy();
    TraceableWordNumber.instructionSpy = spy;

    state = new State(spy, new MyMemory(), new MyIO());
    a = state.getRegister(A);
    b = state.getRegister(B);
    d = state.getRegister(D);
    h = state.getRegister(H);
    l = state.getRegister(L);
    hl = state.getRegister(HL);
    de = state.getRegister(DE);
    ot = new OpcodeTargets(state);
    opc = new OpcodeConditions(state);

    instructionFetcher = new TestInstructionFetcher();
    z80 = new OOZ80(state, instructionFetcher, new DefaultInstructionExecutor(spy));
    instructionFetcher.add(new Ld(state, h, ot.c(createValue(7))));
    instructionFetcher.add(new Ld(state, l, a));
    instructionFetcher.add(new Add16(state, hl, hl));
    instructionFetcher.add(new Add16(state, hl, hl));
    instructionFetcher.add(new Add16(state, hl, hl));
    instructionFetcher.add(new Ld(state, b, ot.c(createValue(8))));

    int loopPlace = instructionFetcher.add(new Ld(state, a, ot.iiRR(HL)));
    instructionFetcher.add(new Ld(state, ot.iiRR(DE), a));
    instructionFetcher.add(new Inc(state, hl));
    instructionFetcher.add(new Inc(state, d));
    instructionFetcher.add(new DJNZ(state, ot.c(createValue(-loopPlace))));
    instructionFetcher.add(new Ret(state, opc.t()));
  }

  @Test
  public <T extends WordNumber> void test1() {
    a.write(createValue(4));
    z80.execute();
    Assert.assertEquals(7, h.read().intValue());
    z80.execute();
    Assert.assertEquals(4, l.read().intValue());
    z80.execute();
    Assert.assertEquals(3592, hl.read().intValue());
    z80.execute();
    Assert.assertEquals(3592 * 2, hl.read().intValue());
  }

  private static class TestInstructionFetcher<T extends WordNumber> implements InstructionFetcher<T> {
    List<Instruction<T>> instructions = new ArrayList<>();
    private int i;

    public int getOpcodeInt() {
      return 0;
    }

    public void fetchNextInstruction(InstructionExecutor<T> instructionExecutor) {
      instructionExecutor.execute(instructions.get(i), -1, createValue(i));
      i++;
    }

    public void reset() {
    }

    public int add(Instruction<T> instruction) {
      instructions.add(instruction);
      return instructions.size();
    }
  }

  private static class MyMemory implements Memory {
    public Object read(Object address) {
      return null;
    }

    public void write(Object address, Object value) {

    }

    public boolean compare() {
      return false;
    }

    public void update() {

    }

    public void setMemoryWriteListener(MemoryWriteListener memoryWriteListener) {

    }

    public void reset() {

    }

    public void addMemoryReadListener(MemoryReadListener memoryReadListener) {

    }

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

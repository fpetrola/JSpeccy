package com.fpetrola.z80.instructions;

import com.fpetrola.z80.cpu.OOZ80;
import com.fpetrola.z80.cpu.SpyInstructionExecutor;
import com.fpetrola.z80.cpu.Z80Cpu;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.OpcodeConditions;
import com.fpetrola.z80.opcodes.references.OpcodeTargets;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.flag.FlagRegister;
import com.fpetrola.z80.spy.InstructionSpy;
import com.fpetrola.z80.spy.SpyRegisterBankFactory;

public class CPUExecutionContext<T extends WordNumber> {
  OpcodeTargets ot;
  State<T> state;
  Z80Cpu<T> z80;
  InstructionFetcherForTest instructionFetcher;
  OpcodeConditions opc;
  InstructionFactory new___;
  FlagRegister<T> flag;

  public CPUExecutionContext(InstructionSpy spy) {
    final MockedMemory memory = new MockedMemory();
    state = new State(new MockedIO(), new SpyRegisterBankFactory(spy).createBank(), spy.wrapMemory(memory));
    ot = new OpcodeTargets(state);
    instructionFetcher = new InstructionFetcherForTest(state, new SpyInstructionExecutor(spy));
    z80 = new OOZ80(state, instructionFetcher);
    z80.reset();
    instructionFetcher.reset();
    new___ = new InstructionFactory<>(state);
    flag = state.getFlag();
    opc = new OpcodeConditions(flag);
  }
}
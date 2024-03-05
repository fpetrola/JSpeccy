package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterName;
import com.fpetrola.z80.registers.flag.FlagRegister;

import java.util.function.Supplier;

public interface ContextDriver<T extends WordNumber> {

  int add(Instruction<T> instruction);

  void step();

  Register<T> r(RegisterName registerName);

  MockedMemory<T> mem();

  MockedMemory<T> initMem(Supplier<T[]> supplier);

  FlagRegister<T> f();

  OpcodeReference iRR(Register<T> memoryReader);

  ImmutableOpcodeReference c(int value);

  OpcodeReference iiRR(Register<T> memoryWriter);

  OpcodeReference nn(ImmutableOpcodeReference<T> r);

  Instruction getInstructionAt(int i);

  <T2 extends WordNumber> ChainedRegister<T2> createPair(ImmutableOpcodeReference immutableOpcodeReference, Register<T2> register);

  <T2 extends WordNumber> ChainedRegister<T2> pair(Register<T2> high, Register<T2> low);

  <T2 extends WordNumber> void addUser(ChainedComposed16BitRegister<T2> result, Register<T2> high1);

  <T2 extends WordNumber> VirtualPlain8BitRegister<T2> cr(InstructionAdapter ia, ChainedRegister... regs);
}

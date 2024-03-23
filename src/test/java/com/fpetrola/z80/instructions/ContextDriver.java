package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.instructions.old.ChainedComposed16BitRegister;
import com.fpetrola.z80.instructions.old.ChainedRegister;
import com.fpetrola.z80.instructions.old.InstructionAdapter;
import com.fpetrola.z80.instructions.old.OldVirtualPlain8BitRegister;
import com.fpetrola.z80.opcodes.references.Condition;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterName;

import java.util.function.Supplier;

public interface ContextDriver<T extends WordNumber> {

  int add(Instruction<T> instruction);

  void step();

  Register<T> r(RegisterName registerName);

  MockedMemory<T> mem();

  MockedMemory<T> initMem(Supplier<T[]> supplier);

  Register<T> f();

  Register<T> pc();

  OpcodeReference iRR(Register<T> memoryReader);

  ImmutableOpcodeReference c(int value);

  OpcodeReference iiRR(Register<T> memoryWriter);

  Condition nz();

  Condition t();

  OpcodeReference nn(ImmutableOpcodeReference<T> r);

  Instruction getInstructionAt(int i);

  Instruction getTransformedInstructionAt(int i);

  <T2 extends WordNumber> ChainedRegister<T2> createPair(ImmutableOpcodeReference immutableOpcodeReference, Register<T2> register);

  <T2 extends WordNumber> ChainedRegister<T2> pair(Register<T2> high, Register<T2> low);

  <T2 extends WordNumber> void addUser(ChainedComposed16BitRegister<T2> result, Register<T2> high1);

  <T2 extends WordNumber> OldVirtualPlain8BitRegister<T2> cr(InstructionAdapter ia, ChainedRegister... regs);
}

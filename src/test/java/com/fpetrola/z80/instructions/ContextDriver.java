package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.opcodes.references.Condition;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterName;
import com.fpetrola.z80.registers.RegisterPair;

import java.util.function.Supplier;

public interface ContextDriver<T extends WordNumber> {

  int add(Instruction<T> instruction);

  void step();

  Register<T> r(RegisterName registerName);

  RegisterPair<T> rp(RegisterName registerName);

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
}

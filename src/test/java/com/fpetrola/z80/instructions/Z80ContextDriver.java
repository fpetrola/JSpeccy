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

public interface Z80ContextDriver<T extends WordNumber> extends Z80InstructionDriver<T> {

  Register<T> r(RegisterName registerName);

  RegisterPair<T> rp(RegisterName registerName);

  Register<T> f();

  Register<T> pc();

  OpcodeReference iRR(Register<T> memoryReader);

  ImmutableOpcodeReference c(int value);

  OpcodeReference iiRR(Register<T> memoryWriter);

  Condition nz();

  Condition t();

  OpcodeReference nn(ImmutableOpcodeReference<T> r);

}

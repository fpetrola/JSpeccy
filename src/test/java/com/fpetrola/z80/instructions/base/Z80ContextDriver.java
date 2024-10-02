package com.fpetrola.z80.instructions.base;

import com.fpetrola.z80.bytecode.Z80InstructionDriver;
import com.fpetrola.z80.opcodes.references.*;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterName;
import com.fpetrola.z80.registers.RegisterPair;

public interface Z80ContextDriver<T extends WordNumber> extends Z80InstructionDriver<T> {

  Register<T> r(RegisterName registerName);

  RegisterPair<T> rp(RegisterName registerName);

  Register<T> f();

  Register<T> pc();

  OpcodeReference iRR(Register<T> memoryReader);

  OpcodeReference iRRn(Register<T> register, int plus);

  ImmutableOpcodeReference c(int value);

  OpcodeReference iiRR(Register<T> memoryWriter);

  OpcodeReference iinn(int delta);

  Condition nz();
  BNotZeroCondition bnz();
  Condition z();

  Condition nc();
  Condition c();

  Condition t();

  ImmutableOpcodeReference nn(int delta);
}

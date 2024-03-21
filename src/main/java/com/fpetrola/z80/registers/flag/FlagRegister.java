package com.fpetrola.z80.registers.flag;

import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Flags;
import com.fpetrola.z80.registers.Plain8BitRegister;

public class FlagRegister<T extends WordNumber> extends Plain8BitRegister<T> {
  public FlagRegister(String registerName) {
    super(registerName);
  }

  public boolean getZ() {
    return (data.intValue() & Flags.ZERO_FLAG) != 0;
  }

  public interface UnaryAluOperation<T extends WordNumber> {
    T execute(FlagRegister<T> flag, T value);
  }

  public interface BinaryAluOperation<T extends WordNumber> {
    T execute(FlagRegister<T> flag, T value1, T value2);
  }

}

package com.fpetrola.z80.instructions.old;

import com.fpetrola.z80.instructions.old.ChainedRegister;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Composed16BitRegister;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterName;

import java.util.ArrayList;
import java.util.List;

public class ChainedComposed16BitRegister<T extends WordNumber> extends Composed16BitRegister<T, Register<T>> implements ChainedRegister<T> {
  private List<ChainedRegister> users = new ArrayList<>();

  public ChainedComposed16BitRegister(Register<T> high, Register<T> low) {
    super(RegisterName.VIRTUAL, high, low);
  }

  public List<ChainedRegister> getUsers() {
    return users;
  }

  @Override
  public String toString() {
    return "[" + getHigh().toString() + "|" + getLow().toString() + "]";
  }
}

package com.fpetrola.z80.instructions;

import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;

import java.util.List;

public interface ChainedRegister<T extends WordNumber> extends Register<T> {
  List<ChainedRegister> getUsers();

  default void evicted() {
    evictUsers();
  }

  default void addUser(ChainedRegister<T> user) {
    getUsers().add(user);
  }

  default void evictUsers() {
    getUsers().forEach(u -> u.evicted());
  }

}

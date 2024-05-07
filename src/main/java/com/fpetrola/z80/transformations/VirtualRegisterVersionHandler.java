package com.fpetrola.z80.transformations;

import com.fpetrola.z80.opcodes.references.WordNumber;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class VirtualRegisterVersionHandler<T extends WordNumber> {
  private List<VirtualRegister<T>> versions = new ArrayList<>();

  public void addVersion(VirtualRegister<T> virtualRegister) {
    versions.add(virtualRegister);
  }

  public VirtualRegister<T> getBiggestScopeFor(VirtualRegister<T> register) {
    if (versions.isEmpty())
      return register;
    else {
      VirtualRegister<T> biggerScope = versions.stream().filter(r -> r.getScope().isIncluding(register)).max(Comparator.comparingInt(r -> r.getScope().size())).get();
      return biggerScope;
    }
  }
}

package com.fpetrola.z80.metadata;

import java.util.HashSet;
import java.util.Set;

public class DataStructureInstance {
  public Set<Integer> addresses = new HashSet<>();

  public void addAddress(int address) {
    addresses.add(address);
  }
}

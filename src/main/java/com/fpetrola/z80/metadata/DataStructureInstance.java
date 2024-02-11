package com.fpetrola.z80.metadata;

import java.util.HashSet;
import java.util.Set;

public class DataStructureInstance {
  public Set<Integer> addresses = new HashSet<>();

  public void addAddress(int address) {
    if (!addresses.isEmpty() && address - addresses.iterator().next().intValue() > 100)
      System.out.println("mucha distancia!");
    addresses.add(address);
  }
}

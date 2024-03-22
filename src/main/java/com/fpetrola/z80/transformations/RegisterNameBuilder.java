package com.fpetrola.z80.transformations;

import com.fpetrola.z80.helpers.Helper;
import com.fpetrola.z80.registers.Register;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

import java.util.Collection;

public class RegisterNameBuilder {
  private MultiValuedMap<String, String> names = new HashSetValuedHashMap<>();
  private int currentAddress;

  public String createVirtualRegisterName(Register register) {
    String name = register.getName();
    String s = name + "_L" + currentAddress;// Helper.convertToHex(currentAddress);

    Collection<String> strings = names.get(name);
    long count = strings.stream().filter(n -> n.startsWith(s)).count();
    String registerName = s;
    if (count > 0)
      registerName += "_" + count;

    names.put(name, registerName);
    return registerName;
  }

  public void setCurrentAddress(int currentAddress) {
    this.currentAddress = currentAddress;
  }
}

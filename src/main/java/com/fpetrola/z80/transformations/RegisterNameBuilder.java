package com.fpetrola.z80.transformations;

import com.fpetrola.z80.helpers.Helper;
import com.fpetrola.z80.registers.Register;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class RegisterNameBuilder {
  static long count = 0L;

  private Map<String, String> names = new HashMap<>();
  private int currentAddress;

  public String createVirtualRegisterName(Register register) {
    String name = register.getName();
    String s = name + "_L" + currentAddress;// Helper.convertToHex(currentAddress);

    String registerName = s;
    if (names.get(s) != null)
      registerName += "_" + ++count;
    else
      names.put(s, registerName);
    return registerName;
  }

  public void setCurrentAddress(int currentAddress) {
    this.currentAddress = currentAddress;
  }
}

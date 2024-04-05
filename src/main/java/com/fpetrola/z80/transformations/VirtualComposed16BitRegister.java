package com.fpetrola.z80.transformations;

import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Composed16BitRegister;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class VirtualComposed16BitRegister<T extends WordNumber> extends Composed16BitRegister<T> implements VirtualRegister<T> {
  private final VirtualRegister<T> virtualH;
  private final VirtualRegister<T> virtualL;

  public VirtualComposed16BitRegister(String virtualRegisterName, VirtualRegister<T> virtualH, VirtualRegister<T> virtualL) {
    super(virtualRegisterName, virtualH, virtualL);
    this.virtualH = virtualH;
    this.virtualL = virtualL;
  }

  @Override
  public List<VirtualRegister<T>> getPreviousVersions() {
    List<VirtualRegister<T>> previousVersionsL = virtualL.getPreviousVersions();
    List<VirtualRegister<T>> previousVersionsH = virtualH.getPreviousVersions();

    List<VirtualRegister<T>> list = new ArrayList<>();
    for (int i = 0, previousVersionsLSize = previousVersionsL.size(); i < previousVersionsLSize; i++) {

      VirtualRegister<T> pL = previousVersionsL.get(i);
      VirtualRegister<T> pH = previousVersionsH.get(i);

      String nameL = pL.getName();
      String nameH = pH.getName();
      String lineL = getLineNumber(nameL);
      String lineH = getLineNumber(nameH);

      String finalName = nameH + "," + nameL;
      if (lineL.equals(lineH))
        finalName = nameH.substring(0, nameH.indexOf("_")) + nameL.substring(0, nameL.indexOf("_")) + "_" + lineL;

      VirtualComposed16BitRegister<T> apply = new VirtualComposed16BitRegister<>(finalName, pH, pL);
      list.add(apply);
    }
    return list;
  }

  private String getLineNumber(String name) {
    return name.substring(name.indexOf("_") + 1);
  }

  @Override
  public boolean usesMultipleVersions() {
    return virtualH.usesMultipleVersions() && virtualL.usesMultipleVersions();
  }

  public void reset() {
    virtualL.reset();
    virtualH.reset();
  }

  public void saveData() {
    virtualL.saveData();
    virtualH.saveData();
  }
}

package com.fpetrola.z80.transformations;

import com.fpetrola.z80.instructions.base.InstructionVisitor;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Composed16BitRegister;

import java.util.ArrayList;
import java.util.List;

public class VirtualComposed16BitRegister<T extends WordNumber> extends Composed16BitRegister<T, IVirtual8BitsRegister<T>> implements VirtualRegister<T> {
  private final int currentAddress;
  private Scope scope= new Scope();

  public VirtualComposed16BitRegister(int currentAddress, String virtualRegisterName, IVirtual8BitsRegister<T> virtualH, IVirtual8BitsRegister<T> virtualL) {
    super(virtualRegisterName, virtualH, virtualL);
    this.currentAddress = currentAddress;
    virtualL.set16BitsRegister(this);
    virtualH.set16BitsRegister(this);
    scope.include(this);
  }

  @Override
  public List<VirtualRegister<T>> getPreviousVersions() {
    List<VirtualRegister<T>> previousVersionsL = low.getPreviousVersions();
    List<VirtualRegister<T>> previousVersionsH = high.getPreviousVersions();

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

      finalName= fixIndexNames(finalName);

      list.add(new VirtualComposed16BitRegister<T>(Math.min(pL.getAddress(), pH.getAddress()), finalName, (IVirtual8BitsRegister<T>) pH, (IVirtual8BitsRegister<T>) pL));
    }
    return list;
  }

  public static String fixIndexNames(String finalName) {
    return finalName.replace("IXHIXL", "IX").replace("IYHIYL", "IY"); //FIXME
  }

  public static String getLineNumber(String name) {
    return name.substring(name.indexOf("_") + 1);
  }

  @Override
  public boolean usesMultipleVersions() {
    return high.usesMultipleVersions() && low.usesMultipleVersions();
  }

  public void reset() {
    low.reset();
    high.reset();
  }

  public void saveData() {
    low.saveData();
    high.saveData();
  }

  public boolean hasNoPrevious() {
    List<VirtualRegister<T>> previousVersions = getPreviousVersions();
    if (previousVersions.isEmpty())
      return true;

    VirtualComposed16BitRegister<T> tVirtualRegister = (VirtualComposed16BitRegister<T>) previousVersions.get(0);
    return tVirtualRegister.getHigh() instanceof InitialVirtualRegister && tVirtualRegister.getLow() instanceof InitialVirtualRegister;
  }

  @Override
  public int getAddress() {
    return Math.min(high.getAddress(), low.getAddress());
  }

  @Override
  public Scope getScope() {
    return scope;
  }

  @Override
  public void accept(InstructionVisitor instructionVisitor) {
    if (!instructionVisitor.visitVirtualComposed16BitRegister(this)) {
      instructionVisitor.visitRegister(getHigh());
      instructionVisitor.visitRegister(getLow());
    }
  }
}

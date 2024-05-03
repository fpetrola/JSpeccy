package com.fpetrola.z80.transformations;

import com.fpetrola.z80.registers.Register;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public interface VirtualRegister<T> extends Register<T> {
  List<VirtualRegister<T>> getPreviousVersions();

  boolean usesMultipleVersions();

  void reset();

  void saveData();

  default boolean hasNoPrevious() {
    List<VirtualRegister<T>> previousVersions = getPreviousVersions();
    return /*previousVersions.size() == 1 && */previousVersions.get(0) instanceof InitialVirtualRegister;
  }

  int getAddress();
  Scope getScope();

  List<VirtualRegister<T>> getDependants();

  default int getRegisterLine() {
    String name = getName();
    if (name.contains(",")) {
      if (this instanceof VirtualComposed16BitRegister<?> virtualComposed16BitRegister) {
        IVirtual8BitsRegister<?> low = virtualComposed16BitRegister.getLow();
        IVirtual8BitsRegister<?> high = virtualComposed16BitRegister.getHigh();
        return Math.min(low.getRegisterLine(), high.getRegisterLine());
      }
    }
    int i = name.indexOf("_");
    i = i != -1 ? Integer.parseInt(name.substring(i + 2)) : 10000000;
    return i;
  }

  default List<VirtualRegister<?>> getAncestorsOf() {
    VirtualRegister<?> r = this;
    List<VirtualRegister<?>> result1 = new ArrayList<>();
    result1.add(r);
    r.getPreviousVersions().stream().filter(r1 -> r1.getRegisterLine() < r.getRegisterLine()).forEach(r1 -> result1.addAll(r1.getAncestorsOf()));
    return result1;
  }

  default VirtualRegister<?> getParentPreviousVersion() {
//    return getRegisterLine(virtualRegister);
    VirtualRegister<?> virtualRegister= this;
    List<VirtualRegister<?>> ancestorsOf1 = virtualRegister.getAncestorsOf();
    Collections.sort(ancestorsOf1, (c1, c2) -> c2.getRegisterLine() - c1.getRegisterLine());

    for (int i = 0; i < ancestorsOf1.size(); i++) {
      int finalI = i;
      long count = ancestorsOf1.stream().filter(r -> r.getName().equals(ancestorsOf1.get(finalI).getName())).count();
      if (count >= virtualRegister.getPreviousVersions().size())
        return ancestorsOf1.get(i);
    }
    return virtualRegister.getPreviousVersions().stream().min(Comparator.comparingInt((r1) -> r1.getRegisterLine())).get();
//    return ancestorsOf1.stream().map(r -> getRegisterLine(r)).min(Integer::compare).get();
  }

  default void getMinLineNumber3() {
    getPreviousVersions().stream().forEach(r -> getScope().start = Math.min(getScope().start, r.getScope().start + 1));
    getPreviousVersions().stream().forEach(r -> getScope().end = Math.max(getScope().end, r.getScope().end - 1));
  }

  default VirtualRegister<?> adjustRegisterScope() {
    getMinLineNumber3();
    VirtualRegister<?> parentPreviousVersion = getParentPreviousVersion();

    Integer minLineNumber = parentPreviousVersion.getRegisterLine();
    getScope().include(minLineNumber + 1);
    return parentPreviousVersion;
  }

  default Integer getMinLineNumber2() {
    VirtualRegister<?> virtualRegister= this;
    return virtualRegister.getPreviousVersions().stream().map(r -> r.getRegisterLine()).min(Integer::compare).get();
  }
}

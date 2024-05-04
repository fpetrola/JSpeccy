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
    int i = name.indexOf("_");
    String substring = name.substring(i + 2);
    int a = substring.indexOf("_");
    int endIndex = a != -1 ? a : substring.length();
    i = i != -1 ? Integer.parseInt(substring.substring(0, endIndex)) : 10000000;
    return i;
  }

  default List<VirtualRegister<?>> getAncestorsOf() {
    List<VirtualRegister<?>> result1 = new ArrayList<>();
    result1.add(this);
    getPreviousVersions().stream().filter(r1 -> r1.getRegisterLine() < getRegisterLine()).forEach(r1 -> result1.addAll(r1.getAncestorsOf()));
    return result1;
  }

  default VirtualRegister<?> getParentPreviousVersion() {
//    return getRegisterLine(virtualRegister);
    List<VirtualRegister<?>> ancestorsOf1 = getAncestorsOf();
    Collections.sort(ancestorsOf1, (c1, c2) -> c2.getRegisterLine() - c1.getRegisterLine());

    for (int i = 0; i < ancestorsOf1.size(); i++) {
      int finalI = i;
      long count = ancestorsOf1.stream().filter(r -> r.getName().equals(ancestorsOf1.get(finalI).getName())).count();
      if (count >= getPreviousVersions().size())
        return ancestorsOf1.get(i);
    }
    return getPreviousVersions().stream().min(Comparator.comparingInt((r1) -> r1.getRegisterLine())).get();
//    return ancestorsOf1.stream().map(r -> getRegisterLine(r)).min(Integer::compare).get();
  }

  default VirtualRegister<?> adjustRegisterScope() {
    List<VirtualRegister<T>> previousVersions = getPreviousVersions();
    if (previousVersions.stream().filter(r -> !(r instanceof InitialVirtualRegister)).count() > 1) {
      previousVersions.stream().forEach(r -> r.getScope().start = Math.min(getScope().start, r.getScope().start));
      previousVersions.stream().forEach(r -> r.getScope().end = Math.max(getScope().end, r.getScope().end));
    }
    VirtualRegister<?> parentPreviousVersion = getParentPreviousVersion();

//    Integer minLineNumber = parentPreviousVersion.getRegisterLine();
//    getScope().include(minLineNumber + 1);
    return parentPreviousVersion;
  }

  default Integer getMinLineNumber2() {
    return getPreviousVersions().stream().map(r -> r.getRegisterLine()).min(Integer::compare).get();
  }

  default boolean isMixRegister() {
    return getName().contains(",");
  }
}

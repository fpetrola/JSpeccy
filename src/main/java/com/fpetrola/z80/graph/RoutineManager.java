package com.fpetrola.z80.graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RoutineManager {

  List<Routine> routines = new ArrayList<>();
  Collection<Branch> branches = new ArrayList<>();

  public Routine findRoutineAt(int routineAddress) {
    List<Routine> foundRoutines = routines.stream().filter(r -> r.startAddress <= routineAddress && r.endAddress >= routineAddress).collect(Collectors.toList());

    if (foundRoutines.size() != 1)
      System.out.println("findRoutineAt bug!");

    return foundRoutines.get(0);
  }

  public void addRoutine(Routine routine) {
    routines.add(routine);
  }

  public boolean getOrCreateBranch(int address) {
    Optional<Branch> branch = branches.stream().filter(b -> b.getAddress() == address).findFirst();

    boolean result = branch.isPresent();
    if (!result)
      branches.add(new Branch(address));
   
    return result;
  }
}

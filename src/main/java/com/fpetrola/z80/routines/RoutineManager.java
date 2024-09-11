package com.fpetrola.z80.routines;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoutineManager {
  List<Routine> routines = new ArrayList<>();
  private int firstAddress;

  public Routine findRoutineAt(int address) {
    Optional<Routine> first = routines.stream().filter(r -> r.contains(address)).findFirst();
    return first.orElse(null);
  }

  public void addRoutine(Routine block) {
    routines.add(block);
  }

  public List<Routine> getRoutines() {
    return new ArrayList<Routine>(routines);
  }

  public void optimizeAll() {
    routines.forEach(routine -> routine.optimize());
  }
}

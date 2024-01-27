package com.fpetrola.z80.graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RoutineManager {

  List<Routine> routines = new ArrayList<>();
  Collection<Branch> branches = new ArrayList<>();
//  protected int startUserCode = 0x5B00;
  protected int startUserCode = 0x0000;
  RoutineChangesListener routineChangesListener;

  public RoutineManager(RoutineChangesListener routineChangesListener) {
    this.routineChangesListener = routineChangesListener;
    addRoutine(new Routine(0, 0xFFFF, "WHOLE_MEMORY", this));
  }

  public Routine findRoutineAt(int routineAddress) {
    List<Routine> foundRoutines = routines.stream().filter(r -> r.getStartAddress() <= routineAddress && r.getEndAddress() >= routineAddress).collect(Collectors.toList());

    if (foundRoutines.size() != 1)
      System.out.println("findRoutineAt bug!");

    return foundRoutines.get(0);
  }

  public void addRoutine(Routine routine) {
    routineChangesListener.addingRoutine(routine);
    routines.add(routine);
  }

  public boolean getOrCreateBranch(int address) {
    Optional<Branch> branch = branches.stream().filter(b -> b.getAddress() == address).findFirst();

    boolean result = branch.isPresent();
    if (!result)
      branches.add(new Branch(address));

    return result;
  }

  public void removeRoutine(Routine routine) {
    routineChangesListener.removingRoutine(routine);
    routines.remove(routine);
  }

  public Routine addRoutine(int routineAddress, int currentPC, boolean stacking, String callType) {
    boolean branchExists = getOrCreateBranch(routineAddress);

    if (routineAddress >= startUserCode && (branchExists || callType.equals("CALL"))) {
      Routine calledRoutine = findRoutineAt(routineAddress);
      Routine currentRoutine = findRoutineAt(currentPC);

      boolean newRoutine = calledRoutine.getStartAddress() < routineAddress;
      if (newRoutine) {
        calledRoutine = calledRoutine.split(routineAddress, callType);
      }
      if (!calledRoutine.references.contains(currentRoutine)) {
        currentRoutine.addCallingRoutine(calledRoutine, currentPC);
      }
      return newRoutine ? calledRoutine : null;
    }

    return null;
  }

  public List<Routine> getRoutines() {
    return new ArrayList<Routine>(routines);
  }

  public void endRoutine(int nextPC, int pcValue, boolean b, String callType) {
    Routine calledRoutine = findRoutineAt(pcValue);
    if (calledRoutine.endAddress > (pcValue + 1))
      calledRoutine.split(pcValue + 1, "RET");
  }

}

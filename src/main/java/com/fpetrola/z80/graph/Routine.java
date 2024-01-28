package com.fpetrola.z80.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Routine {

  int startAddress;
  int endAddress;

  String callType;
  Map<Integer, Routine> calling = new HashMap<>();

  RoutineManager routineManager;
  List<Routine> references = new ArrayList<>();
  String type;

  public Routine(int startAddress, int endAddress, String callType, RoutineManager routineManager, String type) {
    this.startAddress = startAddress;
    this.endAddress = endAddress;
    this.callType = callType;
    this.routineManager = routineManager;
    this.type = type;
  }

  public String toString() {
    return String.format(type + ": %1$04X : %2$04X", startAddress, endAddress);
  }

  public void addCallingRoutine(Routine routine, int from) {
    routineManager.routineChangesListener.addingRoutineCall(this, routine, from);
    calling.put(from, routine);
    routine.references.add(this);
  }

  public void removeCallingRoutine(Routine routine) {
    routineManager.routineChangesListener.removingRoutineCall(this, routine);
    Map<Integer, Routine> calling2 = new HashMap<>(calling);
    calling2.forEach((address, calledRoutine) -> {
      if (calledRoutine.equals(routine)) {
        calling.remove(address);
        routine.references.remove(this);
      }
    });
  }

  public Routine split(int routineAddress, String callType, String aType) {
    if (routineAddress <= endAddress) {
      String lastName = getName();
      int lastEndAddress = endAddress;
      endAddress = routineAddress - 1;

      Routine routine = new Routine(routineAddress, lastEndAddress, callType, routineManager, aType);
      routineManager.addRoutine(routine);

      Set<Entry<Integer, Routine>> entrySet = new HashSet<>(calling.entrySet());
      for (Entry<Integer, Routine> entry : entrySet) {
        Integer callPerformedAddress = entry.getKey();
        if (callPerformedAddress >= routineAddress) {
          removeCallingRoutine(entry.getValue());
          routine.addCallingRoutine(entry.getValue(), callPerformedAddress);
        }
      }

      routineManager.routineChangesListener.routineChanged(this);

      System.out.println("Spliting routine: " + lastName + " in: " + getName() + " -> " + routine.getName());

      return routine;
    } else
      return this;
  }

  public Routine join(Routine routine) {
    removeCallingRoutine(routine);
    Set<Entry<Integer, Routine>> entrySet = new HashSet<>(routine.calling.entrySet());
    for (Entry<Integer, Routine> entry : entrySet) {
      routine.removeCallingRoutine(entry.getValue());
      addCallingRoutine(entry.getValue(), entry.getKey());
    }

    routine.references.clear();
    routineManager.removeRoutine(routine);
    System.out.println("Joining routine: " + this + " -> " + routine);
    endAddress = routine.getEndAddress();
    routineManager.routineChangesListener.routineChanged(this);
    return routine;
  }

  public String getName() {
    return toString();
  }

//  public int hashCode() {
//    return Objects.hash(endAddress, startAddress);
//  }
//
//  public boolean equals(Object obj) {
//    if (this == obj)
//      return true;
//    if (obj == null)
//      return false;
//    if (getClass() != obj.getClass())
//      return false;
//    Routine other = (Routine) obj;
//    return endAddress == other.endAddress && startAddress == other.startAddress;
//  }

  public boolean isCallingTo(Routine routine) {
    return calling.values().stream().anyMatch(r -> r.equals(routine));
  }

  public int getStartAddress() {
    return startAddress;
  }

  public int getEndAddress() {
    return endAddress;
  }

  public String getType() {
    return type;
  }

  public String getCallType() {
    return callType;
  }

  public List<Routine> getReferences() {
    return references;
  }
}

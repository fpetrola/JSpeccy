package com.fpetrola.z80.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.fpetrola.z80.OOZ80;

import java.util.Objects;
import java.util.Set;

public class Routine {

  int startAddress;
  int endAddress;

  String callType;
  Map<Integer, Routine> calling = new HashMap<>();

  RoutineManager routineManager;
  List<Routine> references = new ArrayList<>();

  public Routine(int startAddress, int endAddress, String callType, RoutineManager routineManager) {
    this.startAddress = startAddress;
    this.endAddress = endAddress;
    this.callType = callType;
    this.routineManager = routineManager;
  }

  public String toString() {
    return OOZ80.convertToHex(startAddress) + ":" + OOZ80.convertToHex(endAddress);
  }

  public void addCallingRoutine(Routine routine, int from) {
    calling.put(from, routine);
    routine.references.add(this);
  }

  public void removeCallingRoutine(Routine routine) {
    Map<Integer, Routine> calling2 = new HashMap<>(calling);
    calling2.forEach((address, calledRoutine) -> {
      if (calledRoutine.equals(routine)) {
        calling.remove(address);
        routine.references.remove(this);
      }
    });
  }

  public Routine split(int routineAddress, String callType) {
    int lastEndAddress = endAddress;
    endAddress = routineAddress - 1;

    Routine routine = new Routine(routineAddress, lastEndAddress, callType, routineManager);

    Set<Entry<Integer, Routine>> entrySet = new HashSet<>(calling.entrySet());
    for (Entry<Integer, Routine> entry : entrySet) {
      Integer callPerformedAddress = entry.getKey();
      if (callPerformedAddress >= routineAddress) {
        removeCallingRoutine(entry.getValue());
        routine.addCallingRoutine(entry.getValue(), callPerformedAddress);
      }
    }

    routineManager.routines.add(routine);
    System.out.println("Spliting routine: " + OOZ80.convertToHex(startAddress) + ":" + OOZ80.convertToHex(lastEndAddress) + " -> " + routine.toString());

    return routine;
  }

  public Routine join(Routine routine) {
    if (routine.references.size() == 1 && routine.references.get(0).equals(this)) {
      removeCallingRoutine(routine);
      Set<Entry<Integer, Routine>> entrySet = new HashSet<>(routine.calling.entrySet());
      for (Entry<Integer, Routine> entry : entrySet) {
        routine.removeCallingRoutine(entry.getValue());
        addCallingRoutine(entry.getValue(), entry.getKey());
      }

      routine.references.clear();
      routineManager.routines.remove(routine);
      System.out.println("Joining routine: " + this + " -> " + routine);
    }
    return routine;
  }

  public String getName() {
    return toString();
  }

  public int hashCode() {
    return Objects.hash(endAddress, startAddress);
  }

  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Routine other = (Routine) obj;
    return endAddress == other.endAddress && startAddress == other.startAddress;
  }

  public boolean isCallingTo(Routine routine) {
    return calling.values().stream().anyMatch(r -> r.equals(routine));
  }
}

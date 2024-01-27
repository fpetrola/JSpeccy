package com.fpetrola.z80.graph;

public class NullRoutineChangesListener implements RoutineChangesListener {
  public void removingRoutineCall(Routine routine, Routine calledRoutine) {
  }

  public void removingRoutine(Routine routine) {
  }

  public void addingRoutine(Routine routine) {
  }

  public void addingRoutineCall(Routine routine, Routine calledRoutine, int from) {
  }

  public void routineChanged(Routine routine) {
  }
}
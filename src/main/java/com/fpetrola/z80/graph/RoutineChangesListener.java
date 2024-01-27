package com.fpetrola.z80.graph;

public interface RoutineChangesListener {

  void removingRoutineCall(Routine routine, Routine calledRoutine);

  void addingRoutineCall(Routine routine, Routine calledRoutine, int from);

  void removingRoutine(Routine routine);

  void addingRoutine(Routine routine);

  void routineChanged(Routine routine);

}

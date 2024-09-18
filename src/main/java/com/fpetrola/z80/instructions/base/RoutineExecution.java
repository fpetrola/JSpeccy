package com.fpetrola.z80.instructions.base;

import java.util.*;

public class RoutineExecution {
  public int retInstruction;
  public int start;
  public boolean isNoConditionRet;
  protected LinkedList<Integer> popPoints = new LinkedList<>();
  protected LinkedList<Integer> branchPoints = new LinkedList<>();
  protected Map<Integer, Integer> executions = new HashMap<>();
  public Set<Integer> executedPoints = new HashSet<>();

  {
    int pcValue = 0;
    Integer i = executions.get(pcValue);
    if (i == null) executions.put(pcValue, i = 0);

    executions.put(pcValue, i + 1);
  }

  private boolean executionsAreComplete() {
    return (!executions.isEmpty()) && executions.entrySet().stream().allMatch(e -> e.getValue() > 1);
  }
}

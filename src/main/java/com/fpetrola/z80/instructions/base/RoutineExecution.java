package com.fpetrola.z80.instructions.base;

import java.util.*;

public class RoutineExecution {
  public int retInstruction = -1;
  public int start;
  public boolean isFinalRet;
  protected LinkedList<Integer> branchPoints = new LinkedList<>();
  protected LinkedList<Integer> pendingPoints = new LinkedList<>();
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

  public boolean hasPendingPoints() {
    return !branchPoints.isEmpty() || !pendingPoints.isEmpty();
  }

  public int getNextPending() {
    if (!branchPoints.isEmpty())
      return branchPoints.peek();
    else
      return pendingPoints.poll();
  }

  void addPending(int returnAddressWordNumber) {
    if (!pendingPoints.contains(returnAddressWordNumber))
      pendingPoints.offer(returnAddressWordNumber);
  }

  void addBranch(int returnAddressWordNumber) {
    if (!branchPoints.contains(returnAddressWordNumber))
      branchPoints.offer(returnAddressWordNumber);
  }
}

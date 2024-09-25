package com.fpetrola.z80.instructions.base;

import java.util.*;

public class RoutineExecution {
  public int retInstruction = -1;
  public int start;
  public boolean isFinalRet;
  public LinkedList<Integer> pendingPoints = new LinkedList<>();
  protected Map<Integer, Integer> executions = new HashMap<>();
  public Set<Integer> executedPoints = new TreeSet<>();
  public LinkedList<AddressAction> actions = new LinkedList<>();

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
    return !actions.isEmpty();
  }

  private AddressAction peekNextPending() {
    return actions.peek();
  }


  public AddressAction getNextPending() {
    if (actions.peek() instanceof BasicAddressAction)
      return actions.poll();
    else
      return actions.peek();
  }

  public AddressAction getActionInAddress(int pcValue) {
    AddressAction addressAction1 = actions.stream().filter(addressAction -> addressAction.address == pcValue).findFirst().orElseGet(() -> null);
    actions.remove(addressAction1);
    return addressAction1;
  }

  public void addAddressAction(AddressAction addressAction) {
    actions.offer(addressAction);
  }

  int getNext(int minimalValidCodeAddress, int pcValue) {
    int next = pcValue;
    if (!executedPoints.contains(pcValue) && pcValue >= minimalValidCodeAddress) {
      executedPoints.add(pcValue);
    } else {
      if (!hasPendingPoints()) {
        if (retInstruction == -1)
          System.out.print("");

        next = retInstruction;
      } else
        next = getNextPending().address;
    }
    return next;
  }
}

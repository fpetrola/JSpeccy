package com.fpetrola.z80.instructions.base;

import com.fpetrola.z80.instructions.Call;
import com.fpetrola.z80.instructions.Ret;
import com.fpetrola.z80.opcodes.references.ConditionAlwaysTrue;

import java.util.*;

public class RoutineExecution {
  private final int minimalValidCodeAddress;
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

  public RoutineExecution(int minimalValidCodeAddress) {
    this.minimalValidCodeAddress = minimalValidCodeAddress;
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
    AddressAction addressAction = actions.stream().filter(a -> a.address == pcValue).findFirst().orElseGet(() -> null);
    if (addressAction == null) {
      addressAction = new AddressAction() {
        boolean processBranch(boolean doBranch, Instruction instruction, boolean alwaysTrue, SymbolicExecutionAdapter symbolicExecutionAdapter) {
          AddressAction innerAddressAction;
          if (instruction instanceof Ret ret) {
            innerAddressAction = new AddressAction(pcValue) {
              boolean processBranch(boolean doBranch, Instruction instruction, boolean alwaysTrue, SymbolicExecutionAdapter symbolicExecutionAdapter) {
                isFinalRet = ret.getCondition() instanceof ConditionAlwaysTrue;
                retInstruction = pcValue;
                if (!hasPendingPoints()) {
                  symbolicExecutionAdapter.popFrame();
                  return true;
                } else {
                  return false;
                }
              }
            };
          } else if (instruction instanceof Call call) {
            innerAddressAction = new AddressAction(pcValue) {
              boolean processBranch(boolean doBranch, Instruction instruction, boolean alwaysTrue, SymbolicExecutionAdapter symbolicExecutionAdapter) {
                if (doBranch) {
                  int jumpAddress = call.getJumpAddress().intValue();
                  symbolicExecutionAdapter.createRoutineExecution(jumpAddress);
                }
                return doBranch;
              }
            };
          } else {
            innerAddressAction = new AddressAction(pcValue) {
              boolean processBranch(boolean doBranch, Instruction instruction, boolean alwaysTrue, SymbolicExecutionAdapter symbolicExecutionAdapter) {
                return doBranch;
              }
            };
          }
          if (!alwaysTrue)
            addAddressAction(innerAddressAction);

          return innerAddressAction.processBranch(doBranch, instruction, alwaysTrue, symbolicExecutionAdapter);
        }
      };
    }

    return addressAction;
  }

  public void addAddressAction(AddressAction addressAction) {
    actions.offer(addressAction);
  }

  int getNext(int pcValue) {
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

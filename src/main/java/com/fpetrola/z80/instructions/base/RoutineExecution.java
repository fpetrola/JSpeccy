package com.fpetrola.z80.instructions.base;

import com.fpetrola.z80.instructions.Call;
import com.fpetrola.z80.instructions.Ret;

import java.util.*;

public class RoutineExecution {
  private final int minimalValidCodeAddress;
  public int retInstruction = -1;
  public int start;
  public Set<Integer> executedPoints = new TreeSet<>();
  public LinkedList<AddressAction> actions = new LinkedList<>();

  public RoutineExecution(int minimalValidCodeAddress) {
    this.minimalValidCodeAddress = minimalValidCodeAddress;
  }

  public boolean hasPendingPoints() {
    return actions.stream().anyMatch(AddressAction::isPending);
  }

  private AddressAction peekNextPending() {
    return actions.peek();
  }


  public AddressAction getNextPending() {
    AddressAction addressAction = actions.stream().filter(AddressAction::isPending).findFirst().get();
    if (addressAction instanceof BasicAddressAction)
      addressAction.setPending(false);
    return addressAction;
//    if (actions.peek() instanceof BasicAddressAction)
//      return actions.poll();
//    else
//      return actions.peek();
  }

  public AddressAction getActionInAddress(int pcValue) {
    AddressAction addressAction = actions.stream().filter(a -> a.address == pcValue).findFirst().orElseGet(() -> null);
    if (addressAction == null) {
      addressAction = new AddressAction() {

        public int getNext(int next, int pcValue) {
          int result = pcValue;
          if (retInstruction == next && hasPendingPoints())
            result = getNextPending().address;
          return result;
        }

        boolean processBranch(boolean doBranch, Instruction instruction, boolean alwaysTrue, SymbolicExecutionAdapter symbolicExecutionAdapter) {
          AddressAction innerAddressAction;
          if (instruction instanceof Ret ret) {
            innerAddressAction = new AddressAction(pcValue, true) {
              boolean processBranch(boolean doBranch, Instruction instruction, boolean alwaysTrue, SymbolicExecutionAdapter symbolicExecutionAdapter) {
                super.processBranch(doBranch, instruction, alwaysTrue, symbolicExecutionAdapter);
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
            innerAddressAction = new AddressAction(pcValue, true) {
              boolean processBranch(boolean doBranch, Instruction instruction, boolean alwaysTrue, SymbolicExecutionAdapter symbolicExecutionAdapter) {
                super.processBranch(doBranch, instruction, alwaysTrue, symbolicExecutionAdapter);

                if (doBranch) {
                  int jumpAddress = call.getJumpAddress().intValue();
                  symbolicExecutionAdapter.createRoutineExecution(jumpAddress);
                }
                return doBranch;
              }
            };
          } else {
            innerAddressAction = new AddressAction(pcValue, true) {
              boolean processBranch(boolean doBranch, Instruction instruction, boolean alwaysTrue, SymbolicExecutionAdapter symbolicExecutionAdapter) {
                super.processBranch(doBranch, instruction, alwaysTrue, symbolicExecutionAdapter);

                return doBranch;
              }
            };
          }
          if (!alwaysTrue)
            addAddressAction(innerAddressAction);

          boolean b = innerAddressAction.processBranch(doBranch, instruction, alwaysTrue, symbolicExecutionAdapter);
          innerAddressAction.setPending(true);
          return b;
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

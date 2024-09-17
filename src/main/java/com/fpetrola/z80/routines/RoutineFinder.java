package com.fpetrola.z80.routines;

import com.fpetrola.z80.instructions.Call;
import com.fpetrola.z80.instructions.Ret;
import com.fpetrola.z80.instructions.ReturnAddressWordNumber;
import com.fpetrola.z80.instructions.base.ConditionalInstruction;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.instructions.base.SymbolicExecutionAdapter;
import com.fpetrola.z80.opcodes.references.WordNumber;

@SuppressWarnings("ALL")
public class RoutineFinder {
  private Instruction lastInstruction;

  private Routine currentRoutine;
  public static RoutineManager routineManager;

  public RoutineFinder(RoutineManager routineManager) {
    this.routineManager = routineManager;
  }

  public void checkExecution(Instruction instruction, int pcValue) {

    try {
      if (pcValue == 34928)
        System.out.printf("");
      if (currentRoutine == null)
        createOrUpdateCurrentRoutine(pcValue, instruction.getLength());

      if (lastInstruction instanceof Call) {
        WordNumber nextPC = ((ConditionalInstruction) lastInstruction).getNextPC();
        if (nextPC != null)
          createOrUpdateCurrentRoutine(nextPC.intValue(), instruction.getLength());
      }

      currentRoutine.addInstructionAt(instruction, pcValue);

      if (instruction instanceof SymbolicExecutionAdapter.PopReturnAddress popReturnAddress) {
        ReturnAddressWordNumber returnAddress = popReturnAddress.getReturnAddress();
        if (returnAddress != null) {
          this.currentRoutine = routineManager.findRoutineAt(returnAddress.pc);
          this.currentRoutine.addReturnPoint(returnAddress.pc, pcValue + 1);
        }
      } else if (instruction instanceof Ret ret) {
        if (ret.getNextPC() != null) {
          Routine routineAt = routineManager.findRoutineAt(pcValue);
          if (currentRoutine != routineAt) {
            currentRoutine.addInnerRoutine(routineAt);
          }
          this.currentRoutine = routineManager.findRoutineAt(ret.getNextPC().intValue() - 1);
        }
      }

    } finally {
      routineManager.optimizeAll();
      lastInstruction = instruction;
    }
  }

  private Routine createOrUpdateCurrentRoutine(int startAddress, int length) {
    currentRoutine = routineManager.findRoutineAt(startAddress);
    if (currentRoutine != null) {
      if (currentRoutine.getStartAddress() < startAddress) {
        Routine newRoutine = currentRoutine.split(startAddress);
        routineManager.addRoutine(newRoutine);
        currentRoutine = newRoutine;
      }
    } else {
      currentRoutine = routineManager.createRoutine(startAddress, length);
    }
    return currentRoutine;
  }

}
//Block lastCurrentRoutine = blocksManager.findBlockAt(lastPc);
//    if (lastCurrentRoutine != null)
//    lastCurrentRoutine.getReferencesHandler().addBlockRelation(BlockRelation.createBlockRelation(lastCurrentRoutine.getRangeHandler().getStartAddress(), startAddress));

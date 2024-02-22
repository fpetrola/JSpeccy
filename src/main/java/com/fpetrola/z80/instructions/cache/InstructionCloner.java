package com.fpetrola.z80.instructions.cache;

import java.lang.reflect.Constructor;

import com.fpetrola.z80.instructions.*;
import com.fpetrola.z80.instructions.base.*;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class InstructionCloner<T extends WordNumber> {
  InstructionFactory instructionFactory;

  public InstructionCloner(InstructionFactory instructionFactory) {
    this.instructionFactory = instructionFactory;
  }

  public Instruction<T> clone(Instruction<T> instruction) {
    try {
      AbstractInstruction<T> newInstance;
      boolean isDJNZ = instruction instanceof DJNZ;
      boolean isConditional = instruction instanceof ConditionalInstruction;

      if (instruction instanceof IM) {
        newInstance = instructionFactory.IM(((IM) instruction).getMode());
      } else if (instruction instanceof Ret) {
        newInstance = instructionFactory.Ret(((Ret) instruction).getCondition());
      } else if (instruction instanceof RST) {
        newInstance = instructionFactory.RST(((RST) instruction).getP());
      } else {
        if (isDJNZ) {
          newInstance = instructionFactory.DJNZ(((DJNZ) instruction).getPositionOpcodeReference());
        } else {
          Constructor<?> constructor = instruction.getClass().getConstructors()[0];
          Object[] objects = new Object[0];
          if (isConditional) {
            ConditionalInstruction conditionalInstruction = (ConditionalInstruction) instruction;
            objects = new Object[]{conditionalInstruction.getState(), conditionalInstruction.getPositionOpcodeReference().clone(), conditionalInstruction.getCondition()};
          } else if (instruction instanceof BitOperation) {
            BitOperation bitOperation = (BitOperation) instruction;
            objects = new Object[]{bitOperation.getState(), bitOperation.getTarget().clone(), bitOperation.getN(), bitOperation.getValueDelta()};
          } else if (instruction instanceof InvertedFetchInstruction<T>) {
            InvertedFetchInstruction invertedFetchInstruction = (InvertedFetchInstruction) instruction;
            objects = new Object[]{invertedFetchInstruction.getState(), invertedFetchInstruction.getTarget().clone(), invertedFetchInstruction.getValueDelta()};
          } else if (instruction instanceof TargetSourceInstruction) {
            TargetSourceInstruction<T> targetSourceInstruction = (TargetSourceInstruction<T>) instruction;
            objects = new Object[]{targetSourceInstruction.getState(), targetSourceInstruction.getTarget().clone(), targetSourceInstruction.getSource().clone()};
          } else if (instruction instanceof TargetInstruction) {
            TargetInstruction<T> targetInstruction = (TargetInstruction<T>) instruction;
            objects = new Object[]{targetInstruction.getState(), targetInstruction.getTarget().clone()};
          } else if (constructor.getParameterCount() == 1) {
            objects = new Object[]{instruction.getState()};
          } else
            System.out.println("dagadg");

          newInstance = (AbstractInstruction<T>) constructor.newInstance(objects);
        }
      }

      if (isConditional || isDJNZ)
        ((ConditionalInstruction<T>) newInstance).setJumpAddress(((ConditionalInstruction<T>) instruction).getJumpAddress());

      newInstance.setLength(instruction.getLength());
      return newInstance;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}

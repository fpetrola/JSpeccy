package com.fpetrola.z80.instructions.cache;

import java.lang.reflect.Constructor;

import com.fpetrola.z80.instructions.*;
import com.fpetrola.z80.instructions.base.*;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.flag.FlagRegister;

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
      if (instruction instanceof RL rl) {
        newInstance = instructionFactory.RL(rl.getTarget(), rl.getValueDelta());
      } else if (instruction instanceof And and) {
        newInstance = instructionFactory.And(and.getSource());
      } else if (instruction instanceof Xor xor) {
        newInstance = instructionFactory.Xor(xor.getSource());
      } else if (instruction instanceof Or or) {
        newInstance = instructionFactory.Or(or.getSource());
      } else if (instruction instanceof RLA) {
        newInstance = instructionFactory.RLA();
      } else if (instruction instanceof Inc inc) {
        newInstance = instructionFactory.Inc((OpcodeReference) inc.getTarget().clone());
      } else if (instruction instanceof Ld ld) {
        newInstance = instructionFactory.Ld((OpcodeReference) ld.getTarget().clone(), (ImmutableOpcodeReference) ld.getSource().clone());
      } else if (instruction instanceof IM im) {
        newInstance = instructionFactory.IM(im.getMode());
      } else if (instruction instanceof Ret ret) {
        newInstance = instructionFactory.Ret(ret.getCondition());
      } else if (instruction instanceof RST rst) {
        newInstance = instructionFactory.RST(rst.getP());
      } else {
        if (isDJNZ) {
          newInstance = instructionFactory.DJNZ(((DJNZ) instruction).getPositionOpcodeReference());
        } else {
          FlagRegister flag = null;
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
            TargetSourceInstruction<T, ImmutableOpcodeReference<T>> targetSourceInstruction = (TargetSourceInstruction<T, ImmutableOpcodeReference<T>>) instruction;
            objects = new Object[]{targetSourceInstruction.getTarget().clone(), targetSourceInstruction.getSource().clone(), flag};
          } else if (instruction instanceof TargetInstruction) {
            TargetInstruction<T> targetInstruction = (TargetInstruction<T>) instruction;
            objects = new Object[]{targetInstruction.getState(), targetInstruction.getTarget().clone()};
          } else if (constructor.getParameterCount() == 1) {
            objects = new Object[]{};
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

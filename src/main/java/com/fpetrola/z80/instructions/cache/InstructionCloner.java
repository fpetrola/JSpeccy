package com.fpetrola.z80.instructions.cache;

import java.lang.reflect.Constructor;

import com.fpetrola.z80.instructions.IM;
import com.fpetrola.z80.instructions.RST;
import com.fpetrola.z80.instructions.Ret;
import com.fpetrola.z80.instructions.base.AbstractInstruction;
import com.fpetrola.z80.instructions.base.BitOperation;
import com.fpetrola.z80.instructions.base.ConditionalInstruction;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.instructions.base.TargetInstruction;
import com.fpetrola.z80.instructions.base.TargetSourceInstruction;
import com.fpetrola.z80.opcodes.models.OpcodeReference;

public class InstructionCloner {
  public Instruction clone(AbstractInstruction instruction) {
    try {
      AbstractInstruction newInstance;

      if (instruction instanceof IM) {
        newInstance = new IM(instruction.getState(), ((IM) instruction).getMode());
      } else if (instruction instanceof Ret) {
        newInstance = new Ret(instruction.getState(), ((Ret) instruction).getCondition());
      } else if (instruction instanceof RST) {
        newInstance = new RST(instruction.getState(), ((RST) instruction).getP());
      } else {
        Constructor<?> constructor = instruction.getClass().getConstructors()[0];
        Object[] objects = new Object[0];
        if (instruction instanceof ConditionalInstruction) {
          ConditionalInstruction conditionalInstruction = (ConditionalInstruction) instruction;
          objects = new Object[] { conditionalInstruction.getState(), (OpcodeReference) conditionalInstruction.getTarget().clone(), conditionalInstruction.getCondition() };
        } else if (instruction instanceof BitOperation) {
          BitOperation bitOperation = (BitOperation) instruction;
          objects = new Object[] { bitOperation.getState(), (OpcodeReference) bitOperation.getTarget().clone(), bitOperation.getN(), bitOperation.getValueDelta() };
        } else if (instruction instanceof TargetSourceInstruction) {
          TargetSourceInstruction targetSourceInstruction = (TargetSourceInstruction) instruction;
          objects = new Object[] { targetSourceInstruction.getState(), (OpcodeReference) targetSourceInstruction.getTarget().clone(), (OpcodeReference) targetSourceInstruction.getSource().clone() };
        } else if (instruction instanceof TargetInstruction) {
          TargetInstruction targetInstruction = (TargetInstruction) instruction;
          objects = new Object[] { targetInstruction.getState(), (OpcodeReference) targetInstruction.getTarget().clone() };
        } else if (constructor.getParameterCount() == 1) {
          objects = new Object[] { instruction.getState() };
        } else
          System.out.println("dagadg");

        newInstance = (AbstractInstruction) constructor.newInstance(objects);
      }
      newInstance.setLength(instruction.getLength());
      return newInstance;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}

package com.fpetrola.z80.instructions;

import java.lang.reflect.Constructor;

public class InstructionCloner {
  public OpCode clone(AbstractOpCode instruction) {
    try {
      AbstractOpCode newInstance;

      if (instruction instanceof IM) {
        newInstance = new IM(instruction.state, ((IM) instruction).i);
      } else if (instruction instanceof Ret) {
        newInstance = new Ret(instruction.state, ((Ret) instruction).condition);
      } else if (instruction instanceof RST) {
        newInstance = new RST(instruction.state, ((RST) instruction).p);
      } else {
        Constructor<?> constructor = instruction.getClass().getConstructors()[0];
        Object[] objects = new Object[0];
        if (instruction instanceof ConditionalOpcode) {
          ConditionalOpcode conditionalOpcode = (ConditionalOpcode) instruction;
          objects = new Object[] { conditionalOpcode.state, (OpcodeReference) conditionalOpcode.target.clone(), conditionalOpcode.condition };
        } else if (instruction instanceof BitOperation) {
          BitOperation bitOperation = (BitOperation) instruction;
          objects = new Object[] { bitOperation.state, (OpcodeReference) bitOperation.target.clone(), bitOperation.n, bitOperation.valueDelta };
        } else if (instruction instanceof TargetSourceOpcode) {
          TargetSourceOpcode targetSourceOpcode = (TargetSourceOpcode) instruction;
          objects = new Object[] { targetSourceOpcode.state, (OpcodeReference) targetSourceOpcode.target.clone(), (OpcodeReference) targetSourceOpcode.source.clone() };
        } else if (instruction instanceof TargetOpCode) {
          TargetOpCode targetOpCode = (TargetOpCode) instruction;
          objects = new Object[] { targetOpCode.state, (OpcodeReference) targetOpCode.target.clone() };
        } else if (constructor.getParameterCount() == 1) {
          objects = new Object[] { instruction.state };
        } else
          System.out.println("dagadg");

        newInstance = (AbstractOpCode) constructor.newInstance(objects);
      }
      newInstance.setLength(instruction.length);
      return newInstance;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}

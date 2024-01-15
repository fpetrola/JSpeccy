package com.fpetrola.z80.instructions;

import java.lang.reflect.Constructor;

import com.fpetrola.z80.registers.Register;

public class InstructionCloner {
  private Register pc;

  public InstructionCloner(Register pc) {
    this.pc = pc;
  }

  public void completeClone(AbstractOpCode opCode, int length) {
    opCode.setBasePc(pc.read());
    opCode.setLength(length);
  }

  public Ldir cloneLdir(Ldir ldir) {
    Ldir result = new Ldir(ldir.state);
    completeClone(result, ldir.length);
    return result;
  }

  public IM cloneIM(IM im) {
    IM result = new IM(im.state, im.i);
    completeClone(result, im.length);
    return result;
  }

  public Ret cloneRET(Ret im) {
    Ret result = new Ret(im.state, im.condition);
    completeClone(result, im.length);
    return result;
  }

  public RST cloneRST(RST im) {
    RST result = new RST(im.state, im.p);
    completeClone(result, im.length);
    return result;
  }

  public OpCode clone(AbstractOpCode instruction) {
    try {

      if (instruction instanceof IM) {
        return cloneIM((IM) instruction);
      } else if (instruction instanceof Ret) {
        return cloneRET((Ret) instruction);
      } else if (instruction instanceof RST) {
        return cloneRST((RST) instruction);
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

        AbstractOpCode newInstance = (AbstractOpCode) constructor.newInstance(objects);
        completeClone(newInstance, instruction.length);
        return newInstance;
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}

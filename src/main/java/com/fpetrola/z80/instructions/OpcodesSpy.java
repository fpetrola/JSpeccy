package com.fpetrola.z80.instructions;

import java.util.ArrayList;
import java.util.List;

import com.fpetrola.z80.GraphExperiment;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterName;
import com.fpetrola.z80.registers.RegisterPair;

public class OpcodesSpy {
  boolean capturing;
  private Memory memory;
  private boolean enabled;
  private ExecutionStepData executionStepData = new ExecutionStepData();
  private List<ExecutionStepData> executionStepDatas = new ArrayList<>();

  public OpcodesSpy(Memory memory) {
    super();
    this.memory = memory;
  }

  public boolean isCapturing() {
    return capturing;
  }

  public Memory wrapMemory(Memory aMemory) {
    return new MemorySpy(memory, this);
  }

  public OpcodeReference wrapOpcodeReference(OpcodeReference opcodeReference) {
    return new OpcodeReferenceSpy(opcodeReference, this);
  }

  public Register wrapOpcodeRegister(Register register, RegisterName name) {
    if (name == RegisterName.F) {
      return register;
    } else if (register instanceof RegisterPair) {
      return new RegisterPairSpy(register, this);
    } else
      return new RegisterSpy(register, this);
  }

  public void start(OpCode opcode, int opcodeInt, int pcValue) {
    capturing = enabled;
    if (capturing) {
      executionStepData = new ExecutionStepData();
      executionStepData.opcode = opcode;
      System.out.println(pcValue + " -------------------------------------------------");
      System.out.println(opcode + " (" + GraphExperiment.convertToHex(opcodeInt) + ")");
    }
  }

  public void end() {
    if (capturing) {
//      System.out.println("capturo!!");
//      System.out.println("-------------------------------------------------");
      capturing = false;
      executionStepDatas.add(executionStepData);
    }

  }

  public void enable(boolean enabled) {
    this.enabled = enabled;
  }

  public void addWriteReference(OpcodeReference opcodeReference, int value) {
    if (capturing)
      executionStepData.addWriteReference(opcodeReference, value);
  }

  public void addReadReference(OpcodeReference opcodeReference, int value) {
    if (capturing)
      executionStepData.addReadReference(opcodeReference, value);
  }

  public void addWriteMemoryReference(int address, int value) {
    if (capturing)
      executionStepData.writeMemoryReferences.add(new WriteMemoryReference(address, value));
  }

  public void addReadMemoryReference(int address, int value) {
    if (capturing)
      executionStepData.readMemoryReferences.add(new ReadMemoryReference(address, value));
  }

  public void flipOpcode(OpCode opCode, int opcodeInt) {
    if (capturing) {
      executionStepData.opcode = opCode;
      System.out.println(opCode + " (" + GraphExperiment.convertToHex(opcodeInt) + ")");
    }
  }
}

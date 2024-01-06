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
  private boolean enabled;
  private ExecutionStepData executionStepData = new ExecutionStepData();
  private List<ExecutionStepData> executionStepDatas = new ArrayList<>();
  private MemorySpy memorySpy;
  private boolean print = false;

  public OpcodesSpy() {
    super();
  }

  public boolean isCapturing() {
    return capturing;
  }

  public Memory wrapMemory(Memory aMemory) {
    if (memorySpy == null)
      memorySpy = new MemorySpy(aMemory, this);
    return memorySpy;
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
      executionStepData.opcodeInt = opcodeInt;
      executionStepData.pcValue = pcValue;
      if (print)

        printOpCodeHeader(executionStepData);
    }
  }

  public void printOpCodeHeader(ExecutionStepData executionStepData) {
    System.out.println(executionStepData.pcValue + " -------------------------------------------------");
    System.out.println(executionStepData.opcode + " (" + GraphExperiment.convertToHex(executionStepData.opcodeInt) + ")");
  }

  public void end() {
    if (capturing) {
//      System.out.println("capturo!!");
//      System.out.println("-------------------------------------------------");
      capturing = false;
      executionStepDatas.add(executionStepData);

//      printOpCodeHeader(executionStepData);
//      executionStepData.accessReferences.forEach(ar -> {
//        if (ar.toString().equals("mem(32768):= 1"))
//          System.out.println("sdgsdag");
//        System.out.println(ar);
//      });
    }

  }

  public void enable(boolean enabled) {
    boolean wasEnabled = this.enabled;
    this.enabled = enabled;
    if (wasEnabled) {
      print = true;

      for (int i = executionStepDatas.size() - 1; i >= 0; i--) {
        ExecutionStepData step = executionStepDatas.get(i);
        if (print) {
          printOpCodeHeader(step);
        }

        for (int j = step.accessReferences.size() - 1; j >= 0; j--) {
          Object ar = step.accessReferences.get(j);

          if (print) {
            System.out.println(ar);
          }

//          if (print && ar instanceof ReadMemoryReference) {
//            ReadMemoryReference readMemoryReference = (ReadMemoryReference) ar;
//            if (memorySpy.getAddressModificationsCounter(readMemoryReference.address) == 0 && readMemoryReference.address >= 0x5CCB) {
//              if (step.opcode.toString().contains("(")) {
//                System.out.println("lo encontre!!");
//              }
//            }
//          }
//
//          if (!print && ar instanceof WriteMemoryReference) {
//            WriteMemoryReference wr = (WriteMemoryReference) ar;
//            if (wr.address > 0x4000 && wr.address < (0x5800)) {
//              printOpCodeHeader(step);
//              System.out.println(ar);
//              print = true;
//            }
//          }
        }

//        if (step.opcode.toString().contains("PUSH"))
//          System.out.println("sdgdsag");
      }
    }

  }

  public void addWriteReference(OpcodeReference opcodeReference, int value) {
    if (capturing) {
      WriteOpcodeReference writeReference = executionStepData.addWriteReference(opcodeReference, value);
      if (print)
        System.out.println(writeReference);
    }
  }

  public void addReadReference(OpcodeReference opcodeReference, int value) {
    if (capturing) {
      ReadOpcodeReference readReference = executionStepData.addReadReference(opcodeReference, value);
      if (print)
        System.out.println(readReference);
    }
  }

  public void addWriteMemoryReference(int address, int value) {
    if (capturing) {
      WriteMemoryReference writeMemoryReference = executionStepData.addWriteMemoryReference(address, value);
      if (print)
        System.out.println(writeMemoryReference);

    }
  }

  public void addReadMemoryReference(int address, int value) {
    if (capturing) {
      ReadMemoryReference readMemoryReference = executionStepData.addReadMemoryReference(address, value);
      if (print)
        System.out.println(readMemoryReference);

    }
  }

  public void flipOpcode(OpCode opCode, int opcodeInt) {
    if (capturing) {
      executionStepData.opcode = opCode;
      if (print)
        System.out.println(opCode + " (" + GraphExperiment.convertToHex(opcodeInt) + ")");
    }
  }
}

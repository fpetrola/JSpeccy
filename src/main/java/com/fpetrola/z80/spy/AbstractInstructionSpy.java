package com.fpetrola.z80.spy;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import com.fpetrola.z80.OOZ80;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.opcodes.references.MemoryPlusRegister8BitReference;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterName;
import com.fpetrola.z80.registers.RegisterPair;

public class AbstractInstructionSpy implements InstructionSpy{

  boolean capturing;
  private boolean enabled;
  private ExecutionStepData executionStepData;
  private List<ExecutionStepData> executionStepDatas = new ArrayList<>();
  private MemorySpy memorySpy;
  private boolean print = false;
  private boolean[] bitsWritten;
  private Memory aMemory;

  public AbstractInstructionSpy() {
    super();
  }

  public boolean isCapturing() {
    return capturing;
  }

  public Memory wrapMemory(Memory aMemory) {
    this.aMemory = aMemory;
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

  public void start(Instruction opcode, int opcodeInt, int pcValue) {
    capturing = enabled;
    if (capturing) {
      executionStepData = new ExecutionStepData(aMemory);
      executionStepData.instruction = opcode;
      executionStepData.opcodeInt = opcodeInt;
      executionStepData.pcValue = pcValue;
      if (print)
        executionStepData.printOpCodeHeader();
    }
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
        processInReverse();
        executionStepDatas.clear();
  //      executionStepData.clear();
      }
    }

  private void processInReverse() {
    walkReverse(this::p2);
  }

  private void walkReverse(Consumer<ExecutionStepData> stepProcessor) {
    for (int i = executionStepDatas.size() - 1; i >= 0; i--) {
      ExecutionStepData step = executionStepDatas.get(i);
      stepProcessor.accept(step);
    }
  }

  private void p2(ExecutionStepData step) {
    if (print) {
      step.printOpCodeHeader();
    }
  
    walkAccessReverse(step, this::processAccess);
  }

  private void walkAccessReverse(ExecutionStepData step, AccessProcessor accessProcessor) {
    for (int j = step.accessReferences.size() - 1; j >= 0; j--) {
      Object ar = step.accessReferences.get(j);
      accessProcessor.processAccess(step, ar);
    }
  }

  public void processAccess(ExecutionStepData step, Object ar) {
    if (print) {
      System.out.println(ar);
    }
  
    findSpriteReading(step, ar);
    if (isScreenWriting(step, ar)) {
      if (!step.readMemoryReferences.isEmpty()) {
        System.out.println("memory read when memory write!");
      }
    }
  }

  private boolean isScreenWriting(ExecutionStepData step, Object ar) {
      if (ar instanceof WriteMemoryReference) {
        WriteMemoryReference wr = (WriteMemoryReference) ar;
        if (wr.address > 0x4000 && wr.address < (0x5800)) {
  //        if (print) {
  //          step.printOpCodeHeader();
  //          System.out.println(ar);
  //        }
          return true;
        }
      }
      return capturing;
    }

  private void findSpriteReading(ExecutionStepData step, Object ar) {
      if (ar instanceof ReadMemoryReference) {
        ReadMemoryReference readMemoryReference = (ReadMemoryReference) ar;
        if (memorySpy.getAddressModificationsCounter(readMemoryReference.address) < 100 && readMemoryReference.address >= 0x5CCB) {
          if (step.instruction.toString().contains("(")) {
            int address = readMemoryReference.address;
            if (bitsWritten != null)
              for (int k = 0; k < 8; k++) {
                bitsWritten[address * 8 + k] = true;
              }
  //                System.out.println("lo encontre!!");
          }
        }
      }
    }

  public void addWriteReference(OpcodeReference opcodeReference, int value, boolean isIncrement) {
    if (capturing) {
      WriteOpcodeReference writeReference = executionStepData.addWriteReference(opcodeReference, value, isIncrement);
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

  public void flipOpcode(Instruction instruction, int opcodeInt) {
    if (capturing) {
      executionStepData.instruction = instruction;
      if (print)
        System.out.println(instruction + " (" + OOZ80.convertToHex(opcodeInt) + ")");
    }
  }

  public void setSpritesArray(boolean[] bitsWritten) {
    this.bitsWritten = bitsWritten;
  }

  public void undo() {
    executionStepData.undo();
  }

  public MemoryPlusRegister8BitReference wrapMemoryPlusRegister8BitReference(MemoryPlusRegister8BitReference memoryPlusRegister8BitReference) {
    return new MemoryPlusRegister8BitReferenceSpy(memoryPlusRegister8BitReference);
  }

}
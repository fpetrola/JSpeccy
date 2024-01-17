package com.fpetrola.z80.spy;

public class DefaultInstructionSpy extends AbstractInstructionSpy implements InstructionSpy {
  public DefaultInstructionSpy() {
    super();
  }

  public void process() {
    int screenWritingIndex = walkReverse(step -> walkAccessReverse(step, this::isScreenWriting), executionStepDatas.size());
    System.out.println(screenWritingIndex);
  }

  private int p2(ExecutionStepData step) {
    if (print) {
      step.printOpCodeHeader();
    }

    return walkAccessReverse(step, this::processAccess);
  }

  public boolean processAccess(ExecutionStepData step, Object ar) {
    if (print) {
      System.out.println(ar);
    }

    findSpriteReading(step, ar);
    if (isScreenWriting(step, ar))
      return true;

    return false;
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
}

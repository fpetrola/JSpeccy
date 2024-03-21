package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.BitOperation;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.flag.AluResult;
import com.fpetrola.z80.registers.flag.TableAluOperation;

public class BIT<T extends WordNumber> extends BitOperation<T> {
  public static final TableAluOperation testBitTableAluOperation = new TableAluOperation() {
    public AluResult execute(int bit, int value, int carry) {
      resetS();

      switch (bit) {
        case 0: {
          value = value & setBit0;
          break;
        }
        case 1: {
          value = value & setBit1;
          break;
        }
        case 2: {
          value = value & setBit2;
          break;
        }
        case 3: {
          value = value & setBit3;
          break;
        }
        case 4: {
          value = value & setBit4;
          break;
        }
        case 5: {
          value = value & setBit5;
          break;
        }
        case 6: {
          value = value & setBit6;
          break;
        }
        case 7: {
          value = value & setBit7;
          setS(value != 0);
          break;
        }
      }
      setZ(0 == value);
      setPV(0 == value);
      resetN();
      setH();

      return new AluResult(value, data);
    }
  };

  public BIT(OpcodeReference target, int n, Register<T> flag) {
    super(target, n, flag);
  }

  public int execute() {
    final T value = target.read();
    testBitTableAluOperation.executeWithCarry(value, WordNumber.createValue(n), flag);
    return cyclesCost;
  }
}

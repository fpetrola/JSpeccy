package com.fpetrola.z80.opcodes.decoder.table;

import static com.fpetrola.z80.registers.RegisterName.H;
import static com.fpetrola.z80.registers.RegisterName.L;

import com.fpetrola.z80.instructions.Ld;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.MemoryPlusRegister8BitReference;
import com.fpetrola.z80.opcodes.references.OpcodeConditions;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterName;
import com.fpetrola.z80.spy.InstructionSpy;

public class IndexerRegisterTableOpCodeGenerator extends UnprefixedTableOpCodeGenerator {
  private final RegisterName lowRegisterName;
  private final RegisterName highRegisterName;
  private final RegisterName registerName;

  public IndexerRegisterTableOpCodeGenerator(State state, InstructionSpy opcodesSpy, Instruction cbOpcode, Instruction ddOpcode, Instruction edOpcode, Instruction fdOpcode, RegisterName main16BitRegister, RegisterName mainHigh8BitRegister, RegisterName mainLow8BitRegister, OpcodeReference main16BitRegisterReference, RegisterName lowRegisterName, RegisterName highRegisterName, RegisterName registerName, OpcodeConditions opc1) {
    super(2, state, opcodesSpy, cbOpcode, ddOpcode, edOpcode, fdOpcode, main16BitRegister, mainHigh8BitRegister, mainLow8BitRegister, main16BitRegisterReference, opc1);
    this.lowRegisterName = lowRegisterName;
    this.highRegisterName = highRegisterName;
    this.registerName = registerName;
  }

  protected Ld createLd() {
    OpcodeReference target = r[y];
    OpcodeReference source = r[z];

    if (isHL(source) || isHL(target)) {
      source = replaceLowHigh(source);
      target = replaceLowHigh(target);
    }
    return new Ld(s, target, source);
  }

  private OpcodeReference replaceLowHigh(OpcodeReference source) {
    if (source instanceof Register) {
      Register register = (Register) source;
      if (register.getName().equals(r(lowRegisterName).getName()))
        return r(L);
      else if (register.getName().equals(r(highRegisterName).getName()))
        return r(H);
    }

    return source;
  }

  private boolean isHL(OpcodeReference source) {
    return source instanceof MemoryPlusRegister8BitReference;
  }

  protected Ld createLd1() {
    OpcodeReference target = r[y];
    if (isHL(target))
      return new Ld(s, iRRn(registerName, false, 2), n(3));
    else
      return new Ld(s, target, n(2));
  }
}
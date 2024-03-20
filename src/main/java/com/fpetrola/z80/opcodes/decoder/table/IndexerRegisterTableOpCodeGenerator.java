package com.fpetrola.z80.opcodes.decoder.table;

import com.fpetrola.z80.instructions.Ld;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.instructions.base.InstructionFactory;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.MemoryPlusRegister8BitReference;
import com.fpetrola.z80.opcodes.references.OpcodeConditions;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterName;

import static com.fpetrola.z80.registers.RegisterName.H;
import static com.fpetrola.z80.registers.RegisterName.L;

public class IndexerRegisterTableOpCodeGenerator<T> extends UnprefixedTableOpCodeGenerator<T> {
  private final RegisterName lowRegisterName;
  private final RegisterName highRegisterName;
  private final RegisterName registerName;

  public IndexerRegisterTableOpCodeGenerator(State state, Instruction<T> cbOpcode, Instruction<T> ddOpcode, Instruction<T> edOpcode, Instruction<T> fdOpcode, RegisterName main16BitRegister, RegisterName mainHigh8BitRegister, RegisterName mainLow8BitRegister, OpcodeReference main16BitRegisterReference, RegisterName lowRegisterName, RegisterName highRegisterName, RegisterName registerName, OpcodeConditions opc1, InstructionFactory instructionFactory) {
    super(2, state, cbOpcode, ddOpcode, edOpcode, fdOpcode, main16BitRegister, mainHigh8BitRegister, mainLow8BitRegister, main16BitRegisterReference, opc1, instructionFactory);
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
    return i.Ld(target, source);
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

  private boolean isHL(ImmutableOpcodeReference source) {
    return source instanceof MemoryPlusRegister8BitReference;
  }

  protected Ld createLd1() {
    OpcodeReference target = r[y];
    if (isHL(target))
      return i.Ld(iRRn(registerName, false, 2), n(3));
    else
      return i.Ld(target, n(2));
  }
}
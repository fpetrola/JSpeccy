package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.instructions.base.TargetSourceInstruction;
import com.fpetrola.z80.instructions.cache.InstructionCloner;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.*;
import com.fpetrola.z80.registers.flag.Delegate;
import com.fpetrola.z80.registers.flag.FlagProxyFactory;
import com.fpetrola.z80.registers.flag.FlagRegister;
import com.fpetrola.z80.spy.AbstractInstructionSpy;

import java.util.HashMap;
import java.util.Map;

public class RegisterTransformerInstructionSpy<T extends WordNumber> extends AbstractInstructionSpy<T> {
  private final InstructionCloner<T> instructionCloner;
  protected Instruction<T> cloned;
  protected Map<Register, Register> targets = new HashMap<>();
  protected Delegate flagDelegate;

  public RegisterTransformerInstructionSpy(InstructionCloner<T> instructionCloner) {
    this.instructionCloner = instructionCloner;
  }

  public void enable() {
    super.enable();
    flagDelegate.disable();
  }

  public void disable() {
    super.disable();
    flagDelegate.enable();
  }

  public Register<T> wrapRegister(Register<T> register) {
    if (register instanceof FlagRegister flagRegister) {
      flagDelegate = new FlagProxyFactory().createDummyFlagRegisterProxy(flagRegister);
      return (Register<T>) flagDelegate;
    } else if (register instanceof RegisterPair registerPair) {
      return new Composed16BitRegister<>(register.getName(), registerPair.getHigh(), registerPair.getLow()) {
        public Register getHigh() {
          return super.getHigh();
        }

        @Override
        public T read() {
          return super.read();
        }
      };
    } else {
      return new Plain8BitRegister<T>(register.getName()) {
        public T read() {
          return register.read();
        }

        public void write(T value) {
          register.write(value);
        }
      };
    }
  }


  public Instruction<T> processInstruction(Instruction<T> instruction) {
    cloned = instructionCloner.clone(instruction);
    return cloned;
  }
}

package com.fpetrola.z80.instructions;

import com.fpetrola.z80.blocks.DummyInstructionVisitor;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.instructions.base.InstructionVisitor;
import com.fpetrola.z80.instructions.base.TargetInstruction;
import com.fpetrola.z80.instructions.base.TargetSourceInstruction;
import com.fpetrola.z80.opcodes.references.*;
import com.fpetrola.z80.registers.*;

import java.util.HashMap;
import java.util.Map;

import static com.fpetrola.z80.registers.RegisterName.VIRTUAL;

public class TransformerVisitor<T extends WordNumber> extends DummyInstructionVisitor<T> implements InstructionVisitor<T> {

  protected Map<Register, Register> targets = new HashMap<>();

  public <T extends WordNumber> TransformerVisitor() {
    this.targets = targets;
  }

  public static <T extends WordNumber> Register createVirtualRegister(Instruction<T> targetInstruction, ImmutableOpcodeReference<T> targetRegister, boolean[] executing) {

    Register virtualRegister = new Plain8BitRegister<T>(VIRTUAL) {
      public T read() {
        if (data != null)
          return data;

        if (!executing[0]) {
          executing[0] = true;
          targetInstruction.execute();
          executing[0] = false;
          return (T) data;
        } else
          return (T) targetRegister.read();
      }
    };
    return virtualRegister;
  }

  public <T extends WordNumber> Register create16VirtualRegister(Instruction<T> targetInstruction, RegisterPair registerPair, boolean indirect) {
    if (!indirect) {
      boolean[] executing = new boolean[1];
      targets.put(registerPair.getHigh(), createVirtualRegister(targetInstruction, getTargetRegister(registerPair.getHigh()), executing));
      targets.put(registerPair.getLow(), createVirtualRegister(targetInstruction, getTargetRegister(registerPair.getLow()), executing));
    }
    return new Composed16BitRegister<WordNumber>(VIRTUAL, targets.get(registerPair.getHigh()), targets.get(registerPair.getLow()));
  }

  private <T extends WordNumber> DummyImmutableOpcodeReference<T> getTargetRegister(Register register) {
    Register lastVirtualRegister = targets.get(register);
    return new DummyImmutableOpcodeReference<T>() {
      public T read() {
        return (T) lastVirtualRegister.read();
      }
    };
  }

  @Override
  public void visitingTarget(OpcodeReference target, TargetInstruction targetInstruction) {
    if (target instanceof IndirectMemory8BitReference indirectMemory8BitReference) {
      OpcodeReference target1 = (OpcodeReference) indirectMemory8BitReference.target;
      if (target1 instanceof Register register) {
        indirectMemory8BitReference.target = createVirtualRegister(targetInstruction, register, true);
      }
    } else if (target instanceof Register register) {
      targetInstruction.setTarget(createVirtualRegister(targetInstruction, register, false));

    }
  }

  private Register createVirtualRegister(TargetInstruction targetInstruction, Register register, boolean indirect) {
    Register virtualRegister;
    if (register instanceof RegisterPair registerPair)
      virtualRegister = create16VirtualRegister(targetInstruction, registerPair, indirect);
    else
      virtualRegister = createVirtualRegister(targetInstruction, targets.get(register), new boolean[1]);

    targets.put(register, virtualRegister);

    return virtualRegister;
  }

  @Override
  public void visitingSource(ImmutableOpcodeReference source, TargetSourceInstruction targetSourceInstruction) {
    if (source instanceof Register register) {
      Register virtual = targets.get(register);
      targetSourceInstruction.setSource(virtual);
    }
  }
}

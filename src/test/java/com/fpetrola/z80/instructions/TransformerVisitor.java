package com.fpetrola.z80.instructions;

import com.fpetrola.z80.blocks.DummyInstructionVisitor;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.instructions.base.InstructionVisitor;
import com.fpetrola.z80.instructions.base.TargetInstruction;
import com.fpetrola.z80.instructions.base.TargetSourceInstruction;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.IndirectMemory16BitReference;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.*;

import java.util.HashMap;
import java.util.Map;

import static com.fpetrola.z80.registers.RegisterName.VIRTUAL;

public class TransformerVisitor<T extends WordNumber> extends DummyInstructionVisitor<T> implements InstructionVisitor<T> {

  protected Map<Register, Register> targets = new HashMap<>();

  public <T extends WordNumber> TransformerVisitor() {
    this.targets = targets;
  }

  public static <T extends WordNumber> Register createVirtualRegister(Instruction<T> targetInstruction, Register register) {
    Register virtualRegister = new Plain8BitRegister<T>(VIRTUAL) {
      private boolean executing;

      public T read() {
        if (data != null)
          return data;

        if (!executing) {
          executing = true;
          targetInstruction.execute();
          executing = false;
          return (T) data;
        } else
          return (T) register.read();
      }
    };
    return virtualRegister;
  }

  public <T extends WordNumber> Register create16VirtualRegister(Instruction<T> targetInstruction, RegisterPair registerPair) {
    Register sourceH = targets.get(registerPair.getHigh());
    Register sourceL = targets.get(registerPair.getLow());

    return new Composed16BitRegister(VIRTUAL, sourceH, sourceL);
  }

  @Override
  public void visitingTarget(OpcodeReference target, TargetInstruction targetInstruction) {
    if (target instanceof IndirectMemory16BitReference indirectMemory16BitReference) {
      OpcodeReference target1 = (OpcodeReference) indirectMemory16BitReference.target;
      if (target1 instanceof Register register) {
        Register register1 = pR1(target1, targetInstruction, register);
        indirectMemory16BitReference.target = register1;
      }
    } else if (target instanceof Register register) {
      targetInstruction.setTarget(pR1(target, targetInstruction, register));

    }
  }

  private Register pR1(OpcodeReference target, TargetInstruction targetInstruction, Register register) {
    Register virtualRegister;
    if (register instanceof RegisterPair registerPair)
      virtualRegister = create16VirtualRegister(targetInstruction, registerPair);
    else
      virtualRegister = createVirtualRegister(targetInstruction, targets.get(target));

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

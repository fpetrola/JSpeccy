package com.fpetrola.z80.instructions.transformations;

import com.fpetrola.z80.blocks.DummyInstructionVisitor;
import com.fpetrola.z80.cpu.InstructionExecutor;
import com.fpetrola.z80.instructions.DJNZ;
import com.fpetrola.z80.instructions.Inc16;
import com.fpetrola.z80.instructions.base.TargetInstruction;
import com.fpetrola.z80.instructions.base.TargetSourceInstruction;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.IndirectMemory8BitReference;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;

@SuppressWarnings("ALL")
public class TransformerVisitor<T extends WordNumber> extends DummyInstructionVisitor<T> {
  private final VirtualRegisterFactory<T> virtualRegisterFactory;

  public <T extends WordNumber> TransformerVisitor(InstructionExecutor instructionExecutor) {
    virtualRegisterFactory = new VirtualRegisterFactory(instructionExecutor);
  }

  @Override
  public void visitingTarget(OpcodeReference target, TargetInstruction targetInstruction) {
    if (target instanceof IndirectMemory8BitReference indirectMemory8BitReference) {
      OpcodeReference target1 = (OpcodeReference) indirectMemory8BitReference.target;
      if (target1 instanceof Register register) {
        indirectMemory8BitReference.target = virtualRegisterFactory.createVirtual8BitsRegister(targetInstruction, register, true);
      }
    } else if (target instanceof Register register) {
      targetInstruction.setTarget(virtualRegisterFactory.createVirtual8BitsRegister(targetInstruction, register, false));
    }
  }

  public void visitingSource(ImmutableOpcodeReference source, TargetSourceInstruction targetSourceInstruction) {
    if (source instanceof Register register) {
      targetSourceInstruction.setSource(virtualRegisterFactory.getOrCreateVirtualRegister(register));
    }
  }

  public void visitingInc16(Inc16 inc16) {
    visitingTarget(inc16.getTarget(), inc16);
  }

  public void visitingDjnz(DJNZ djnz) {
    Register virtualRegister = virtualRegisterFactory.createVirtual8BitsRegister(null, djnz.getB(), false);
    djnz.setB(virtualRegister);
    super.visitingDjnz(djnz);
  }
}

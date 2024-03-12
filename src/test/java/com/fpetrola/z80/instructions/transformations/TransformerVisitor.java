package com.fpetrola.z80.instructions.transformations;

import com.fpetrola.z80.blocks.DummyInstructionVisitor;
import com.fpetrola.z80.cpu.InstructionExecutor;
import com.fpetrola.z80.instructions.DJNZ;
import com.fpetrola.z80.instructions.Dec;
import com.fpetrola.z80.instructions.Inc16;
import com.fpetrola.z80.instructions.JR;
import com.fpetrola.z80.instructions.base.TargetInstruction;
import com.fpetrola.z80.instructions.base.TargetSourceInstruction;
import com.fpetrola.z80.opcodes.references.*;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.flag.FlagRegister;

@SuppressWarnings("ALL")
public class TransformerVisitor<T extends WordNumber> extends DummyInstructionVisitor<T> {
  public final VirtualRegisterFactory<T> virtualRegisterFactory;

  public <T extends WordNumber> TransformerVisitor(InstructionExecutor instructionExecutor) {
    virtualRegisterFactory = new VirtualRegisterFactory(instructionExecutor);
  }

  @Override
  public void visitingTarget(OpcodeReference target, TargetInstruction targetInstruction) {
    if (target instanceof IndirectMemory8BitReference indirectMemory8BitReference) {
      OpcodeReference target1 = (OpcodeReference) indirectMemory8BitReference.target;
      if (target1 instanceof Register register) {
        indirectMemory8BitReference.target = virtualRegisterFactory.createVirtualRegister(null, register, new VirtualFetcher());
      }
    } else if (target instanceof Register register) {
      targetInstruction.setTarget(virtualRegisterFactory.createVirtualRegister(targetInstruction, register, new VirtualFetcher()));
    }
  }

  public void visitingSource(ImmutableOpcodeReference source, TargetSourceInstruction targetSourceInstruction) {
    if (source instanceof Register register) {
      targetSourceInstruction.setSource(virtualRegisterFactory.createVirtualRegister(null, register, new VirtualFetcher()));
    }
  }

  public void visitingInc16(Inc16 inc16) {
    visitingTarget(inc16.getTarget(), inc16);
  }

  public void visitingDjnz(DJNZ djnz) {
    Register virtualRegister = virtualRegisterFactory.createVirtualRegister(null, djnz.getB(), new VirtualFetcher());
    djnz.setB(virtualRegister);
  }

  public void visitingJR(JR jr) {
    jr.accept(new DummyInstructionVisitor() {
      public void visitingConditionFlag(ConditionFlag conditionFlag) {
        Register virtualRegister = virtualRegisterFactory.createVirtualRegister(null, conditionFlag.getRegister(), new VirtualFetcher());
        conditionFlag.setRegister(virtualRegister);
      }
    });
  }

  public void visitingDec(Dec dec) {
    FlagRegister flag = dec.getFlag();
    VirtualFetcher virtualFetcher = new VirtualFetcher();
    FlagRegister virtualRegister = (FlagRegister) virtualRegisterFactory.createVirtualRegister(dec, flag, virtualFetcher);
    Register virtualRegister1 = virtualRegisterFactory.createVirtualRegister(dec, (Register) dec.getTarget(), virtualFetcher);

    dec.setFlag(virtualRegister);
    dec.setTarget(virtualRegister1);

  }
}

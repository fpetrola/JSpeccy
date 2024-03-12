package com.fpetrola.z80.instructions.transformations;

import com.fpetrola.z80.blocks.DummyInstructionVisitor;
import com.fpetrola.z80.cpu.InstructionExecutor;
import com.fpetrola.z80.instructions.*;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.instructions.base.TargetInstruction;
import com.fpetrola.z80.instructions.base.TargetSourceInstruction;
import com.fpetrola.z80.instructions.cache.InstructionCloner;
import com.fpetrola.z80.opcodes.references.*;
import com.fpetrola.z80.registers.Register;

public class InstructionTransformer<T extends WordNumber> extends InstructionCloner<T> {
  public final VirtualRegisterFactory virtualRegisterFactory;

  public void setCurrentInstruction(Instruction currentInstruction) {
    this.currentInstruction = currentInstruction;
  }

  private Instruction currentInstruction;

  public InstructionTransformer(InstructionFactory instructionFactory, InstructionExecutor instructionExecutor) {
    super(instructionFactory);
    virtualRegisterFactory = new VirtualRegisterFactory(instructionExecutor);
  }

  private <R extends PublicCloneable> R createRegisterReplacement(R cloneable, Instruction currentInstruction1, VirtualFetcher virtualFetcher) {
    if (cloneable instanceof IndirectMemory8BitReference indirectMemory8BitReference) {
      OpcodeReference target1 = (OpcodeReference) indirectMemory8BitReference.target;
      ImmutableOpcodeReference result;
      if (target1 instanceof Register register) {
        result = virtualRegisterFactory.createVirtualRegister(null, register, virtualFetcher);
      } else {
        result = clone(indirectMemory8BitReference.target);
      }

      return (R) new IndirectMemory8BitReference(result, indirectMemory8BitReference.getMemory());
    } else if (cloneable instanceof Register register) {
      return (R) virtualRegisterFactory.createVirtualRegister(currentInstruction1, register, virtualFetcher);
    } else
      return super.clone(cloneable);
  }

  public <R extends PublicCloneable> R clone2(ImmutableOpcodeReference immutableOpcodeReference) {
    return (R) createRegisterReplacement(immutableOpcodeReference, null, new VirtualFetcher());
  }

  public void visitingLd(Ld ld) {
    super.visitingLd(ld);
    visitingTargetSourceInstruction((TargetSourceInstruction) cloned);
  }

  public void visitingInc16(Inc16 inc16) {
    super.visitingInc16(inc16);
    Inc16 cloned1 = (Inc16) cloned;

    cloned1.setTarget(createRegisterReplacement(cloned1.getTarget(), cloned1, new VirtualFetcher()));
  }

  public void visitingTargetInstruction(TargetInstruction targetInstruction, VirtualFetcher virtualFetcher) {
    targetInstruction.setTarget(createRegisterReplacement(targetInstruction.getTarget(), targetInstruction, virtualFetcher));
  }

  @Override
  public void visitingTargetSourceInstruction(TargetSourceInstruction targetSourceInstruction) {
    visitingTargetInstruction(targetSourceInstruction, new VirtualFetcher());
    targetSourceInstruction.setSource(createRegisterReplacement(targetSourceInstruction.getSource(), null, new VirtualFetcher()));
  }

  public void visitingDjnz(DJNZ djnz) {
    super.visitingDjnz(djnz);
    DJNZ djnz1 = (DJNZ) cloned;
    djnz1.setB(createRegisterReplacement(djnz1.getB(), null, new VirtualFetcher()));
  }

  public void visitingJR(JR jr) {
    super.visitingJR(jr);
    JR jr1 = (JR) cloned;

    jr1.accept(new DummyInstructionVisitor() {
      public void visitingConditionFlag(ConditionFlag conditionFlag) {
        changeConditionalFlag(conditionFlag);
      }
    });
  }

  private void changeConditionalFlag(ConditionFlag conditionFlag) {
    conditionFlag.setRegister(createRegisterReplacement(conditionFlag.getRegister(), null, new VirtualFetcher()));
  }

  @Override
  public void visitingInc(Inc inc) {
    super.visitingInc(inc);
    TargetInstruction cloned1 = (TargetInstruction) cloned;
    visitingTargetInstruction(cloned1, new VirtualFetcher());
  }


  public void visitingDec(Dec dec) {
    super.visitingDec(dec);

    Dec dec1 = (Dec) cloned;
    VirtualFetcher virtualFetcher = new VirtualFetcher();
    visitingTargetInstruction(dec1, virtualFetcher);
    dec1.setFlag(createRegisterReplacement(dec1.getFlag(), dec1, virtualFetcher));
  }

  @Override
  public void visitingRla(RLA rla) {
    super.visitingRla(rla);
    TargetInstruction cloned1 = (TargetInstruction) cloned;
    visitingTargetInstruction(cloned1, new VirtualFetcher());
  }

  @Override
  public void visitingRl(RL rl) {
    super.visitingRl(rl);
    TargetInstruction cloned1 = (TargetInstruction) cloned;
    visitingTargetInstruction(cloned1, new VirtualFetcher());
  }
}

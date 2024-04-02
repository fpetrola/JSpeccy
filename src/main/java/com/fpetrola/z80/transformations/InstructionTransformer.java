package com.fpetrola.z80.transformations;

import com.fpetrola.z80.blocks.DummyInstructionVisitor;
import com.fpetrola.z80.instructions.*;
import com.fpetrola.z80.instructions.base.*;
import com.fpetrola.z80.opcodes.references.*;
import com.fpetrola.z80.registers.Register;

@SuppressWarnings("ALL")
public class InstructionTransformer<T extends WordNumber> extends InstructionTransformerBase<T> {
  public final VirtualRegisterFactory virtualRegisterFactory;

  public void setCurrentInstruction(Instruction currentInstruction) {
    this.currentInstruction = currentInstruction;
  }

  private Instruction currentInstruction;

  public InstructionTransformer(InstructionFactory instructionFactory, VirtualRegisterFactory virtualRegisterFactory) {
    super(instructionFactory);
    this.virtualRegisterFactory = virtualRegisterFactory;
  }

  public void visitingLd(Ld ld) {
    setCloned(instructionFactory.Ld(clone(ld.getTarget()), clone(ld.getSource())), ld);
    TargetSourceInstruction cloned1 = (TargetSourceInstruction) cloned;

    cloned1.setTarget(createRegisterReplacement(cloned1.getTarget(), cloned1, new VirtualFetcher()));
    cloned1.setSource(createRegisterReplacement(cloned1.getSource(), null, new VirtualFetcher()));
  }

  public void visitingInc16(Inc16 inc16) {
    setCloned(instructionFactory.Inc16(clone(inc16.getTarget())), inc16);
    Inc16 cloned1 = (Inc16) cloned;
    cloned1.setTarget(createRegisterReplacement(cloned1.getTarget(), cloned1, new VirtualFetcher()));
  }

  public void visitingDjnz(DJNZ djnz) {
    setCloned(instructionFactory.DJNZ(djnz.getPositionOpcodeReference()), djnz);
    DJNZ djnz1 = (DJNZ) cloned;
    djnz1.setB(createRegisterReplacement(djnz1.getB(), null, new VirtualFetcher()));
  }

  public void visitingJR(JR jr) {
    setCloned(instructionFactory.JR(clone(jr.getCondition()), clone(jr.getPositionOpcodeReference())), jr);
    JR clonedJr = (JR) cloned;
    clonedJr.accept(new DummyInstructionVisitor() {
      public void visitingConditionFlag(ConditionFlag conditionFlag) {
        conditionFlag.setRegister(createRegisterReplacement(conditionFlag.getRegister(), null, new VirtualFetcher()));
      }
    });
  }

  @Override
  public void visitingParameterizedUnaryAluInstruction(ParameterizedUnaryAluInstruction parameterizedUnaryAluInstruction) {
    super.visitingParameterizedUnaryAluInstruction(parameterizedUnaryAluInstruction);
    ParameterizedUnaryAluInstruction cloned1 = (ParameterizedUnaryAluInstruction) cloned;
    VirtualFetcher virtualFetcher = new VirtualFetcher();
    cloned1.setTarget(createRegisterReplacement(cloned1.getTarget(), cloned1, virtualFetcher));
    cloned1.setFlag(createRegisterReplacement(cloned1.getFlag(), cloned1, virtualFetcher));
  }

  @Override
  public void visitingJP(JP jp) {
    setCloned(instructionFactory.JP(clone(jp.getPositionOpcodeReference()), clone(jp.getCondition())), jp);
    JP clonedJp = (JP) cloned;
    clonedJp.accept(new DummyInstructionVisitor() {
      public void visitingConditionFlag(ConditionFlag conditionFlag) {
        conditionFlag.setRegister(createRegisterReplacement(conditionFlag.getRegister(), null, new VirtualFetcher()));
      }
    });
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


  @Override
  public void visitingParameterizedBinaryAluInstruction(ParameterizedBinaryAluInstruction parameterizedBinaryAluInstruction) {
    super.visitingParameterizedBinaryAluInstruction(parameterizedBinaryAluInstruction);
    ParameterizedBinaryAluInstruction cloned1 = (ParameterizedBinaryAluInstruction) cloned;
    VirtualFetcher virtualFetcher = new VirtualFetcher();
    OpcodeReference targetReplacement = createRegisterReplacement(cloned1.getTarget(), cloned1, virtualFetcher);
    if (cloned1.getTarget() == cloned1.getSource()) {
      cloned1.setTarget(targetReplacement);
      cloned1.setSource(targetReplacement);
    } else {
      cloned1.setTarget(targetReplacement);
      cloned1.setSource(createRegisterReplacement(cloned1.getSource(), cloned1, virtualFetcher));
    }
    cloned1.setFlag(createRegisterReplacement(cloned1.getFlag(), cloned1, virtualFetcher));
  }
}

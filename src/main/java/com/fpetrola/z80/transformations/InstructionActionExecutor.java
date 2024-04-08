package com.fpetrola.z80.transformations;

import com.fpetrola.z80.blocks.DummyInstructionVisitor;
import com.fpetrola.z80.instructions.*;
import com.fpetrola.z80.instructions.base.*;
import com.fpetrola.z80.opcodes.references.*;
import com.fpetrola.z80.registers.Register;

import java.util.function.Consumer;

@SuppressWarnings("ALL")
public class InstructionActionExecutor<T extends WordNumber> extends DummyInstructionVisitor<T> {
  private int tick;
  private Consumer<VirtualRegister<?>> actionExecutor;

  public InstructionActionExecutor(Consumer<VirtualRegister<?>> actionExecutor) {
    this.actionExecutor = actionExecutor;
  }

  @Override
  public void visitingSource(ImmutableOpcodeReference source, TargetSourceInstruction targetSourceInstruction) {
    source.accept(this);
  }

  @Override
  public void visitIndirectMemory8BitReference(IndirectMemory8BitReference indirectMemory8BitReference) {
    indirectMemory8BitReference.target.accept(this);
  }

  @Override
  public void visitIndirectMemory16BitReference(IndirectMemory16BitReference indirectMemory16BitReference) {
    indirectMemory16BitReference.target.accept(this);
  }

  @Override
  public void visitingTarget(OpcodeReference target, TargetInstruction targetInstruction) {
    target.accept(this);
  }

  public void visitRegister(Register register) {
    if (register instanceof VirtualRegister<?> virtualRegister) {
      actionExecutor.accept(virtualRegister);
    }
  }

  private void executeAction(Object target) {
    if (target instanceof VirtualRegister<?> virtualRegister) {
      actionExecutor.accept(virtualRegister);
    }
  }

  public void visitingInc16(Inc16 inc16) {
    executeAction(inc16.getTarget());
  }

  @Override
  public void visitingDec16(Dec16 tDec16) {
    executeAction(tDec16.getTarget());
  }

  public void visitingDjnz(DJNZ djnz) {
    executeAction(djnz.getPositionOpcodeReference());
    executeAction(djnz.getB());
  }

  public void visitingJR(JR jr) {
    executeAction(jr.getPositionOpcodeReference());
    jr.accept(new DummyInstructionVisitor() {
      public void visitingConditionFlag(ConditionFlag conditionFlag) {
        executeAction(conditionFlag.getRegister());
      }
    });
  }

  public void visitingParameterizedUnaryAluInstruction(ParameterizedUnaryAluInstruction parameterizedUnaryAluInstruction) {
    executeAction(parameterizedUnaryAluInstruction.getTarget());
    executeAction(parameterizedUnaryAluInstruction.getFlag());
  }

  @Override
  public void visitingParameterizedBinaryAluInstruction(ParameterizedBinaryAluInstruction parameterizedBinaryAluInstruction) {
    executeAction(parameterizedBinaryAluInstruction.getTarget());
    executeAction(parameterizedBinaryAluInstruction.getSource());
    executeAction(parameterizedBinaryAluInstruction.getFlag());
  }

  public void visitingJP(JP jp) {
    executeAction(jp.getPositionOpcodeReference());
    jp.accept(new DummyInstructionVisitor() {
      public void visitingConditionFlag(ConditionFlag conditionFlag) {
        executeAction(conditionFlag.getRegister());
      }
    });
  }

  @Override
  public void visitingVirtualAssignmentInstruction(VirtualAssignmentInstruction virtualAssignmentInstruction) {
    executeAction(virtualAssignmentInstruction.getRegister());
    executeAction(virtualAssignmentInstruction.getLastRegister().get());
  }

  @Override
  public void visitRepeatingInstruction(RepeatingInstruction tRepeatingInstruction) {
    executeAction(tRepeatingInstruction.getBc());
    executeAction(tRepeatingInstruction.getInstructionToRepeat());
  }

  public void visitBlockInstruction(BlockInstruction blockInstruction) {
    executeAction(blockInstruction.getBc());
    executeAction(blockInstruction.getHl());
    executeAction(blockInstruction.getFlag());
  }

  @Override
  public void visitLdir(Ldir tLdir) {
    Ldi instructionToRepeat = (Ldi) tLdir.getInstructionToRepeat();
    executeAction(instructionToRepeat.getDe());
  }

  public void visitingPop(Pop tPop) {
    executeAction(tPop.getFlag());
    executeAction(tPop.getTarget());
  }

  @Override
  public void visitPush(Push push) {
    executeAction(push.getTarget());
  }

  @Override
  public void visitIn(In tIn) {
    executeAction(tIn.getTarget());
  }


  public void executeAction(Instruction<T> instruction) {
    instruction.accept(this);
  }
}

package com.fpetrola.z80.transformations;

import com.fpetrola.z80.blocks.DummyInstructionVisitor;
import com.fpetrola.z80.instructions.*;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.instructions.base.ParameterizedUnaryAluInstruction;
import com.fpetrola.z80.opcodes.references.ConditionFlag;
import com.fpetrola.z80.opcodes.references.WordNumber;

import java.util.function.Consumer;

@SuppressWarnings("ALL")
public class InstructionActionExecutor<T extends WordNumber> extends DummyInstructionVisitor<T> {
  private int tick;
  private Consumer<VirtualRegister<?>> actionExecutor;

  public InstructionActionExecutor(Consumer<VirtualRegister<?>> actionExecutor) {
    this.actionExecutor = actionExecutor;
  }

  public void visitingLd(Ld ld) {
    executeAction(ld.getTarget());
    executeAction(ld.getSource());
  }

  private void executeAction(Object target) {
    if (target instanceof VirtualRegister<?> virtualRegister) {
      actionExecutor.accept(virtualRegister);
    }
  }

  public void visitingInc16(Inc16 inc16) {
    executeAction(inc16.getTarget());
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

  public void executeAction(Instruction<T> instruction) {
    instruction.accept(this);
  }
}

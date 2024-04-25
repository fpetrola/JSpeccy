package com.fpetrola.z80.blocks;

import com.fpetrola.z80.instructions.Dec;
import com.fpetrola.z80.instructions.JR;
import com.fpetrola.z80.instructions.Ret;
import com.fpetrola.z80.instructions.base.*;
import com.fpetrola.z80.opcodes.references.*;
import com.fpetrola.z80.registers.Register;

public class GenerateTestSourceInstructionVisitor extends DummyInstructionVisitor<WordNumber> {
  StringBuilder result = new StringBuilder();

  public void visitingTargetSourceInstruction(TargetSourceInstruction targetSourceInstruction) {
    add("add(new " + targetSourceInstruction.getClass().getSimpleName() + " (");
    targetSourceInstruction.getTarget().accept(getWordNumberDummyInstructionVisitor());
    add(", ");
    targetSourceInstruction.getSource().accept(getWordNumberDummyInstructionVisitor());
    add(", f()));");
  }

  private void add(String string) {
    result.append(string);
  }

  private DummyInstructionVisitor<WordNumber> getWordNumberDummyInstructionVisitor() {
    DummyInstructionVisitor<WordNumber> instructionVisitor = new DummyInstructionVisitor<>() {
      public void visitRegister(Register register) {
        add("r(" + register.getName() + ")");
      }

      public void visitConstantOpcodeReference(ConstantOpcodeReference<WordNumber> constantOpcodeReference) {
        add("c(" + constantOpcodeReference + ")");
      }

      public void visitIndirectMemory16BitReference(IndirectMemory16BitReference indirectMemory16BitReference) {
        add("iRR(r(" + indirectMemory16BitReference + "))");
      }

      public void visitOpcodeReference(OpcodeReference opcodeReference) {
        add("c(" + opcodeReference.toString() + ")");
      }

      public void visitMemoryPlusRegister8BitReference(MemoryPlusRegister8BitReference<WordNumber> memoryPlusRegister8BitReference) {
        add("iRRn(");
        memoryPlusRegister8BitReference.getTarget().accept(this);
        add(STR. " , \{ memoryPlusRegister8BitReference.fetchRelative() })" );
      }

      public void visitImmutableOpcodeReference(ImmutableOpcodeReference immutableOpcodeReference) {
        add("c(" + immutableOpcodeReference.toString() + ")");
      }
    };
    return instructionVisitor;
  }

  public void visitingTargetInstruction(TargetInstruction targetInstruction) {
    add("add(new " + targetInstruction.getClass().getSimpleName() + " (" + targetInstruction.getTarget() + "));");
  }

//  @Override
//  public void visitingJR(JR conditionalInstruction) {
//    String simpleName = conditionalInstruction.getClass().getSimpleName();
//    String replace = conditionalInstruction.getCondition().toString().replace("FlipFlop: ", "");
//    replace= replace.toLowerCase();
//    int jumpAddress = conditionalInstruction.calculateJumpAddress().intValue();
//    String s = STR. "add(new \{ simpleName }(c(\{ jumpAddress }), \{ replace }(), r(PC)));" ;
//    add(s);
//  }

//  @Override
//  public void visitingRet(Ret conditionalInstruction) {
//    String replace = conditionalInstruction.getCondition().toString().replace("FlipFlop: ", "");
//    WordNumber jumpAddress = conditionalInstruction.getJumpAddress();
//    String simpleName = conditionalInstruction.getClass().getSimpleName();
//    add("add(new " + simpleName + " " + replace + ", " + jumpAddress + ");");
//  }

  public void visitingConditionalInstruction(ConditionalInstruction conditionalInstruction) {
    String simpleName = conditionalInstruction.getClass().getSimpleName();
    String replace = conditionalInstruction.getCondition().toString().replace("FlipFlop: ", "");
    replace = replace.isBlank() ? ", t()" : STR. ", \{ replace.toLowerCase() }()" ;
    int jumpAddress = conditionalInstruction.calculateJumpAddress().intValue();
    String s = STR. "add(new \{ simpleName }(c(\{ jumpAddress }) \{ replace }, r(PC)));" ;
    add(s);
  }

  @Override
  public void visitingBitOperation(BitOperation targetSourceInstruction) {
    add("add(new " + targetSourceInstruction.getClass().getSimpleName() + " (");
    targetSourceInstruction.getTarget().accept(getWordNumberDummyInstructionVisitor());
    add(", " + targetSourceInstruction.getN() + ", f()));");
  }

  @Override
  public void visitingDec(Dec targetSourceInstruction) {
    add("add(new " + targetSourceInstruction.getClass().getSimpleName() + " (");
    targetSourceInstruction.getTarget().accept(getWordNumberDummyInstructionVisitor());
    add(", f()));");
  }
}

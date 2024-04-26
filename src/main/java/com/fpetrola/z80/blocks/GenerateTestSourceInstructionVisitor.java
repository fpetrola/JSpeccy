package com.fpetrola.z80.blocks;

import com.fpetrola.z80.instructions.JR;
import com.fpetrola.z80.instructions.Ret;
import com.fpetrola.z80.instructions.base.*;
import com.fpetrola.z80.opcodes.references.*;
import com.fpetrola.z80.registers.Register;

public class GenerateTestSourceInstructionVisitor extends DummyInstructionVisitor<WordNumber> {
  StringBuilder result = new StringBuilder();
  private int startAddress;

  public GenerateTestSourceInstructionVisitor(int startAddress) {
    this.startAddress = startAddress;
  }

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
    jumpAddress-= startAddress;

    if (conditionalInstruction instanceof JR) {
      jumpAddress= ((WordNumber) conditionalInstruction.getPositionOpcodeReference().read()).intValue();
    }
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
  public boolean visitingParameterizedUnaryAluInstruction(ParameterizedUnaryAluInstruction dec) {
    add("add(new " + dec.getClass().getSimpleName() + " (");
    dec.getTarget().accept(getWordNumberDummyInstructionVisitor());
    add(", f()));");
    return true;
  }

  public boolean visitingRet(Ret conditionalInstruction) {
    String conditionConstructor = conditionalInstruction.getCondition().toString().replace("FlipFlop: ", "");
    conditionConstructor = conditionConstructor.isBlank() ? "t()" : conditionConstructor.toLowerCase() + "()";
    String s = "add(new Ret(" + conditionConstructor + ", r(SP), mem(), r(PC)));";
    add(s);
    return true;
  }
}

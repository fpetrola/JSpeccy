package com.fpetrola.z80.blocks;

import com.fpetrola.z80.instructions.*;
import com.fpetrola.z80.instructions.base.BitOperation;
import com.fpetrola.z80.instructions.base.ConditionalInstruction;
import com.fpetrola.z80.instructions.base.InstructionVisitor;
import com.fpetrola.z80.opcodes.references.*;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterName;
import com.fpetrola.z80.transformations.VirtualRegister;
import org.cojen.maker.Field;
import org.cojen.maker.Label;
import org.cojen.maker.MethodMaker;
import org.cojen.maker.Variable;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class ByteCodeGeneratorVisitor extends DummyInstructionVisitor implements InstructionVisitor {
  static Map<String, Variable> initializers = new HashMap<>();
  private final MethodMaker methodMaker;
  private final ByteCodeGenerator byteCodeGenerator;
  public static Map<String, String> commonRegisters = new HashMap<>();

  public ByteCodeGeneratorVisitor(MethodMaker methodMaker, int label, ByteCodeGenerator byteCodeGenerator) {
    this.methodMaker = methodMaker;
    this.byteCodeGenerator = byteCodeGenerator;
  }

  @Override
  public void visitingBit(BIT bit) {
    bit.accept(new VariableInstructionVisitor((s, t) -> t.and(bit.getN()), byteCodeGenerator));
  }

  @Override
  public void visitingBitOperation(BitOperation tBitOperation) {
    tBitOperation.accept(new VariableInstructionVisitor((s, t) -> t.and(tBitOperation.getN()), byteCodeGenerator));
  }

  public void visitingInc(Inc inc) {
    inc.accept(new VariableInstructionVisitor((s, t) -> t.inc(1), byteCodeGenerator));
  }

  public void visitingXor(Xor xor) {
    xor.accept(new VariableInstructionVisitor((s, t) -> t.set(t.xor(s)), byteCodeGenerator));
  }

  @Override
  public void visitingOr(Or tOr) {
    tOr.accept(new VariableInstructionVisitor((s, t) -> t.set(t.or(s)), byteCodeGenerator));
  }

  @Override
  public void visitingAnd(And and) {
    and.accept(new VariableInstructionVisitor((s, t) -> t.set(t.and(s)), byteCodeGenerator));
  }

  public void visitingAdd16(Add16 add16) {
    add16.accept(new VariableInstructionVisitor((s, t) -> getSet(s, t), byteCodeGenerator));
  }

  private Variable getSet(Object s, Variable t) {
    if (s != null && t != null)
      if (t == s)
        return t.set(t.mul(2));
      else
        return t.set(t.add(s));

    return t;
  }

  public void visitingInc16(Inc16 inc16) {
    inc16.accept(new VariableInstructionVisitor((s, t) -> {
      t.inc(1);
    }, byteCodeGenerator));
  }

  public void visitingAdd(Add add) {
    add.accept(new VariableInstructionVisitor((s, t) -> getSet(s, t), byteCodeGenerator));
  }

  public void visitingDec(Dec dec) {
    dec.accept(new VariableInstructionVisitor((s, t) -> t.inc(-1), byteCodeGenerator));

//    OpcodeReferenceVisitor instructionVisitor2 = new OpcodeReferenceVisitor(false, byteCodeGenerator);
//
//    dec.getTarget().accept(instructionVisitor2);
//    Variable a = (Variable) instructionVisitor2.getResult();
//
//    dec.getFlag().accept(instructionVisitor2);
//    Variable flag = (Variable) instructionVisitor2.getResult();
//
//    flag.set(a.sub(1));
  }

  public void visitingLd(Ld ld) {
    OpcodeReferenceVisitor instructionVisitor2 = new OpcodeReferenceVisitor(false, byteCodeGenerator);
    ld.getSource().accept(instructionVisitor2);
    Object sourceVariable1 = instructionVisitor2.getResult();

    OpcodeReferenceVisitor instructionVisitor = new OpcodeReferenceVisitor(true, byteCodeGenerator);
    instructionVisitor.setCreateInitializer((virtual8BitsRegister) -> sourceVariable1);
    ld.getTarget().accept(instructionVisitor);
    Object targetVariable1 = instructionVisitor.getResult();

    BiConsumer<Object, Variable> objectVariableBiConsumer = (sourceVariable, targetVariable) -> {
      Class<?> aClass = targetVariable.classType();
      if (!aClass.equals(int.class))
        targetVariable.aset(0, sourceVariable);
      else if (targetVariable instanceof WriteArrayVariable writeArrayVariable)
        writeArrayVariable.set(sourceVariable);
      else if (sourceVariable instanceof Variable variable && variable.name() == null) {
        targetVariable.set(sourceVariable);
      }
    };

    if (targetVariable1 instanceof Variable variable)
      objectVariableBiConsumer.accept(sourceVariable1, variable);
  }

  protected static <T extends WordNumber> Object getSourceVariableOf(ImmutableOpcodeReference<T> source, Object initializer, ByteCodeGenerator byteCodeGenerator1) {
    Object sourceVariable = !(source instanceof Register) ? source.read().intValue() : 12345;

    if (source instanceof VirtualRegister<T> virtual) {
      initializer = initializer != null ? initializer : sourceVariable;
      if (virtual.usesMultipleVersions()) {
        sourceVariable = byteCodeGenerator1.getVariable(virtual, createCommonRegister(virtual, initializer, byteCodeGenerator1));
      } else {
        sourceVariable = byteCodeGenerator1.getVariable(virtual, initializer);
      }
    }
    return sourceVariable;
  }

  private static <T extends WordNumber> Variable createCommonRegister(VirtualRegister<T> virtualRegister, Object initializer, ByteCodeGenerator byteCodeGenerator1) {
    String name = virtualRegister.getName();
    if (!byteCodeGenerator1.variableExists(name)) {
      Label branchLabel = byteCodeGenerator1.getBranchLabel();
      Label insert = branchLabel.insert(() -> {
        Variable t = byteCodeGenerator1.getVariable(virtualRegister, initializer);
        virtualRegister.getPreviousVersions().forEach(p -> commonRegisters.put(p.getName(), name));
      });
      byteCodeGenerator1.setBranchLabel(insert);
    }
    return byteCodeGenerator1.getVariable(virtualRegister, initializer);
  }

  protected void executeConditional(ByteCodeGenerator byteCodeGenerator, Runnable runnable, Condition condition) {
    Field f = byteCodeGenerator.registers.get(RegisterName.F.name());
    if (condition.toString().equals("NZ")) f.ifNe(0, runnable);
    else if (condition.toString().equals("Z")) f.ifEq(0, runnable);
    else if (condition.toString().equals("NC")) f.ifGe(0, runnable);
    else if (condition.toString().equals("C")) f.ifLt(0, runnable);
    else runnable.run();
  }


  public void visitingCp(Cp cp) {


    OpcodeReferenceVisitor instructionVisitor2 = new OpcodeReferenceVisitor(true, byteCodeGenerator);
//    cp.getSource().accept(instructionVisitor2);
//    Object sourceVariable = instructionVisitor2.getResult();
//
//    cp.getTarget().accept(instructionVisitor2);
//    Variable a = (Variable) instructionVisitor2.getResult();

    cp.getFlag().accept(instructionVisitor2);
    Variable flag = (Variable) instructionVisitor2.getResult();

//    flag.set(a.sub(sourceVariable));

    cp.accept(new VariableInstructionVisitor((s, t) -> flag.set(t.sub(s)), byteCodeGenerator));

  }

  public void visitingRet(Ret ret) {
    //executeConditional(byteCodeGenerator, () -> methodMaker.return_(), ret.getCondition());
  }

  public void visitingCall(Call call) {
    int jumpLabel = call.getJumpAddress().intValue();

    String labelName = ByteCodeGenerator.createLabelName(jumpLabel);
    MethodMaker method = byteCodeGenerator.getMethod(jumpLabel);
    if (method != null) {
      Runnable runnable = () -> methodMaker.invoke(labelName);
      executeConditional(byteCodeGenerator, runnable, call.getCondition());
    }
  }

  @Override
  public void visitingConditionalInstruction(ConditionalInstruction conditionalInstruction) {
    conditionalInstruction.calculateJumpAddress();

    int jumpLabel = conditionalInstruction.getJumpAddress().intValue();
    Label label1 = byteCodeGenerator.getLabel(jumpLabel);
    if (label1 != null) {
      Variable f = getVariable(conditionalInstruction);
      //Variable f =  (Variable) getSourceVariableOf(register, 0, byteCodeGenerator);

      //Variable f = (Variable) byteCodeGenerator.getVariable(condition1.getRegister().getName(), getSourceUsingPreviousForRegister(true, (Virtual8BitsRegister) condition1.getRegister(), true));
//      Field f = byteCodeGenerator.registers.get(RegisterName.F.name());

      if (f == null)
        label1.goto_();
      else {
        Condition condition = conditionalInstruction.getCondition();
        if (condition.toString().contains("NZ")) f.ifNe(0, label1);
        else if (condition.toString().equals("Z")) {
          f.ifEq(0, label1);
        } else if (condition.toString().equals("NC")) f.ifGe(0, label1);
        else if (condition.toString().equals("C")) f.ifLt(0, label1);
        else
          label1.goto_();
      }
    }
  }

  private <T> Variable getVariable(ConditionalInstruction conditionalInstruction) {
    VirtualRegister<T> register = null;
    if (conditionalInstruction instanceof DJNZ djnz) {
      register = (VirtualRegister) djnz.getB();
    } else if (!(conditionalInstruction.getCondition() instanceof ConditionAlwaysTrue)) {
      ConditionFlag condition1 = (ConditionFlag) conditionalInstruction.getCondition();
      register = (VirtualRegister) condition1.getRegister();
    }

    if (register == null)
      return null;

    OpcodeReferenceVisitor opcodeReferenceVisitor = new OpcodeReferenceVisitor(false, byteCodeGenerator);
    register.accept(opcodeReferenceVisitor);
    Object sourceVariable = opcodeReferenceVisitor.getResult();

    Variable f = (Variable) sourceVariable;
//
//    register.getPreviousVersions().get(0).accept(new DummyInstructionVisitor<>() {
//      public void visitingDec(Dec dec) {
//        super.visitingDec(dec);
//      }
//    });

    if (conditionalInstruction instanceof DJNZ djnz)
      f.inc(-1);
    String s = ByteCodeGeneratorVisitor.commonRegisters.get(f.name());
    if (s != null) {
      if (!s.equals(f.name())) {
        byteCodeGenerator.getExistingVariable(s).set(f);
      }
    }
//    }
    return f;
  }
}

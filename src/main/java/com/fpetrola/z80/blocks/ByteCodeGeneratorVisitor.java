package com.fpetrola.z80.blocks;

import com.fpetrola.z80.helpers.Helper;
import com.fpetrola.z80.instructions.*;
import com.fpetrola.z80.instructions.base.*;
import com.fpetrola.z80.opcodes.references.*;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterName;
import com.fpetrola.z80.transformations.Virtual8BitsRegister;
import org.cojen.maker.Field;
import org.cojen.maker.Label;
import org.cojen.maker.MethodMaker;
import org.cojen.maker.Variable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

public class ByteCodeGeneratorVisitor extends DummyInstructionVisitor implements InstructionVisitor {
    private final MethodMaker methodMaker;
    private final int label;
    private final ByteCodeGenerator byteCodeGenerator;
    private static Map<String, Variable> commonRegisters = new HashMap<>();
    private static Map<Virtual8BitsRegister, String> assignPrevious = new HashMap<>();
    private boolean labelAdded;

    public ByteCodeGeneratorVisitor(MethodMaker methodMaker, int label, ByteCodeGenerator byteCodeGenerator) {
        this.methodMaker = methodMaker;
        this.label = label;
        this.byteCodeGenerator = byteCodeGenerator;
    }

    public static String createLabelName(int label) {
        return "$" + Helper.convertToHex(label);
    }

    @Override
    public void visitingTargetInstruction(TargetInstruction targetInstruction) {
        Object sourceVariable = 0;
        Object targetVariable = getSourceUsingPrevious(targetInstruction.getTarget(), true);
        if (targetVariable instanceof Variable)
            ((Variable) targetVariable).set(sourceVariable);
    }

    @Override
    public void visitingInstruction(AbstractInstruction instruction) {
    }

    @Override
    public void visitingAdd(Add add) {
        Object targetVariable = getSourceUsingPrevious(add.getTarget(), true);
        Object sourceVariable2 = getSourceUsingPrevious(add.getSource(), false);

        if (targetVariable instanceof Variable)
            ((Variable) targetVariable).inc(sourceVariable2);
    }

    private Object getSourceUsingPrevious(Object register, boolean isTarget) {
        if (register instanceof Virtual8BitsRegister virtual8BitsRegister) {

            Object sourceUsingPreviousForRegister = getSourceUsingPreviousForRegister(isTarget, virtual8BitsRegister, false);

            String s = assignPrevious.get(virtual8BitsRegister);
            if (s != null) {
                Variable variable = byteCodeGenerator.getVariable(s, 0);
                Variable sourceUsingPreviousForRegister1 = (Variable) getSourceUsingPreviousForRegister(isTarget, virtual8BitsRegister.getCurrentPreviousVersion(), false);
                variable.set(sourceUsingPreviousForRegister1);
            }

            return sourceUsingPreviousForRegister;
        } else {
            Object sourceVariable = getSourceVariableOf((ImmutableOpcodeReference) register, false, 0, true);
            int init = sourceVariable instanceof Integer ? (int) sourceVariable : 0;
            return init;
        }
    }

    @Override
    public void visitingAdd16(Add16 add) {
        extracted(add.getSource(), add.getTarget(), (sourceVariable1, targetVariable1) -> targetVariable1.inc(sourceVariable1));
    }

    private void extracted(ImmutableOpcodeReference source, OpcodeReference target, BiConsumer<Object, Variable> objectVariableBiConsumer) {
        Object targetVariable = getSourceUsingPrevious(target, true);
        Object sourceVariable = getSourceUsingPrevious(source, true);
        if (targetVariable instanceof Variable) {
            objectVariableBiConsumer.accept(sourceVariable, (Variable) targetVariable);
        }
    }

    private Object getSourceUsingPreviousForRegister(boolean isTarget, Virtual8BitsRegister virtual8BitsRegister, boolean putLabel) {
        List<Virtual8BitsRegister> previousVersions = virtual8BitsRegister.previousVersions;
        if (previousVersions.isEmpty())
            return 0;

        if (previousVersions.size() > 1)
            previousVersions.forEach(r -> assignPrevious.put(r, virtual8BitsRegister.getName() + "_0"));

        Virtual8BitsRegister previousVersion = (Virtual8BitsRegister) previousVersions.get(0);
        Object sourceVariable = getSourceVariableOf(previousVersion, isTarget, getSourceUsingPreviousForRegister(isTarget, previousVersion, false), putLabel);
        return sourceVariable;
    }

    @Override
    public void visitingAnd(And and) {
        extracted(and.getSource(), and.getTarget(), (sourceVariable1, targetVariable1) -> targetVariable1.set(targetVariable1.and(sourceVariable1)));
    }

    @Override
    public void visitingDec(Dec dec) {
        Object sourceVariable1 = getSourceUsingPrevious(dec.getFlag(), true);
        Variable targetVariable1 = (Variable) getSourceVariableOf(dec.getFlag(), true, sourceVariable1, true);

        Object sourceVariable = getSourceUsingPrevious(dec.getTarget(), true);
        Object targetVariable = getSourceVariableOf(dec.getTarget(), true, sourceVariable, true);
        if (targetVariable instanceof Variable variable) {
            variable.inc(-1);
            targetVariable1.set(variable.sub(10));
        }
    }

    @Override
    public void visitingDec16(Dec16 dec) {
        Object sourceVariable = 0;
        Object targetVariable = getSourceUsingPrevious(dec.getTarget(), true);
        if (targetVariable instanceof Variable)
            ((Variable) targetVariable).sub(sourceVariable);
    }

    @Override
    public void visitingInc(Inc inc) {
        Object sourceVariable = getSourceUsingPrevious(inc.getTarget(), true);
        Object targetVariable = getSourceVariableOf(inc.getTarget(), true, sourceVariable, true);
        if (targetVariable instanceof Variable)
            ((Variable) targetVariable).inc(1);
    }

    @Override
    public void visitingOr(Or or) {
        extracted(or.getSource(), or.getTarget(), (sourceVariable1, variable1) -> variable1.set(variable1.or(sourceVariable1)));
    }

    @Override
    public void visitingSub(Sub sub) {
        extracted(sub.getSource(), sub.getTarget(), (sourceVariable1, variable1) -> variable1.set(variable1.sub(sourceVariable1)));
    }

    @Override
    public void visitingXor(Xor xor) {

        Object targetVariable = getSourceVariableOf(xor.getTarget(), true, getSourceUsingPrevious(xor.getTarget(), true), true);
        Object sourceVariable = getSourceUsingPrevious(xor.getSource(), false);

        if (targetVariable instanceof Variable) {
            Variable variable = (Variable) targetVariable;
            variable.set(variable.xor(sourceVariable));
        }
    }

    @Override
    public void visitingCp(Cp cp) {
        Object sourceVariable = getSourceVariableOf(cp.getSource(), false, 0, true);
        Variable a = byteCodeGenerator.registers.get(RegisterName.A.name());
        Variable targetVariable = byteCodeGenerator.registers.get(RegisterName.F.name());

        targetVariable.set(a.sub(sourceVariable));
    }

    @Override
    public void visitingRet(Ret ret) {
        Runnable runnable = () -> methodMaker.return_();
        executeConditional(byteCodeGenerator, runnable, ret.getCondition());
    }

    @Override
    public void visitingCall(Call call) {
        int jumpLabel = call.getJumpAddress().intValue();

        String labelName = createLabelName(jumpLabel);
        MethodMaker method = byteCodeGenerator.getMethod(jumpLabel);
        if (method != null) {
            Runnable runnable = () -> methodMaker.invoke(labelName);
            executeConditional(byteCodeGenerator, runnable, call.getCondition());
        }
    }

    @Override
    public void visitingConditionalInstruction(ConditionalInstruction conditionalInstruction) {
        conditionalInstruction.calculateRelativeJumpAddress();

        int jumpLabel = conditionalInstruction.getJumpAddress().intValue();
        Label label1 = byteCodeGenerator.getLabel(jumpLabel);
        if (label1 != null) {
            ConditionFlag condition1 = (ConditionFlag) conditionalInstruction.getCondition();
            Variable f = (Variable) getSourceVariableOf(condition1.getRegister(), false, 0, true);

            //Variable f = (Variable) byteCodeGenerator.getVariable(condition1.getRegister().getName(), getSourceUsingPreviousForRegister(true, (Virtual8BitsRegister) condition1.getRegister(), true));
//      Field f = byteCodeGenerator.registers.get(RegisterName.F.name());
            Condition condition = conditionalInstruction.getCondition();
            if (condition.toString().equals("NZ")) f.ifNe(0, label1);
            else if (condition.toString().equals("Z")) f.ifEq(0, label1);
            else if (condition.toString().equals("NC")) f.ifGe(0, label1);
            else if (condition.toString().equals("C")) f.ifLt(0, label1);
            else label1.goto_();
        }
    }

    @Override
    public void visitingTargetSourceInstruction(TargetSourceInstruction targetSourceInstruction) {
        extracted(targetSourceInstruction.getSource(), targetSourceInstruction.getTarget(), (sourceVariable1, targetVariable1) -> targetVariable1.set(sourceVariable1));
    }

    public void visitingLd(Ld ld) {
        Object sourceVariable = getSourceUsingPrevious(ld.getSource(), true);

        Object targetVariable = getSourceVariableOf(ld.getTarget(), true, sourceVariable, true);

        if (targetVariable instanceof Variable variable) {
            Class<?> aClass = variable.classType();
            if (!aClass.equals(int.class)) {
                ((Variable) targetVariable).aset(0, sourceVariable);
                Object sv2 = getSourceVariableOf(ld.getSource(), true, sourceVariable, true);
            }
        }
    }

    protected void hereLabel(boolean putLabel) {
        if (putLabel && !labelAdded) {
            labelAdded = true;
            if (label != -1)
                byteCodeGenerator.hereLabel(label);
        }
    }

    protected <T extends WordNumber> Object getSourceVariableOf(ImmutableOpcodeReference<T> source2, boolean isTarget, Object initializer, boolean putLabel) {
        hereLabel(putLabel);
        Object sourceVariable2 = source2.read().intValue();

        if (source2 instanceof Virtual8BitsRegister<T> virtual8BitsRegister) {
            if (virtual8BitsRegister.previousVersions.size() > 1 && putLabel) {
                Label label1 = byteCodeGenerator.getBranchLabel();

                String name = virtual8BitsRegister.getName() + "_0";
                label1.insert(() -> {
                    Variable t = byteCodeGenerator.getVariable(name, initializer);
                    commonRegisters.put(virtual8BitsRegister.getName(), t);
                });
                Variable t2 = byteCodeGenerator.getVariable(name, initializer);
                sourceVariable2 = byteCodeGenerator.getVariable(virtual8BitsRegister.getName(), t2);
            } else {
                sourceVariable2 = byteCodeGenerator.getVariable(virtual8BitsRegister.getName(), initializer);
            }
        } else if (source2 instanceof Register register) {
            sourceVariable2 = byteCodeGenerator.getVariable(register.getName(), initializer);
        } else if (source2 instanceof MemoryPlusRegister8BitReference<T> source1) {
            String name = ((Register) source1.getTarget()).getName();
            Field field = byteCodeGenerator.getField(name);
            if (isTarget)
                sourceVariable2 = new WriteArrayVariable(byteCodeGenerator, field, source1);
            else
                sourceVariable2 = byteCodeGenerator.memory.aget(field.add(source1.fetchRelative()));
        } else if (source2 instanceof MemoryAccessOpcodeReference<T> memoryAccessOpcodeReference) {
            Field memoryField = byteCodeGenerator.getField("memory");
            sourceVariable2 = memoryField;
        }
        return sourceVariable2;
    }

    protected void executeConditional(ByteCodeGenerator byteCodeGenerator, Runnable runnable, Condition condition) {
        Field f = byteCodeGenerator.registers.get(RegisterName.F.name());
        if (condition.toString().equals("NZ")) f.ifNe(0, runnable);
        else if (condition.toString().equals("Z")) f.ifEq(0, runnable);
        else if (condition.toString().equals("NC")) f.ifGe(0, runnable);
        else if (condition.toString().equals("C")) f.ifLt(0, runnable);
        else runnable.run();
    }
}

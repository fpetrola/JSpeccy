package com.fpetrola.z80.bytecode.impl;

import com.fpetrola.z80.instructions.base.DummyInstructionVisitor;
import com.fpetrola.z80.instructions.MemoryAccessOpcodeReference;
import com.fpetrola.z80.opcodes.references.*;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.transformations.*;
import org.cojen.maker.Field;
import org.cojen.maker.Label;
import org.cojen.maker.Variable;

import java.util.*;
import java.util.function.Function;

public class OpcodeReferenceVisitor<T extends WordNumber> extends DummyInstructionVisitor<T> {
  private Object result;
  private boolean isTarget;
  private ByteCodeGenerator byteCodeGenerator;

  public void setCreateInitializer(Function createInitializer) {
    this.createInitializer = createInitializer;
  }

  private Function<VirtualRegister<T>, Object> createInitializer;

  public OpcodeReferenceVisitor(boolean isTarget, ByteCodeGenerator byteCodeGenerator1) {
    this.isTarget = isTarget;
    byteCodeGenerator = byteCodeGenerator1;
    createInitializer = new Function<>() {
      public Object apply(VirtualRegister<T> virtualRegister) {
        List<VirtualRegister<T>> previousVersions = virtualRegister.getPreviousVersions();
        if (!virtualRegister.hasNoPrevious() && isMixRegister(previousVersions.get(0))) {
          VirtualComposed16BitRegister virtualComposed16BitRegister = (VirtualComposed16BitRegister) previousVersions.get(0);
          String name = ByteCodeGenerator.getRegisterName(virtualComposed16BitRegister);
          Variable variable = ByteCodeGeneratorVisitor.initializers.get(name);
          if (variable == null) {
            Variable o = (Variable) processValue(createInitializer, virtualComposed16BitRegister.getHigh());
            Variable o2 = (Variable) processValue(createInitializer, virtualComposed16BitRegister.getLow());
            variable = o.shl(8).or(o2);
            ByteCodeGeneratorVisitor.initializers.put(name, variable);
          }
          return variable;
        } else {
          Object previousVersion = getPreviousVersion(virtualRegister, byteCodeGenerator1);
          if (previousVersion == null && !virtualRegister.hasNoPrevious()) {
            Register previousVersion1 = virtualRegister.getPreviousVersions().get(0);
            for (Map.Entry<String, VirtualRegister> e : byteCodeGenerator1.registerByVariable.entrySet()) {
              if (e.getValue() instanceof VirtualComposed16BitRegister<?>) {
                VirtualComposed16BitRegister<?> value = (VirtualComposed16BitRegister<?>) e.getValue();
                Variable existingVariable = byteCodeGenerator1.getExistingVariable(value);
                if (value.getLow() == previousVersion1)
                  return existingVariable.and(0xFF);
                else if (value.getHigh() == previousVersion1)
                  return existingVariable.shr(8);
              }
            }
            return processValue(this, null);
            //throw new RuntimeException("previous not found");
          } else {
            if (previousVersion == null)
              if (!previousVersions.isEmpty()) {
                previousVersion = previousVersions.get(0);
              } else
                previousVersion = virtualRegister.read().intValue();

            return processValue(this, previousVersion);
          }
        }
      }
    };
  }


  @Override
  public void visitOpcodeReference(OpcodeReference opcodeReference) {
    result = ((WordNumber) opcodeReference.read()).intValue();
  }

  public static boolean isMixRegister(VirtualRegister virtualRegister) {
    return virtualRegister.getName().contains(",");
  }

  public OpcodeReferenceVisitor(boolean isTarget, ByteCodeGenerator byteCodeGenerator1, Function createInitializer) {
    this.isTarget = isTarget;
    byteCodeGenerator = byteCodeGenerator1;
    this.createInitializer = createInitializer;
  }

  private static <T extends WordNumber> Object getPreviousVersion(VirtualRegister<T> virtualRegister, ByteCodeGenerator byteCodeGenerator1) {
    Virtual8BitsRegister.breakInStackOverflow();
    List<VirtualRegister<T>> previousVersions = virtualRegister.getPreviousVersions();
    if (virtualRegister.hasNoPrevious()) {
      return null;
    } else {
      Optional first = previousVersions.stream().filter(r -> byteCodeGenerator1.variableExists(r)).findFirst();
      // return first.orElse(previousVersions.get(0).read().intValue());
      return first.orElse(null);
    }
  }

  public Object getResult() {
    return result;
  }

  @Override
  public boolean visitVirtualComposed16BitRegister(VirtualComposed16BitRegister virtualComposed16BitRegister) {
    result = processValue(createInitializer, virtualComposed16BitRegister);
    return true;
  }

  public void visitRegister(Register register) {
    result = processValue(createInitializer, register);
  }

  protected <T extends WordNumber> Object processValue(Function<VirtualRegister<T>, Object> createInitializer, Object value) {
    Object initializer = null;
    if (value instanceof VirtualRegister virtualRegister) {
      if (virtualRegister.usesMultipleVersions()) {
        if (!byteCodeGenerator.variableExists(virtualRegister)) {
          VirtualRegister<?> parentPreviousVersion = virtualRegister.adjustRegisterScope();

          int minLine = virtualRegister.getScope().start;

          Label branchLabel = byteCodeGenerator.getBranchLabel(minLine);
          VirtualRegister<?> virtualRegister1 = parentPreviousVersion.getDependants().get(0);
          initializer = findInitializer(createInitializer, virtualRegister1);
          Object finalInitializer = initializer;
          Runnable runnable = () -> {
            byteCodeGenerator.getVariable(virtualRegister, finalInitializer);
            virtualRegister.getPreviousVersions().forEach(p -> ByteCodeGeneratorVisitor.commonRegisters.put((VirtualRegister<WordNumber>) p, (VirtualRegister<WordNumber>) virtualRegister));
          };

          int registerLine = virtualRegister.getRegisterLine();
          if (minLine == registerLine + 1)
            runnable.run();
          else {
            Label insert = branchLabel.insert(runnable);
            byteCodeGenerator.setBranchLabel(insert);
          }
        }

        initializer = byteCodeGenerator.getExistingVariable(virtualRegister);
      } else
        initializer = findInitializer(createInitializer, virtualRegister);


      if (virtualRegister instanceof InitialVirtualRegister) {
        Object finalInitializer1 = initializer;
        Runnable runnable = () -> {
          byteCodeGenerator.getVariable(virtualRegister, 100000);
        };
        Label branchLabel = byteCodeGenerator.getBranchLabel(0);
        Label insert = branchLabel.insert(runnable);
      }

      return byteCodeGenerator.getVariable(virtualRegister, initializer);
    } else
      return value;
  }

  private <T extends WordNumber> Object findInitializer(Function<VirtualRegister<T>, Object> createInitializer, VirtualRegister<?> virtualRegister) {
    Object initializer = createInitializer.apply((VirtualRegister<T>) virtualRegister);
    if (initializer == null)
      initializer = byteCodeGenerator.initial;
    return initializer;
  }

  public void visitConstantOpcodeReference(ConstantOpcodeReference<T> constantOpcodeReference) {
    result = constantOpcodeReference.read().intValue();
  }

  public void visitMemoryAccessOpcodeReference(MemoryAccessOpcodeReference<T> memoryAccessOpcodeReference) {
    Field memoryField = byteCodeGenerator.getField("memory");
    int o = memoryAccessOpcodeReference.getC().read().intValue();
    if (isTarget)
      result = new WriteArrayVariable(byteCodeGenerator, () -> o);
    else
      result = getFromMemory(o);
  }

  public void visitMemoryPlusRegister8BitReference(MemoryPlusRegister8BitReference<T> memoryPlusRegister8BitReference) {
    Register target = (Register) memoryPlusRegister8BitReference.getTarget();
    OpcodeReferenceVisitor opcodeReferenceVisitor = new OpcodeReferenceVisitor(isTarget, byteCodeGenerator);
    target.accept(opcodeReferenceVisitor);

    Variable variable = (Variable) opcodeReferenceVisitor.getResult();

    byte value = memoryPlusRegister8BitReference.fetchRelative();
    Variable variablePlusDelta = value > 0 ? variable.add(value) : variable;
    if (isTarget)
      result = new WriteArrayVariable(byteCodeGenerator, () -> variablePlusDelta);
    else {
      result = getFromMemory(variablePlusDelta);
    }
  }


  public void visitIndirectMemory8BitReference(IndirectMemory8BitReference indirectMemory8BitReference) {
    Object variable;
    if (indirectMemory8BitReference.getTarget() instanceof Memory16BitReference<?> memory16BitReference) {
      variable = memory16BitReference.read().intValue();
    } else {
      Register target = (Register) indirectMemory8BitReference.getTarget();
      OpcodeReferenceVisitor opcodeReferenceVisitor = new OpcodeReferenceVisitor(false, byteCodeGenerator);
      target.accept(opcodeReferenceVisitor);

      variable = (Variable) opcodeReferenceVisitor.getResult();
    }
    if (isTarget)
      result = new WriteArrayVariable(byteCodeGenerator, () -> variable);
    else {
      result = getFromMemory(variable);
    }
  }

  private Variable getFromMemory(Object variable) {
    Variable get = byteCodeGenerator.memory.aget(variable);
    return get;
  }

  @Override
  public void visitImmutableOpcodeReference(ImmutableOpcodeReference immutableOpcodeReference) {
    result = ((T) immutableOpcodeReference.read()).intValue();
  }

  public <T> Variable process(VirtualRegister<T> register) {
    register.accept(this);
    return (Variable) getResult();
  }
}

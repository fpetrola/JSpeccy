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

  public void setInitializerFactory(Function initializerFactory) {
    this.initializerFactory = initializerFactory;
  }

  private Function<VirtualRegister<T>, Object> initializerFactory;

  public OpcodeReferenceVisitor(boolean isTarget, ByteCodeGenerator byteCodeGenerator) {
    this.isTarget = isTarget;
    this.byteCodeGenerator = byteCodeGenerator;

    initializerFactory = new Function<>() {
      public Object apply(VirtualRegister<T> virtualRegister) {
        List<VirtualRegister<T>> previousVersions = virtualRegister.getPreviousVersions();
        if (!virtualRegister.hasNoPrevious()) {
          VirtualRegister<T> firstPrevious = previousVersions.get(0);

          if (isMixRegister(firstPrevious)) {
            VirtualComposed16BitRegister virtualComposed16BitRegister = (VirtualComposed16BitRegister) firstPrevious;
            Variable o = (Variable) processValue(initializerFactory, virtualComposed16BitRegister.getHigh());
            Variable o2 = (Variable) processValue(initializerFactory, virtualComposed16BitRegister.getLow());
            return o.shl(8).or(o2);
          } else {
            if (previousVersions.stream().noneMatch(r -> byteCodeGenerator.variableExists(r))) {
              for (Map.Entry<String, VirtualRegister> entry : byteCodeGenerator.registerByVariable.entrySet()) {
                if (entry.getValue() instanceof VirtualComposed16BitRegister<?> virtualComposed16BitRegister) {
                  Variable existingVariable = byteCodeGenerator.getExistingVariable(virtualComposed16BitRegister);
                  if (virtualComposed16BitRegister.getLow() == firstPrevious)
                    return existingVariable.and(0xFF);
                  else if (virtualComposed16BitRegister.getHigh() == firstPrevious)
                    return existingVariable.shr(8);
                }
              }
              return processValue(this, null);
            }
          }
        }

        return processValue(this, !previousVersions.isEmpty() ? previousVersions.get(0) : Integer.valueOf(virtualRegister.read().intValue()));
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

  public Object getResult() {
    return result;
  }

  @Override
  public boolean visitVirtualComposed16BitRegister(VirtualComposed16BitRegister virtualComposed16BitRegister) {
    result = processValue(initializerFactory, virtualComposed16BitRegister);
    return true;
  }

  public void visitRegister(Register register) {
    result = processValue(initializerFactory, register);
  }

  protected <T extends WordNumber> Object
  processValue(Function<VirtualRegister<T>, Object> initializerFactory, Object value) {
    if (value instanceof VirtualRegister virtualRegister) {
      Variable variable = byteCodeGenerator.variablesByRegister.get(virtualRegister);
      if (variable == null) {
        variable = byteCodeGenerator.getVariable(virtualRegister, solveInitializer(initializerFactory, virtualRegister));
        byteCodeGenerator.variablesByRegister.put(virtualRegister, variable);
      }
      return variable;
    } else
      return value;
  }

  private <T extends WordNumber> Object
  solveInitializer(Function<VirtualRegister<T>, Object> initializerFactory, VirtualRegister virtualRegister) {
    Object initializer;
    if (virtualRegister.usesMultipleVersions()) {
      if (!byteCodeGenerator.variableExists(virtualRegister)) {
        VirtualRegister<?> parentPreviousVersion = virtualRegister.adjustRegisterScope();

        int minLine = virtualRegister.getScope().start;

        Label branchLabel = byteCodeGenerator.getBranchLabel(minLine);
        VirtualRegister<?> virtualRegister1 = parentPreviousVersion.getDependants().get(0);
        initializer = findInitializer(initializerFactory, virtualRegister1);
        Object finalInitializer = initializer;
        Runnable runnable = () -> {
          byteCodeGenerator.getVariable(virtualRegister, finalInitializer);
          virtualRegister.getPreviousVersions().forEach(p -> byteCodeGenerator.commonRegisters.put((VirtualRegister<WordNumber>) p, (VirtualRegister<WordNumber>) virtualRegister));
        };

        if (minLine == virtualRegister.getRegisterLine() + 1)
          runnable.run();
        else {
          Label insert = branchLabel.insert(runnable);
          byteCodeGenerator.setBranchLabel(insert);
        }
      }

      initializer = byteCodeGenerator.getExistingVariable(virtualRegister);
    } else {
      initializer = findInitializer(initializerFactory, virtualRegister);

      if (virtualRegister instanceof InitialVirtualRegister) {
        Object finalInitializer1 = initializer;
        Runnable runnable = () -> {
          byteCodeGenerator.getVariable(virtualRegister, 100000);
        };
        Label branchLabel = byteCodeGenerator.getBranchLabel(0);
        Label insert = branchLabel.insert(runnable);
      }
    }
    return initializer;
  }

  private <T extends WordNumber> Object
  findInitializer(Function<VirtualRegister<T>, Object> createInitializer, VirtualRegister<?> virtualRegister) {
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

  public void visitMemoryPlusRegister8BitReference
      (MemoryPlusRegister8BitReference<T> memoryPlusRegister8BitReference) {
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

      variable = opcodeReferenceVisitor.getResult();
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

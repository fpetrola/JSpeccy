package com.fpetrola.z80.bytecode.impl;

import com.fpetrola.z80.instructions.base.DummyInstructionVisitor;
import com.fpetrola.z80.instructions.MemoryAccessOpcodeReference;
import com.fpetrola.z80.opcodes.references.*;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.transformations.*;
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
        if (previousVersions.isEmpty()) {
          return virtualRegister.read().intValue();
        } else {
          VirtualRegister<T> firstPrevious = previousVersions.get(0);
          if (!virtualRegister.hasNoPrevious()) {
            if (firstPrevious.isMixRegister()) {
              VirtualComposed16BitRegister<T> virtualComposed16BitRegister = (VirtualComposed16BitRegister) firstPrevious;
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
                return byteCodeGenerator.initial;
              }
            }
          }

          return processValue(this, firstPrevious);
        }
      }
    };
  }

  @Override
  public void visitOpcodeReference(OpcodeReference opcodeReference) {
    result = ((WordNumber) opcodeReference.read()).intValue();
  }

  public Object getResult() {
    return result;
  }

  public boolean visitRegister(Register register) {
    result = processValue(initializerFactory, (VirtualRegister<T>) register);
    return true;
  }

  protected Object processValue(Function<VirtualRegister<T>, Object> initializerFactory, VirtualRegister<T> virtualRegister) {
    Variable variable;
    VirtualRegister top = ByteCodeGenerator.getTop(virtualRegister);
    boolean b = !(top instanceof InitialVirtualRegister);
    if (!byteCodeGenerator.variableExists(top)) {
      if (!b) {
        Variable[] variable2 = new Variable[1];

        //byteCodeGenerator.createVariable(virtualRegister);
        Runnable runnable = () -> variable2[0] = byteCodeGenerator.getVariable(virtualRegister, () -> solveInitializer(initializerFactory, virtualRegister));
//        runnable.run();
        Label branchLabel = byteCodeGenerator.getBranchLabel(top.getScope().start);
        Label insert = branchLabel.insert(runnable);
        variable = variable2[0];
      } else
        variable = byteCodeGenerator.getVariable(virtualRegister, () -> solveInitializer(initializerFactory, virtualRegister));
    } else {
      variable = byteCodeGenerator.getExistingVariable(top);
      if (true || b) {
        if (virtualRegister.isInitialized()) {
          Object value = solveInitializer(initializerFactory, virtualRegister);
          if (value != null)
            variable.set(value);
        }
      }
    }

    return variable;
  }

  private Object solveInitializer(Function<VirtualRegister<T>, Object> initializerFactory, VirtualRegister<T> virtualRegister) {
    Object initializer;
    if (virtualRegister.usesMultipleVersions()) {
      if (!byteCodeGenerator.variableExists(virtualRegister)) {
        VirtualRegister<T> parentPreviousVersion = virtualRegister.adjustRegisterScope();
        initializer = initializerFactory.apply(parentPreviousVersion);
        Object finalInitializer = initializer;
        byteCodeGenerator.getVariable(virtualRegister, () -> finalInitializer);
        virtualRegister.getPreviousVersions().forEach(p -> byteCodeGenerator.commonRegisters.put(p, virtualRegister));
      }

      initializer = byteCodeGenerator.getExistingVariable(virtualRegister);
    } else {
      initializer = initializerFactory.apply(virtualRegister);
    }
    return initializer;
  }

  public void visitConstantOpcodeReference(ConstantOpcodeReference<T> constantOpcodeReference) {
    result = constantOpcodeReference.read().intValue();
  }

  public void visitMemoryAccessOpcodeReference(MemoryAccessOpcodeReference<T> memoryAccessOpcodeReference) {
    Variable memoryField = byteCodeGenerator.getField("memory");
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
    if (variable instanceof Integer integer)
      variable = integer.intValue() * 1;

    Object variable1 = WriteArrayVariable.getRealVariable(variable);

    Variable get = byteCodeGenerator.mm.invoke("mem", variable1);
//    Variable get = byteCodeGenerator.memory.aget(variable);
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

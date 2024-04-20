package com.fpetrola.z80.blocks;

import com.fpetrola.z80.instructions.MemoryAccessOpcodeReference;
import com.fpetrola.z80.opcodes.references.*;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.transformations.Virtual8BitsRegister;
import com.fpetrola.z80.transformations.VirtualComposed16BitRegister;
import com.fpetrola.z80.transformations.VirtualRegister;
import org.cojen.maker.Field;
import org.cojen.maker.Label;
import org.cojen.maker.Variable;

import java.util.List;
import java.util.Map;
import java.util.Optional;
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
            Variable o = (Variable) processRegister(createInitializer, virtualComposed16BitRegister.getHigh());
            Variable o2 = (Variable) processRegister(createInitializer, virtualComposed16BitRegister.getLow());
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
            return processRegister(this, null);
            //throw new RuntimeException("previous not found");
          } else {
            if (previousVersion == null)
              if (!previousVersions.isEmpty()) {
                previousVersion = previousVersions.get(0);
              } else
                previousVersion = virtualRegister.read().intValue();

            return processRegister(this, previousVersion);
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
    result = processRegister(createInitializer, virtualComposed16BitRegister);
    return true;
  }

  public void visitRegister(Register register) {
    result = processRegister(createInitializer, register);
  }

  protected <T extends WordNumber> Object processRegister(Function<VirtualRegister<T>, Object> createInitializer, Object register) {
    if (register instanceof VirtualRegister<?> virtualRegister) {
      Object initializer = createInitializer.apply((VirtualRegister) virtualRegister);
      Object result = !(virtualRegister instanceof Register) ? ((ImmutableOpcodeReference<WordNumber>) virtualRegister).read().intValue() : 12345;

      if ((ImmutableOpcodeReference<WordNumber>) virtualRegister instanceof VirtualRegister<WordNumber> virtual) {
        initializer = initializer != null ? initializer : result;
        Object value;
        if (virtual.usesMultipleVersions()) {
          final Variable[] t = new Variable[1];
          String name = virtual.getName();
          if (!byteCodeGenerator.variableExists(virtual)) {
            Label branchLabel = byteCodeGenerator.getBranchLabel();
            Object finalInitializer = initializer;
            Label insert = branchLabel.insert(() -> {
              t[0] = byteCodeGenerator.getVariable(virtual, finalInitializer);
              virtual.getPreviousVersions().forEach(p -> ByteCodeGeneratorVisitor.commonRegisters.put(p, virtual));
            });
            byteCodeGenerator.setBranchLabel(insert);
          } else
            t[0] = byteCodeGenerator.getExistingVariable(virtual);

          value = t[0];
        } else {
          value = initializer;
        }
        result = byteCodeGenerator.getVariable(virtual, value);
      }
      return result;
    } else
      return register;
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

    Variable variablePlusDelta = variable.add(memoryPlusRegister8BitReference.fetchRelative());
    if (isTarget)
      result = new WriteArrayVariable(byteCodeGenerator, () -> variablePlusDelta);
    else {
      result = getFromMemory(variablePlusDelta);
    }
  }


  public void visitIndirectMemory8BitReference(IndirectMemory8BitReference indirectMemory8BitReference) {
    Register target = (Register) indirectMemory8BitReference.getTarget();
    OpcodeReferenceVisitor opcodeReferenceVisitor = new OpcodeReferenceVisitor(false, byteCodeGenerator);
    target.accept(opcodeReferenceVisitor);

    Variable variable = (Variable) opcodeReferenceVisitor.getResult();
    if (isTarget)
      result = new WriteArrayVariable(byteCodeGenerator, () -> variable);
    else {
      result = getFromMemory(variable);
    }
  }

  private Variable getFromMemory(Object variable) {
    Variable get = byteCodeGenerator.memory.aget(variable);
//
//    Variable get = byteCodeGenerator.memory.invoke(Object.class, "get", new Object[]{int.class}, variable);
//    Variable cast = get.cast(Integer.class);
    return get;
  }

  @Override
  public void visitImmutableOpcodeReference(ImmutableOpcodeReference immutableOpcodeReference) {
    result = ((T) immutableOpcodeReference.read()).intValue();
  }
}

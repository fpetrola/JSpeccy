package com.fpetrola.z80.blocks;

import com.fpetrola.z80.instructions.MemoryAccessOpcodeReference;
import com.fpetrola.z80.opcodes.references.*;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.transformations.MyVirtualRegister;
import com.fpetrola.z80.transformations.Virtual8BitsRegister;
import com.fpetrola.z80.transformations.VirtualComposed16BitRegister;
import com.fpetrola.z80.transformations.VirtualRegister;
import org.cojen.maker.Field;
import org.cojen.maker.Variable;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;

public class OpcodeReferenceVisitor<T extends WordNumber> extends DummyInstructionVisitor<T> {
  private Object result;
  private boolean isTarget;
  private ByteCodeGenerator byteCodeGenerator;

  public void setCreateInitializer(BiFunction<ByteCodeGenerator, VirtualRegister<T>, Object> createInitializer) {
    this.createInitializer = createInitializer;
  }

  private BiFunction<ByteCodeGenerator, VirtualRegister<T>, Object> createInitializer;

  public OpcodeReferenceVisitor(boolean isTarget, ByteCodeGenerator byteCodeGenerator1) {
    this.isTarget = isTarget;
    byteCodeGenerator = byteCodeGenerator1;
    createInitializer = new BiFunction<>() {
      public Object apply(ByteCodeGenerator byteCodeGenerator1, VirtualRegister<T> virtualRegister) {
        List<VirtualRegister<T>> previousVersions = virtualRegister.getPreviousVersions();
        if (!virtualRegister.hasNoPrevious() && isMixRegister(previousVersions.get(0))) {
          VirtualComposed16BitRegister virtualComposed16BitRegister = (VirtualComposed16BitRegister) previousVersions.get(0);
          String name = virtualComposed16BitRegister.getName();
          Variable variable = ByteCodeGeneratorVisitor.initializers.get(name);
          if (variable == null) {
            Variable o = (Variable) processRegister((VirtualRegister) virtualComposed16BitRegister.getHigh(), byteCodeGenerator, createInitializer);
            Variable o2 = (Variable) processRegister((VirtualRegister) virtualComposed16BitRegister.getLow(), byteCodeGenerator, createInitializer);
            variable = o.shl(8).or(o2);
            ByteCodeGeneratorVisitor.initializers.put(name, variable);
          }
          return variable;
        } else {
          VirtualRegister previousVersion = (VirtualRegister) getPreviousVersion(virtualRegister, byteCodeGenerator1);
          if (previousVersion == null && !virtualRegister.hasNoPrevious()) {
            Register previousVersion1 = virtualRegister.getPreviousVersions().get(0);
            for (Map.Entry<String, VirtualRegister> e : byteCodeGenerator1.registerByVariable.entrySet()) {
              if (e.getValue() instanceof VirtualComposed16BitRegister<?>) {
                VirtualComposed16BitRegister<?> value = (VirtualComposed16BitRegister<?>) e.getValue();
                Variable existingVariable = byteCodeGenerator1.getExistingVariable(value.getName());
                if (value.getLow() == previousVersion1)
                  return existingVariable.and(0xFF);
                else if (value.getHigh() == previousVersion1)
                  return existingVariable.shr(8);
              }
            }
            return processRegister(null, byteCodeGenerator1, this);
            //throw new RuntimeException("previous not found");
          } else
            return processRegister(previousVersion, byteCodeGenerator1, this);
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

  public OpcodeReferenceVisitor(boolean isTarget, ByteCodeGenerator byteCodeGenerator1, BiFunction<ByteCodeGenerator, VirtualRegister<T>, Object> createInitializer) {
    this.isTarget = isTarget;
    byteCodeGenerator = byteCodeGenerator1;
    this.createInitializer = createInitializer;
  }

  private static <T extends WordNumber> Object getPreviousVersion(VirtualRegister<T> virtualRegister, ByteCodeGenerator byteCodeGenerator1) {
    List<VirtualRegister<T>> previousVersions = virtualRegister.getPreviousVersions();
    if (virtualRegister.hasNoPrevious()) {
      return null;
    } else {
      Optional<? extends VirtualRegister<T>> first = previousVersions.stream().filter(r -> byteCodeGenerator1.variableExists(r.getName())).findFirst();
      return first.orElse(null);
    }
  }

  public Object getResult() {
    return result;
  }

  @Override
  public boolean visitVirtualComposed16BitRegister(VirtualComposed16BitRegister virtualComposed16BitRegister) {
    result = processRegister(virtualComposed16BitRegister, this.byteCodeGenerator, createInitializer);
    return true;
  }

  public void visitRegister(Register register) {
    result = processRegister((VirtualRegister) register, this.byteCodeGenerator, createInitializer);
  }

  private static <T extends WordNumber> Object processRegister(VirtualRegister register, ByteCodeGenerator byteCodeGenerator, BiFunction<ByteCodeGenerator, VirtualRegister<T>, Object> createInitializer) {
    if (register instanceof VirtualRegister<?>)
      return create8BitsRegister(byteCodeGenerator, createInitializer, register);
    else
      return register;

//    if (register instanceof Virtual8BitsRegister virtual8BitsRegister) {
//      return create8BitsRegister(byteCodeGenerator, createInitializer, virtual8BitsRegister);
//    } else if (register instanceof VirtualComposed16BitRegister virtualComposed16BitRegister) {
//      return new Variable16Bits(byteCodeGenerator, createInitializer, virtualComposed16BitRegister);
//    } else
//      return register;
  }

  public static <T extends WordNumber> Object create8BitsRegister(ByteCodeGenerator byteCodeGenerator, BiFunction<ByteCodeGenerator, VirtualRegister<T>, Object> createInitializer, VirtualRegister virtual8BitsRegister) {
    Object initializer = createInitializer.apply(byteCodeGenerator, virtual8BitsRegister);
    Object sourceVariableOf = ByteCodeGeneratorVisitor.getSourceVariableOf(virtual8BitsRegister, initializer, byteCodeGenerator);
    return sourceVariableOf;
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
//    String name = ((Register) memoryPlusRegister8BitReference.getTarget()).getName();
//    Field field = byteCodeGenerator.getField(name);
//    if (isTarget)
//      result = new WriteArrayVariable(byteCodeGenerator, () -> field.add(memoryPlusRegister8BitReference.fetchRelative()));
//    else {
//      Variable o = field.add(memoryPlusRegister8BitReference.fetchRelative());
//      result = getFromMemory(o);
//    }

    Register target = (Register) memoryPlusRegister8BitReference.getTarget();
    OpcodeReferenceVisitor opcodeReferenceVisitor = new OpcodeReferenceVisitor(false, byteCodeGenerator);
    target.accept(opcodeReferenceVisitor);

    Variable variable = (Variable) opcodeReferenceVisitor.getResult();
    if (isTarget)
      result = new WriteArrayVariable(byteCodeGenerator, () -> variable);
    else {
      result = getFromMemory(variable);
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
    if (variable instanceof Variable16Bits variable16Bits) {
      Variable get = byteCodeGenerator.memory.aget(variable16Bits.get1());
      return get;
    } else {
      Variable get = byteCodeGenerator.memory.aget(variable);
//
//    Variable get = byteCodeGenerator.memory.invoke(Object.class, "get", new Object[]{int.class}, variable);
//    Variable cast = get.cast(Integer.class);
      return get;
    }
  }

  @Override
  public void visitImmutableOpcodeReference(ImmutableOpcodeReference immutableOpcodeReference) {
    result = ((T) immutableOpcodeReference.read()).intValue();
  }
}

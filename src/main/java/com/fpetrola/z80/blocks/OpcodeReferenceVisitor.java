package com.fpetrola.z80.blocks;

import com.fpetrola.z80.instructions.MemoryAccessOpcodeReference;
import com.fpetrola.z80.opcodes.references.ConstantOpcodeReference;
import com.fpetrola.z80.opcodes.references.IndirectMemory8BitReference;
import com.fpetrola.z80.opcodes.references.MemoryPlusRegister8BitReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.transformations.Virtual8BitsRegister;
import com.fpetrola.z80.transformations.VirtualComposed16BitRegister;
import org.cojen.maker.*;

import java.util.Optional;
import java.util.function.BiFunction;

public class OpcodeReferenceVisitor<T extends WordNumber> extends DummyInstructionVisitor<T> {
  private Object result;
  private boolean isTarget;
  private ByteCodeGenerator byteCodeGenerator;

  public void setCreateInitializer(BiFunction<ByteCodeGenerator, Virtual8BitsRegister, Object> createInitializer) {
    this.createInitializer = createInitializer;
  }

  private BiFunction<ByteCodeGenerator, Virtual8BitsRegister, Object> createInitializer;

  public OpcodeReferenceVisitor(boolean isTarget, ByteCodeGenerator byteCodeGenerator1) {
    this.isTarget = isTarget;
    byteCodeGenerator = byteCodeGenerator1;
    createInitializer = new BiFunction<>() {
      public Object apply(ByteCodeGenerator byteCodeGenerator1, Virtual8BitsRegister virtual8BitsRegister1) {
        return processRegister((Register) getPreviousVersion(virtual8BitsRegister1, byteCodeGenerator1), byteCodeGenerator1, this);
      }
    };
  }

  public OpcodeReferenceVisitor(boolean isTarget, ByteCodeGenerator byteCodeGenerator1, BiFunction<ByteCodeGenerator, Virtual8BitsRegister, Object> createInitializer) {
    this.isTarget = isTarget;
    byteCodeGenerator = byteCodeGenerator1;
    this.createInitializer = createInitializer;
  }

  private static <T extends WordNumber> Object getPreviousVersion(Virtual8BitsRegister<T> virtual8BitsRegister, ByteCodeGenerator byteCodeGenerator1) {
    if (virtual8BitsRegister.previousVersions.isEmpty())
      return null;
    else {
      Optional<Virtual8BitsRegister<T>> first = virtual8BitsRegister.previousVersions.stream().filter(r -> byteCodeGenerator1.variableExists(r.getName())).findFirst();
      // FIXME:
      //  return first.get();
      return first.orElse(null);
    }
  }

  public Object getResult() {
    return result;
  }

  public void visitRegister(Register register) {
    result = processRegister(register, this.byteCodeGenerator, createInitializer);
  }

  private static Object processRegister(Register register, ByteCodeGenerator byteCodeGenerator, BiFunction<ByteCodeGenerator, Virtual8BitsRegister, Object> createInitializer) {
    if (register instanceof Virtual8BitsRegister virtual8BitsRegister) {
      Object initializer = createInitializer.apply(byteCodeGenerator, virtual8BitsRegister);
      Object sourceVariableOf = ByteCodeGeneratorVisitor.getSourceVariableOf(virtual8BitsRegister, initializer, byteCodeGenerator);
      return sourceVariableOf;
    } else if (register instanceof VirtualComposed16BitRegister virtualComposed16BitRegister) {
      return new Variable16Bits(processRegister(virtualComposed16BitRegister.getLow(), byteCodeGenerator, createInitializer), processRegister(virtualComposed16BitRegister.getHigh(), byteCodeGenerator, createInitializer));
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
    String name = ((Register) memoryPlusRegister8BitReference.getTarget()).getName();
    Field field = byteCodeGenerator.getField(name);
    if (isTarget)
      result = new WriteArrayVariable(byteCodeGenerator, () -> field.add(memoryPlusRegister8BitReference.fetchRelative()));
    else {
      Variable o = field.add(memoryPlusRegister8BitReference.fetchRelative());
      result = getFromMemory(o);
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
}

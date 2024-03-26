package com.fpetrola.z80.blocks;

import com.fpetrola.z80.instructions.MemoryAccessOpcodeReference;
import com.fpetrola.z80.opcodes.references.ConstantOpcodeReference;
import com.fpetrola.z80.opcodes.references.MemoryPlusRegister8BitReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.transformations.Virtual8BitsRegister;
import org.cojen.maker.Field;
import org.cojen.maker.Variable;

import java.util.Optional;

public class OpcodeReferenceVisitor<T extends WordNumber> extends DummyInstructionVisitor<T> {
  private Object result;
  private boolean isTarget;
  private ByteCodeGenerator byteCodeGenerator;

  public OpcodeReferenceVisitor(boolean isTarget, ByteCodeGenerator byteCodeGenerator1) {
    this.isTarget = isTarget;
    byteCodeGenerator = byteCodeGenerator1;
  }

  private Object getPreviousVersion(Virtual8BitsRegister<T> virtual8BitsRegister) {
    if (virtual8BitsRegister.previousVersions.isEmpty())
      return null;
    else {
      Optional<Virtual8BitsRegister<T>> first = virtual8BitsRegister.previousVersions.stream().filter(r -> byteCodeGenerator.variableExists(r.getName())).findFirst();
      return first.get();
    }
  }

  public Object getResult() {
    return result;
  }

  public void visitRegister(Register register) {
    result = processRegister(register);
  }

  private Object processRegister(Register register) {
    if (register instanceof Virtual8BitsRegister virtual8BitsRegister) {
      Object sourceVariableOf = ByteCodeGeneratorVisitor.getSourceVariableOf(virtual8BitsRegister, processRegister((Register) getPreviousVersion(virtual8BitsRegister)), byteCodeGenerator);
      Virtual8BitsRegister s = ByteCodeGeneratorVisitor.commonRegisters.get(virtual8BitsRegister);
      if (s != null) {
        Variable variable = (Variable) ByteCodeGeneratorVisitor.getSourceVariableOf(s, 0, byteCodeGenerator);
        if (variable != sourceVariableOf) {
          variable.set(sourceVariableOf);
        }
      }

      return sourceVariableOf;
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
      result = byteCodeGenerator.memory.aget(o);
  }

  public void visitMemoryPlusRegister8BitReference(MemoryPlusRegister8BitReference<T> memoryPlusRegister8BitReference) {
    String name = ((Register) memoryPlusRegister8BitReference.getTarget()).getName();
    Field field = byteCodeGenerator.getField(name);
    if (isTarget)
      result = new WriteArrayVariable(byteCodeGenerator, () -> field.add(memoryPlusRegister8BitReference.fetchRelative()));
    else
      result = byteCodeGenerator.memory.aget(field.add(memoryPlusRegister8BitReference.fetchRelative()));
  }
}

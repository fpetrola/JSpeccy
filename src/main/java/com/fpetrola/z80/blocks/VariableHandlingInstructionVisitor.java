package com.fpetrola.z80.blocks;

import com.fpetrola.z80.instructions.base.DefaultTargetFlagInstruction;
import com.fpetrola.z80.instructions.base.TargetInstruction;
import com.fpetrola.z80.instructions.base.TargetSourceInstruction;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.transformations.IVirtual8BitsRegister;
import com.fpetrola.z80.transformations.VirtualComposed16BitRegister;
import com.fpetrola.z80.transformations.VirtualRegister;
import org.cojen.maker.Variable;

import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class VariableHandlingInstructionVisitor extends DummyInstructionVisitor<WordNumber> {
  protected Function createInitializer;
  private BiConsumer<Object, Variable> variableAction;
  protected Object sourceVariable;
  protected Object targetVariable;
  private OpcodeReference target;
  private ImmutableOpcodeReference source;
  private ByteCodeGenerator byteCodeGenerator;

  public VariableHandlingInstructionVisitor(BiConsumer<Object, Variable> variableAction, ByteCodeGenerator byteCodeGenerator1) {
    this.variableAction = variableAction;
    byteCodeGenerator = byteCodeGenerator1;
  }

  public void visitingTarget(OpcodeReference target, TargetInstruction targetInstruction) {
    this.target = target;
    OpcodeReferenceVisitor instructionVisitor = new OpcodeReferenceVisitor(true, byteCodeGenerator);
    if (createInitializer != null) instructionVisitor.setCreateInitializer(createInitializer);
    target.accept(instructionVisitor);
    targetVariable = instructionVisitor.getResult();
  }

  public void visitingSource(ImmutableOpcodeReference source, TargetSourceInstruction targetSourceInstruction) {
    this.source = source;
    OpcodeReferenceVisitor opcodeReferenceVisitor = new OpcodeReferenceVisitor(false, byteCodeGenerator);
    if (createInitializer != null) opcodeReferenceVisitor.setCreateInitializer(createInitializer);

    source.accept(opcodeReferenceVisitor);
    sourceVariable = opcodeReferenceVisitor.getResult();
  }

  public void visitingFlag(Register<WordNumber> flag, DefaultTargetFlagInstruction targetSourceInstruction) {
    OpcodeReferenceVisitor instructionVisitor = new OpcodeReferenceVisitor(true, byteCodeGenerator);
    if (createInitializer != null) instructionVisitor.setCreateInitializer(createInitializer);
    flag.accept(instructionVisitor);
  }

  public void visitingTargetSourceInstruction(TargetSourceInstruction targetSourceInstruction) {
    extracted();
  }

  private void extracted() {
    if (targetVariable instanceof Variable variable) {
      variableAction.accept(sourceVariable, variable);
      Optional<Map.Entry<VirtualRegister<WordNumber>, VirtualRegister<WordNumber>>> fromCommonRegisters = getFromCommonRegisters(variable);
      VirtualRegister<WordNumber> s = fromCommonRegisters.isEmpty() ? null : fromCommonRegisters.get().getValue();

      if (s != null) {
        if (!s.getName().equals(variable.name())) {
          byteCodeGenerator.getExistingVariable(s).set(variable);
        }
      } else {
        Optional<Map.Entry<VirtualRegister<WordNumber>, VirtualRegister<WordNumber>>> first = ByteCodeGeneratorVisitor.commonRegisters.entrySet().stream().filter(e -> {
          if (e.getKey() instanceof VirtualComposed16BitRegister<WordNumber> virtualComposed16BitRegister && e.getKey().getName().contains(",")) {
            boolean contains = byteCodeGenerator.getExistingVariable(virtualComposed16BitRegister.getLow()) == variable;
            contains |= byteCodeGenerator.getExistingVariable(virtualComposed16BitRegister.getHigh()) == variable;
            if (contains) return true;
          }
          return false;
        }).findFirst();
        first.ifPresent(e -> {
          VirtualComposed16BitRegister<WordNumber> virtualRegister = (VirtualComposed16BitRegister<WordNumber>) e.getKey();
          Variable variable1 = get8BitCommon(virtualRegister.getHigh()).shl(8).or(get8BitCommon(virtualRegister.getLow()).and(0xFF));
          byteCodeGenerator.getExistingVariable(e.getValue()).set(variable1);
        });
      }
    }
  }

  public static Optional<Map.Entry<VirtualRegister<WordNumber>, VirtualRegister<WordNumber>>> getFromCommonRegisters(Variable variable) {
    return ByteCodeGeneratorVisitor.commonRegisters.entrySet().stream().filter(e -> e.getKey().getName().equals(variable.name())).findFirst();
  }

  private Variable get8BitCommon(IVirtual8BitsRegister<WordNumber> virtualRegister) {
    VirtualComposed16BitRegister<WordNumber> virtualComposed16BitRegister = virtualRegister.getVirtualComposed16BitRegister();
    String name = virtualComposed16BitRegister.getName();
    return name.contains(",") ? byteCodeGenerator.getExistingVariable(virtualRegister) : byteCodeGenerator.getExistingVariable(virtualComposed16BitRegister);
  }

  public void visitingTargetInstruction(TargetInstruction targetInstruction) {
    extracted();
  }
}

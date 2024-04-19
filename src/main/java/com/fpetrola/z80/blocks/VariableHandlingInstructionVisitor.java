package com.fpetrola.z80.blocks;

import com.fpetrola.z80.instructions.base.DefaultTargetFlagInstruction;
import com.fpetrola.z80.instructions.base.TargetInstruction;
import com.fpetrola.z80.instructions.base.TargetSourceInstruction;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
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

      if (s!= null) {
        if (!s.getName().equals(variable.name())) {
          byteCodeGenerator.getExistingVariable(s.getName()).set(variable);
        }
      } else {
        Optional<Map.Entry<VirtualRegister<WordNumber>, VirtualRegister<WordNumber>>> first = ByteCodeGeneratorVisitor.commonRegisters.entrySet().stream().filter(e -> {
          return e.getKey().getName().contains(",") && (e.getKey().getName() + ",").contains(variable.name() + ",");
        }).findFirst();
        first.ifPresent(e -> {
          String[] split = e.getKey().getName().split(",");
          byteCodeGenerator.getExistingVariable(e.getValue().getName()).set(create16BitVariable(split, e.getKey(), e.getValue()));
        });
      }
    }
  }

  public static Optional<Map.Entry<VirtualRegister<WordNumber>, VirtualRegister<WordNumber>>> getFromCommonRegisters(Variable variable) {
    Optional<Map.Entry<VirtualRegister<WordNumber>, VirtualRegister<WordNumber>>> s = ByteCodeGeneratorVisitor.commonRegisters.entrySet().stream().filter(e-> e.getKey().getName().equals(variable.name())).findFirst();
    return s;
  }

  private Variable create16BitVariable(String[] split, VirtualRegister<WordNumber> virtualRegister, VirtualRegister<WordNumber> value) {
    Variable h = variable8_Or_16(split[0], split[1], 1);
    Variable l = variable8_Or_16(split[1], split[0], 0);
    Variable result = h.shl(8).or(l.and(0xFF));
    return result;
  }

  private Variable variable8_Or_16(String first, String second, int insertIndex) {
    Variable result = null;
    boolean b = byteCodeGenerator.variableExists(first);
    if (!b) {
      StringBuffer stringBuffer = new StringBuffer(first);
      stringBuffer.insert(insertIndex, second.charAt(0));
      boolean b1 = byteCodeGenerator.variableExists(stringBuffer.toString());
      if (b1) {
        result = byteCodeGenerator.getExistingVariable(stringBuffer.toString());
      }
    } else result = byteCodeGenerator.getExistingVariable(first);
    return result;
  }

  public void visitingTargetInstruction(TargetInstruction targetInstruction) {
    extracted();
  }
}

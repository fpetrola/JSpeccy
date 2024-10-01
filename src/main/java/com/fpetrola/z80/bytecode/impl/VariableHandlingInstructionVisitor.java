package com.fpetrola.z80.bytecode.impl;

import com.fpetrola.z80.instructions.base.*;
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

import static com.fpetrola.z80.bytecode.impl.ByteCodeGenerator.getRegisterName;

public class VariableHandlingInstructionVisitor extends DummyInstructionVisitor<WordNumber> {
  protected Function createInitializer;
  private BiConsumer<Object, Variable> variableAction;
  protected Object sourceVariable;
  protected Variable targetVariable;
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
    if (createInitializer != null) instructionVisitor.setInitializerFactory(createInitializer);
    target.accept(instructionVisitor);
    targetVariable = (Variable) instructionVisitor.getResult();
  }

  public void visitingSource(ImmutableOpcodeReference source, TargetSourceInstruction targetSourceInstruction) {
    this.source = source;
    OpcodeReferenceVisitor opcodeReferenceVisitor = new OpcodeReferenceVisitor(false, byteCodeGenerator);
    if (createInitializer != null) opcodeReferenceVisitor.setInitializerFactory(createInitializer);

    source.accept(opcodeReferenceVisitor);
    sourceVariable = opcodeReferenceVisitor.getResult();

    int i = byteCodeGenerator.pc.read().intValue();
    Optional<Integer> mutantCode = SymbolicExecutionAdapter.mutantAddress.stream()
        .filter(m -> m >= i && m <= byteCodeGenerator.currentInstruction.getLength() + i).findFirst();
    if (mutantCode.isPresent()) {
      sourceVariable = byteCodeGenerator.getField("mem").aget(mutantCode.get());
    }
  }

  public void visitingFlag(Register<WordNumber> flag, DefaultTargetFlagInstruction targetSourceInstruction) {
//    OpcodeReferenceVisitor instructionVisitor = new OpcodeReferenceVisitor(true, byteCodeGenerator);
//    if (createInitializer != null) instructionVisitor.setCreateInitializer(createInitializer);
//    flag.accept(instructionVisitor);
  }

  public void visitingTargetSourceInstruction(TargetSourceInstruction targetSourceInstruction) {
    createResult();
  }

  @Override
  public void visitingBitOperation(BitOperation tBitOperation) {
    variableAction.accept(sourceVariable, (Variable) targetVariable);
  }

  private void createResult() {
    if (targetVariable instanceof Variable variable) {
      variableAction.accept(sourceVariable, variable);
      Optional<Map.Entry<VirtualRegister<?>, VirtualRegister<?>>> fromCommonRegisters = getFromCommonRegisters(variable, byteCodeGenerator);
      VirtualRegister<?> s = fromCommonRegisters.isEmpty() ? null : fromCommonRegisters.get().getValue();

      if (s != null) {
        if (!s.getName().equals(variable.name())) {
          byteCodeGenerator.getExistingVariable(s).set(variable);
        }
      } else {
        byteCodeGenerator.commonRegisters.entrySet().stream().forEach(e -> {
          if (e.getKey() instanceof VirtualComposed16BitRegister<?> virtualComposed16BitRegister && virtualComposed16BitRegister.isMixRegister()) {
            boolean contains = byteCodeGenerator.getExistingVariable(virtualComposed16BitRegister.getLow()) == variable;
            contains |= byteCodeGenerator.getExistingVariable(virtualComposed16BitRegister.getHigh()) == variable;
            if (contains) {
              Variable commonHigh = get8BitCommon(virtualComposed16BitRegister.getHigh());
              Variable commonLow = get8BitCommon(virtualComposed16BitRegister.getLow());
              Variable variable1;

              if (commonHigh == null) variable1 = commonLow.and(0xFF);
              else variable1 = commonHigh.shl(8).or(commonLow.and(0xFF));

              byteCodeGenerator.getExistingVariable(e.getValue()).set(variable1);
            }
          }
        });
      }
    }
  }

  public static Optional<Map.Entry<VirtualRegister<?>, VirtualRegister<?>>> getFromCommonRegisters(Variable variable, ByteCodeGenerator byteCodeGenerator) {
    return byteCodeGenerator.commonRegisters.entrySet().stream().filter(e -> getRegisterName(e.getKey()).equals(variable.name())).findFirst();
  }

  private Variable get8BitCommon(IVirtual8BitsRegister<?> virtualRegister) {
    VirtualComposed16BitRegister<?> virtualComposed16BitRegister = virtualRegister.getVirtualComposed16BitRegister();
    if (virtualComposed16BitRegister.isMixRegister()) return byteCodeGenerator.getExistingVariable(virtualRegister);
    return byteCodeGenerator.getExistingVariable(virtualComposed16BitRegister);
  }

  public void visitingTargetInstruction(TargetInstruction targetInstruction) {
    createResult();
  }
}

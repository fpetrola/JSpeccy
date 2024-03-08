package com.fpetrola.z80.instructions;

import com.fpetrola.z80.blocks.DummyInstructionVisitor;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.instructions.base.InstructionVisitor;
import com.fpetrola.z80.instructions.base.TargetInstruction;
import com.fpetrola.z80.instructions.base.TargetSourceInstruction;
import com.fpetrola.z80.opcodes.references.*;
import com.fpetrola.z80.registers.*;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

import java.util.HashMap;
import java.util.Map;

public class TransformerVisitor<T extends WordNumber> extends DummyInstructionVisitor<T> implements InstructionVisitor<T> {
  private MultiValuedMap<String, String> names = new HashSetValuedHashMap<>();
  protected Map<Register, Register> targets = new HashMap<>();

  public <T extends WordNumber> TransformerVisitor() {
    this.targets = targets;
  }

  public <T extends WordNumber> Register createVirtualRegister(Register register, Instruction<T> targetInstruction, ImmutableOpcodeReference<T> targetRegister, boolean[] executing) {
    Register virtualRegister = new VirtualPlain8BitRegister(createVirtualRegisterName(register.getName()), executing, targetInstruction, targetRegister);
    targets.put(register, virtualRegister);
    return virtualRegister;
  }

  public <T extends WordNumber> Register create16VirtualRegister(Instruction<T> targetInstruction, RegisterPair registerPair, boolean indirect) {
    Register high = registerPair.getHigh();
    Register low = registerPair.getLow();
    if (!indirect) {
      boolean[] executing = new boolean[1];
      createVirtualRegister(high, targetInstruction, getTargetRegister(high), executing);
      createVirtualRegister(low, targetInstruction, getTargetRegister(low), executing);
    }
    Composed16BitRegister<WordNumber> virtualRegister = new Composed16BitRegister<>(createVirtualRegisterName(high.getName() + low.getName()), targets.get(high), targets.get(low));
    targets.put(registerPair, virtualRegister);
    return virtualRegister;
  }

  private <T extends WordNumber> DummyImmutableOpcodeReference<T> getTargetRegister(Register register) {
    Register lastVirtualRegister = targets.get(register);
    return new DummyImmutableOpcodeReference<T>() {
      public T read() {
        return (T) lastVirtualRegister.read();
      }
    };
  }

  @Override
  public void visitingTarget(OpcodeReference target, TargetInstruction targetInstruction) {
    if (target instanceof IndirectMemory8BitReference indirectMemory8BitReference) {
      OpcodeReference target1 = (OpcodeReference) indirectMemory8BitReference.target;
      if (target1 instanceof Register register) {
        indirectMemory8BitReference.target = createVirtualRegister(targetInstruction, register, true);
      }
    } else if (target instanceof Register register) {
      targetInstruction.setTarget(createVirtualRegister(targetInstruction, register, false));
    }
  }

  private Register createVirtualRegister(TargetInstruction targetInstruction, Register register, boolean indirect) {
    if (register instanceof RegisterPair registerPair)
      return create16VirtualRegister(targetInstruction, registerPair, indirect);
    else
      return createVirtualRegister(register, targetInstruction, targets.get(register), new boolean[1]);
  }

  @Override
  public void visitingSource(ImmutableOpcodeReference source, TargetSourceInstruction targetSourceInstruction) {
    if (source instanceof Register register) {
      Register virtual = targets.get(register);

      if (virtual == null && register instanceof RegisterPair registerPair)
        virtual = create16VirtualRegister(null, registerPair, true);

      targetSourceInstruction.setSource(virtual);
    }
  }

  private String createVirtualRegisterName(String name) {
    String s = name + "_v" + names.get(name).size();
    names.put(name, s);
    return s;
  }

  private class VirtualPlain8BitRegister<T extends WordNumber> extends Plain8BitRegister<T> {
    private final boolean[] executing;
    private final Instruction<T> targetInstruction;
    private final ImmutableOpcodeReference<T> lastRegister;

    public VirtualPlain8BitRegister(String name, boolean[] executing, Instruction<T> targetInstruction, ImmutableOpcodeReference<T> lastRegister) {
      super(name);
      this.executing = executing;
      this.targetInstruction = targetInstruction;
      this.lastRegister = lastRegister;
    }

    public T read() {
      if (data != null)
        return data;

      if (!executing[0]) {
        executing[0] = true;
        targetInstruction.execute();
        executing[0] = false;
        return (T) data;
      } else
        return (T) lastRegister.read();
    }
  }
}

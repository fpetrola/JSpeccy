package com.fpetrola.z80.instructions.transformations;

import com.fpetrola.z80.instructions.DummyImmutableOpcodeReference;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.instructions.base.TargetInstruction;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Composed16BitRegister;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterPair;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

import java.util.HashMap;
import java.util.Map;

public class VirtualRegisterFactory<T extends WordNumber> {
  private Map<Register, Register> targets = new HashMap<>();
  private MultiValuedMap<String, String> names = new HashSetValuedHashMap<>();

  public VirtualRegisterFactory() {
  }

  public Register createVirtualRegister(TargetInstruction targetInstruction, Register register, boolean indirect) {
    if (register instanceof RegisterPair registerPair)
      return create16VirtualRegister(targetInstruction, registerPair, indirect);
    else
      return createVirtualRegister(register, targetInstruction, targets.get(register), new boolean[1]);
  }

  public Register getVirtualRegisterFor(Register register) {
    return targets.get(register);
  }

  private <T extends WordNumber> Register createVirtualRegister(Register register, Instruction<T> targetInstruction, ImmutableOpcodeReference<T> targetRegister, boolean[] executing) {
    Register virtualRegister = new VirtualPlain8BitRegister(createVirtualRegisterName(register.getName()), executing, targetInstruction, targetRegister);
    targets.put(register, virtualRegister);
    return virtualRegister;
  }

  private <T extends WordNumber> Register create16VirtualRegister(Instruction<T> targetInstruction, RegisterPair registerPair, boolean indirect) {
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

  private String createVirtualRegisterName(String name) {
    String s = name + "_v" + names.get(name).size();
    names.put(name, s);
    return s;
  }
}

package com.fpetrola.z80.instructions.transformations;

import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Composed16BitRegister;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterPair;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class VirtualRegisterFactory<T extends WordNumber> {
  private Map<Register<T>, Register<T>> virtualRegisters = new HashMap<>();
  private MultiValuedMap<String, String> names = new HashSetValuedHashMap<>();

  public VirtualRegisterFactory() {
  }

  public Register createVirtualRegister(Instruction<T> instruction, Register register, boolean readOnly) {
    if (register instanceof RegisterPair registerPair)
      return create16VirtualRegister(instruction, registerPair, readOnly);
    else {
      Register<T> virtualRegister = getVirtualRegisterFor(register);
      return createVirtualRegister(register, instruction, virtualRegister == null ? null : virtualRegister::read, new boolean[1]);
    }
  }

  public Register getVirtualRegisterFor(Register register) {
    return virtualRegisters.get(register);
  }

  private <T extends WordNumber> Register<T> createVirtualRegister(Register register, Instruction<T> targetInstruction, Supplier<T> targetRegister, boolean[] semaphore) {
    Register virtualRegister = new VirtualPlain8BitRegister(createVirtualRegisterName(register.getName()), semaphore, targetInstruction, targetRegister);
    virtualRegisters.put(register, virtualRegister);
    return virtualRegister;
  }

  private <T extends WordNumber> Register create16VirtualRegister(Instruction<T> targetInstruction, RegisterPair registerPair, boolean readOnly) {
    Register high = registerPair.getHigh();
    Register low = registerPair.getLow();
    if (!readOnly) {
      boolean[] semaphore = new boolean[1];
      createVirtualRegister(high, targetInstruction, getTargetRegister(high), semaphore);
      createVirtualRegister(low, targetInstruction, getTargetRegister(low), semaphore);
    }
    Composed16BitRegister virtualRegister = new Composed16BitRegister<>(createVirtualRegisterName(high.getName() + low.getName()), getVirtualRegisterFor(high), getVirtualRegisterFor(low));
    virtualRegisters.put(registerPair, virtualRegister);
    return virtualRegister;
  }

  private <T extends WordNumber> Supplier<T> getTargetRegister(Register register) {
    Register lastVirtualRegister = getVirtualRegisterFor(register);
    return () -> (T) lastVirtualRegister.read();
  }

  private String createVirtualRegisterName(String name) {
    String s = name + "_v" + names.get(name).size();
    names.put(name, s);
    return s;
  }
}

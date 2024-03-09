package com.fpetrola.z80.instructions.transformations;

import com.fpetrola.z80.cpu.InstructionExecutor;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Composed16BitRegister;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterPair;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public class VirtualRegisterFactory<T extends WordNumber> {
  private final InstructionExecutor instructionExecutor;
  private Map<Register<T>, Register<T>> virtualRegisters = new HashMap<>();
  private MultiValuedMap<String, String> names = new HashSetValuedHashMap<>();

  public VirtualRegisterFactory(InstructionExecutor instructionExecutor) {
    this.instructionExecutor = instructionExecutor;
  }

  public Register createVirtualRegister(Instruction<T> instruction, Register register, boolean readOnly) {
    if (register instanceof RegisterPair registerPair)
      return create16VirtualRegister(instruction, registerPair, readOnly);
    else {
      return createVirtualRegister(register, instruction, new boolean[1]);
    }
  }

  public Register getOrCreateVirtualRegister(Register register) {
    return getVirtualRegisterFor(register).orElseGet(() -> createVirtualRegister(null, register, true));
  }

  public Optional<Register> getVirtualRegisterFor(Register register) {
    return Optional.ofNullable(virtualRegisters.get(register));
  }

  private <T extends WordNumber> Register<T> createVirtualRegister(Register register, Instruction<T> targetInstruction, boolean[] semaphore) {
    Supplier<T> lastValueSupplier = (Supplier<T>) getVirtualRegisterFor(register).map((Register r) -> (Supplier) r::read).orElse(null);
    Register virtualRegister = new VirtualPlain8BitRegister(instructionExecutor, createVirtualRegisterName(register), semaphore, targetInstruction, lastValueSupplier);
    virtualRegisters.put(register, virtualRegister);
    return virtualRegister;
  }

  private <T extends WordNumber> Register create16VirtualRegister(Instruction<T> targetInstruction, RegisterPair registerPair, boolean readOnly) {
    Register high = registerPair.getHigh();
    Register low = registerPair.getLow();
    if (!readOnly) {
      boolean[] semaphore = new boolean[1];
      createVirtualRegister(high, targetInstruction, semaphore);
      createVirtualRegister(low, targetInstruction, semaphore);
    }
    Composed16BitRegister virtualRegister = new Composed16BitRegister<>(createVirtualRegisterName(registerPair), getVirtualRegisterFor(high).get(), getVirtualRegisterFor(low).get());
    virtualRegisters.put(registerPair, virtualRegister);
    return virtualRegister;
  }

  private String createVirtualRegisterName(Register register) {
    String name = register.getName();
    String s = name + "_v" + names.get(name).size();
    names.put(name, s);
    return s;
  }
}

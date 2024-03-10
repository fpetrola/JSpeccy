package com.fpetrola.z80.instructions.transformations;

import com.fpetrola.z80.cpu.InstructionExecutor;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Composed16BitRegister;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterPair;
import com.fpetrola.z80.registers.flag.FlagRegister;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public class VirtualRegisterFactory<T extends WordNumber> {
  private final InstructionExecutor instructionExecutor;
  private Map<Register, Register> virtualRegisters = new HashMap<>();
  private MultiValuedMap<String, String> names = new HashSetValuedHashMap<>();

  public VirtualRegisterFactory(InstructionExecutor instructionExecutor) {
    this.instructionExecutor = instructionExecutor;
  }

  public Register createVirtualRegister(Instruction<T> instruction, Register register, boolean readOnly, VirtualFetcher virtualFetcher) {
    if (register instanceof FlagRegister flagRegister)
      return createVirtualFlagRegister(flagRegister, instruction, virtualFetcher);
    else if (register instanceof RegisterPair registerPair)
      return create16VirtualRegister(instruction, registerPair, readOnly);
    else {
      return createVirtual8BitsRegister(register, instruction, virtualFetcher);
    }
  }

  private <T extends WordNumber> FlagRegister<T> createVirtualFlagRegister(FlagRegister register, Instruction<T> targetInstruction, VirtualFetcher virtualFetcher) {
    Supplier<T> lastValueSupplier = (Supplier<T>) getVirtualRegisterFor(register).map((Register r) -> new RegisterSupplier(r)).orElse(null);
    FlagRegister virtualRegister = new VirtualFlagRegister(instructionExecutor, createVirtualRegisterName(register), targetInstruction, lastValueSupplier, virtualFetcher);
    virtualRegisters.put(register, virtualRegister);
    return virtualRegister;
  }

  public Register getOrCreateVirtualRegister(Register register) {
    return getVirtualRegisterFor(register).orElseGet(() -> createVirtualRegister(null, register, true, new VirtualFetcher()));
  }

  public Optional<Register> getVirtualRegisterFor(Register register) {
    return Optional.ofNullable(virtualRegisters.get(register));
  }

  private <T extends WordNumber> Register<T> createVirtual8BitsRegister(Register register, Instruction<T> targetInstruction, VirtualFetcher virtualFetcher) {
    Supplier<T> lastValueSupplier = (Supplier<T>) getVirtualRegisterFor(register).map((Register r) -> new RegisterSupplier(r)).orElse(null);
    Register virtualRegister = new Virtual8BitsRegister(instructionExecutor, createVirtualRegisterName(register), targetInstruction, lastValueSupplier, virtualFetcher);
    virtualRegisters.put(register, virtualRegister);
    return virtualRegister;
  }

  private <T extends WordNumber> Register create16VirtualRegister(Instruction<T> targetInstruction, RegisterPair registerPair, boolean readOnly) {
    Register high = registerPair.getHigh();
    Register low = registerPair.getLow();
    if (!readOnly) {
      boolean[] semaphore = new boolean[1];
      VirtualFetcher virtualFetcher = new VirtualFetcher();
      createVirtual8BitsRegister(high, targetInstruction, virtualFetcher);
      createVirtual8BitsRegister(low, targetInstruction, virtualFetcher);
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

  private record RegisterSupplier(Register r) implements Supplier {
    public Object get() {
      return r.read();
    }

    public String toString() {
      return r.getName();
    }
  }
}

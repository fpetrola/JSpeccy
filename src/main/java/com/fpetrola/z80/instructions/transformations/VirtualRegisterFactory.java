package com.fpetrola.z80.instructions.transformations;

import com.fpetrola.z80.cpu.InstructionExecutor;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterPair;
import com.fpetrola.z80.registers.flag.FlagRegister;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;

import java.util.HashMap;
import java.util.Map;

public class VirtualRegisterFactory<T extends WordNumber> {
  private final InstructionExecutor<T> instructionExecutor;
  private final RegisterNameBuilder registerNameBuilder;
  private final ArrayListValuedHashMap<Register<T>, VirtualRegister<T>> virtualRegisters = new ArrayListValuedHashMap<>();
  private final Map<Register<T>, VirtualRegister<T>> lastVirtualRegisters = new HashMap<>();

  public VirtualRegisterFactory(InstructionExecutor<T> instructionExecutor, RegisterNameBuilder registerNameBuilder) {
    this.instructionExecutor = instructionExecutor;
    this.registerNameBuilder = registerNameBuilder;
  }

  public Register<T> createVirtualRegister(Instruction<T> instruction, Register<T> register, VirtualFetcher<T> virtualFetcher) {
    if (register instanceof FlagRegister<T> flagRegister)
      return createVirtualFlagRegister(flagRegister, instruction, virtualFetcher);
    else if (register instanceof RegisterPair<T> registerPair)
      return create16VirtualRegister(instruction, registerPair, virtualFetcher);
    else
      return createVirtual8BitsRegister(register, instruction, virtualFetcher);
  }

  private VirtualRegister<T> createVirtualFlagRegister(Register<T> register, Instruction<T> targetInstruction, VirtualFetcher<T> virtualFetcher) {
    return buildVirtualRegister(register, (virtualRegisterName, lastRegister) -> new VirtualFlagRegister<>(instructionExecutor, virtualRegisterName, targetInstruction, lastRegister, virtualFetcher));
  }

  private VirtualRegister<T> createVirtual8BitsRegister(Register<T> register, Instruction<T> targetInstruction, VirtualFetcher<T> virtualFetcher) {
    return buildVirtualRegister(register, (virtualRegisterName, lastRegister) -> new Virtual8BitsRegister<>(instructionExecutor, virtualRegisterName, targetInstruction, lastRegister, virtualFetcher));
  }

  private VirtualRegister<T> create16VirtualRegister(Instruction<T> targetInstruction, RegisterPair<T> registerPair, VirtualFetcher<T> virtualFetcher) {
    VirtualRegister<T> virtualH = createVirtual8BitsRegister(registerPair.getHigh(), targetInstruction, virtualFetcher);
    VirtualRegister<T> virtualL = createVirtual8BitsRegister(registerPair.getLow(), targetInstruction, virtualFetcher);
    return buildVirtualRegister(registerPair, (virtualRegisterName, supplier) -> new VirtualComposed16BitRegister<>(virtualRegisterName, virtualH, virtualL));
  }

  private VirtualRegister<T> buildVirtualRegister(Register<T> register, VirtualRegisterBuilder<T> registerBuilder) {
    VirtualRegister<T> virtualRegister = registerBuilder.build(registerNameBuilder.createVirtualRegisterName(register), lastVirtualRegisters.get(register));

    VirtualRegister<T> result = virtualRegisters.get(register).stream().filter(r -> virtualRegister.getName().startsWith(r.getName() + "_")).findFirst().orElseGet(() -> {
      virtualRegisters.put(register, virtualRegister);
      return virtualRegister;
    });

    if (result != virtualRegister && result instanceof Virtual8BitsRegister<T> multiEntryRegister)
      if (multiEntryRegister.getPreviousVersion() != null)
        multiEntryRegister.addLastRegister(((Virtual8BitsRegister<T>) virtualRegister).getPreviousVersion());

    lastVirtualRegisters.put(register, result);
    return result;
  }

  public RegisterNameBuilder getRegisterNameBuilder() {
    return registerNameBuilder;
  }

  public interface VirtualRegisterBuilder<T extends WordNumber> {
    VirtualRegister<T> build(String virtualRegisterName, VirtualRegister<T> lastRegister);
  }
}





















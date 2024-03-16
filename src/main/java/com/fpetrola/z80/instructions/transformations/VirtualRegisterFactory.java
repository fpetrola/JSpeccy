package com.fpetrola.z80.instructions.transformations;

import com.fpetrola.z80.cpu.InstructionExecutor;
import com.fpetrola.z80.helpers.Helper;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterPair;
import com.fpetrola.z80.registers.flag.FlagRegister;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

import java.util.*;

public class VirtualRegisterFactory<T extends WordNumber> {
  public interface VirtualRegisterBuilder {
    VirtualRegister build(String virtualRegisterName, VirtualRegister lastRegister);
  }

  private final InstructionExecutor instructionExecutor;
  private ArrayListValuedHashMap<Register, VirtualRegister> virtualRegisters = new ArrayListValuedHashMap<>();
  private Map<Register, VirtualRegister> lastVirtualRegisters = new HashMap<>();
  private MultiValuedMap<String, String> names = new HashSetValuedHashMap<>();
  public int currentAddress;

  public VirtualRegisterFactory(InstructionExecutor instructionExecutor) {
    this.instructionExecutor = instructionExecutor;
  }

  public Register createVirtualRegister(Instruction<T> instruction, Register register, VirtualFetcher virtualFetcher) {
    if (register instanceof FlagRegister flagRegister)
      return createVirtualFlagRegister(flagRegister, instruction, virtualFetcher);
    else if (register instanceof RegisterPair registerPair)
      return create16VirtualRegister(instruction, registerPair, virtualFetcher);
    else {
      return createVirtual8BitsRegister(register, instruction, virtualFetcher);
    }
  }

  private <T extends WordNumber> VirtualRegister<T> createVirtualFlagRegister(Register register, Instruction<T> targetInstruction, VirtualFetcher virtualFetcher) {
    return setupVirtualRegister(register, (virtualRegisterName, lastRegister) -> new VirtualFlagRegister(instructionExecutor, virtualRegisterName, targetInstruction, lastRegister, virtualFetcher));
  }

  private <T extends WordNumber> VirtualRegister<T> setupVirtualRegister(Register register, VirtualRegisterBuilder registerBuilder) {
    VirtualRegister virtualRegister = registerBuilder.build(createVirtualRegisterName(register), getVirtualRegisterFor(register));

    List<VirtualRegister> registers = virtualRegisters.get(register);

    registers.stream().filter(r -> r instanceof Virtual8BitsRegister).filter(r -> virtualRegister.getName().startsWith(r.getName() + "_")).forEach(r -> {
      Virtual8BitsRegister r1 = (Virtual8BitsRegister) r;


      VirtualRegister<T> lastValueSupplier = r1.getLastRegister();
      Virtual8BitsRegister virtualRegister1 = (Virtual8BitsRegister) virtualRegister;
      if (lastValueSupplier != null) {
        VirtualRegister lastRegister = virtualRegister1.getLastRegister();
        if (r1.addLastRegister(lastRegister))
          lastRegister.clear();
      }
    });

    Optional<VirtualRegister> b = registers.stream().filter(r -> virtualRegister.getName().startsWith(r.getName() + "_")).findFirst();

    VirtualRegister result;
    if (b.isEmpty()) {
      virtualRegisters.put(register, virtualRegister);
      result = virtualRegister;
    } else {
      result = b.get();
    }
    lastVirtualRegisters.put(register, result);
    return result;
  }

  public VirtualRegister getVirtualRegisterFor(Register register) {
    return lastVirtualRegisters.get(register);
  }

  private <T extends WordNumber> VirtualRegister<T> createVirtual8BitsRegister(Register register, Instruction<T> targetInstruction, VirtualFetcher virtualFetcher) {
    return setupVirtualRegister(register, (virtualRegisterName, lastRegister) -> new Virtual8BitsRegister(instructionExecutor, virtualRegisterName, targetInstruction, lastRegister, virtualFetcher));
  }

  private <T extends WordNumber> VirtualRegister create16VirtualRegister(Instruction<T> targetInstruction, RegisterPair registerPair, VirtualFetcher virtualFetcher) {
    VirtualRegister<T> virtualH = createVirtual8BitsRegister(registerPair.getHigh(), targetInstruction, virtualFetcher);
    VirtualRegister<T> virtualL = createVirtual8BitsRegister(registerPair.getLow(), targetInstruction, virtualFetcher);
    return setupVirtualRegister(registerPair, (virtualRegisterName, supplier) -> new VirtualComposed16BitRegister(virtualRegisterName, virtualH, virtualL));
  }

  private String createVirtualRegisterName(Register register) {
    String name = register.getName();
    String s = name + "_L" + Helper.convertToHex(currentAddress);

    Collection<String> strings = names.get(name);
    long count = strings.stream().filter(n -> n.startsWith(s)).count();
    String registerName = s;
    if (count > 0)
      registerName += "_" + count;

    names.put(name, registerName);
    return registerName;
  }

}

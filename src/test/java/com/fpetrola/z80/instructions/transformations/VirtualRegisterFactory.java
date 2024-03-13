package com.fpetrola.z80.instructions.transformations;

import com.fpetrola.z80.cpu.InstructionExecutor;
import com.fpetrola.z80.helpers.Helper;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Composed16BitRegister;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterPair;
import com.fpetrola.z80.registers.flag.FlagRegister;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

import java.util.*;
import java.util.function.Supplier;

public class VirtualRegisterFactory<T extends WordNumber> {
  public interface VirtualRegisterBuilder {
    Register build(String virtualRegisterName, RegisterSupplier registerSupplier);
  }

  private final InstructionExecutor instructionExecutor;
  private ArrayListValuedHashMap<Register, Register> virtualRegisters = new ArrayListValuedHashMap<>();
  private Map<Register, Register> lastVirtualRegisters = new HashMap<>();
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

  private <T extends WordNumber> Register<T> createVirtualFlagRegister(Register register, Instruction<T> targetInstruction, VirtualFetcher virtualFetcher) {
    return setupVirtualRegister(register, (virtualRegisterName, lastValueSupplier) -> new VirtualFlagRegister(instructionExecutor, virtualRegisterName, targetInstruction, lastValueSupplier, virtualFetcher));
  }

  private <T extends WordNumber> Register setupVirtualRegister(Register register, VirtualRegisterBuilder registerBuilder) {
    Register virtualRegister = registerBuilder.build(createVirtualRegisterName(register), getVirtualRegisterFor(register).map((Register r) -> new RegisterSupplier(r)).orElse(null));

    List<Register> registers = virtualRegisters.get(register);

//    registers.stream().filter(r -> r instanceof Virtual8BitsRegister).filter(r -> virtualRegister.getName().startsWith(r.getName())).forEach(r -> {
//      Virtual8BitsRegister r1 = (Virtual8BitsRegister) r;
//      RegisterSupplier lastValueSupplier = r1.getLastValueSupplier();
//      if (lastValueSupplier != null)
//        lastValueSupplier.addSupplier(((Virtual8BitsRegister) virtualRegister).getLastValueSupplier());
//
//      r1.reset();
//    });

    Optional<Register> b = registers.stream().filter(r -> virtualRegister.getName().startsWith(r.getName())).findFirst();
    if (true || b.isEmpty()) {
      virtualRegisters.put(register, virtualRegister);
      lastVirtualRegisters.put(register, virtualRegister);
      return virtualRegister;
    } else {
      Virtual8BitsRegister register1 = (Virtual8BitsRegister) b.get();
      lastVirtualRegisters.put(register, register1);
      //register1.reset();
      return register1;
    }
  }

  public Register getOrCreateVirtualRegister(Register register) {
    return getVirtualRegisterFor(register).orElseGet(() -> createVirtualRegister(null, register, new VirtualFetcher()));
  }

  public Optional<Register> getVirtualRegisterFor(Register register) {
    Register virtualRegister = lastVirtualRegisters.get(register);
    return Optional.ofNullable(virtualRegister);
  }

  private <T extends WordNumber> Register<T> createVirtual8BitsRegister(Register register, Instruction<T> targetInstruction, VirtualFetcher virtualFetcher) {
    return setupVirtualRegister(register, (virtualRegisterName, lastValueSupplier) -> new Virtual8BitsRegister(instructionExecutor, virtualRegisterName, targetInstruction, lastValueSupplier, virtualFetcher));
  }

  private <T extends WordNumber> Register create16VirtualRegister(Instruction<T> targetInstruction, RegisterPair registerPair, VirtualFetcher virtualFetcher) {
    Register<T> virtualH = createVirtual8BitsRegister(registerPair.getHigh(), targetInstruction, virtualFetcher);
    Register<T> virtualL = createVirtual8BitsRegister(registerPair.getLow(), targetInstruction, virtualFetcher);
    return setupVirtualRegister(registerPair, (virtualRegisterName, supplier) -> new Composed16BitRegister<>(virtualRegisterName, virtualH, virtualL));
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

  protected static final class RegisterSupplier<T> implements Supplier<T> {
    private final List<Register<T>> registers = new ArrayList<>();

    protected RegisterSupplier(Register<T> register) {
      this.registers.add(register);
    }

    public T get() {
      return getCurrentRegister().read();
    }

    private Register<T> getCurrentRegister() {
      return registers.get(registers.size() - 1);
    }

    public String toString() {
      return getCurrentRegister().getName();
    }

    public Register<T> getRegister() {
      return getCurrentRegister();
    }

    public boolean addSupplier(RegisterSupplier lastValueSupplier) {
      if (!registers.contains(lastValueSupplier.getCurrentRegister())) {
        registers.add(lastValueSupplier.getCurrentRegister());
        return true;
      } else
        return false;
    }
  }
}

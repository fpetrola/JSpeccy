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
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public class VirtualRegisterFactory<T extends WordNumber> {
    private final InstructionExecutor instructionExecutor;
    private Map<Register, Register> virtualRegisters = new HashMap<>();
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

    private <T extends WordNumber> FlagRegister<T> createVirtualFlagRegister(FlagRegister register, Instruction<T> targetInstruction, VirtualFetcher virtualFetcher) {
        Supplier<T> lastValueSupplier = (Supplier<T>) getVirtualRegisterFor(register).map((Register r) -> new RegisterSupplier(r)).orElse(null);
        FlagRegister virtualRegister = new VirtualFlagRegister(instructionExecutor, createVirtualRegisterName(register, targetInstruction), targetInstruction, lastValueSupplier, virtualFetcher);
        virtualRegisters.put(register, virtualRegister);
        return virtualRegister;
    }

    public Register getOrCreateVirtualRegister(Register register) {
        return getVirtualRegisterFor(register).orElseGet(() -> createVirtualRegister(null, register, new VirtualFetcher()));
    }

    public Optional<Register> getVirtualRegisterFor(Register register) {
        return Optional.ofNullable(virtualRegisters.get(register));
    }

    private <T extends WordNumber> Register<T> createVirtual8BitsRegister(Register register, Instruction<T> targetInstruction, VirtualFetcher virtualFetcher) {
        Supplier<T> lastValueSupplier = (Supplier<T>) getVirtualRegisterFor(register).map((Register r) -> new RegisterSupplier(r)).orElse(null);
        Register virtualRegister = new Virtual8BitsRegister(instructionExecutor, createVirtualRegisterName(register, targetInstruction), targetInstruction, lastValueSupplier, virtualFetcher);
        virtualRegisters.put(register, virtualRegister);
        return virtualRegister;
    }

    private <T extends WordNumber> Register create16VirtualRegister(Instruction<T> targetInstruction, RegisterPair registerPair, VirtualFetcher virtualFetcher) {
        Register<T> virtual8BitsRegisterH = createVirtual8BitsRegister(registerPair.getHigh(), targetInstruction, virtualFetcher);
        Register<T> virtual8BitsRegisterL = createVirtual8BitsRegister(registerPair.getLow(), targetInstruction, virtualFetcher);
        Composed16BitRegister virtualRegister = new Composed16BitRegister<>(createVirtualRegisterName(registerPair, targetInstruction), virtual8BitsRegisterH, virtual8BitsRegisterL);
        virtualRegisters.put(registerPair, virtualRegister);
        return virtualRegister;
    }

    private String createVirtualRegisterName(Register register, Instruction targetInstruction) {
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

    private record RegisterSupplier(Register r) implements Supplier {
        public Object get() {
            return r.read();
        }

        public String toString() {
            return r.getName();
        }
    }
}

package com.fpetrola.z80.instructions.transformations;

import com.fpetrola.z80.cpu.InstructionExecutor;
import com.fpetrola.z80.instructions.InstructionFactory;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.instructions.base.TargetInstruction;
import com.fpetrola.z80.instructions.cache.InstructionCloner;
import com.fpetrola.z80.opcodes.references.*;
import com.fpetrola.z80.registers.Register;

public class InstructionTransformer<T extends WordNumber> extends InstructionCloner<T> {
    public final VirtualRegisterFactory virtualRegisterFactory;

    public void setCurrentInstruction(Instruction currentInstruction) {
        this.currentInstruction = currentInstruction;
    }

    private Instruction currentInstruction;

    public InstructionTransformer(InstructionFactory instructionFactory, InstructionExecutor instructionExecutor) {
        super(instructionFactory);
        virtualRegisterFactory = new VirtualRegisterFactory(instructionExecutor);
    }

    @Override
    public <R extends PublicCloneable> R clone(R cloneable) {
        Instruction currentInstruction1 = currentInstruction;
        return clone1(cloneable, currentInstruction1);
    }

    private <R extends PublicCloneable> R clone1(R cloneable, Instruction currentInstruction1) {
        if (cloneable instanceof IndirectMemory8BitReference indirectMemory8BitReference) {
            OpcodeReference target1 = (OpcodeReference) indirectMemory8BitReference.target;
            ImmutableOpcodeReference result;
            if (target1 instanceof Register register) {
                result = virtualRegisterFactory.createVirtualRegister(null, register, new VirtualFetcher());
            } else {
                result = clone(indirectMemory8BitReference.target);
            }

            return (R) new IndirectMemory8BitReference(result, indirectMemory8BitReference.getMemory());
        } else if (cloneable instanceof Register register) {
            return (R) virtualRegisterFactory.createVirtualRegister(currentInstruction1, register, new VirtualFetcher());
        } else
            return super.clone(cloneable);
    }

    @Override
    public <R extends PublicCloneable> R clone(OpcodeReference opcodeReference) {
        return (R) clone1(opcodeReference, currentInstruction);

    }
    @Override
    public <R extends PublicCloneable> R clone(ImmutableOpcodeReference immutableOpcodeReference) {
        return (R) clone1(immutableOpcodeReference, null);

    }

    @Override
    public void visitingTarget(OpcodeReference target, TargetInstruction targetInstruction) {
        if (target instanceof IndirectMemory8BitReference indirectMemory8BitReference) {
            OpcodeReference target1 = (OpcodeReference) indirectMemory8BitReference.target;
            if (target1 instanceof Register register) {
                indirectMemory8BitReference.target = virtualRegisterFactory.createVirtualRegister(null, register, new VirtualFetcher());
            }
        } else if (target instanceof Register register) {
            ((TargetInstruction) cloned).setTarget(virtualRegisterFactory.createVirtualRegister(targetInstruction, register, new VirtualFetcher()));
        }
    }
}

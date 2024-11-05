package com.fpetrola.z80.instructions.base;

import com.fpetrola.z80.bytecode.Z80InstructionDriver;
import com.fpetrola.z80.minizx.emulation.MockedMemory;
import com.fpetrola.z80.opcodes.references.WordNumber;

import java.util.function.Supplier;

public class ContextDriverMux<T extends WordNumber> extends ContextDriverDelegator<T> {
  private final Z80InstructionDriver<T> secondContext;

  public ContextDriverMux(Z80ContextDriver<T> firstContext, Z80InstructionDriver<T> secondContext) {
    super(firstContext);
    this.secondContext = secondContext;
  }

  @Override
  public int add(Instruction<T> instruction) {
    int add = super.add(instruction);
    secondContext.add(instruction);
    return add;
  }

  @Override
  public void step() {
    super.step();
    secondContext.step();
  }

  @Override
  public MockedMemory<T> initMem(Supplier<T[]> supplier) {
    MockedMemory<T> result = super.initMem(supplier);
    secondContext.initMem(supplier);
    return result;
  }
}

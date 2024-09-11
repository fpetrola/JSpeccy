package com.fpetrola.z80.instructions.base;

import com.fpetrola.z80.minizx.emulation.MockedMemory;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.transformations.RegisterTransformerInstructionSpy;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.IntStream;

import static com.fpetrola.z80.opcodes.references.WordNumber.createValue;
import static org.junit.Assert.assertNotNull;

public interface Z80InstructionDriver<T extends WordNumber> {
  int add(Instruction<T> instruction);

  State<T> getState();

  void step();

  MockedMemory<T> mem();

  MockedMemory<T> initMem(Supplier<T[]> supplier);

  Instruction getInstructionAt(int i);

  Instruction getTransformedInstructionAt(int i);

  default int readMemAt(int i) {
    T read = mem().read(createValue(i));
    assertNotNull(read);
    return read.intValue();
  }

  default void step(int i) {
    IntStream.range(0, i).forEach(i2 -> step());
  }

  default long countExecutedInstructionsOfType(Class<? extends Instruction> instructionType) {
    List executedInstructions = getRegisterTransformerInstructionSpy().getExecutedInstructions();
    return executedInstructions.stream().filter(i -> instructionType.isAssignableFrom(i.getClass())).count();
  }

  RegisterTransformerInstructionSpy getRegisterTransformerInstructionSpy();
}

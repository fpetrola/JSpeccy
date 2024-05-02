package com.fpetrola.z80.instructions.tests;

import com.fpetrola.z80.instructions.base.TransformInstructionsTest;
import com.fpetrola.z80.opcodes.references.WordNumber;

public abstract class ManualBytecodeGenerationTest<T extends WordNumber> extends TransformInstructionsTest<T> implements BytecodeGenerationTest {
  protected String classFile = "JSW.class";

  public String generateAndDecompile() {
    return getDecompiledSource(0, addedInstructions, currentContext.pc(),
        (address) -> currentContext.getTransformedInstructionAt(address),
        getRegisterTransformerInstructionSpy(), classFile);
  }
}

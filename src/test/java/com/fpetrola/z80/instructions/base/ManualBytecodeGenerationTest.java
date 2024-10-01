package com.fpetrola.z80.instructions.base;

import com.fpetrola.z80.opcodes.references.WordNumber;

public abstract class ManualBytecodeGenerationTest<T extends WordNumber> extends TransformInstructionsTest<T> implements BytecodeGenerationTest {
  public String generateAndDecompile() {
    return generateAndDecompile("");
  }

  @Override
  public String generateAndDecompile(String base64Memory) {
    return getDecompiledSource(0, addedInstructions, currentContext.pc(),
        (address) -> currentContext.getTransformedInstructionAt(address),
        getRegisterTransformerInstructionSpy(), "JSW", base64Memory);
  }
}

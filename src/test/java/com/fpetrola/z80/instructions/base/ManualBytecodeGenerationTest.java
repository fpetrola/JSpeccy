package com.fpetrola.z80.instructions.base;

import com.fpetrola.z80.bytecode.BytecodeGeneration;
import com.fpetrola.z80.opcodes.references.WordNumber;

public abstract class ManualBytecodeGenerationTest<T extends WordNumber> extends TransformInstructionsTest<T> implements BytecodeGeneration {
  public String generateAndDecompile() {
    return generateAndDecompile("");
  }

  @Override
  public String generateAndDecompile(String base64Memory) {
    return getDecompiledSource(currentContext.pc(),
        (address) -> currentContext.getTransformedInstructionAt(address),
        "JSW", base64Memory);
  }
}

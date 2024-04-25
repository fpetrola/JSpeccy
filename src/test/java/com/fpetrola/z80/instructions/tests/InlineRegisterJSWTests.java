package com.fpetrola.z80.instructions.tests;

import com.fpetrola.z80.instructions.*;
import com.fpetrola.z80.opcodes.references.WordNumber;
import org.junit.Assert;
import org.junit.Test;

import static com.fpetrola.z80.registers.RegisterName.*;

@SuppressWarnings("ALL")

public class InlineRegisterJSWTests<T extends WordNumber> extends BytecodeGenerationTransformInstructionsTests<T> {
  @Test
  public void testJSW2() {
    Assert.assertEquals("""
public class JSW {
   public int[] memory;

   public void $0() {
   }
}
""", generateAndDecompile());
  }
}

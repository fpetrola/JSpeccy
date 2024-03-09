package com.fpetrola.z80.instructions.tests;

import com.fpetrola.z80.instructions.BaseInstructionLoopTest;
import com.fpetrola.z80.opcodes.references.WordNumber;
import org.junit.Before;

public class TransformInstructionsTests<T extends WordNumber> extends BaseInstructionLoopTest<T> {
  protected int memPosition = 1000;

  @Before
  public void setUp() {
    super.setUp();
    useSecond();
  }
}

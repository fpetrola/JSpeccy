package com.fpetrola.z80.instructions.tests;

import com.fpetrola.z80.opcodes.references.WordNumber;
import org.junit.Assert;
import org.junit.Test;

@SuppressWarnings("ALL")
public class RealCodeTransformationsInstructionsTests2<T extends WordNumber> extends RealCodeTestBase<T> {

  @Test
  public void testJSW1() {
//    value4.setRecentFile0("/home/fernando/detodo/desarrollo/m/zx/zx/jsw.z80");
//    value4.setRecentFile1("/home/fernando/detodo/desarrollo/m/zx/zx/emlyn.z80");
//    value4.setRecentFile2("/home/fernando/detodo/desarrollo/m/zx/zx/tge.z80");
//
    startAddress = 37056;
    endAddress = 37307;

    setUpMemory("/home/fernando/detodo/desarrollo/m/zx/zx/jsw.z80");

    stepUntilComplete();

    Assert.assertEquals("""
        public class JSW {
           public int[] memory;

           public void $90C0() {
              // $FF: Couldn't be decompiled
           }
        }
        """, generateAndDecompile());
  }

}

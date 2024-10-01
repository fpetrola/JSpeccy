package com.fpetrola.z80.instructions.tests;

import com.fpetrola.z80.instructions.base.RealCodeBytecodeCreationTestsBase;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.routines.Routine;
import com.fpetrola.z80.routines.RoutineManager;
import com.fpetrola.z80.transformations.Base64Utils;
import com.fpetrola.z80.transformations.RegisterTransformerInstructionSpy;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

@SuppressWarnings("ALL")
public class JSWBytecodeCreationTests<T extends WordNumber> extends RealCodeBytecodeCreationTestsBase<T> {
//    value4.setRecentFile0("/home/fernando/detodo/desarrollo/m/zx/zx/jsw.z80");
//    value4.setRecentFile1("/home/fernando/detodo/desarrollo/m/zx/zx/emlyn.z80");
//    value4.setRecentFile2("/home/fernando/detodo/desarrollo/m/zx/zx/tge.z80");


  private RoutineManager routineManager = RegisterTransformerInstructionSpy.routineFinder.routineManager;

  @Test
  public void testJSWMoveWilly() {
    startAddress = 35090;
    endAddress = 38621;
    setUpMemory("/home/fernando/detodo/desarrollo/m/zx/zx/jsw.z80");

    String base64Memory = getBase64Memory();

    //startAddress = state.getPc().read().intValue();
    firstAddress = startAddress;

    System.out.println(state.getMemory().read(WordNumber.createValue(34480)).intValue());

    stepUntilComplete();

    String actual = generateAndDecompile(base64Memory);
    List<Routine> routines = routineManager.getRoutines();

    Assert.assertEquals(""" 
        
        """, actual);

  }

  private String getBase64Memory() {
    WordNumber[] data1 = state.getMemory().getData();
    int ramEnd = 65536;
    byte[] data = new byte[ramEnd];
    Arrays.fill(data, (byte) 0);

    for (int i = 0; i < ramEnd; i++) {
      WordNumber wordNumber = data1[i];
      int i1 = wordNumber == null ? 0 : wordNumber.intValue();
      data[i] = (byte) i1;
    }
    String memoryInBase64 = Base64Utils.gzipArrayCompressToBase64(data);
    return memoryInBase64;
  }

}

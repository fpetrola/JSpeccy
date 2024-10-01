package com.fpetrola.z80.instructions.tests;

import com.fpetrola.z80.instructions.base.RealCodeBytecodeCreationTestsBase;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.routines.Routine;
import com.fpetrola.z80.routines.RoutineManager;
import com.fpetrola.z80.transformations.RegisterTransformerInstructionSpy;
import org.junit.Assert;
import org.junit.Test;

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

    //startAddress = state.getPc().read().intValue();
    firstAddress = startAddress;

    System.out.println(state.getMemory().read(WordNumber.createValue(34480)).intValue());

    stepUntilComplete();

    String actual = generateAndDecompile();
    List<Routine> routines = routineManager.getRoutines();

    Assert.assertEquals(""" 
        
        """, actual);

  }

}

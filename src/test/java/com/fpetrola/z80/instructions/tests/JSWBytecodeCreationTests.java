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
  private RoutineManager routineManager = RegisterTransformerInstructionSpy.routineFinder.routineManager;

  @Test
  public void testJSWMoveWilly() {
    startAddress = 35090;
    endAddress = 38621;
    firstAddress = startAddress;
    setUpMemory("/home/fernando/detodo/desarrollo/m/zx/zx/jsw.z80");
    String base64Memory = getBase64Memory();
    stepUntilComplete();

    String actual = generateAndDecompile(base64Memory);
    List<Routine> routines = routineManager.getRoutines();

    Assert.assertEquals(""" 
        
        """, actual);
  }

  @Test
  public void testTranslateWillyToJava() {
    startAddress = 35090;
    firstAddress = startAddress;
    setUpMemory("/home/fernando/detodo/desarrollo/m/zx/zx/jsw.z80");
    String base64Memory = getBase64Memory();
    stepUntilComplete();
    translateToJava("JetSetWilly", base64Memory, "$34762");
  }
}

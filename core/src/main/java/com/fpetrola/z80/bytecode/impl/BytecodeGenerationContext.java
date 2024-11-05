package com.fpetrola.z80.bytecode.impl;

import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.routines.RoutineManager;
import org.cojen.maker.ClassMaker;
import org.cojen.maker.MethodMaker;

import java.util.HashMap;
import java.util.Map;

public class BytecodeGenerationContext {
  public RoutineManager routineManager;
  public boolean optimize16Convertion = false;
  public ClassMaker cm;
  public Map<String, MethodMaker> methods;
  public Register<WordNumber> pc;
  public boolean syncEnabled;
  public  boolean useFields;

  public BytecodeGenerationContext(RoutineManager routineManager, ClassMaker classMaker, Register<?> pc1, HashMap<String, MethodMaker> methods, boolean syncEnabled, boolean useFields) {
    this.routineManager = routineManager;
    this.cm = classMaker;
    this.pc = (Register<WordNumber>) pc1;
    this.methods = methods;
    this.syncEnabled = syncEnabled;
    this.useFields = useFields;
  }
}
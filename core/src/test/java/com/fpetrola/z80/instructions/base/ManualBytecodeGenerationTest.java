package com.fpetrola.z80.instructions.base;

import com.fpetrola.z80.bytecode.BytecodeGeneration;
import com.fpetrola.z80.bytecode.examples.RemoteZ80Translator;
import com.fpetrola.z80.bytecode.se.SymbolicExecutionAdapter;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.routines.Routine;
import com.fpetrola.z80.routines.RoutineManager;

import java.util.List;

public abstract class ManualBytecodeGenerationTest<T extends WordNumber> extends TransformInstructionsTest<T> implements BytecodeGeneration {
  public String generateAndDecompile() {
    return generateAndDecompile("", RemoteZ80Translator.getRoutines(), ".", "JetSetWilly");
  }

  @Override
  public String generateAndDecompile(String base64Memory, List<Routine> routines, String targetFolder, String className1) {
    SymbolicExecutionAdapter.mutantAddress.clear();
    return getDecompiledSource(currentContext.pc(),
        (address) -> currentContext.getTransformedInstructionAt(address),
        "JSW", base64Memory, routines, ".");
  }

  @Override
  public RoutineManager getRoutineManager() {
    return new RoutineManager();
  }
}

package com.fpetrola.z80.instructions;

import com.fpetrola.z80.blocks.ByteCodeGenerator;
import com.fpetrola.z80.helpers.Helper;
import com.fpetrola.z80.instructions.base.TargetSourceInstruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterName;
import org.cojen.maker.Field;
import org.cojen.maker.MethodMaker;
import org.cojen.maker.Variable;

public class Ld<T extends WordNumber> extends TargetSourceInstruction<T> {

  public Ld(State state, OpcodeReference<T> target, OpcodeReference<T> source) {
    super(state, target, source);
  }

  public int execute() {
    T value = source.read();
    target.write(value);
    return cyclesCost;
  }
}

package com.fpetrola.z80.opcodes.decoder.table;

import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WritableOpcodeReference;

public interface RotFactory {
  Instruction create(OpcodeReference target, int valueDelta);
}

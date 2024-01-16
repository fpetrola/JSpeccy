package com.fpetrola.z80.opcodes.decoder;

import com.fpetrola.z80.opcodes.references.Instruction;

public interface OpCodeDecoder {
  Instruction[] getOpcodeLookupTable();
}
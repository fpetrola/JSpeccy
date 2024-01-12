package com.fpetrola.z80.opcodes;

import com.fpetrola.z80.instructions.OpCode;

public interface OpCodeDecoder {
  OpCode[] getOpcodeLookupTable();
}
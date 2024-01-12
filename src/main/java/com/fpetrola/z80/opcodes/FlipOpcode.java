package com.fpetrola.z80.opcodes;

import com.fpetrola.z80.instructions.OpCode;

public interface FlipOpcode extends OpCode{
  int getIncPc();
  OpCode[] getTable();
}
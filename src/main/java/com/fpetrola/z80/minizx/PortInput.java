package com.fpetrola.z80.minizx;

import com.fpetrola.z80.opcodes.references.WordNumber;

public class PortInput {
  public WordNumber result;
  public WordNumber port;

  public PortInput(WordNumber port, WordNumber in) {
    this.port = port;
    this.result = in;
  }
}

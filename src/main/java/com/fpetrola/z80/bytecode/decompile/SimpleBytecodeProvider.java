package com.fpetrola.z80.bytecode.decompile;

import org.jetbrains.java.decompiler.main.extern.IBytecodeProvider;

import java.io.IOException;

public class SimpleBytecodeProvider implements IBytecodeProvider {
  private final byte[] bytecode;

  public SimpleBytecodeProvider(byte[] bytecode) {

    this.bytecode = bytecode;
  }

  public byte[] getBytecode(String externalPath, String internalPath) throws IOException {
    return bytecode;
  }
}

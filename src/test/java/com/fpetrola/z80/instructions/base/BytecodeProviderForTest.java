package com.fpetrola.z80.instructions.base;

import org.jetbrains.java.decompiler.main.extern.IBytecodeProvider;

import java.io.IOException;

public class BytecodeProviderForTest implements IBytecodeProvider {
  private final byte[] bytecode;

  public BytecodeProviderForTest(byte[] bytecode) {

    this.bytecode = bytecode;
  }

  public byte[] getBytecode(String externalPath, String internalPath) throws IOException {
    return bytecode;
  }
}

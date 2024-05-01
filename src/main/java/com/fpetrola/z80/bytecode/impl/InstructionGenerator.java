package com.fpetrola.z80.bytecode.impl;

public record InstructionGenerator(Runnable labelGenerator, Runnable instructionGenerator) {
}

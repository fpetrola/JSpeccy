package com.fpetrola.z80.bytecode.impl;

public record InstructionGenerator(Runnable scopeAdjuster, Runnable labelGenerator, Runnable instructionGenerator) {
}

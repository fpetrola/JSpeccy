package com.fpetrola.z80.blocks;

public record InstructionGenerator(Runnable labelGenerator, Runnable instructionGenerator) {
}

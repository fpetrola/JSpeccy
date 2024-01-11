package com.fpetrola.z80;

import java.util.BitSet;

import com.fpetrola.z80.instructions.FlagRegister;
import com.fpetrola.z80.instructions.OpcodesSpy;
import com.fpetrola.z80.mmu.IO;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.registers.Composed16BitRegister;
import com.fpetrola.z80.registers.Plain16BitRegister;
import com.fpetrola.z80.registers.RegisterBank;

import z80core.Z80;
import z80core.Z80.IntMode;

public class StateImpl extends State {

  public StateImpl(Z80B z80, OpcodesSpy spy, Memory memory, IO io) {
    super();
    init(createBank(z80, this), spy, memory, io);
  }

  private static RegisterBank createBank(Z80B z80, State state) {
    Composed16BitRegister af = new Composed16BitRegister("A", (v) -> z80.setRegA(v), () -> z80.getRegA(), new FlagRegister("F", (v) -> z80.setFlags(v), () -> (z80.getFlags() & 0xD7)));
    Composed16BitRegister bc = new Composed16BitRegister("B", "C", (v) -> z80.setRegB(v), () -> z80.getRegB(), (v) -> z80.setRegC(v), () -> z80.getRegC());
    Composed16BitRegister de = new Composed16BitRegister("D", "E", (v) -> z80.setRegD(v), () -> z80.getRegD(), (v) -> z80.setRegE(v), () -> z80.getRegE());
    Composed16BitRegister hl = new Composed16BitRegister("H", "L", (v) -> z80.setRegH(v), () -> z80.getRegH(), (v) -> z80.setRegL(v), () -> z80.getRegL());
    Composed16BitRegister _af = new Composed16BitRegister("A'", "F'", (v) -> z80.setRegAx(v), () -> z80.getRegAx(), (v) -> z80.setRegFx(v), () -> z80.getRegFx());
    Composed16BitRegister _bc = new Composed16BitRegister("B'", "C'", (v) -> z80.setRegBx(v), () -> z80.getRegBx(), (v) -> z80.setRegCx(v), () -> z80.getRegCx());
    Composed16BitRegister _de = new Composed16BitRegister("D'", "E'", (v) -> z80.setRegDx(v), () -> z80.getRegDx(), (v) -> z80.setRegEx(v), () -> z80.getRegEx());
    Composed16BitRegister _hl = new Composed16BitRegister("H'", "L'", (v) -> z80.setRegHx(v), () -> z80.getRegHx(), (v) -> z80.setRegLx(v), () -> z80.getRegLx());
    Composed16BitRegister ix = new Composed16BitRegister("IXh", "IXl", (v) -> z80.setRegIX(v), () -> z80.getRegIX());
    Composed16BitRegister iy = new Composed16BitRegister("IYh", "IYl", (v) -> z80.setRegIY(v), () -> z80.getRegIY());
    Composed16BitRegister ir = new Composed16BitRegister("I", "R", (v) -> z80.setRegI(v), () -> z80.getRegI(), (v) -> z80.setRegR(v & 0x7f), () -> z80.getRegR() & 0x7f);

    Plain16BitRegister pc = new Plain16BitRegister("PC") {
      public int readFromRealEmulator() {
        int result = z80.getRegPC();
        write(result);
        return result;
      };
    };
    Plain16BitRegister sp = new Plain16BitRegister("SP") {
      public int readFromRealEmulator() {
        int result = z80.getRegSP();
        write(result);
        return result;
      };

      public void decrement(int by) {
        data = (data - by) & 0xFFFF;
      }
    };

    Plain16BitRegister memptr = new Plain16BitRegister("MEMPTR") {
      public int readFromRealEmulator() {
        int result = z80.getMemPtr();
        write(result);
        return result;
      };

      public void increment(int by) {
        data = (data + by) & 0xFFFF;
      }

      public void decrement(int by) {
        data = (data - by) & 0xFFFF;
      }
    };

    return new RegisterBank(af, bc, de, hl, _af, _bc, _de, _hl, pc, sp, ix, iy, ir, memptr);
  }
}

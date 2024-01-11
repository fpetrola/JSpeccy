package jmce;

import jmce.sim.cpu.MultiOpcode;

public class InvertedMultiOpcode extends MultiOpcode {

  public InvertedMultiOpcode(int opcode) {
    super(opcode);
  }

  protected int fetchAddres(int pc) {
    return pc + 1;
  }
}

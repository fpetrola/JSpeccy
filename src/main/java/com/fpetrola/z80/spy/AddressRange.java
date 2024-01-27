package com.fpetrola.z80.spy;

import com.fpetrola.z80.OOZ80;

public class AddressRange {
  private ExecutionStepData lastStep;
  private int firstAddress = Integer.MAX_VALUE;
  private int lastAddress = 0;
  int distance = 10;

  public AddressRange() {
  }

  public AddressRange(int address, ExecutionStepData firstStep) {
    add(address, firstStep);
  }

  public String getName() {
    return "[" + OOZ80.convertToHex(firstAddress) + "-" + OOZ80.convertToHex(lastAddress) + "]";
  }

  public boolean canAdd(int address, ExecutionStepData step) {
    if (lastStep == null)
      return true;
    else if (isInside(address))
      return true;
    else if (Math.abs(firstAddress - address) < distance || Math.abs(lastAddress - address) < distance)
      return true;
    else
      return false;

  }

  public void add(int address, ExecutionStepData step) {
    lastStep = step;
    if (address < firstAddress)
      firstAddress = address;

    if (address > lastAddress)
      lastAddress = address;
  }

  public boolean mergeIfRequired(AddressRange b) {
    boolean merged = isInside(b.firstAddress) || isInside(b.lastAddress);

    if (merged) {
      firstAddress = Math.min(firstAddress, b.firstAddress);
      lastAddress = Math.min(lastAddress, b.lastAddress);
    }

    return merged;
  }

  private boolean isInside(int address) {
    return (address >= firstAddress && address <= lastAddress);
  }

}

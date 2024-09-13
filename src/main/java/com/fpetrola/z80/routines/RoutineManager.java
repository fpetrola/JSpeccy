package com.fpetrola.z80.routines;

import com.fpetrola.z80.blocks.Block;
import com.fpetrola.z80.blocks.BlocksManager;
import com.fpetrola.z80.blocks.CodeBlockType;
import com.fpetrola.z80.blocks.UnknownBlockType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoutineManager {
  public static BlocksManager blocksManager;
  List<Routine> routines = new ArrayList<>();
  private int firstAddress;

  public RoutineManager(BlocksManager blocksManager) {
    this.blocksManager = blocksManager;
  }

  public Routine findRoutineAt(int address) {
    Optional<Routine> first = routines.stream().filter(r -> r.contains(address)).findFirst();
    if (first.isPresent()) {
      Optional<Routine> b = first.get().innerRoutines.stream().filter(i -> i.contains(address)).findFirst();
      if (b.isPresent())
        return b.get();
    }
    return first.orElse(null);
  }

  public Routine addRoutine(Routine routine) {
    routines.add(routine);
    routine.setRoutineManager(this);
    return routine;
  }

  public List<Routine> getRoutines() {
    return new ArrayList<Routine>(routines);
  }

  public void optimizeAll() {
    routines.forEach(Routine::optimize);
  }

  public Routine createRoutine(int startAddress, int length) {
    Block foundBlock = RoutineManager.blocksManager.findBlockAt(startAddress);
    if (foundBlock.getRangeHandler().getStartAddress() != startAddress) {
      foundBlock.split(startAddress + length - 1);
      foundBlock = foundBlock.split(startAddress - 1, CodeBlockType.class);
    } else {
      Block blockAt1 = foundBlock.split(startAddress, UnknownBlockType.class);
      foundBlock.setType(new CodeBlockType());
    }

    return addRoutine(new Routine(foundBlock));
  }
}

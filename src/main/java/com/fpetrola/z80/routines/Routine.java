package com.fpetrola.z80.routines;

import com.fpetrola.z80.blocks.Block;
import com.fpetrola.z80.blocks.CodeBlockType;
import com.fpetrola.z80.blocks.UnknownBlockType;
import com.fpetrola.z80.instructions.MemoryAccessOpcodeReference;
import com.fpetrola.z80.instructions.base.DummyInstructionVisitor;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.instructions.base.TargetInstruction;
import com.fpetrola.z80.instructions.base.TargetSourceInstruction;
import com.fpetrola.z80.opcodes.references.*;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.transformations.Virtual8BitsRegister;
import com.fpetrola.z80.transformations.VirtualRegister;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

import java.util.*;

public class Routine {
  public List<Block> blocks;
  public boolean finished;
  public Map<Integer, Integer> virtualPop = new HashMap<>();
  private List<Instruction> instructions = new ArrayList<>();
  public Set<Routine> innerRoutines = new HashSet<>();
  private RoutineManager routineManager;

  public MultiValuedMap<Integer, Integer> returnPoints = new HashSetValuedHashMap<>();
  public Set<String> parameters = new HashSet<>();
  public Set<Register> returnValues = new HashSet<>();

  public Routine() {
  }

  public Routine(Block block) {
    this.blocks = new ArrayList<>(Arrays.asList(block));
  }

  public void addInstruction(Instruction instruction) {
    instructions.add(instruction);
  }

  public boolean contains(int address) {
    return blocks.stream().anyMatch(b -> b.contains(address));
  }

  public void addInnerRoutine(Routine routine) {
    innerRoutines.add(routine);
  }

  public void growTo(int address, int length) {
    Block nearestBlock = findNearestBlock(address);
    nearestBlock.growBlockTo(address + length - 1);
  }

  public Block findNearestBlock(int address) {
    return blocks.stream().filter(b -> b.canTake(address)).findFirst().orElse(null);
  }

  @Override
  public String toString() {
    return blocks.toString();
  }

  public void addBlock(Block block) {
    if (blocks.contains(block)) {
      System.out.print("");
      // throw new RuntimeException("block already added");
    } else
      blocks.add(block);
  }

  public void optimize() {
    {
//      if (finished) {
//        if (blocks.size() > 1)
//          System.out.println("finished");
//      }
      blocks.sort(Comparator.comparingInt(b -> b.getRangeHandler().getStartAddress()));
      List<Block> blocksInReverse = new ArrayList<>(blocks);
      Collections.reverse(blocksInReverse);
      blocksInReverse.forEach(block -> {
        Block previousBlock = block.getRangeHandler().getPreviousBlock();
        if (blocks.contains(previousBlock))
          if (previousBlock.isAdjacent(block) && previousBlock.getBlockType() instanceof CodeBlockType) {
            ArrayList<Routine> inner = new ArrayList<>(innerRoutines);
            if (inner.isEmpty() || (isNotInner(previousBlock) && isNotInner(block))) {
              previousBlock.join(block);
              blocks.remove(block);
            }
          }
      });

      innerRoutines.stream().forEach(ir -> {
        parameters.addAll(ir.parameters);
      });
    }
  }

  private boolean isNotInner(Block block) {
    return innerRoutines.stream().noneMatch(i -> i != null && i.contains(block));
  }

  private boolean contains(Block block) {
    return blocks.contains(block);
  }

  private boolean noneContaining(Block block, ArrayList<Routine> inner) {
    return inner.stream().allMatch(i -> !i.contains(block.getRangeHandler().getStartAddress()));
  }

  public Routine split(int address) {
    Routine[] result = new Routine[1];
    Optional<Block> first = blocks.stream().filter(b -> b.contains(address)).findFirst();
    if (first.get().getRangeHandler().getStartAddress() < address) {
      first.ifPresent(b -> {
        Block split = b.split(address - 1);
        addBlock(split);
        Routine routine = new Routine(split);
        addInnerRoutine(routine);
        result[0] = routine;
      });
      routineManager.addRoutine(result[0]);
    } else {
      Routine routine = new Routine(first.get());
      result[0] = routine;
      addInnerRoutine(result[0]);
      routineManager.addRoutine(routine);
    }
    return result[0];
  }

  void addInstructionAt(Instruction instruction, int pcValue) {
    if (!finished) {
      Block currentBlock = RoutineManager.blocksManager.findBlockAt(pcValue);
      if (currentBlock.getBlockType() instanceof UnknownBlockType) {
        currentBlock.split(pcValue + instruction.getLength() - 1);
        Block blockAt2 = currentBlock.split(pcValue - 1);
        blockAt2.setType(new CodeBlockType());
        addBlock(blockAt2);
      } else {
        Routine routineAt = routineManager.findRoutineAt(pcValue);
        if (routineAt != this && !innerRoutines.contains(routineAt)) {
          routineAt.blocks.forEach(this::addBlock);
          addInnerRoutine(routineAt);
        }
      }
    } else if (contains(pcValue))
      System.out.hashCode();
    else
      System.out.hashCode();

    instruction.accept(new DummyInstructionVisitor<>() {
      private boolean isTarget;

      public void visitingTarget(OpcodeReference target, TargetInstruction targetInstruction) {
        isTarget = true;
        target.accept(this);
      }

      public void visitingSource(ImmutableOpcodeReference source, TargetSourceInstruction targetSourceInstruction) {
        if (getStartAddress() == 38562)
          System.out.println("sdgsdsdhhsdh!");
        isTarget = false;
        source.accept(this);
      }

      public void visitIndirectMemory8BitReference(IndirectMemory8BitReference indirectMemory8BitReference) {
        indirectMemory8BitReference.target.accept(this);
      }

      public void visitIndirectMemory16BitReference(IndirectMemory16BitReference indirectMemory16BitReference) {
        indirectMemory16BitReference.target.accept(this);
      }

      public void visitMemoryAccessOpcodeReference(MemoryAccessOpcodeReference memoryAccessOpcodeReference) {
        memoryAccessOpcodeReference.getC().accept(this);
      }

      public void visitMemoryPlusRegister8BitReference(MemoryPlusRegister8BitReference memoryPlusRegister8BitReference) {
        memoryPlusRegister8BitReference.getTarget().accept(this);
      }

      public boolean visitRegister(Register register) {
        VirtualRegister virtualRegister = (VirtualRegister) register;

        addReturnValues(virtualRegister);
        addParameters(virtualRegister);

        return super.visitRegister(register);
      }

      private void addParameters(VirtualRegister virtualRegister) {
        List<VirtualRegister> previousVersions = virtualRegister.getPreviousVersions();
        boolean isParameter = previousVersions.stream().anyMatch(virtualRegister1 -> {
          int registerLine = virtualRegister1.getRegisterLine();
          Routine routineAt = routineManager.findRoutineAt(registerLine);
          return routineAt != Routine.this;
        });
        if (isParameter)
          addParameter(virtualRegister);
      }

      private void addReturnValues(VirtualRegister virtualRegister) {
        List<VirtualRegister> dependants = virtualRegister.getDependants();
        boolean isReturnValue = dependants.stream().anyMatch(virtualRegister1 -> {
          int registerLine0 = virtualRegister.getRegisterLine();
          Routine routineAt0 = routineManager.findRoutineAt(registerLine0);
          int registerLine = virtualRegister1.getRegisterLine();
          Routine routineAt = routineManager.findRoutineAt(registerLine);
          boolean b = routineAt != routineAt0;
          boolean[] isReturnValue2 = new boolean[]{false};
          if (b) {
            if (virtualRegister1 instanceof Virtual8BitsRegister<?> virtual8BitsRegister) {
              virtual8BitsRegister.instruction.accept(new DummyInstructionVisitor() {
                public void visitingSource(ImmutableOpcodeReference source, TargetSourceInstruction targetSourceInstruction) {
                  source.accept(this);
                }

                public void visitIndirectMemory8BitReference(IndirectMemory8BitReference indirectMemory8BitReference) {
                  indirectMemory8BitReference.target.accept(this);
                }

                public void visitIndirectMemory16BitReference(IndirectMemory16BitReference indirectMemory16BitReference) {
                  indirectMemory16BitReference.target.accept(this);
                }

                public void visitMemoryAccessOpcodeReference(MemoryAccessOpcodeReference memoryAccessOpcodeReference) {
                  memoryAccessOpcodeReference.getC().accept(this);
                }

                public void visitMemoryPlusRegister8BitReference(MemoryPlusRegister8BitReference memoryPlusRegister8BitReference) {
                  memoryPlusRegister8BitReference.getTarget().accept(this);
                }

                public boolean visitRegister(Register register) {
                  VirtualRegister virtualRegister2 = (VirtualRegister) register;
                  boolean b1 = virtualRegister.getVersionHandler().versions.getFirst() == virtualRegister2.getVersionHandler().versions.getFirst();
                  if (b1) {
                    isReturnValue2[0] = b1;
                  }
                  return b1;
                }
              });
            }
          }
          return isReturnValue2[0];
        });
        if (isReturnValue)
          addReturnValue(virtualRegister);
      }
    });
  }

  private void addParameter(VirtualRegister register) {
    VirtualRegister o = (VirtualRegister) register.getVersionHandler().versions.getFirst();
    parameters.add(o.getName());
  }

  private void addReturnValue(VirtualRegister register) {
    VirtualRegister o = (VirtualRegister) register.getVersionHandler().versions.getFirst();
    returnValues.add(o);
  }

  public void setRoutineManager(RoutineManager routineManager) {
    this.routineManager = routineManager;
  }

  public int getStartAddress() {
    return blocks.stream().map(b -> b.getRangeHandler().getStartAddress()).min(Comparator.comparingInt(b -> b)).get();
  }

  public int getEndAddress() {
    return blocks.stream().map(b -> b.getRangeHandler().getEndAddress()).max(Comparator.comparingInt(b -> b)).get();
  }

  public void addReturnPoint(int returnAddress, int pc) {
    returnPoints.put(returnAddress, pc);
  }

  public void finish() {
    optimize();
    finished = true;
  }

  public <R> R accept(RoutineVisitor<R> routineVisitor) {
    routineVisitor.visit(this);
    List<String> list = Arrays.asList("AF", "BC", "DE", "HL", "IX", "IY", "A", "F", "B", "C", "D", "E", "H", "L", "IXL", "IXH", "IYL", "IYH");
    list.stream().forEachOrdered(routineVisitor::visitParameter);
//    parameters.stream().forEachOrdered(routineVisitor::visitParameter);
//    routineVisitor.visitParameter("B");
    list.stream().forEachOrdered(routineVisitor::visitReturnValue);
    //returnValues.stream().forEachOrdered(routineVisitor::visitReturnValue);
    return routineVisitor.getResult();
  }
}

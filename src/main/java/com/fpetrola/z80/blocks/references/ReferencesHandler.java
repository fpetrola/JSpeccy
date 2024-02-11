package com.fpetrola.z80.blocks.references;

import com.fpetrola.z80.blocks.AbstractBlock;
import com.fpetrola.z80.blocks.Block;
import com.fpetrola.z80.blocks.BlocksManager;
import com.fpetrola.z80.blocks.DataBlock;
import com.fpetrola.z80.blocks.spy.RoutineGrouperSpy;
import com.fpetrola.z80.helpers.Helper;
import com.fpetrola.z80.jspeccy.MemoryReadListener;
import com.fpetrola.z80.metadata.DataStructure;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.opcodes.references.ExecutionPoint;
import com.fpetrola.z80.opcodes.references.TraceableWordNumber;
import com.fpetrola.z80.opcodes.references.WordNumber;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

import javax.swing.*;
import java.util.*;
import java.util.stream.Collectors;

public class ReferencesHandler {
  private final AbstractBlock associatedBlock;
  private BlocksManager blocksManager;
  private MultiValuedMap<Integer, BlockRelation> relationsBySourceAddress = new HashSetValuedHashMap<>();
  Map<Long, DataStructure> dataStructures = new HashMap<>();

  public ReferencesHandler(AbstractBlock associatedBlock) {
    this.associatedBlock = associatedBlock;
    this.blocksManager = associatedBlock.getBlocksManager();
  }

  private void removeBlockRelations(Collection<BlockRelation> newBlockRelations) {
    new ArrayList<>(newBlockRelations).forEach(r -> removeBlockRelation(r));
  }

  void removeBlockRelation(BlockRelation blockRelation) {
    boolean mine = isMine(blockRelation);
    int source = mine ? blockRelation.getSourceAddress() : blockRelation.getTargetAddress();
    int target = mine ? blockRelation.getTargetAddress() : blockRelation.getSourceAddress();

    Block otherBlock = blocksManager.findBlockAt(target);

    otherBlock.getReferencesHandler().relationsBySourceAddress.get(target).remove(blockRelation);
    relationsBySourceAddress.get(source).remove(blockRelation);

    if (mine)
      blocksManager.getBlockChangesListener().removingKnownBlock(associatedBlock, otherBlock);
  }

  Collection<BlockRelation> getRelations() {
    return relationsBySourceAddress.values();
  }

  public Map<Long, DataStructure> getFoundStructures() {
    return dataStructures;
  }

  public void addBlockRelations(Collection<BlockRelation> blockRelations) {
    blockRelations.forEach(r -> addBlockRelation(r));
  }

  private Set<Block> getReferencedByBlocks() {
    return getRelations().stream().map(r -> blocksManager.findBlockAt(r.getSourceAddress())).collect(Collectors.toSet());
  }

  public void addBlockRelation(BlockRelation blockRelation) {
    Collection<BlockRelation> blockRelations = relationsBySourceAddress.get(blockRelation.getSourceAddress());
    if (blockRelations.size() > 100)
      return;

    if (!blockRelations.isEmpty()) {
      for (BlockRelation r : blockRelations) {
        if (r.equals(blockRelation)) {
          r.addInCycle(blocksManager.getCycle(), blocksManager.getExecutionNumber());
          return;
        }
      }
    }

    boolean mine = isMine(blockRelation);
    int source = mine ? blockRelation.getSourceAddress() : blockRelation.getTargetAddress();
    int target = mine ? blockRelation.getTargetAddress() : blockRelation.getSourceAddress();

    Block otherBlock = blocksManager.findBlockAt(target);

//    if (!associatedBlock.contains(source))
//      System.out.println("dagadg");
//
//    if (!otherBlock.contains(target))
//      System.out.println("dagadg");

    otherBlock.getReferencesHandler().relationsBySourceAddress.get(target).add(blockRelation);
    relationsBySourceAddress.get(source).add(blockRelation);

    if (mine)
      blocksManager.getBlockChangesListener().addingKnownBLock(associatedBlock, otherBlock, blockRelation.getSourceAddress());
  }

  private boolean isMine(BlockRelation e) {
    return associatedBlock.contains(e.getSourceAddress());
  }

  public void joinReferences(Block otherBlock) {
    ReferencesHandler otherBlockReferencesHandler = otherBlock.getReferencesHandler();
    MultiValuedMap<Integer, BlockRelation> otherRelationsBySourceAddress = otherBlockReferencesHandler.relationsBySourceAddress;
    Collection<Map.Entry<Integer, BlockRelation>> entries = new ArrayList<>(otherRelationsBySourceAddress.entries());
    entries.stream().forEach(c -> addBlockRelation(c.getValue()));

    otherRelationsBySourceAddress.clear();
  }

  public <T extends Block> List<BlockRelation> splitReferences(T otherBlock) {
    Collection<Map.Entry<Integer, BlockRelation>> entries = relationsBySourceAddress.entries();
    List<BlockRelation> newBlockRelations = new ArrayList<>();

    entries.stream()
        .filter(r1 -> otherBlock.contains(r1.getKey()))
        .forEach(r1 -> newBlockRelations.add(r1.getValue()));

    removeBlockRelations(newBlockRelations);
    return newBlockRelations;
  }

  public <T extends Block> void copyReferences(T block) {
    block.getReferencesHandler().relationsBySourceAddress = relationsBySourceAddress;
    relationsBySourceAddress = new ArrayListValuedHashMap<>();
  }

  public boolean isReferencing(Block block) {
    return getRelations().stream().anyMatch(r -> block.contains(r.getTargetAddress()));
  }

  public boolean isReferencedBy(Block block) {
    return getReferencedByBlocks().contains(block);
  }

  public List<Map.Entry<BlockRelation, ReferenceVersion>> findRelationsForCycle(int cycle) {
    Collection<Map.Entry<Integer, BlockRelation>> entries = relationsBySourceAddress.entries();
    Map<BlockRelation, ReferenceVersion> collect = new HashMap<>();
    entries.stream()
        .filter(e -> isMine(e.getValue()))
        .filter(e -> isDataBlock(e.getValue().getTargetAddress()))
        .forEach(e ->
            e.getValue().getVersions().stream().filter(v -> v.cycle == cycle).forEach(v -> collect.put(e.getValue(), v))
        );


//    System.out.println("---------------------------------------");
    List<Map.Entry<BlockRelation, ReferenceVersion>> entries1 = new ArrayList<>(collect.entrySet());

    Collections.sort(entries1, (o1, o2) -> (int) (o1.getValue().executionNumber - o2.getValue().executionNumber));

    entries1.stream().forEach(r -> System.out.println(Helper.convertToHex(r.getKey().getSourceAddress()) + " -> " + Helper.convertToHex(r.getKey().getTargetAddress()) + " = " + r.getValue()));
//
//    collect.stream().map(r -> r.getTargetAddress()).distinct().sorted().forEach(r -> System.out.println(Helper.convertToHex(r)));
//    System.out.println("---------------------------------------");

    return entries1;
  }

  public <T extends WordNumber> void removeDataObserver(Memory memory, RoutineGrouperSpy spy) {

  }

  public <T extends WordNumber> void installDataObserver(Memory memory, RoutineGrouperSpy spy) {
    Collection<Map.Entry<Integer, BlockRelation>> entries = relationsBySourceAddress.entries();
    Set<BlockRelation> blockRelations = new HashSet<>();
    entries.stream()
        .filter(e -> isMine(e.getValue()))
        .filter(e -> isDataBlock(e.getValue().getTargetAddress()))
        .forEach(e ->
            e.getValue().getVersions().stream().forEach(v -> blockRelations.add(e.getValue()))
        );
    List<ExecutionPoint> processedLasts = new ArrayList<>();

    memory.addMemoryReadListener((MemoryReadListener<T>) (address, value) -> {
      if (isDataBlock(address.intValue())) {
        if (blocksManager.findBlockAt(spy.getLastExecutionPoint().pc) == associatedBlock) {
          Collection<Map.Entry<Integer, BlockRelation>> entries1 = relationsBySourceAddress.entries();
          for (BlockRelation blockRelation : blockRelations) {
            if (blockRelation.getTargetAddress() == address.intValue()) {

              Collection<BlockRelation> blockRelations1 = relationsBySourceAddress.get(blockRelation.getSourceAddress());
              if (blockRelations1.size() < 100) {
                if (address instanceof TraceableWordNumber) {
                  TraceableWordNumber traceableWordNumber = (TraceableWordNumber) address;
                  TreeSet<ExecutionPoint> operationsAddresses = traceableWordNumber.getOperationsAddresses();
                  ExecutionPoint first = operationsAddresses.iterator().next();

                  DataStructure dataStructure = dataStructures.get(first.executionNumber);
                  if (dataStructure == null) {
                    dataStructures.put(first.executionNumber, dataStructure = new DataStructure());
                  }

                  ExecutionPoint last = operationsAddresses.descendingIterator().next();


                  boolean invalid = processedLasts.stream().anyMatch(l -> operationsAddresses.contains(l));

                  if (!invalid) {
//                    System.out.println("---------------------------------------");
//                    operationsAddresses.forEach(e-> System.out.println(e));
//                    System.out.println("---------------------------------------");
                    LinkedList<ExecutionPoint> spyExecutionPoints = spy.getExecutionPoints();

                    int firstIndex = spyExecutionPoints.indexOf(first);
                    int lastIndex = spyExecutionPoints.indexOf(last);

                    if (firstIndex != -1 && lastIndex != -1) {
                      int repetitions = 0;
                      for (int i = firstIndex; i < lastIndex; i++) {
                        if (spyExecutionPoints.get(i).pc == last.pc) {
                          repetitions++;
                        }
                      }

                      dataStructure.getInstance(repetitions).addAddress(address.intValue());
                    } else {
                      System.out.println("hola!: ");
                    }
                    processedLasts.add(last);
                  } else
                    System.out.println("hola!: ");
                }
              }
              return;
            }
          }
        }
      }
    });
  }

  private <T extends WordNumber> boolean isDataBlock(int address) {
    return blocksManager.findBlockAt(address) instanceof DataBlock;
  }
}
package com.fpetrola.z80.blocks.references;

import com.fpetrola.z80.blocks.AbstractBlock;
import com.fpetrola.z80.blocks.Block;
import com.fpetrola.z80.blocks.BlocksManager;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

import java.util.*;
import java.util.stream.Collectors;

public class ReferencesHandler {
  private final AbstractBlock associatedBlock;
  private BlocksManager blocksManager;
  private MultiValuedMap<Integer, BlockRelation> relationsBySourceAddress = new HashSetValuedHashMap<>();

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

  public void addBlockRelations(Collection<BlockRelation> blockRelations) {
    blockRelations.forEach(r -> addBlockRelation(r));
  }

  private Set<Block> getReferencedByBlocks() {
    return getRelations().stream().map(r -> blocksManager.findBlockAt(r.getSourceAddress())).collect(Collectors.toSet());
  }

  public void addBlockRelation(BlockRelation blockRelation) {
//    blockRelation.setExecutionNumber(blocksManager.getExecutionNumber());
    Collection<BlockRelation> blockRelations = relationsBySourceAddress.get(blockRelation.getSourceAddress());
    if (blockRelations.size() > 100)
      return;

    if (!blockRelations.isEmpty()) {
      for (BlockRelation r : blockRelations) {
        if (r.equals(blockRelation)) {
          r.addInCycle(blocksManager.getCycle());
          return;
        }
      }
    }

    boolean mine = isMine(blockRelation);
    int source = mine ? blockRelation.getSourceAddress() : blockRelation.getTargetAddress();
    int target = mine ? blockRelation.getTargetAddress() : blockRelation.getSourceAddress();

    Block otherBlock = blocksManager.findBlockAt(target);

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
}
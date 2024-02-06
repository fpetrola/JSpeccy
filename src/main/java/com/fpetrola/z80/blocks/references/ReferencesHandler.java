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
  protected Collection<BlockRelation> blockRelations = new HashSet<>();
  private BlocksManager blocksManager;
  private MultiValuedMap multiValuedMap = new HashSetValuedHashMap();
  private MultiValuedMap relationsByCycle = new ArrayListValuedHashMap();

  public ReferencesHandler(AbstractBlock associatedBlock) {
    this.associatedBlock = associatedBlock;
    this.blocksManager = associatedBlock.getBlocksManager();
  }

  private void removeBlockRelations(Collection<BlockRelation> newBlockRelations) {
    new ArrayList<>(newBlockRelations).forEach(r -> removeBlockRelation(r));
  }

  void removeBlockRelation(BlockRelation blockRelation) {
    blockRelations.remove(blockRelation);
    blockRelation.getTargetBlock().getReferencesHandler().getRelations().remove(blockRelation);

    if (isMine(blockRelation))
      blocksManager.getBlockChangesListener().removingKnownBlock(blockRelation.getSourceBlock(), blockRelation.getTargetBlock());
  }

  Collection<BlockRelation> getRelations() {
    return blockRelations;
  }

  void addBlockRelations(Collection<BlockRelation> references1) {
    references1.forEach(r -> addBlockRelation(r));
  }

  List<BlockRelation> replaceBlockInReferences(Collection<BlockRelation> references1, Block block, Block replaceBlock) {
    return references1.stream().map(r -> {
      if (r.getSourceBlock() == block) r.setSourceBlock(replaceBlock);
      if (r.getTargetBlock() == block) r.setTargetBlock(replaceBlock);
      return r;
    }).collect(Collectors.toList());
  }

  private Set<Block> getReferencedByBlocks() {
    return blockRelations.stream().map(r -> r.getSourceBlock()).collect(Collectors.toSet());
  }

  public void addBlockRelation(BlockRelation blockRelation) {
//    if (multiValuedMap.get(0x9204).stream().anyMatch(i-> ((Integer)i) == 0x8103))
//      System.out.println("dsagdgdg");
    blockRelation.setExecutionNumber(blocksManager.getExecutionNumber());

    relationsByCycle.put(blocksManager.getCycle(), blockRelation);

    if (!multiValuedMap.get(blockRelation.getSourceAddress()).contains(blockRelation.getTargetAddress())) {
      multiValuedMap.put(blockRelation.getSourceAddress(), blockRelation.getTargetAddress());
    }

    if (isMine(blockRelation)) {
      blockRelations.add(blockRelation);
      blockRelation.getTargetBlock().getReferencesHandler().getRelations().add(blockRelation);
      blocksManager.getBlockChangesListener().addingKnownBLock(blockRelation.getSourceBlock(), blockRelation.getTargetBlock(), blockRelation.getSourceAddress());

//      if (blockRelation.getSourceAddress() == 0x9204) {
//        getRelations().stream().filter(r -> r.getSourceAddress() == 0x9204).forEach(r -> System.out.println(this.associatedBlock.getName() + ":: " + Helper.convertToHex(r.getTargetAddress())));
//        System.out.println("-------------");
//      }
    } else blockRelation.getSourceBlock().getReferencesHandler().addBlockRelation(blockRelation);
  }

  private boolean isMine(BlockRelation e) {
    return e.getSourceBlock() == associatedBlock;
  }

  public void joinReferences(AbstractBlock block, Block otherBlock) {
    ReferencesHandler otherBlockReferencesHandler = otherBlock.getReferencesHandler();

    Collection<BlockRelation> otherBlockRelations = new ArrayList<>(otherBlockReferencesHandler.getRelations());
    otherBlockReferencesHandler.removeBlockRelations(otherBlockRelations);
    otherBlockRelations = replaceBlockInReferences(otherBlockRelations, otherBlock, block);

    addBlockRelations(otherBlockRelations);
  }

  public <T extends Block> void splitReferences(AbstractBlock block, T otherBlock) {
    List<BlockRelation> newBlockRelations = blockRelations.stream().filter(r1 -> otherBlock.contains(r1.getSourceAddress())).collect(Collectors.toList());
    newBlockRelations.addAll(blockRelations.stream().filter(r -> otherBlock.contains(r.getTargetAddress())).collect(Collectors.toList()));
    removeBlockRelations(newBlockRelations);
    List<BlockRelation> newBlockReferences2 = replaceBlockInReferences(newBlockRelations, block, otherBlock);
    otherBlock.getReferencesHandler().addBlockRelations(newBlockReferences2);
  }

  public <T extends Block> void copyReferences(T block) {
    Collection<BlockRelation> references1 = getRelations();
    block.getReferencesHandler().addBlockRelations(references1);
    removeBlockRelations(references1);
  }

  public boolean isReferencing(Block block) {
    return getRelations().stream().anyMatch(r -> r.getTargetBlock() == block);
  }

  public boolean isReferencedBy(Block block) {
    return getReferencedByBlocks().contains(block);
  }

  public boolean hasReferers() {
    return getRelations().stream().anyMatch(r -> r.getTargetBlock() == associatedBlock);
  }
}
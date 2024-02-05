package com.fpetrola.z80.blocks;

import java.util.*;
import java.util.stream.Collectors;

public class ReferencesHandler {
  private final AbstractBlock abstractBlock;
  protected Set<BlockRelation> references = new HashSet<BlockRelation>();

  public ReferencesHandler(AbstractBlock abstractBlock) {
    this.abstractBlock = abstractBlock;
  }

  public void removeBlockReferences(Collection<BlockRelation> newBlockRelations) {
    new ArrayList<>(newBlockRelations).forEach(r -> removeBlockReference(r));
  }

  public void removeBlockReference(BlockRelation blockRelation) {
    references.remove(blockRelation);
    blockRelation.getTargetBlock().getReferencesHandler().getReferences().remove(blockRelation);

    if (blockRelation.getSourceBlock() == abstractBlock)
      abstractBlock.getBlocksManager().blockChangesListener.removingKnownBlock(blockRelation.getSourceBlock(), blockRelation.getTargetBlock());
  }

  public Collection<BlockRelation> getReferences() {
    return references;
  }

  public void addBlockReferences(Collection<BlockRelation> references1) {
    references1.forEach(r -> addBlockRelation(r));
  }

  List<BlockRelation> selectSourceBlockReferences(Block block) {
    return references.stream().filter(r -> block.contains(r.getSourceAddress())).collect(Collectors.toList());
  }

  List<BlockRelation> selectTargetBlockReferences(Block block) {
    return references.stream().filter(r -> block.contains(r.getTargetAddress())).collect(Collectors.toList());
  }

  public List<BlockRelation> replaceBlockInReferences(Collection<BlockRelation> references1, Block block, Block replaceBlock) {
    return references1.stream().map(r -> {
      if (r.getSourceBlock() == block) r.setSourceBlock(replaceBlock);
      if (r.getTargetBlock() == block) r.setTargetBlock(replaceBlock);
      return r;
    }).collect(Collectors.toList());
  }

  public Set<Block> getReferencedByBlocks() {
    return references.stream().map(r -> r.getSourceBlock()).collect(Collectors.toSet());
  }

  public void addBlockRelation(BlockRelation e) {
    if (e.getSourceBlock() == abstractBlock) {
      references.add(e);
      e.getTargetBlock().getReferencesHandler().getReferences().add(e);
      abstractBlock.getBlocksManager().blockChangesListener.addingKnownBLock(abstractBlock, e.getTargetBlock(), e.getSourceAddress());
    } else e.getSourceBlock().getReferencesHandler().addBlockRelation(e);
  }

  void joinReferences(Block block, AbstractBlock abstractBlock) {
    Collection<BlockRelation> references1 = new ArrayList<>(block.getReferencesHandler().getReferences());
    block.getReferencesHandler().removeBlockReferences(references1);
    references1 = replaceBlockInReferences(references1, block, abstractBlock);
    addBlockReferences(references1);
  }

  <T extends Block> void splitReferences(T block, AbstractBlock abstractBlock) {
    List<BlockRelation> newBlockRelations = selectSourceBlockReferences(block);
    newBlockRelations.addAll(selectTargetBlockReferences(block));
    removeBlockReferences(newBlockRelations);
    List<BlockRelation> newBlockReferences2 = replaceBlockInReferences(newBlockRelations, abstractBlock, block);
    block.getReferencesHandler().addBlockReferences(newBlockReferences2);
  }

  <T extends Block> void copyReferences(T block) {
    Collection<BlockRelation> references1 = getReferences();
    block.getReferencesHandler().addBlockReferences(references1);
    removeBlockReferences(references1);
  }
}
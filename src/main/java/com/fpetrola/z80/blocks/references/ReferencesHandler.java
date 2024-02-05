package com.fpetrola.z80.blocks.references;

import com.fpetrola.z80.blocks.AbstractBlock;
import com.fpetrola.z80.blocks.Block;
import com.fpetrola.z80.blocks.BlocksManager;

import java.util.*;
import java.util.stream.Collectors;

public class ReferencesHandler {
  private final AbstractBlock associatedBlock;
  protected Set<BlockRelation> references = new HashSet<BlockRelation>();
  private BlocksManager blocksManager;

  public ReferencesHandler(AbstractBlock associatedBlock) {
    this.associatedBlock = associatedBlock;
    this.blocksManager = associatedBlock.getBlocksManager();
  }

  public void removeBlockRelations(Collection<BlockRelation> newBlockRelations) {
    new ArrayList<>(newBlockRelations).forEach(r -> removeBlockRelation(r));
  }

  public void removeBlockRelation(BlockRelation blockRelation) {
    references.remove(blockRelation);
    blockRelation.getTargetBlock().getReferencesHandler().getRelations().remove(blockRelation);

    if (blockRelation.getSourceBlock() == associatedBlock)
      blocksManager.getBlockChangesListener().removingKnownBlock(blockRelation.getSourceBlock(), blockRelation.getTargetBlock());
  }

  public Collection<BlockRelation> getRelations() {
    return references;
  }

  public void addBlockRelations(Collection<BlockRelation> references1) {
    references1.forEach(r -> addBlockRelation(r));
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
    if (e.getSourceBlock() == associatedBlock) {
      references.add(e);
      e.getTargetBlock().getReferencesHandler().getRelations().add(e);
      blocksManager.getBlockChangesListener().addingKnownBLock(e.getSourceBlock(), e.getTargetBlock(), e.getSourceAddress());
    } else e.getSourceBlock().getReferencesHandler().addBlockRelation(e);
  }

  public void joinReferences(AbstractBlock block, Block otherBlock) {
    ReferencesHandler otherBlockReferencesHandler = otherBlock.getReferencesHandler();

    Collection<BlockRelation> otherBlockRelations = new ArrayList<>(otherBlockReferencesHandler.getRelations());
    otherBlockReferencesHandler.removeBlockRelations(otherBlockRelations);
    otherBlockRelations = replaceBlockInReferences(otherBlockRelations, otherBlock, block);
    
    addBlockRelations(otherBlockRelations);
  }

  public <T extends Block> void splitReferences(AbstractBlock block, T otherBlock) {
    List<BlockRelation> newBlockRelations = references.stream().filter(r1 -> otherBlock.contains(r1.getSourceAddress())).collect(Collectors.toList());
    newBlockRelations.addAll(references.stream().filter(r -> otherBlock.contains(r.getTargetAddress())).collect(Collectors.toList()));
    removeBlockRelations(newBlockRelations);
    List<BlockRelation> newBlockReferences2 = replaceBlockInReferences(newBlockRelations, block, otherBlock);
    otherBlock.getReferencesHandler().addBlockRelations(newBlockReferences2);
  }

  public <T extends Block> void copyReferences(T block) {
    Collection<BlockRelation> references1 = getRelations();
    block.getReferencesHandler().addBlockRelations(references1);
    removeBlockRelations(references1);
  }
}
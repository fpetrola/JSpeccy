package com.fpetrola.z80.blocks.references;

import com.fpetrola.z80.blocks.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class BlockTest1 {

  private BlocksManager blocksManager;
  private AbstractBlock firstBlock;
  private AbstractBlock secondBlock;

  @Before
  public void setUp() {
    blocksManager = new BlocksManager(new NullBlockChangesListener()); // Assuming you have a constructor for BlocksManager
    Block blockAt = blocksManager.findBlockAt(0);
    blocksManager.removeBlock(blockAt);
    // Create two blocks for testing
    firstBlock = new CodeBlock(0, 10, "CALL", blocksManager);
    secondBlock = new CodeBlock(11, 20, "JUMP", blocksManager);
    blocksManager.addBlock(firstBlock);
    blocksManager.addBlock(secondBlock);
  }

  @Test
  public void testAddBlockReference() {
    // Add a block reference from firstBlock to secondBlock
    firstBlock.getReferencesHandler().addBlockRelation(BlockRelation.createBlockRelation(5, 15));

    // Check if the reference is added in both blocks
    Collection<BlockRelation> referencesInFirstBlock = firstBlock.getReferencesHandler().getRelations();
    Collection<BlockRelation> referencesInSecondBlock = secondBlock.getReferencesHandler().getRelations();

    assertEquals(1, referencesInFirstBlock.size());
    assertEquals(1, referencesInSecondBlock.size());

    // Ensure the reference details are correct
    BlockRelation reference = referencesInFirstBlock.iterator().next();
    assertEquals(firstBlock, blocksManager.findBlockAt(reference.getSourceAddress()));
    assertEquals(secondBlock, blocksManager.findBlockAt(reference.getTargetAddress()));
    assertEquals(5, reference.getSourceAddress());
    assertEquals(15, reference.getTargetAddress());
  }

  @Test
  public void testRemoveBlockReference() {
    // Add a block reference from firstBlock to secondBlock
    BlockRelation reference = BlockRelation.createBlockRelation(5, 15);
    ReferencesHandler referencesHandler = firstBlock.getReferencesHandler();
    referencesHandler.addBlockRelation(reference);

    // Remove the reference
    referencesHandler.removeBlockRelation(reference);

    // Check if the reference is removed in both blocks
    Collection<BlockRelation> referencesInFirstBlock = firstBlock.getReferencesHandler().getRelations();
    Collection<BlockRelation> referencesInSecondBlock = secondBlock.getReferencesHandler().getRelations();

    assertEquals(0, referencesInFirstBlock.size());
    assertEquals(0, referencesInSecondBlock.size());
  }

  @Test
  public void testAddAndRemoveBlockReferences() {
    // Add multiple block references
    BlockRelation reference1 = BlockRelation.createBlockRelation(5, 15);
    BlockRelation reference2 = BlockRelation.createBlockRelation(18, 8);

    ReferencesHandler firstReferencesHandler = firstBlock.getReferencesHandler();
    firstReferencesHandler.addBlockRelations(Set.of(reference1, reference2));

    // Check if the references are added in both blocks
    Collection<BlockRelation> referencesInFirstBlock = firstBlock.getReferencesHandler().getRelations();
    Collection<BlockRelation> referencesInSecondBlock = secondBlock.getReferencesHandler().getRelations();

    assertEquals(2, referencesInFirstBlock.size());
    assertEquals(2, referencesInSecondBlock.size());

    // Remove one reference
    firstReferencesHandler.removeBlockRelation(reference1);

    // Check if the reference is removed in both blocks
    referencesInFirstBlock = firstBlock.getReferencesHandler().getRelations();
    referencesInSecondBlock = secondBlock.getReferencesHandler().getRelations();

    assertEquals(1, referencesInFirstBlock.size());
    assertEquals(1, referencesInSecondBlock.size());
  }

}

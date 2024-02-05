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

    // Create two blocks for testing
    firstBlock = new CodeBlock(0, 10, "CALL", blocksManager);
    secondBlock = new CodeBlock(11, 20, "JUMP", blocksManager);
  }

  @Test
  public void testAddBlockReference() {
    // Add a block reference from firstBlock to secondBlock
    firstBlock.addBlockRelation(new BlockRelation(new BlockReference(firstBlock, 5), new BlockReference(secondBlock, 15)));

    // Check if the reference is added in both blocks
    Collection<BlockRelation> referencesInFirstBlock = firstBlock.getReferences();
    Collection<BlockRelation> referencesInSecondBlock = secondBlock.getReferences();

    assertEquals(1, referencesInFirstBlock.size());
    assertEquals(1, referencesInSecondBlock.size());

    // Ensure the reference details are correct
    BlockRelation reference = referencesInFirstBlock.iterator().next();
    assertEquals(firstBlock, reference.getSourceBlock());
    assertEquals(secondBlock, reference.getTargetBlock());
    assertEquals(5, reference.getSourceAddress());
    assertEquals(15, reference.getTargetAddress());
  }

  @Test
  public void testRemoveBlockReference() {
    // Add a block reference from firstBlock to secondBlock
    BlockRelation reference = new BlockRelation(new BlockReference(firstBlock, 5), new BlockReference(secondBlock, 15));
    firstBlock.addBlockRelation(reference);

    // Remove the reference
    firstBlock.removeBlockReference(reference);

    // Check if the reference is removed in both blocks
    Collection<BlockRelation> referencesInFirstBlock = firstBlock.getReferences();
    Collection<BlockRelation> referencesInSecondBlock = secondBlock.getReferences();

    assertEquals(0, referencesInFirstBlock.size());
    assertEquals(0, referencesInSecondBlock.size());
  }

  @Test
  public void testAddAndRemoveBlockReferences() {
    // Add multiple block references
    BlockRelation reference1 = new BlockRelation(new BlockReference(firstBlock, 5), new BlockReference(secondBlock, 15));
    BlockRelation reference2 = new BlockRelation(new BlockReference(secondBlock, 18), new BlockReference(firstBlock, 8));

    firstBlock.addBlockReferences(Set.of(reference1, reference2));

    // Check if the references are added in both blocks
    Collection<BlockRelation> referencesInFirstBlock = firstBlock.getReferences();
    Collection<BlockRelation> referencesInSecondBlock = secondBlock.getReferences();

    assertEquals(2, referencesInFirstBlock.size());
    assertEquals(2, referencesInSecondBlock.size());

    // Remove one reference
    firstBlock.removeBlockReference(reference1);

    // Check if the reference is removed in both blocks
    referencesInFirstBlock = firstBlock.getReferences();
    referencesInSecondBlock = secondBlock.getReferences();

    assertEquals(1, referencesInFirstBlock.size());
    assertEquals(1, referencesInSecondBlock.size());
  }
}

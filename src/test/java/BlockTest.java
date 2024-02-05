import com.fpetrola.z80.blocks.*;
import com.fpetrola.z80.blocks.references.BlockReference;
import com.fpetrola.z80.blocks.references.BlockRelation;
import com.fpetrola.z80.blocks.references.ReferencesHandler;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.*;

public class BlockTest {

  private BlocksManager blocksManager;
  private CodeBlock block1;
  private CodeBlock block2;

  @Before
  public void setUp() {
    blocksManager = new BlocksManager(new NullBlockChangesListener());
    block1 = new CodeBlock(0, 10, "CALL", blocksManager);
    block2 = new CodeBlock(11, 20, "JUMP", blocksManager);
  }

  @Test
  public void testSplitBlock() {
    // Add a reference from block1 to block2
    ReferencesHandler referencesHandler = block1.getReferencesHandler();
    referencesHandler.addBlockRelation(new BlockRelation(new BlockReference(block1, 2), new BlockReference(block2, 15)));
    referencesHandler.addBlockRelation(new BlockRelation(new BlockReference(block1, 5), new BlockReference(block2, 15)));
    referencesHandler.addBlockRelation(new BlockRelation(new BlockReference(block1, 7), new BlockReference(block2, 15)));

    // Split block1 at address 8
    Block newBlock = block1.split(3 - 1, "CALL", CodeBlock.class);

    // Check if the split is successful
    assertTrue(block1.contains(0));
    assertTrue(block1.contains(2));
    assertFalse(block1.contains(3));

    assertFalse(newBlock.contains(2));
    assertTrue(newBlock.contains(3));
    assertTrue(newBlock.contains(10));
    assertFalse(newBlock.contains(11));

    // Check references after the split
    Collection<BlockRelation> referencesInBlock1 = block1.getReferencesHandler().getReferences();
    Collection<BlockRelation> referencesInNewBlock = newBlock.getReferencesHandler().getReferences();

    assertEquals(1, referencesInBlock1.size());
    assertEquals(2, referencesInNewBlock.size());
    assertEquals(newBlock, referencesInNewBlock.iterator().next().getSourceBlock());
  }

  @Test
  public void testJoinBlocks() {
    ReferencesHandler referencesHandler = block1.getReferencesHandler();
    referencesHandler.addBlockRelation(new BlockRelation(new BlockReference(block1, 2), new BlockReference(block2, 13)));
    referencesHandler.addBlockRelation(new BlockRelation(new BlockReference(block1, 5), new BlockReference(block2, 17)));

    Block newBlock = block2.split(15 - 1, "JUMP", CodeBlock.class);
    block1.join(block2);

    assertTrue(block1.contains(0));
    assertTrue(block1.contains(14));
    assertFalse(block1.contains(15));

    assertFalse(newBlock.contains(14));
    assertTrue(newBlock.contains(15));
    assertTrue(newBlock.contains(20));
    assertFalse(newBlock.contains(21));

    Collection<BlockRelation> referencesInBlock1 = block1.getReferencesHandler().getReferences();
    Collection<BlockRelation> referencesInNewBlock = newBlock.getReferencesHandler().getReferences();
    Collection<BlockRelation> referencesInBlock2 = block2.getReferencesHandler().getReferences();

    assertEquals(2, referencesInBlock1.size());
    assertEquals(0, referencesInBlock2.size());
    assertEquals(1, referencesInNewBlock.size());
  }

  @Test
  public void testReplaceBlockInReferences() {
    // Add a reference from block1 to block2
    BlockRelation reference = new BlockRelation(new BlockReference(block1, 5), new BlockReference(block2, 15));
    block1.getReferencesHandler().addBlockRelation(reference);

    // Create a new block to replace block2
    CodeBlock newBlock = new CodeBlock(21, 30, "JUMP", blocksManager);

    // Replace block2 with newBlock in references
    Collection<BlockRelation> referencesInBlock1 = block1.getReferencesHandler().getReferences();
    Collection<BlockRelation> newReferences = block1.getReferencesHandler().replaceBlockInReferences(referencesInBlock1, block2, newBlock);

    // Check if the references are updated
    assertEquals(1, referencesInBlock1.size());
    assertEquals(1, newReferences.size());
    assertEquals(newBlock, newReferences.iterator().next().getTargetBlock());
  }
}

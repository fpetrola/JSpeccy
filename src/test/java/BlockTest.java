import com.fpetrola.z80.blocks.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.assertEquals;

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
        block1.addBlockRelation(new BlockRelation(new BlockReference(block1, 2), new BlockReference(block2, 15)));
        block1.addBlockRelation(new BlockRelation(new BlockReference(block1, 5), new BlockReference(block2, 15)));
        block1.addBlockRelation(new BlockRelation(new BlockReference(block1, 7), new BlockReference(block2, 15)));

        // Split block1 at address 8
        Block newBlock = block1.split(3, "CALL", CodeBlock.class);

        // Check if the split is successful
        assertEquals(0, block1.getRangeHandler().getStartAddress());
        assertEquals(2, block1.getRangeHandler().getEndAddress());
        assertEquals(3, newBlock.getRangeHandler().getStartAddress());
        assertEquals(10, newBlock.getRangeHandler().getEndAddress());

        // Check references after the split
        Collection<BlockRelation> referencesInBlock1 = block1.getReferences();
        Collection<BlockRelation> referencesInNewBlock = newBlock.getReferences();

        assertEquals(1, referencesInBlock1.size());
        assertEquals(2, referencesInNewBlock.size());
        assertEquals(newBlock, referencesInNewBlock.iterator().next().getSourceBlock());
    }

    @Test
    public void testJoinBlocks() {
        block1.addBlockRelation(new BlockRelation(new BlockReference(block1, 2), new BlockReference(block2, 13)));
        block1.addBlockRelation(new BlockRelation(new BlockReference(block1, 5), new BlockReference(block2, 17)));

        // Split block2 at address 15
        Block newBlock = block2.split(15, "JUMP", CodeBlock.class);

        // Join block1 and block2
        block1.join(block2);

        // Check if the join is successful
        assertEquals(0, block1.getRangeHandler().getStartAddress());
        assertEquals(14, block1.getRangeHandler().getEndAddress());
        assertEquals(newBlock, block1.getRangeHandler().getNextBlock());
        assertEquals(block1, newBlock.getRangeHandler().getPreviousBlock());

        // Check references after the join
        Collection<BlockRelation> referencesInBlock1 = block1.getReferences();
        Collection<BlockRelation> referencesInNewBlock = newBlock.getReferences();
        Collection<BlockRelation> referencesInBlock2 = block2.getReferences();

        assertEquals(2, referencesInBlock1.size());
        assertEquals(0, referencesInBlock2.size());
        assertEquals(1, referencesInNewBlock.size());
    }

    @Test
    public void testReplaceBlockInReferences() {
        // Add a reference from block1 to block2
        BlockRelation reference = new BlockRelation(new BlockReference(block1, 5), new BlockReference(block2, 15));
        block1.addBlockRelation(reference);

        // Create a new block to replace block2
        CodeBlock newBlock = new CodeBlock(21, 30, "JUMP", blocksManager);

        // Replace block2 with newBlock in references
        Collection<BlockRelation> referencesInBlock1 = block1.getReferences();
        Collection<BlockRelation> newReferences = block1.replaceBlockInReferences(referencesInBlock1, block2, newBlock);

        // Check if the references are updated
        assertEquals(1, referencesInBlock1.size());
        assertEquals(1, newReferences.size());
        assertEquals(newBlock, newReferences.iterator().next().getTargetBlock());
    }
}

import com.fpetrola.z80.blocks.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.assertEquals;

public class BlockTest {

    private BlocksManager blocksManager;
    private Routine block1;
    private Routine block2;

    @Before
    public void setUp() {
        blocksManager = new BlocksManager(new NullBlockChangesListener());
        block1 = new Routine(0, 10, "CALL", blocksManager);
        block2 = new Routine(11, 20, "JUMP", blocksManager);
    }

    @Test
    public void testSplitBlock() {
        // Add a reference from block1 to block2
        block1.addBlockReference(new BlockReference(block1, block2, 2, 15));
        block1.addBlockReference(new BlockReference(block1, block2, 5, 15));
        block1.addBlockReference(new BlockReference(block1, block2, 7, 15));

        // Split block1 at address 8
        Block newBlock = block1.split(3, "CALL", new Routine());

        // Check if the split is successful
        assertEquals(0, block1.getStartAddress());
        assertEquals(2, block1.getEndAddress());
        assertEquals(3, newBlock.getStartAddress());
        assertEquals(10, newBlock.getEndAddress());

        // Check references after the split
        Collection<BlockReference> referencesInBlock1 = block1.getReferences();
        Collection<BlockReference> referencesInNewBlock = newBlock.getReferences();

        assertEquals(1, referencesInBlock1.size());
        assertEquals(2, referencesInNewBlock.size());
        assertEquals(newBlock, referencesInNewBlock.iterator().next().getSourceBlock());
    }

    @Test
    public void testJoinBlocks() {
        block1.addBlockReference(new BlockReference(block1, block2, 2, 13));
        block1.addBlockReference(new BlockReference(block1, block2, 5, 17));

        // Split block2 at address 15
        Block newBlock = block2.split(15, "JUMP", new Routine());

        // Join block1 and block2
        block1.join(block2);

        // Check if the join is successful
        assertEquals(0, block1.getStartAddress());
        assertEquals(14, block1.getEndAddress());
        assertEquals(newBlock, block1.getNextBlock());
        assertEquals(block1, newBlock.getPreviousBlock());

        // Check references after the join
        Collection<BlockReference> referencesInBlock1 = block1.getReferences();
        Collection<BlockReference> referencesInNewBlock = newBlock.getReferences();
        Collection<BlockReference> referencesInBlock2 = block2.getReferences();

        assertEquals(2, referencesInBlock1.size());
        assertEquals(0, referencesInBlock2.size());
        assertEquals(1, referencesInNewBlock.size());
    }

    @Test
    public void testReplaceBlockInReferences() {
        // Add a reference from block1 to block2
        BlockReference reference = new BlockReference(block1, block2, 5, 15);
        block1.addBlockReference(reference);

        // Create a new block to replace block2
        Routine newBlock = new Routine(21, 30, "JUMP", blocksManager);

        // Replace block2 with newBlock in references
        Collection<BlockReference> referencesInBlock1 = block1.getReferences();
        Collection<BlockReference> newReferences = block1.replaceBlockInReferences(referencesInBlock1, block2, newBlock);

        // Check if the references are updated
        assertEquals(1, referencesInBlock1.size());
        assertEquals(1, newReferences.size());
        assertEquals(newBlock, newReferences.iterator().next().getTargetBlock());
    }
}

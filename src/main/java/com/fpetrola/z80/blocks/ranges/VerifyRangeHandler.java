package com.fpetrola.z80.blocks.ranges;

import com.fpetrola.z80.blocks.Block;
import com.fpetrola.z80.blocks.NullBlock;
import org.apache.commons.lang3.Range;

import java.util.List;

public class VerifyRangeHandler extends RangeHandler {
  public VerifyRangeHandler(int start, int end, String typeName, RangeChangeListener rangeChangeListener) {
    super(start, end, typeName, rangeChangeListener);
  }

  public void doVerify(List<Block> blocks) {

    for (int i = 0; i < blocks.size(); i++) {
      for (int j = 0; j < blocks.size(); j++) {
        RangeHandler rangeHandler2 = blocks.get(i).getRangeHandler();
        RangeHandler rangeHandler4 = blocks.get(i).getRangeHandler();
        if (rangeHandler4.nextBlock == null || rangeHandler2.previousBlock == null) {
          System.out.println("ups!");
        }
        RangeHandler rangeHandler3 = blocks.get(i).getRangeHandler();
        if (rangeHandler3.nextBlock instanceof NullBlock) {
          RangeHandler rangeHandler = blocks.get(i).getRangeHandler();
          if (rangeHandler.endAddress != 0xFFFF) {
            System.out.println("ups!");
          }
        }
        RangeHandler rangeHandler1 = blocks.get(i).getRangeHandler();
        if (rangeHandler1.previousBlock instanceof NullBlock) {
          RangeHandler rangeHandler = blocks.get(i).getRangeHandler();
          if (rangeHandler.startAddress != 0x0) {
            System.out.println("ups!");
          }
        }
        if (j != i)
          if (isOverlappedBy(blocks.get(i), blocks.get(j))) {
            System.out.println("ups!");
          }

      }
    }
  }

  private boolean isOverlappedBy(Block block, Block block1) {
    Range<Integer> range1 = getRange(block);
    Range<Integer> range2 = getRange(block1);
    return range1.isOverlappedBy(range2);
  }

  private Range<Integer> getRange(Block block) {
    RangeHandler rangeHandler = block.getRangeHandler();
    RangeHandler rangeHandler1 = block.getRangeHandler();
    return Range.between(rangeHandler.startAddress, rangeHandler1.endAddress);
  }

  private Range<Integer> getOwnRange() {
    return Range.between(startAddress, endAddress);
  }
}

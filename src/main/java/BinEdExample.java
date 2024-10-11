import org.exbin.auxiliary.binary_data.ByteArrayEditableData;
import org.exbin.bined.swing.basic.CodeArea;

import javax.swing.*;

public class BinEdExample {

    public static void main(String[] args) {
        final JFrame frame = new JFrame("BinEd Frame");
        CodeArea codeArea = new CodeArea();
        codeArea.setContentData(new ByteArrayEditableData(new byte[]{1, 2, 3}));
        frame.add(codeArea);
        frame.setSize(1000, 600);
        frame.setVisible(true);
    }
}
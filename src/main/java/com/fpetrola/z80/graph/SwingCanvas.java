package com.fpetrola.z80.graph;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

import javax.swing.BorderFactory;
import javax.swing.CellRendererPane;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;

import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rsyntaxtextarea.TextEditorPane;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.swing.view.mxInteractiveCanvas;
import com.mxgraph.view.mxCellState;

public class SwingCanvas extends mxInteractiveCanvas {
  protected CellRendererPane rendererPane = new CellRendererPane();

  protected JLabel vertexRenderer = new JLabel();

  protected mxGraphComponent graphComponent;

  public SwingCanvas(mxGraphComponent graphComponent) {
    this.graphComponent = graphComponent;

    vertexRenderer.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
    vertexRenderer.setHorizontalAlignment(JLabel.CENTER);
    vertexRenderer.setBackground(graphComponent.getBackground().darker());
    vertexRenderer.setOpaque(true);
  }

  public void drawVertex(mxCellState state, String label) {
    vertexRenderer.setText(label);
    // TODO: Configure other properties..

//    RSyntaxTextAreaHighlighter rSyntaxTextAreaHighlighter = new RSyntaxTextAreaHighlighter();
//    rSyntaxTextAreaHighlighter.install(new RTextArea(label));

    TextEditorPane textArea = new TextEditorPane() {

      public Graphics getGraphics() {
        return g;
      }

      private double zoomFactor = 0.3;
      private double prevZoomFactor = 1;
      private boolean zoomer = true;

      public void setBounds(int x, int y, int width, int height) {
        super.setBounds(x, y, width, height);
        zoomFactor = (width / 200f)+ 0.2f;
      }

      public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if (zoomer) {
          AffineTransform at = new AffineTransform();
          at.scale(zoomFactor, zoomFactor);
          prevZoomFactor = zoomFactor;
          g2.transform(at);
          zoomer = false;
        }
        super.paint(g);
      }
    };
    textArea.setText(label);
    textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_ASSEMBLER_X86);
    textArea.setCodeFoldingEnabled(true);
//    RTextScrollPane sp = new RTextScrollPane(textArea);
//    sp.setOpaque(true);

    int x = (int) (state.getX() + translate.getX());
    int y = (int) (state.getY() + translate.getY());
    int width = (int) state.getWidth();

    rendererPane.paintComponent(g, textArea, graphComponent, x, y, width, (int) state.getHeight(), true);
  }

}
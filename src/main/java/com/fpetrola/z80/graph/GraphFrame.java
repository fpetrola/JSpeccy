package com.fpetrola.z80.graph;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import com.mxgraph.canvas.mxICanvas;
import com.mxgraph.canvas.mxImageCanvas;
import com.mxgraph.layout.mxCompactTreeLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.swing.view.mxInteractiveCanvas;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxPoint;
import com.mxgraph.view.mxCellState;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxGraphView;

public class GraphFrame extends JFrame {
  public mxGraph graph = new mxGraph() {

    public void drawState(mxICanvas canvas, mxCellState state, boolean drawLabel) {
      String label = (drawLabel) ? state.getLabel() : "";

      // Indirection for wrapped swing canvas inside image canvas (used for creating
      // the preview image when cells are dragged)
      Object cell = state.getCell();
      if (getModel().isVertex(cell) && canvas instanceof mxImageCanvas && ((mxImageCanvas) canvas).getGraphicsCanvas() instanceof SwingCanvas) {
        ((SwingCanvas) ((mxImageCanvas) canvas).getGraphicsCanvas()).drawVertex(state, label);
      }
      // Redirection of drawing vertices in SwingCanvas
      else if (getModel().isVertex(cell) && canvas instanceof SwingCanvas) {
        ((SwingCanvas) canvas).drawVertex(state, label);
      } else {
        super.drawState(canvas, state, drawLabel);
      }
    }
  };
  private mxGraphComponent graphComponent;
  private int id;
  public boolean ready;

  public static void main(String[] args) {
    JFrame frame = new GraphFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(1000, 700);
    frame.setVisible(true);
  }

  public GraphFrame() {
    Map<String, Object> style = graph.getStylesheet().getDefaultEdgeStyle();
    style.put(mxConstants.STYLE_ROUNDED, true);
    style.put(mxConstants.STYLE_EDGE, mxConstants.EDGESTYLE_ENTITY_RELATION);

    graphComponent = new mxGraphComponent(graph) {
      public mxInteractiveCanvas createCanvas() {
        return new SwingCanvas(this);
      }
    };

    MouseAdapter mouseAdapter = new MouseAdapter() {
      private mxPoint start;

      public void mouseClicked(MouseEvent e) {
        ready = true;
        if (e.isControlDown())
          morphGraph();
      }

      public void mousePressed(MouseEvent e) {
        if (!e.isConsumed()) {
          mxPoint translate = graph.getView().getTranslate();

          double pX = (e.getX() / graph.getView().getScale()) - translate.getX();
          double pY = (e.getY() / graph.getView().getScale()) - translate.getY();

          start = new mxPoint(pX, pY);
        }
      }

      public void mouseDragged(MouseEvent e) {

        if (start != null) {
          double pX = (e.getX() / graph.getView().getScale()) - start.getX();
          double pY = (e.getY() / graph.getView().getScale()) - start.getY();

//          System.out.println(start.getX() + " - " + start.getY());

          graph.getView().setTranslate(new mxPoint(pX, pY));
          e.consume();
        }
      }

      public void mouseReleased(MouseEvent e) {
        start = null;
      }
    };
    graphComponent.getGraphControl().addMouseListener(mouseAdapter);
    graphComponent.getGraphControl().addMouseMotionListener(mouseAdapter);

    graphComponent.getGraphControl().addMouseWheelListener(new MouseAdapter() {
      public void zoom(double factor) {
        mxGraphView view = graph.getView();
        double newScale = (double) ((int) (view.getScale() * 100 * factor)) / 100;

        if (newScale != view.getScale() && newScale > 0.04) {
          mxPoint translate = new mxPoint();
          graph.getView().scaleAndTranslate(newScale, translate.getX(), translate.getY());

        }
      }

      public void mouseWheelMoved(MouseWheelEvent evt) {
        if (evt.isConsumed()) {
          return;
        }

        boolean gridEnabled = graph.isGridEnabled();

        // disable snapping
        graph.setGridEnabled(false);

        mxPoint p1 = graphComponent.getPointForEvent(evt, false);

        if (evt.getWheelRotation() < 0) {
          zoom(1.2);
        } else {
          zoom(1 / 1.2);
        }

        mxPoint p2 = graphComponent.getPointForEvent(evt, false);
        double deltaX = p2.getX() - p1.getX();
        double deltaY = p2.getY() - p1.getY();

        mxGraphView view = graph.getView();

        mxPoint translate = view.getTranslate();
        view.setTranslate(new mxPoint(translate.getX() + deltaX, translate.getY() + deltaY));

//        graph.setGridEnabled(gridEnabled);

        evt.consume();
//        zoomWithWheel(evt);
      }

      private void zoomWithWheel(MouseWheelEvent evt) {
        double pX = (evt.getX() / graph.getView().getScale()) - graph.getView().getTranslate().getX();
        double pY = (evt.getY() / graph.getView().getScale()) - graph.getView().getTranslate().getY();
        Object v1 = graph.insertVertex(graph.getDefaultParent(), null, null, pX, pY, 1, 1, "strokeColor=rgb(85,85,85,0);fillColor=rgb(184,184,184,0)");
        mxCellState state = graph.getView().getState(v1);
        if (state != null) {
          double old_x = state.getX();
          double old_y = state.getY();

          if (evt.getWheelRotation() > 0) {
            graphComponent.zoomOut();
          } else {
            graphComponent.zoomIn();
          }

          double new_x = state.getX();
          double new_y = state.getY();

          double deltaX = (old_x - new_x) / graph.getView().getScale();
          double deltaY = (old_y - new_y) / graph.getView().getScale();

          double d = graph.getView().getTranslate().getX() + deltaX;
          double e = graph.getView().getTranslate().getY() + deltaY;
          graph.getView().setTranslate(new mxPoint(d, e));
        }
        graph.removeCells(new Object[] { v1 });
      }
    });
    Object defaultParent = graph.getDefaultParent();
//    Object v1 = graph.insertVertex(defaultParent, id++ + "", new StringBuffer("hola mundo"), 800, 50, 100, 300);
//    Object v2 = graph.insertVertex(defaultParent, id++ + "", "class A{ \n public int i= 0;\n}", 200, 150, 100, 300);

//    graph.insertEdge(defaultParent, "e1", "edge1", v1, v2);
    JScrollPane jScrollPane = new JScrollPane(graphComponent);
    jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    getContentPane().add(graphComponent);
    morphGraph();
    graphComponent.zoomOut();
  }

  public void morphGraph() {
//    mxIGraphLayout layout = new mxOrganicLayout(graph);
    mxHierarchicalLayout layout = new mxHierarchicalLayout(graph);
    layout.setIntraCellSpacing(130.0);
    layout.setInterRankCellSpacing(130.0) ;
    layout.setDisableEdgeStyle(true);
//    mxIGraphLayout layout = new mxCircleLayout(graph);
//    mxIGraphLayout layout = new mxCompactTreeLayout(graph);

    graph.getModel().beginUpdate();
    try {
      layout.execute(graph.getDefaultParent());
    } finally {
//      mxMorphing morph = new mxMorphing(graphComponent, 20, 1.5, 20);
//
//      morph.addListener(mxEvent.DONE, new mxIEventListener() {
//
//        public void invoke(Object arg0, mxEventObject arg1) {
//          graph.getModel().endUpdate();
//        }
//
//      });
//
//      morph.startAnimation();
    }
    graph.getModel().endUpdate();

//    graphComponent.zoomAndCenter();
  }

}

package com.fpetrola.z80.graph;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.layout.mxOrganicLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.swing.util.mxMorphing;
import com.mxgraph.util.mxEvent;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import com.mxgraph.util.mxPoint;
import com.mxgraph.view.mxGraph;

public class GraphFrame extends JFrame {
  public mxGraph graph = new mxGraph();
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
    graphComponent = new mxGraphComponent(graph);

    MouseAdapter mouseAdapter = new MouseAdapter() {
      private mxPoint start;

      public void mouseClicked(MouseEvent e) {
        ready = true;
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
      public void mouseWheelMoved(MouseWheelEvent evt) {
        double pX = (evt.getX() / graph.getView().getScale()) - graph.getView().getTranslate().getX();
        double pY = (evt.getY() / graph.getView().getScale()) - graph.getView().getTranslate().getY();
        Object v1 = graph.insertVertex(graph.getDefaultParent(), null, null, pX, pY, 1, 1, "strokeColor=rgb(85,85,85,0);fillColor=rgb(184,184,184,0)");
        double old_x = graph.getView().getState(v1).getX();
        double old_y = graph.getView().getState(v1).getY();

        if (evt.getWheelRotation() > 0) {
          graphComponent.zoomOut();
        } else {
          graphComponent.zoomIn();
        }

        double new_x = graph.getView().getState(v1).getX();
        double new_y = graph.getView().getState(v1).getY();

        double deltaX = (old_x - new_x) / graph.getView().getScale();
        double deltaY = (old_y - new_y) / graph.getView().getScale();

        double d = graph.getView().getTranslate().getX() + deltaX;
        double e = graph.getView().getTranslate().getY() + deltaY;
        graph.getView().setTranslate(new mxPoint(d, e));
        graph.removeCells(new Object[] { v1 });
      }
    });
    Object defaultParent = graph.getDefaultParent();
    Object v1 = graph.insertVertex(defaultParent, id++ + "", "hola1", 800, 50, 100, 30);
    Object v2 = graph.insertVertex(defaultParent, id++ + "", "hola2", 200, 150, 100, 30);

    graph.insertEdge(defaultParent, "e1", "edge1", v1, v2);
    JScrollPane jScrollPane = new JScrollPane(graphComponent);
    jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    getContentPane().add(graphComponent);
    morphGraph();
    graphComponent.zoomOut();
  }

  public void morphGraph() {
    mxIGraphLayout layout = new mxOrganicLayout(graph);

    graph.getModel().beginUpdate();
    try {
      layout.execute(graph.getDefaultParent());
    } finally {
      mxMorphing morph = new mxMorphing(graphComponent, 20, 1.5, 20);

      morph.addListener(mxEvent.DONE, new mxIEventListener() {

        public void invoke(Object arg0, mxEventObject arg1) {
          graph.getModel().endUpdate();
        }

      });

      morph.startAnimation();
    }
    graph.getModel().endUpdate();

    graphComponent.zoomAndCenter();
  }

}

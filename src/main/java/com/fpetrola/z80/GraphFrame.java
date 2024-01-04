package com.fpetrola.z80;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import com.mxgraph.layout.mxCompactTreeLayout;
import com.mxgraph.layout.mxFastOrganicLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.layout.mxOrganicLayout;
import com.mxgraph.layout.mxParallelEdgeLayout;
import com.mxgraph.layout.mxPartitionLayout;
import com.mxgraph.layout.mxStackLayout;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.layout.orthogonal.mxOrthogonalLayout;
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
		graphComponent.getGraphControl().addMouseListener(new MouseAdapter() {

			private boolean dragging;

			public void mouseClicked(MouseEvent e) {
				ready = true;
				morphGraph();
			}

			public void mouseDragged(MouseEvent e) {

//				ready = true;
//				if (e.isControlDown())
//					morphGraph();

//				if (dragging) {
//					int x = e.getX();
//					int y = e.getY();
//					mxPoint translate = graph.getView().getTranslate();
//					
//					graph.getView().setTranslate(new mxPoint(x, y));
//					dragging = false;
//				} else
//					dragging = true;
			}
		});
		graphComponent.getGraphControl().addMouseWheelListener(new MouseAdapter() {
			public void mouseWheelMoved(MouseWheelEvent e) {
//				graphComponent.scrollToCenter(true);
//				graphComponent.scrollToCenter(false);
				if (e.getWheelRotation() > 0)
					graphComponent.zoomOut();
				else
					graphComponent.zoomIn();
			}
		});
		Object defaultParent = graph.getDefaultParent();
//		Object v1 = graph.insertVertex(defaultParent, id++ + "", "hola1", 800, 50, 100, 30);
//		Object v2 = graph.insertVertex(defaultParent, id++ + "", "hola2", 200, 150, 100, 30);
//
//		graph.insertEdge(defaultParent, "e1", "edge1", v1, v2);
//		JScrollPane jScrollPane = new JScrollPane(graphComponent);
//		jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
//		jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

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

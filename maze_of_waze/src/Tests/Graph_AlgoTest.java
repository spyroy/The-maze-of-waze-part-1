package Tests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node;
import dataStructure.node_data;
import utils.Point3D;

class Graph_AlgoTest {
	
	
	graph create() {
		graph g = new DGraph();
		Point3D p1 = new Point3D(1, 0, 10);
		Point3D p2 = new Point3D(-5, 1, 2.5);
		Point3D p3 = new Point3D(10, 100, 20);
		Point3D p4 = new Point3D(0, 0, 1);
		Point3D p5 = new Point3D(-5, -10, -7);
		Point3D p6 = new Point3D(6, 111, 2);
		Point3D p7 = new Point3D(4.2, 5.5, -50);
		Point3D p8 = new Point3D(23, 26, 436);
		node n1 = new node(1, p1, 2);
		node n2 = new node(2, p2, 1);
		node n3 = new node(3, p3, 1);
		node n4 = new node(4, p4, 1);
		node n5 = new node(5, p5, 1);
		node n6 = new node(6, p6, 1);
		node n7 = new node(7, p7, 1);
		node n8 = new node(8, p8, 1);
		g.addNode(n1);
		g.addNode(n2);
		g.addNode(n3);
		g.addNode(n4);
		g.addNode(n5);
		g.addNode(n6);
		g.addNode(n7);
		g.addNode(n8);
		g.connect(1, 2, 7);
		g.connect(2, 1, 2.77);
		g.connect(1, 5, 23);
		g.connect(5, 3, 10);
		g.connect(2, 3, 4.5);
		g.connect(6, 2, 42);
		g.connect(6, 4, 4.11);
		g.connect(7, 4, 42);
	//	Graph_Algo gr = new Graph_Algo(g);
		return g;
	}
	
	graph connected() {
		graph g = new DGraph();
		Point3D p1 = new Point3D(1, 0, 10);
		Point3D p2 = new Point3D(-5, 1, 2.5);
		Point3D p3 = new Point3D(10, 100, 20);
		Point3D p4 = new Point3D(0, 0, 1);
		Point3D p5 = new Point3D(-5, -10, -7);
		Point3D p6 = new Point3D(6, 111, 2);
		Point3D p7 = new Point3D(4.2, 5.5, -50);
		Point3D p8 = new Point3D(23, 26, 436);
		node n1 = new node(1, p1, 2);
		node n2 = new node(2, p2, 1);
		node n3 = new node(3, p3, 1);
		node n4 = new node(4, p4, 1);
		node n5 = new node(5, p5, 1);
		node n6 = new node(6, p6, 1);
		node n7 = new node(7, p7, 1);
		node n8 = new node(8, p8, 1);
		g.addNode(n1);
		g.addNode(n2);
		g.addNode(n3);
		g.addNode(n4);
		g.addNode(n5);
		g.addNode(n6);
		g.addNode(n7);
		g.addNode(n8);
		g.connect(1, 2, 7);
		g.connect(2, 1, 2.77);
		g.connect(1, 5, 23);
		g.connect(5, 3, 10);
		g.connect(2, 3, 4.5);
		g.connect(6, 2, 42);
		g.connect(6, 4, 4.11);
		g.connect(7, 4, 42);
		g.connect(3, 2, 10);
		g.connect(3, 1, 1);
		g.connect(4, 3, 5);
		g.connect(1, 4, 10);
		g.connect(3, 4, 10);
		g.connect(4, 6, 10);
		g.connect(6, 7, 10);
		g.connect(1, 8, 10);
		g.connect(8, 1, 10);
		//Graph_Algo gr = new Graph_Algo(g);
		return g;
	}

	@Test
	void testGraph_AlgoGraph() {
		graph g = create();
		Graph_Algo gr = new Graph_Algo(g); 
	}


	@Test
	void testIsConnected() {
		graph g = create();
		Graph_Algo gr = new Graph_Algo(g); 
		boolean b = gr.isConnected();
		assertFalse(b);
		g = connected();
		gr = new Graph_Algo(g);
		b = gr.isConnected();
		assertTrue(b);
	}


	@Test
	void testShortestPathDist() {
		graph g = create();
		Graph_Algo gr = new Graph_Algo(g); 
		double shortest = gr.shortestPathDist(1, 5);
		assertEquals(23.0, shortest,0.00001);
		g = connected();
		gr = new Graph_Algo(g);
		shortest = gr.shortestPathDist(1, 7);
		assertEquals(30.0, shortest,0.00001);
	}

	@Test
	void testShortestPath() {
		graph g = create();
		Graph_Algo gr = new Graph_Algo(g); 
		List<node_data> list = gr.shortestPath(1, 5);
		if((!list.contains(1)||!list.contains(5))&&list.size()!=2)
			fail();
		g = connected();
		gr = new Graph_Algo(g);
		list = gr.shortestPath(1, 7);
		if((!list.contains(1)||!list.contains(4)||!list.contains(6)||!list.contains(7))&&list.size()!=4)
			fail();
	}

	@Test
	void testTSP() {
		graph g = create();
		Graph_Algo gr = new Graph_Algo(g);
		List<Integer> t = new ArrayList();
		List<node_data> list = null;
		t.add(1);
		t.add(5);
		t.add(3);
		t.add(4);
		t.add(8);
		if(gr.TSP(t)!=null)
			fail();
		g = connected();
		gr = new Graph_Algo(g);
		list=gr.TSP(t);
		Point3D p1 = new Point3D(1, 0, 10);
		Point3D p5 = new Point3D(-5, -10, -7);
		Point3D p3 = new Point3D(10, 100, 20);
		Point3D p4 = new Point3D(0, 0, 1);
		Point3D p8 = new Point3D(23, 26, 436);
		node n1 = new node(1, p1, 2);
		node n5 = new node(5, p5, 1);
		node n3 = new node(3, p3, 1);
		node n4 = new node(4, p4, 1);
		node n8 = new node(8, p8, 1);
		List<node_data> ans = new ArrayList<node_data>();
		ans.add(n1);
		ans.add(n5);
		ans.add(n3);
		ans.add(n4);
		ans.add(n8);
		List<Integer> f = new ArrayList();
		for(node_data n : list)
			f.add(n.getKey());
		if(!f.contains(1)||!f.contains(5)||!f.contains(3)||!f.contains(4)||!f.contains(8))
			fail();
	}

	@Test
	void testCopy() {
		graph g = connected();
		Graph_Algo gr = new Graph_Algo(g);
		graph gd = gr.copy();
		Graph_Algo gd2 = new Graph_Algo(g);
		List<node_data> ans = gr.shortestPath(1, 7);
		List<node_data> ans2 = gd2.shortestPath(1, 7);
		assertEquals(ans, ans2);
		ans = gr.shortestPath(1, 6);
		ans2 = gd2.shortestPath(1, 6);
		assertEquals(ans, ans2);
		ans = gr.shortestPath(1, 5);
		ans2 = gd2.shortestPath(1, 5);
		assertEquals(ans, ans2);
		ans = gr.shortestPath(1, 4);
		ans2 = gd2.shortestPath(1, 4);
		assertEquals(ans, ans2);
		for(node_data n : g.getV()) {
			for(edge_data e : g.getE(n.getKey())) {
				if(gd.getEdge(e.getSrc(), e.getDest()) == null)
					fail();
				if(gd.getNode(n.getKey()) == null)
					fail();
			}
		}
		
	}



}

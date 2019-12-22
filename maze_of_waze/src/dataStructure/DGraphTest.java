package dataStructure;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import org.junit.jupiter.api.Test;

import utils.Point3D;

class DGraphTest {

	@Test
	void testDGraph() {
		graph g = new DGraph();
		Point3D p1 = new Point3D(1, 0, 0);
		Point3D p2 = new Point3D(0, 1, 0);
		Point3D p3 = new Point3D(0, 0, 1);
		node n1 = new node(0, p1, 1);
		node n2 = new node(1, p2, 10);
		node n3 = new node(2, p3, 100);
		g.addNode(n1);
		g.addNode(n2);
		g.addNode(n3);
		if (g.nodeSize() != 3) {
			fail();
		}
	}

	@Test
	void testGetNode() {
		graph g = new DGraph();
		Point3D p1 = new Point3D(1, 0, 0);
		Point3D p2 = new Point3D(0, 1, 0);
		Point3D p3 = new Point3D(0, 0, 1);
		node n1 = new node(0, p1, 1);
		node n2 = new node(1, p2, 10);
		node n3 = new node(2, p3, 100);
		g.addNode(n1);
		g.addNode(n2);
		g.addNode(n3);
		if (g.getNode(1).getKey() != 1) {
			fail();
		}
	}

	@Test
	void testGetEdge() {
		graph g = new DGraph();
		Point3D p1 = new Point3D(1, 0, 0);
		Point3D p2 = new Point3D(0, 1, 0);
		Point3D p3 = new Point3D(0, 0, 1);
		node n1 = new node(0, p1, 1);
		node n2 = new node(1, p2, 10);
		node n3 = new node(2, p3, 100);
		g.addNode(n1);
		g.addNode(n2);
		g.addNode(n3);
		g.connect(n1.getKey(), n2.getKey(), 2);
		g.connect(n2.getKey(), n3.getKey(), 3);
		if (g.getEdge(0, 1).getSrc() != n1.getKey()) {
			fail();
		}
		if (g.getEdge(0, 1).getDest() != n2.getKey()) {
			fail();
		}
		if (g.getEdge(1, 2).getSrc() != n2.getKey()) {
			fail();
		}
		if (g.getEdge(1, 2).getDest() != n3.getKey()) {
			fail();
		}

	}

	@Test
	void testAddNode() {
		Point3D p1 = new Point3D(1, 0, 0);
		Point3D p2 = new Point3D(0, 1, 0);
		Point3D p3 = new Point3D(0, 0, 1);
		node n1 = new node(1, p1, 1);
		node n2 = new node(1, p2, 10);
		node n3 = new node(2, p3, 100);
		graph g = new DGraph();
		g.addNode(n1);
		g.addNode(n2);
		g.addNode(n3);
		if (g.nodeSize() != 2) {
			fail();
		}
	}

	@Test
	void testConnect() {
		Point3D p1 = new Point3D(1, 0, 0);
		Point3D p2 = new Point3D(0, 1, 0);
		Point3D p3 = new Point3D(0, 0, 1);
		node n1 = new node(0, p1, 1);
		node n2 = new node(1, p2, 10);
		node n3 = new node(2, p3, 100);
		graph g = new DGraph();
		g.addNode(n1);
		g.addNode(n2);
		g.addNode(n3);
		g.connect(n1.getKey(), n2.getKey(), 2);
		g.connect(n2.getKey(), n3.getKey(), 3);
		if (g.edgeSize() != 2) {
			fail();
		}
		if (g.getEdge(n2.getKey(), n3.getKey()).getWeight() != 3) {
			fail();
		}
	}

	@Test
	void testGetV() {
		Point3D p1 = new Point3D(1, 0, 0);
		Point3D p2 = new Point3D(0, 1, 0);
		Point3D p3 = new Point3D(0, 0, 1);
		node n1 = new node(0, p1, 1);
		node n2 = new node(1, p2, 10);
		node n3 = new node(2, p3, 100);
		graph g = new DGraph();
		g.addNode(n1);
		g.addNode(n2);
		g.addNode(n3);
		g.connect(n1.getKey(), n2.getKey(), 2);
		g.connect(n2.getKey(), n3.getKey(), 3);
		assertEquals(g.getV().size(), 3);
	}

	@Test
	void testGetE() {
		Point3D p1 = new Point3D(1, 0, 0);
		Point3D p2 = new Point3D(0, 1, 0);
		Point3D p3 = new Point3D(0, 0, 1);
		node n1 = new node(0, p1, 1);
		node n2 = new node(1, p2, 10);
		node n3 = new node(2, p3, 100);
		graph g = new DGraph();
		g.addNode(n1);
		g.addNode(n2);
		g.addNode(n3);
		g.connect(n1.getKey(), n2.getKey(), 2);
		g.connect(n2.getKey(), n3.getKey(), 3);
		g.connect(n1.getKey(), n3.getKey(), 2);
		assertEquals(g.getE(0).size(), 2);
		//******************fix******************
	}
	

	@Test
	void testRemoveNode() {
		Point3D p1 = new Point3D(1, 0, 0);
		Point3D p2 = new Point3D(0, 1, 0);
		Point3D p3 = new Point3D(0, 0, 1);
		Point3D p4 = new Point3D(2, 5, 1);
		node n1 = new node(0, p1, 1);
		node n2 = new node(1, p2, 10);
		node n3 = new node(2, p3, 100);
		node n4 = new node(3, p4, 1000);
		graph g = new DGraph();
		g.addNode(n1);
		g.addNode(n2);
		g.addNode(n3);
		g.addNode(n4);
		g.connect(n1.getKey(), n2.getKey(), 2);
		g.connect(n2.getKey(), n1.getKey(), 2);
		g.connect(n2.getKey(), n3.getKey(), 3);
		g.removeNode(1);
		if (g.edgeSize() != 0) {
			fail();
		}
		if(g.getEdge(n2.getKey(), n3.getKey()) != null)
			fail();
		if(g.getEdge(n1.getKey(), n2.getKey()) != null)
			fail();
		g.connect(n2.getKey(), n3.getKey(), 3);
		g.connect(n1.getKey(), n2.getKey(), 2);
		if(g.getEdge(n2.getKey(), n3.getKey()) == null)
			fail();
		if(g.getEdge(n1.getKey(), n2.getKey()) == null)
			fail();
	}

	@Test
	void testRemoveEdge() {
		Point3D p1 = new Point3D(1, 0, 0);
		Point3D p2 = new Point3D(0, 1, 0);
		Point3D p3 = new Point3D(0, 0, 1);
		node n1 = new node(0, p1, 1);
		node n2 = new node(1, p2, 10);
		node n3 = new node(2, p3, 100);
		graph g = new DGraph();
		g.addNode(n1);
		g.addNode(n2);
		g.addNode(n3);
		g.connect(n1.getKey(), n2.getKey(), 2);
		g.connect(n2.getKey(), n3.getKey(), 3);
		g.removeEdge(1, 2);
		if (g.edgeSize() != 1) {
			fail();
		}
		if (g.getEdge(n2.getKey(), n3.getKey()) != null) {
			fail();
		}
		if (g.getEdge(n1.getKey(), n2.getKey()) == null) {
			fail();
		}
		g.removeEdge(0, 1);
		if (g.edgeSize() != 0) {
			fail();
		}
		if (g.getEdge(n2.getKey(), n3.getKey()) != null) {
			fail();
		}
		if (g.getEdge(n1.getKey(), n2.getKey()) != null) {
			fail();
		}
	}

	@Test
	void testNodeSize() {
		Point3D p1 = new Point3D(1, 0, 0);
		Point3D p2 = new Point3D(0, 1, 0);
		Point3D p3 = new Point3D(0, 0, 1);
		node n1 = new node(0, p1, 1);
		node n2 = new node(1, p2, 10);
		node n3 = new node(2, p3, 100);
		graph g = new DGraph();
		g.addNode(n1);
		g.addNode(n2);
		g.addNode(n3);
		g.connect(n1.getKey(), n2.getKey(), 2);
		g.connect(n2.getKey(), n3.getKey(), 3);
		assertEquals(g.nodeSize(), 3);
	}

	@Test
	void testEdgeSize() {
		Point3D p1 = new Point3D(1, 0, 0);
		Point3D p2 = new Point3D(0, 1, 0);
		Point3D p3 = new Point3D(0, 0, 1);
		node n1 = new node(0, p1, 1);
		node n2 = new node(1, p2, 10);
		node n3 = new node(2, p3, 100);
		graph g = new DGraph();
		g.addNode(n1);
		g.addNode(n2);
		g.addNode(n3);
		g.connect(n1.getKey(), n2.getKey(), 2);
		g.connect(n2.getKey(), n3.getKey(), 3);
		assertEquals(g.edgeSize(), 2);
	}

	@Test
	void testGetMC() {
		Point3D p1 = new Point3D(1, 0, 0);
		Point3D p2 = new Point3D(0, 1, 0);
		Point3D p3 = new Point3D(0, 0, 1);
		node n1 = new node(0, p1, 1);
		node n2 = new node(1, p2, 10);
		node n3 = new node(2, p3, 100);
		graph g = new DGraph();
		g.addNode(n1);
		g.addNode(n2);
		g.addNode(n3);
		g.connect(n1.getKey(), n2.getKey(), 2);
		g.connect(n2.getKey(), n3.getKey(), 3);
		g.removeEdge(1, 2);
		g.removeEdge(0, 1);
		assertEquals(g.getMC(), 7);
	}

}

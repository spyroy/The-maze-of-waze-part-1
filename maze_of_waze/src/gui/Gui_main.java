package gui;

import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.graph;
import dataStructure.node;
import utils.Point3D;

public class Gui_main {

	public static void main(String[] args) {
		
		Point3D p1 = new Point3D(99, 95);
		Point3D p2 = new Point3D(203, 96);
		Point3D p3 = new Point3D(154, 152);
		Point3D p4 = new Point3D(455, 151);
		Point3D p5 = new Point3D(687, 206);
		Point3D p6 = new Point3D(142, 702);
		Point3D p7 = new Point3D(232, 437);
		Point3D p8 = new Point3D(300, 602);
		node n1 = new node(1,p1, 5);
		node n2 = new node(2,p2, 10);
		node n3 = new node(3,p3, 15.5);
		node n4 = new node(4,p4, 111);
		node n5 = new node(5,p5, 1111);
		node n6 = new node(6,p6, 204.1);
		node n7 = new node(7,p7, 200);
		node n8 = new node(8,p8, 22.123);
		
		graph g = new DGraph();
		g.addNode(n1);
		g.addNode(n2);
		g.addNode(n3);
		g.addNode(n4);
		g.addNode(n5);
		g.addNode(n6);
		g.addNode(n7);
		g.addNode(n8);
		g.connect(n1.getKey(), n2.getKey(), 7);
		g.connect(n2.getKey(), n1.getKey(), 2.77);
		g.connect(n2.getKey(), n3.getKey(), 4.5);
		g.connect(n5.getKey(), n3.getKey(), 10);
		g.connect(n6.getKey(), n4.getKey(), 4.11);
		g.connect(n1.getKey(), n5.getKey(), 3.55);
		g.connect(n7.getKey(), n4.getKey(), 42);
		g.connect(n1.getKey(), n5.getKey(), 23);
		g.connect(n6.getKey(), n2.getKey(), 4.20);
		g.connect(n5.getKey(), n1.getKey(), 21.1);
		g.connect(n2.getKey(), n4.getKey(), 88.2);
		g.connect(n2.getKey(), n6.getKey(), 40);
		g.connect(n4.getKey(), n7.getKey(), 4.55);
		g.connect(n4.getKey(), n8.getKey(), 50.22);
		g.connect(n3.getKey(), n5.getKey(), 4.60);
		g.connect(n4.getKey(), n6.getKey(), 4.23);
		g.connect(n8.getKey(), n4.getKey(), 4.78);

		Graph_Gui a = new Graph_Gui(g);
		a.setVisible(true);

	}

}
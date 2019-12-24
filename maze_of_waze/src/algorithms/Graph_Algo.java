package algorithms;

import java.util.ArrayList;
import java.util.Collection;

import java.util.HashSet;

import java.util.List;

import java.util.PriorityQueue;
import java.util.Set;

import dataStructure.DGraph;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node;
import dataStructure.node_data;
import utils.Point3D;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * This empty class represents the set of graph-theory algorithms which should
 * be implemented as part of Ex2 - Do edit this class.
 * 
 * @author
 *
 */
public class Graph_Algo implements graph_algorithms {

	private graph g;

	@Override
	public void init(graph g) {
		this.g = g;
	}

	@Override
	public void init(String file_name) {
		try {
			FileInputStream file = new FileInputStream(file_name);
			ObjectInputStream in = new ObjectInputStream(file);
			g = (graph) in.readObject();
			in.close();
			file.close();
			System.out.println(in);
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			e.printStackTrace();
		}
	}

	@Override
	public void save(String file_name) {

		try {
			FileOutputStream file = new FileOutputStream(file_name);
			ObjectOutputStream out = new ObjectOutputStream(file);
			out.writeObject(g);
			out.close();
			file.close();
		} catch (Throwable e) {
			System.out.println("problem with file");
			e.printStackTrace();
		}

	}

	@Override
	public boolean isConnected() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double shortestPathDist(int src, int dest) {
		// reset tag
		for(node_data n : g.getV()) {
			n.setTag(0);
		}
		node_data nd = g.getNode(dest);
		Collection<node_data> nodes = g.getV();
		PriorityQueue<node_data> unvisited = new PriorityQueue<node_data>(g.nodeSize(), new node_Comperator());
		Set<node_data> visited = new HashSet<node_data>();
		visited.add(g.getNode(src));
		java.util.Iterator<node_data> it = nodes.iterator();
		while (it.hasNext()) {
			node_data n = it.next();
			if (n.getKey() == src)
				n.setWeight(0);
			else
				n.setWeight(Double.MAX_VALUE);
			unvisited.add(n);
		}
		while (!unvisited.isEmpty()) {
			node_data tmp = unvisited.poll();
			Collection<edge_data> edges = g.getE(tmp.getKey());
			if (edges != null) {
				// for all neighbors
				for (edge_data edge : edges) {
					node_data neighbor = g.getNode(edge.getDest());
					if (neighbor.getTag() != 1) {
						double weight = tmp.getWeight() + edge.getWeight();
						if (weight < neighbor.getWeight()) {
							neighbor.setWeight(weight);
							neighbor.setInfo(tmp.getKey() + "");
						}
						PriorityQueue<node_data> tmp_q = new PriorityQueue<>(g.nodeSize(),
								new node_Comperator());
						while (!unvisited.isEmpty()) {
							tmp_q.add(unvisited.poll());
						}
						unvisited = tmp_q;
					}
				}
				tmp.setTag(1);
			}
		}
		return nd.getWeight();
	}

	@Override
	public List<node_data> shortestPath(int src, int dest) {
		// shortestPathDist(src,dest);
		ArrayList<node_data> ans = new ArrayList<node_data>();
		int des = dest;
		while (g.getNode(des).getKey() != src) {
			if (g.getNode(des).getInfo() == null)
				return null;
			int n = Integer.parseInt(g.getNode(des).getInfo());
			ans.add(g.getNode(n));
			des = n;
		}
		ans.add(g.getNode(dest));
		ans.sort(new node_Comperator());
		return ans;
	}

	@Override
	public List<node_data> TSP(List<Integer> targets) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public graph copy() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void main(String[] args) throws Exception {

		Point3D p1 = new Point3D(1, 0, 0);
		Point3D p2 = new Point3D(0, 1, 0);
		Point3D p3 = new Point3D(0, 0, 1);
		Point3D p4 = new Point3D(2, 5, 1);
		Point3D p5 = new Point3D(2, 3, 5);
		Point3D p6 = new Point3D(4, 6, 1);
		Point3D p7 = new Point3D(6, 6, 1);
		node n1 = new node(0, p1, 0);
		node n2 = new node(1, p2, 0);
		node n3 = new node(2, p3, 0);
		node n4 = new node(3, p4, 0);
		node n5 = new node(4, p4, 0);
		node n6 = new node(5, p4, 0);
		node n7 = new node(6, p4, 0);
		graph g = new DGraph();
		g.addNode(n1);
		g.addNode(n2);
		g.addNode(n3);
		g.addNode(n4);
		g.addNode(n5);
		g.addNode(n6);
		g.addNode(n7);
		g.connect(0, 1, 4);
		g.connect(0, 2, 3);
		g.connect(0, 3, 7);
		g.connect(1, 2, 6);
		g.connect(1, 4, 5);
		g.connect(2, 4, 11);
		g.connect(2, 3, 8);
		g.connect(3, 4, 2);
		g.connect(3, 5, 5);
		g.connect(4, 5, 10);
		g.connect(4, 6, 2);
		g.connect(5, 6, 3);
		g.connect(6, 3, 2);

		Graph_Algo algo = new Graph_Algo();
		Graph_Algo algo2 = new Graph_Algo();
		algo.init(g);

		System.out.println(algo.shortestPathDist(0, 6));
		for (node_data n : algo.shortestPath(0, 6)) {
			System.out.print(n.getKey() + ", ");
		}
//		algo.print();
//		graph m=algo.copy();
//		System.out.println("**************");
//		for(node_data node: m.getV()) {
//			System.out.println(node.toString());
//			System.out.println("Edges:\n");
//			System.out.println(m.getE(node.getKey()));
//		}
//		DGraph g2 = new DGraph(4);
//		g2.connect(0, 1, 10);
//		g2.connect(1, 2, 20);
//		g2.connect(2, 4, 30);
//		//g2.connect(4, 2, 40);
//		g2.connect(2, 3, 1);
//		g2.connect(3, 0, 2);
//		algo.init(g2);
//		algo.save("file.txt");
//		algo2.init("file.txt");
//		algo2.print();
		// System.out.println(algo.isConnected());
	}
}

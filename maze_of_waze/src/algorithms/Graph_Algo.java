package algorithms;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
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
public class Graph_Algo implements graph_algorithms
{

	public HashMap<Integer, node_data> nodesMap = new HashMap<Integer, node_data>();
	public HashMap<Integer, HashMap<Integer, edge_data>> edgesMap = new HashMap<Integer, HashMap<Integer, edge_data>>();
	public int edgesCounter = 0;
	public int MC = 0;
	private graph g;

	@Override
	/**
	 * function for the programmer
	 */
	public void init(graph g) 
	{
		if (g instanceof DGraph)
			this.g = (DGraph) g;
		else
			throw new RuntimeException("Error initiallaizing the graph");
	}

	@Override
	public void init(String file_name) 
	{
		try 
		{
			FileInputStream file = new FileInputStream(file_name);
			ObjectInputStream in = new ObjectInputStream(file);
			g = (graph) in.readObject();
			in.close();
			file.close();
			System.out.println(in);
		} catch (IOException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void save(String file_name) 
	{

		try 
		{
			FileOutputStream file = new FileOutputStream(file_name);
			ObjectOutputStream out = new ObjectOutputStream(file);
			out.writeObject(g);
			out.close();
			file.close();
		} catch (Throwable e)
		{
			System.out.println("problem with file");
			e.printStackTrace();
		}

	}

	@Override
	public boolean isConnected()
	{
		Collection<node_data>nodes=g.getV();
		Iterator<node_data>it=nodes.iterator();
		Graph_Algo gr=new Graph_Algo();
		gr.init(g);
		while(it.hasNext())
		{
			node_data tmp=it.next();
			for(node_data n:g.getV())
			{
				if(gr.shortestPathDist(tmp.getKey(), n.getKey())==-1)
					return false;
			}
		}
		return true;
	}

//	@SuppressWarnings("null")//For programmer
	@Override
	/**
	 * Diaxtra algorithm
	 * If there is no path : -1 is returned
	 */
	public double shortestPathDist(int src, int dest) 
	{
		// reset tag
		for (node_data n : g.getV())
		{
			n.setTag(0);
		}
		node_data nd = g.getNode(dest);
		Collection<node_data> nodes = g.getV();
		PriorityQueue<node_data> unvisited = new PriorityQueue<node_data>(g.nodeSize(), new node_Comperator());
//		Set<node_data> visited = new HashSet<node_data>();
//		visited.add(g.getNode(src));
		Iterator<node_data> it = nodes.iterator();
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
			if (g.getE(tmp.getKey()) != null) {
				Collection<edge_data> edges = g.getE(tmp.getKey());

				// for all neighbors
				for (edge_data edge : edges) {
					node_data neighbor = g.getNode(edge.getDest());
					if (neighbor.getTag() != 1) {
						double weight = tmp.getWeight() + edge.getWeight();
						if (weight < neighbor.getWeight()) {
							neighbor.setWeight(weight);
							neighbor.setInfo(tmp.getKey() + "");
						}
						PriorityQueue<node_data> tmp_q = new PriorityQueue<>(g.nodeSize(), new node_Comperator());
						while (!unvisited.isEmpty())
							tmp_q.add(unvisited.poll());
						unvisited = tmp_q;
					}
				}
				tmp.setTag(1);
			}
		}
		if(nd.getWeight()==Double.MAX_VALUE)
		{
			System.err.println("There is no path between "+src+" to "+dest);
			return -1;
		}
		return nd.getWeight();
	}

	@Override
	public List<node_data> shortestPath(int src, int dest)
	{
		ArrayList<node_data> ans = new ArrayList<node_data>();
		if(shortestPathDist(src, dest)==-1)
		{
			return ans;//Empty ArrayList 
		}
		int des = dest;
		while (g.getNode(des).getKey() != src) {
			if (g.getNode(des).getInfo().isEmpty())
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
	/**
	 * TRAVEL SALESMAN PROBLEM We need to compute the simple route between two
	 * vertexes, using shortestPath functions
	 */
	public List<node_data> TSP(List<Integer> targets) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * DEEP COPY
	 */
	public graph copy() {

		graph copy = new DGraph();
		Collection<node_data> nodes = g.getV();
		for (node_data n : nodes) {
			node no = new node(n.getKey(), n.getLocation(), n.getWeight());
			copy.addNode(no);// Add node
			copy.getNode(n.getKey()).setInfo(n.getInfo());//Set info to node
			copy.getNode(n.getKey()).setTag(n.getTag());//Set tag to node
		}

		for (node_data n : nodes)
		{
			Collection<edge_data> edges = g.getE(n.getKey());
			if(edges!=null){
				for (edge_data e : edges)
				{// Add all edges (0 or more) by connecting key,dest and weight
					copy.connect(n.getKey(), e.getDest(), e.getWeight());
				}
			}
		}
		return copy;
	}

	/**
	 * self checking
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		graph g = new DGraph();
		Point3D p1 = new Point3D(1, 0, 10);
		Point3D p2 = new Point3D(-5, 1, 2.5);
		Point3D p3 = new Point3D(10, 100, 20);
		Point3D p4 = new Point3D(0, 0, 1);
		Point3D p5 = new Point3D(-5, -10, -7);
		Point3D p6 = new Point3D(6, 111, 2);
		Point3D p7 = new Point3D(4.2, 5.5, -50);
		node n1 = new node(0, p1, 2);
		node n2 = new node(1, p2, 1);
		node n3 = new node(2, p3, 1);
		node n4 = new node(3, p4, 1);
		node n5 = new node(4, p5, 1);
		node n6 = new node(5, p6, 1);
		node n7 = new node(6, p7, 1);
		g.addNode(n1);
		g.addNode(n2);
		g.addNode(n3);
		g.addNode(n4);
		g.addNode(n5);
		g.addNode(n6);
		g.addNode(n7);
		g.connect(0, 2, 7);
		g.connect(0, 1, 5);
		g.connect(2, 3, 2);
		g.connect(1, 3, 4);
		g.connect(2, 4, 3);
		g.connect(1, 4, 9);
		g.connect(4, 5, 2);
		g.connect(4, 6, 1);
		g.connect(5, 6, 3);
		g.connect(3, 4, 10);
		g.connect(6, 5, 10);
		Graph_Algo gr = new Graph_Algo();
		gr.init(g);
		System.out.println(gr.shortestPathDist(0, 6));
		List<node_data>list=gr.shortestPath(0, 6);
		for (node_data n : list)
			System.out.print(n.getKey() + "-->");
		System.out.println();
		System.out.println(gr.isConnected());
		graph copy1 = new DGraph();
		copy1 = gr.copy();
		Graph_Algo gr_copy = new Graph_Algo();
		gr_copy.init(copy1);
		System.out.println(gr_copy.shortestPathDist(0, 6));
		for (node_data n : gr_copy.shortestPath(0, 6))
			System.out.print(n.getKey() + "-->");
		System.out.println();
		g.connect(2, 0, 50);
		g.connect(3, 2, 10);
		g.connect(3, 1, 5);
		g.connect(6, 4, 2);
		g.connect(4, 3, 5);
		gr.init(g);
		System.out.println(gr.isConnected());
		
	}
}

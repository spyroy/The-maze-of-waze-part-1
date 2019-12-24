package algorithms;

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
public class Graph_Algo implements graph_algorithms {

	public HashMap<Integer, node_data> nodesMap = new HashMap<Integer, node_data>();
	public HashMap<Integer, HashMap<Integer,edge_data>> edgesMap = new HashMap<Integer, HashMap<Integer,edge_data>>();
	public int edgesCounter=0;
	public int MC=0;
	private graph g;

	@Override
	public void init(graph g) {
		if(g instanceof DGraph) 
			this.g = (DGraph) g; 	
		else 
			throw new RuntimeException("Error initialaizing the graph");
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
		graph copy = g;
		return copy;
	}
}

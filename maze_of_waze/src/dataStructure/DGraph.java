package dataStructure;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Set;

import javax.management.RuntimeErrorException;
import javax.swing.text.html.HTMLDocument.Iterator;

public class DGraph<edgeID> implements graph,Serializable {

	private int edgescount = 0;
	private int MC = 0;
//	private edge edge[];
//	private node vertex[];

	private HashMap<Integer, node_data> nodeMap = new HashMap<Integer, node_data>();
	private HashMap<Integer, HashMap<Integer, edge_data>> edgeMap = new HashMap<Integer, HashMap<Integer, edge_data>>();

	public DGraph() {
		this.nodeMap = new HashMap<Integer, node_data>();
		this.edgeMap = new HashMap<Integer, HashMap<Integer, edge_data>>();
		this.edgescount = 0;
		this.MC = 0;
	}
	public DGraph(DGraph other)
	{
		this.nodeMap=other.nodeMap;
		this.edgeMap=other.edgeMap;
		this.edgescount=other.edgescount;
		this.MC=other.MC;
	}
	@Override
	public node_data getNode(int key) {
		return this.nodeMap.get(key);
	}

	@Override
	public edge_data getEdge(int src, int dest) {
		if(this.edgeMap.isEmpty() ||  !this.edgeMap.containsKey(src) || this.edgeMap.get(src).get(dest) == null)
			return null;
		return this.edgeMap.get(src).get(dest);
	}

	@Override
	public void addNode(node_data n) {
		if (nodeMap.keySet().contains(n.getKey())) {
			System.err.println("Err: key already exists, add fail");
			return;
		}
		if(n.getWeight()<0)
		{
			System.err.println("The weight must be positive! . The node hadn't been added successfully..");
			return;
		}
		int key = n.getKey();
		this.nodeMap.put(key, n);//n used to be casted into (node)
		MC++;
	}

	@Override
	public void connect(int src, int dest, double w)
	{
		if(w<=0)
		{
			System.err.println("The weight must be positive! . connect failed");
			return;
		}
		edge e = new edge(src, dest, w);
		if (nodeMap.get(src) == null || nodeMap.get(dest) == null) {
			System.err.println("can't connect");
			//throw new RuntimeErrorException(null);
		}
		if (edgeMap.get(src) != null) {
			edgeMap.get(src).put(dest, e);
			edgescount++;
			MC++;
		} else {
			this.edgeMap.put(src, new HashMap<Integer, edge_data>());
			this.edgeMap.get(src).put(dest, e);
			edgescount++;
			MC++;
		}

	}

	@Override
	public Collection<node_data> getV() {
		Collection<node_data> col = this.nodeMap.values();
		return col;
	}

	@Override
	public Collection<edge_data> getE(int node_id)
	{
		if(this.edgeMap.get(node_id)==null)
			return null;
		Collection<edge_data> col =  this.edgeMap.get(node_id).values();
		return col;
	}

	@Override
	public node_data removeNode(int key) {
//		node_data n = nodeMap.get(key);
//		Collection<edge_data> col = (Collection<edge_data>)this.getE(key);
//		java.util.Iterator<edge_data> it = col.iterator();
//		while(it.hasNext()) {
//			edgeMap.remove(it.next());
//			edgescount--;
//		}
//		this.nodeMap.remove(key);
//		LinkedList<Integer> list = new LinkedList<Integer>();// to-Delete all empty HashMaps.
//		
//		// remove all edges going into key-node.
//		this.edgeMap.forEach((keys, node) -> {
//			if (node.get(key) != null) {
//				node.remove(key);
//				edgescount--;
//				if (node.isEmpty()) {
//					list.add(keys);
//				}
//			}
//		});
//		for (int e : list) {
//			this.edgeMap.remove(e);
//		}
		
		if (this.nodeMap.get(key) == null) {
			return null;
		}
		node_data n = nodeMap.get(key);
		Set<Integer> edgeKeys = edgeMap.keySet();
		for(Integer node: edgeKeys) {
			if(edgeMap.get(node).get(key)!=null) 
			{
				edgeMap.get(node).remove(key);
				edgescount--;
			}
		}

		// remove all edges coming out of key-node.
		if(this.edgeMap.get(key) != null ) 
			edgescount -= this.edgeMap.get(key).values().size();
		this.edgeMap.remove(key);
		// remove the key-node.
		this.nodeMap.remove(key);
		MC++;

		return n;
	}

	@Override
	public edge_data removeEdge(int src, int dest) {
		if(getEdge(src,dest) == null) {
			return null;
		}
		edge e = (edge) this.edgeMap.get(src).get(dest);
		this.edgeMap.get(src).remove(dest);
		edgescount--;
		MC++;
		return e;
	}

	@Override
	public int nodeSize() {
		return this.nodeMap.size();
	}

	@Override
	public int edgeSize() {
		return edgescount;
	}

	@Override
	public int getMC() {
		return MC;
	}

}
package algorithms;

import java.util.HashMap;
import java.util.List;

import dataStructure.DGraph;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;
/**
 * This empty class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2 - Do edit this class.
 * @author 
 *
 */
public class Graph_Algo implements graph_algorithms{

	public HashMap<Integer, node_data> nodesMap = new HashMap<Integer, node_data>();
	public HashMap<Integer, HashMap<Integer,edge_data>> edgesMap = new HashMap<Integer, HashMap<Integer,edge_data>>();
	public int edgesCounter=0;
	public int MC=0;
	private DGraph gr;
	@Override
	public void init(graph g)
	{
		if(g instanceof DGraph) 
			this.gr=(DGraph) g; 	
		else 
			throw new RuntimeException("Error initialaizing the graph"); 
	}

	@Override
	public void init(String file_name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(String file_name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isConnected() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double shortestPathDist(int src, int dest) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<node_data> shortestPath(int src, int dest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<node_data> TSP(List<Integer> targets) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public graph copy() 
	{
		graph copy = new DGraph(this.gr);
		return copy;
	}	

}

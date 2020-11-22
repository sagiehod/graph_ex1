package ex1.src;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import ex1.src.WGraph_DS.NodeInfo;



public class WGraph_Algo  implements weighted_graph_algorithms,Serializable {
	
	private weighted_graph WGraph;
	/**
	 * constructor
	 */
	 public WGraph_Algo() {
		WGraph=new WGraph_DS();
	}
		/**
		 * Init this set of algorithms on the parameter - graph.
		 * @param g
		 */
	@Override
	public void init(weighted_graph g) {
		this.WGraph=g;
	}
	 /**
     * Return the underlying graph of which this class works.
     * @return
     */
	@Override
	public weighted_graph getGraph() {
		return WGraph;
	}
	/** 
	 *  deep copy of this graph.
	 * @return copyGraph;
	 */
	@Override
	public weighted_graph copy() {
		weighted_graph copyGraph= new WGraph_DS (WGraph);
		return copyGraph;
	}
	/**
	 * bfs algoritm
	 * Returns true if and only if (iff) there is a valid path from EVREY node to each
	 * tells if the graph is connected or not.
	 */
	@Override
	public boolean isConnected() {
		//Queue for store
				Queue<node_info> q= new LinkedList<>();
				//Edge test
				if(this.WGraph==null||this.WGraph.nodeSize()==0||this.WGraph.nodeSize()==1)
					return true;
				
				node_info s=this.WGraph.getV().iterator().next();
				 // bfs Algorithm
				//If we visited the node then mark it with zero
				s.setTag(0);
				q.add(s);		
				while(!q.isEmpty()) {
					node_info t=q.poll();
					for(node_info i:((NodeInfo) t).getNi().values()) {
						if(i.getTag()!=0) {
							i.setTag(0);				
							q.add(i);
						}
					}
				}
				for(node_info i:this.WGraph.getV())
				{
					if(i.getTag()!=0) {
						inittag();
						return false;
					}
				}
				inittag();
				return true;
			}
			//Initializes the tag to -1 and the 
	// dist POSITIVE_INFINITY 
			private void inittag() {
				for(node_info i : this.WGraph.getV()) {
					i.setTag(-1);
					((NodeInfo)i).setDist(Double.POSITIVE_INFINITY);
				}
			}	
			/**
			 * dijkstra  algoritm
			 * returns the  length of the shortest path dist between src to dest
			 * @param src - start node
			 * @param dest - end (target) node
			 * @return size path from src until dest
			 */
	
	@Override

	public double shortestPathDist(int src, int dest) {
		//check
		if(WGraph.getNode(src)==null||WGraph.getNode(dest)==null) 
		{
		return-1;
	}
		if (src == dest) {
			return 0;
		}
		
	if (src < 0 || dest < 0)
	{
		return -1;
	}
		
	if (this.WGraph.getV().size() == 0 || this.WGraph == null)
	{
		return -1;
	}

		
		// go  to dijkstra algo
	this.dijkstra(src);
	double answer=((NodeInfo)this.WGraph.getNode(dest)).getDist();
	if(answer==Double.POSITIVE_INFINITY) {
		inittag();
		return -1;
	}
	inittag();
	return answer;
	}
	
	/**
	 * dijkstra algoritm
	 * returns the  shortest path between src to dest that have min dist - as an ordered List of nodes:
	 * @param src - start node
	 * @param dest - end (target) node
	 * @return path
	 */
	@Override
	public List<node_info> shortestPath(int src, int dest) {
 //check
		
	if (this.WGraph.getV().size() == 0 || this.WGraph == null)
	{
		return null;
	}
	if(WGraph.getNode(src)==null||WGraph.getNode(dest)==null) 
		{
		return null;
	}
	//store for the parents 
	HashMap<node_info,node_info> parntes=this.dijkstra(src);

	List<node_info> s_path=new ArrayList<>();
	node_info d=this.WGraph.getNode(dest);
	while(d!=null) {
		s_path.add(d);
		d=parntes.get(d);
	}
	
//		double dd = WGraph.getNode(dest).getTag();
//	if(dd==Double.POSITIVE_INFINITY) {
//		inittag();
//		return null;
	//}
	Collections.reverse(s_path);

	inittag();
	if(s_path.get(0).getKey()!=src) {
		return null;
	}
	return s_path;
	}
	
	
	
	//Dijkstra's algo for shortestPath and shortestPathDist
private HashMap<node_info,node_info> dijkstra(int src){

	HashMap<node_info,node_info> parents=new HashMap<node_info,node_info>();
	//PriorityQueue for Sort store
	PriorityQueue<node_info> PriorQueue=new PriorityQueue<node_info>(
			(a,b) -> (int)((NodeInfo)a).getDist() -  (int)((NodeInfo)b).getDist());
	//initialization
	node_info s = WGraph.getNode(src);
	PriorQueue.add(s);
	((NodeInfo)s).setDist(0);
	// while not all node reached
			while(!PriorQueue.isEmpty()) {
				//Extract  vertex from Set.
				NodeInfo t=(NodeInfo) PriorQueue.poll();
				 //Loop through all adjacent of u and do following for every destination
					// node.
				{
				for(node_info i:t.getNi().values()) {
					if(i.getTag()!=0) {
						double d=  t.getDist()+((NodeInfo)i).getWi().get(t.getKey());
						if(d<((NodeInfo)i).getDist()) {
							((NodeInfo)i).setDist(d);
							
							parents.put(i, t);
							PriorQueue.remove(i);
							
							PriorQueue.add(i);
						}
					}
				}
				t.setTag(0);
			}
			}
			return parents;
			}


/**
 * Saves this weighted (undirected) graph to the given
 * file name
 * @param file - the file name (may include a relative path).
 * @return true - iff the file was successfully saved
 */
	@Override
	public boolean save(String file) {
		try
		{
			FileOutputStream file1 = new FileOutputStream(file);
			ObjectOutputStream out = new ObjectOutputStream(file1);

			out.writeObject(this.WGraph);

			out.close();
			file1.close();
            return true;
		}
		catch(IOException ex)
		{
			System.out.println("IOException is caught");
			return false;

		}
	}
	 /**
     * This method load a graph to this graph algorithm.
     * @param file - file name
     * @return true - iff the graph was successfully loaded.
     */
	@Override
	public boolean load(String file) {
		try {
			FileInputStream file1 = new FileInputStream(file);
			ObjectInputStream in = new ObjectInputStream(file1);
			this.WGraph = (weighted_graph) in.readObject();
			in.close();
			file1.close();
			System.out.println("Object has been deserialized");
			return true;
		}
		catch(IOException ex)
		{
			System.out.println("IOException is caught");
			return false;
		}
		catch(ClassNotFoundException ex)
		{
			System.out.println("ClassNotFoundException is caught");
			return false;
			
		}
	}

}

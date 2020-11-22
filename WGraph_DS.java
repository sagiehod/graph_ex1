package ex1.src;
import java.util.Random;


import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;



public class WGraph_DS implements weighted_graph,Serializable{

	private int Mc;
	private int numOfEdges;
	private HashMap<Integer,node_info> nodes;

	public WGraph_DS() {
		nodes = new HashMap<>();
		numOfEdges = 0;
		numOfEdges=0;
	}
	/**
	 * @param gh
	 * A init hash map data of graph 
	 */
	public WGraph_DS (weighted_graph gh) {

		this.Mc=gh.getMC();
		this.numOfEdges=gh.edgeSize();

		Iterator<node_info> it = gh.getV().iterator();
		HashMap< Integer,node_info> temp=new HashMap< Integer,node_info>();
		while(it.hasNext()) {
			node_info t=it.next();
			temp.put(t.getKey(),t);
		}
		nodes=temp;
	}
	//inner classes Node info that implements of node_info
	public class NodeInfo implements node_info,Serializable {
		private String str;
		private int _key;
		private double tag;
		private double dist;
		private HashMap <Integer, node_info> map_info =new HashMap<Integer,node_info>();
		private HashMap <Integer, Double> map_weight =new HashMap<Integer,Double>();
		public  NodeInfo(int key)  {
			_key=key;
			tag=-1;
			str= "";
			dist=Double.POSITIVE_INFINITY;
		}
		public NodeInfo(node_info Node) {
			this.str=Node.getInfo();
			this._key=Node.getKey();
			this.tag=Node.getTag();
			Iterator<node_info> it = ((NodeInfo) Node).getNi().values().iterator();
			HashMap< Integer,node_info> temp=new HashMap< Integer,node_info>();
			HashMap< Integer,Double> temp1=new HashMap< Integer,Double>();
			while(it.hasNext()) {
				node_info t=it.next();
				temp.put(t.getKey(),t);
				temp1.put(t.getKey(), ((NodeInfo) Node).getWi().values().iterator().next());
			}
			this.map_info=temp;
			this.map_weight=temp1;
		}
		@Override
		public int getKey() {

			return this._key ;
		}

		@Override
		public String getInfo() {
			// TODO Auto-generated method stub
			return str;
		}

		public double getDist() {

			return dist ;
		}
		public void setDist(double v) {
			// TODO Auto-generated method stub
			this.dist=v;
		}
		@Override
		public void setInfo(String s) {
			str =s;		
		}

		@Override
		public double getTag() {
			// TODO Auto-generated method stub
			return tag;	
		}

		@Override
		public void setTag(double t) {
			tag = t;
		}
		//Holds my keys
		public HashMap <Integer, node_info> getNi(){
			return this.map_info;
		}
		//Holds my weight
		public  HashMap <Integer, Double> getWi(){
			return this.map_weight;
		}
	}
	/**
	 * return the node_data by the node_id,
	 * @param key - the node_id
	 * @return the node_data by the node_id, null if none.
	 */
	@Override
	public node_info getNode(int key) {
		if(nodes.containsKey(key)) {
			return  this.nodes.get(key);
		}
		return null;
	}
	/**
	 * Checks if there is a Edge between two nodes
	 * @param node1
	 * @param node2
	 * @return True if there is a connected Edge otherwise false 
	 */
	@Override
	public boolean hasEdge(int node1, int node2) {
		node_info nd1 = getNode(node1);
		if(nd1 != null) {
			return ((NodeInfo) nd1).getNi().containsKey(node2);
		}
		return false; 
	}
	/**
	 * Give  back the weight between the two edge
	 * @param node1
	 * @param node2
	 * @return weight
	 */
	@Override
	public double getEdge(int node1, int node2) {
//		//if(((NodeInfo) getNode(node1)).getNi().containsKey(node2)&&getNode(node1)!=null) {
		if(hasEdge(node1,node2)) {
			return ((NodeInfo) getNode(node1)).getWi().get(node2);
		}
		return -1;
	}
		


	/**
	 * add a new node to the graph with the given node_data.
	 * @param int key
	 */
	@Override
	public void addNode(int key) {	
		if(!nodes.containsKey(key)) {
			
		nodes.put(key, new NodeInfo(key));
		Mc++;
		}
	}
	/**
	 * Connect an edge between node1 and node2, with an edge with weight >=0.
	 * @param node1
	 * @param node2

	 */

	@Override
	public void connect(int node1, int node2, double w) {
		node_info nd1 = nodes.get(node1);
		node_info nd2 = nodes.get(node2);
		if(node1==node2) {
			return;
		}
			
		//weight < 0 throw exception
		try {
			if(w<0)
				throw new Exception("The weight must be positive");
		} catch (Exception e) {e.printStackTrace(); return;	}	


		if(!hasEdge(node1,node2)&&nd1!=null&&nd2!=null) {

			((NodeInfo) nd1).getNi().put(node2, nd2);
			((NodeInfo) nd1).getWi().put(node2, w);

			((NodeInfo) nd2).getNi().put(node1, nd1);
			((NodeInfo) nd2).getWi().put(node1, w);
			numOfEdges++;
			Mc++;
		}
		else if(hasEdge(node1,node2)&&nd1!=null&&nd2!=null) {
		((NodeInfo) nd1).getWi().replace(node2,w);
		((NodeInfo) nd2).getWi().replace(node1,w);
	
		Mc++;
	}
}




/**
 * This method return a pointer (shallow copy) for the
 * collection representing all the nodes in the graph. 
 * @return nodes.values
 */
@Override
public Collection<node_info> getV() {
	return this.nodes.values();
}
/**
 *
 * This method returns a Collection containing all the
 * @param node_id
 * nodes connected to node_id
 * @return Collection<node_data>
 * nodes.remove(key);
 */
@Override
public Collection<node_info> getV(int node_id) {
	 if (getNode(node_id)==null) return null;
	return ((NodeInfo) getNode(node_id)).getNi().values();
}
/**
 * Delete the node (with the given ID) from the graph -
 * and removes all edges which starts or ends at this node.
 * @return the data of the removed node (null if none). 
 * @param key
 */
@Override
public node_info removeNode(int key) {
	if(nodes.containsKey(key)==true) {

		Collection<node_info> neighbors = getV(key);
		node_info node = nodes.get(key);
		Iterator<node_info> it=neighbors.iterator();
		while(it.hasNext()) {
			node_info temp= it.next();
			((NodeInfo) temp).getNi().remove(key);
			((NodeInfo) temp).getWi().remove(key);

			numOfEdges--;
			Mc++;
		}
		Mc++;
		return nodes.remove(key);
	}else {
		return null;
	}
}
/**
 * Delete the edge from the graph,and the weight.
 * @param node1
 * @param node2
 */
@Override
public void removeEdge(int node1, int node2) {
	node_info nd1 = nodes.get(node1);
	node_info nd2 = nodes.get(node2);
	if(nd1 != null && nd2 != null&& hasEdge(node1,node2)==true) {
		((NodeInfo) nd1).getNi().remove(node2);
		((NodeInfo) nd1).getWi().remove(node2);

		((NodeInfo) nd2).getNi().remove(node1);
		((NodeInfo) nd2).getWi().remove(node1);
		numOfEdges--;
		Mc++;
	}
}

/** return the number of vertices (nodes) in the graph.
 * @return nodes.size
 */
@Override
public int nodeSize() {
	return this.nodes.size();
}
/** 
 * return the number of edges 
 * @return numOfEdges
 */
@Override
public int edgeSize() {
	return numOfEdges;
}
/**
 * Any change in the inner state of the graph should cause an increment in the ModeCount
 * @return Mc
 */
@Override
public int getMC() {
	return this.Mc;
}

@Override
public boolean equals(Object obj) {
		if (this == obj)
			return true;
		WGraph_DS other = (WGraph_DS) obj;
		if (Mc != other.Mc)
			return false;
		if (nodes == null) {
			if (other.nodes != null)
				return false;
		} else if (!nodes.equals(other.nodes))
			return false;
		if (numOfEdges != other.numOfEdges)
			return false;
		return true;

			
}


}

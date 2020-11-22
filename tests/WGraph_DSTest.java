package ex1.tests;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;
import java.util.Collection;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.*;

//import javax.swing.text.html.HTMLDocument.Iterator;

import org.junit.jupiter.api.Test;

import ex1.src.WGraph_DS;
import ex1.src.node_info;
import ex1.src.weighted_graph;

import java.util.concurrent.TimeUnit;
class WGraph_DSTest {

	/**
	 * Tests addNode function.
	 */
	@Test
	void testAddNode() {
		//create graph
		weighted_graph g = new WGraph_DS();
		g.addNode(0);
		g.addNode(1);
		g.addNode(3);
		g.addNode(5);
		g.addNode(7);
		g.addNode(9);
		//check edgeSize AND Equals
		assertEquals(g.edgeSize(),0);
		assertNull(g.getNode(-1));
		assertNull(g.getNode(-100));
		assertNotNull(g.getNode(0));
		assertNotNull(g.getNode(1));
		assertNotNull(g.getNode(3));
		assertNotNull(g.getNode(5));
		assertNotNull(g.getNode(7));
		assertNotNull(g.getNode(9));	
		assertEquals(g.nodeSize(),6);
		g.addNode(-1);
		assertNotNull(g.getNode(-1));
		assertEquals(g.nodeSize(),7);
	}
	/**
	 * Tests nodeSize() function.
	 */
	@Test
	void testNodeSize() 
	{
		//create graph
		weighted_graph g = new WGraph_DS();
		g.addNode(0);
		g.addNode(1);
		g.addNode(2);
		//equals
		assertEquals(3,g.nodeSize());
		g.removeNode(0);
		assertEquals(2,g.nodeSize());
		g.removeNode(1);
		assertEquals(1,g.nodeSize());
	}
	/**
	 * Tests connect(node1,node2,weight) function.
	 */
	@Test
	void testConnect() {
		//create graph
		weighted_graph g =new WGraph_DS();
		g.addNode(0);
		g.addNode(1);
		g.addNode(2);
        g.addNode(3);
        //connect
		g.connect(0,1,7);
		g.connect(1,2,14);
		g.connect(0,2,20);
       g.connect(1, 3, 5);
       //Equlas
		assertTrue(g.hasEdge(1,0));
		assertFalse(g.hasEdge(3,0));
		g.removeEdge(1,2);
		//new edge (connect node and new weight)
		g.connect(0,3,8);
		double w = g.getEdge(3,0);
		assertEquals((int)w,8);
	
}

/**
 * Tests removeNode function.
 */
@Test
public void removeNode() {
	//create graph
	weighted_graph g = new WGraph_DS();

	g.addNode(0);
	g.addNode(1);
	g.addNode(2);
	g.addNode(3);
	//Connect Edge with Weight
	g.connect(0,1,7);
	g.connect(1,2,14);
	g.connect(0,2,20);
	g.connect(1, 3, 5);
	//Remove a node that is in the graph.
	node_info node= g.removeNode(1);
	//equlas
	assertEquals(1, node.getKey());
	assertEquals(1, g.edgeSize());
	assertEquals(3, g.nodeSize());
	//Remove a node that isn't in the graph.
	node= g.removeNode(12);
	//equlas

	assertEquals(1, g.edgeSize());
	assertEquals(3, g.nodeSize());
}
/**
 * Tests removeEdge function.
 */
@Test
public void removeEdge() {
	//create graph
	weighted_graph g = new WGraph_DS();
	g.addNode(0);
	g.addNode(1);
	g.addNode(2);
	g.addNode(3);
	//Connect Edge with Weight

	g.connect(0,1,7);
	g.connect(1,2,14);
	g.connect(0,2,20);
	g.connect(1, 3, 5);
	//remove edges
	g.removeEdge(0, 1);
	g.removeEdge(2, 3);		
	g.removeEdge(0, 3);		
	g.removeEdge(3, 11);	
	g.removeEdge(0, 7);		
	g.removeEdge(20, 25);	
	g.removeEdge(9, 9);		
	g.removeEdge(8, 8);
	//equals
	assertEquals(4, g.nodeSize());
	assertEquals(3, g.edgeSize());
}
/**
 * Tests hasEdge function.
 */
@Test
public void hasEdge() {
	//create graph
	weighted_graph g = new WGraph_DS();

	g.addNode(0);
	g.addNode(1);
	g.addNode(2);
	g.addNode(3);
	//Connect Edge with Weight
	g.connect(0,1,7);
	g.connect(1,2,14);
	g.connect(0,2,20);
	g.connect(1, 3, 5);
	//equals
	assertFalse(g.hasEdge(5, 2));			
	assertTrue(g.hasEdge(0, 1));	
	assertFalse(g.hasEdge(1, 15));	
	assertFalse(g.hasEdge(15, 1));

}
}
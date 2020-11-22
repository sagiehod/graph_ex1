package ex1.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ex1.src.WGraph_Algo;
import ex1.src.WGraph_DS;
import ex1.src.weighted_graph;
import ex1.src.weighted_graph_algorithms;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test class for the WGraph_Algo class representing undirectional
 * weighted graph theory algorithms.
 */
class WGraph_AlgoTest {
	weighted_graph_algorithms cg= new WGraph_Algo();
	weighted_graph g = new WGraph_DS();

	@BeforeEach
	void setupGraphs() {
		
		g.addNode(1);
		g.addNode(2);
		g.addNode(3);
		g.addNode(4);
		g.addNode(5);
		g.connect(1, 2, 2);
		g.connect(1, 4, 62.3);
		g.connect(1, 3, 6.3);
		g.connect(2, 4, 1);
		g.connect(3, 5, 4.3);
		cg.init(g);

	}
	
	/**
	 * Test init(graph) function
	 */
	@Test
	public void testInitGraph() {
		cg.init(g);

	}
	/**
	 * Tests copygraph and.
	 */
	@Test
	
	public void copy() {
		weighted_graph_algorithms cg= new WGraph_Algo();
		weighted_graph g = new WGraph_DS();
		cg.init(g);
		weighted_graph gc= cg.copy();
		assertTrue(g.equals(gc));
	}
	@Test
	/**
	 * Tests if IsConnected
	 */
	public void testIsConnected() 
	{

		cg.init(null);
		assertTrue(cg.isConnected());
		
		g= new WGraph_DS();
		cg.init(g);
		assertTrue(cg.isConnected());
		//add node to graph
		g.addNode(0);
		assertTrue(cg.isConnected());
		
		g.addNode(1);
		assertFalse(cg.isConnected());
		//connect edge and weight
		g.connect(1, 0, 5.62);
		assertTrue(cg.isConnected());	
	}
	/**
	 * Tests save and load file 
	 */
	@Test
	public void file() {
		assertFalse(cg.load(""));
		assertFalse(cg.save(""));
		System.out.println();
		assertTrue(cg.save("test1.txt"));
		assertTrue(cg.load("test1.txt"));
		assertFalse(cg.load("test2.txt"));

	}
	/**
	 * Tests shortestPath and shortestPathDist
	 */
	@Test
	public void shortestPath() {
		assertEquals(9.3, cg.shortestPathDist(4, 3));
		assertEquals(2, cg.shortestPath(2, 1).size());
		assertEquals(-1, cg.shortestPathDist(8, 1));
		assertEquals(3, cg.shortestPath(4, 1).size());
}
}
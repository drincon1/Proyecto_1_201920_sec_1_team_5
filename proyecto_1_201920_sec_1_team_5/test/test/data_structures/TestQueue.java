package test.data_structures;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.data_structures.Queue;

public class TestQueue 
{
	
	private Queue<String> queue;
	
	@Before
	public void setUp()
	{
		queue = new Queue<String>();
	}
	
	public void setUp2()
	{
		queue = new Queue<String>();
		queue.enqueue("Primero");
		queue.enqueue("Segundo");
		queue.enqueue("Tercero");
	}
	@Test
	public void testQueue()
	{
		assertTrue(queue != null);
		assertTrue(queue.isEmpty());
	}
	
	@Test
	public void testDequeue()
	{
		setUp2();
		assertEquals("Primero", queue.dequeue());
		assertEquals(2, queue.tamano());
		assertEquals("Tercero", queue.lastElement());
		assertEquals("Segundo", queue.firstElement());
	}
}

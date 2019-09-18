package test.data_structures;

import org.junit.Before;

import junit.framework.TestCase;
import model.data_structures.Stack;;

public class StackTest extends TestCase {
	
	
	private Stack<Integer> pila; 
	
	
	@Before
	public void setUp()
	{
		pila = new  Stack<Integer>();
		pila.push(1);
		pila.push(2);
		pila.push(3);
		
		
		
	}
	
	public void testPush()
	{
		pila.push(10);
		assertEquals("Error en el último", 10 , pila.pop().intValue());
	}
	public void testPop()
	{
		assertEquals("Error al agregar", 3 , pila.pop().intValue());
	}
	
	

}
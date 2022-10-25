package question2;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TestWarmest<K,V> {
	
	Warmest<Integer,String> warmest; 

	void RoutineFlow(){
		warmest.put(1,"Hello");
		warmest.put(2,"Flower");
		warmest.put(3,"Danny");
		warmest.put(4,"Shir");
		warmest.put(5,"Yuval");
		warmest.put(6,"Amit");
		warmest.put(7,"Or");
		warmest.put(8,"Yarden");
		warmest.put(9,"Meirav");
		warmest.get(7);
		warmest.get(2);
		warmest.remove(3);
		warmest.get(1);
		warmest.remove(2);
		warmest.put(11,"Yoav");
		warmest.get(9);
		warmest.remove(6);
		warmest.remove(10);
	}
	
	int SizeOfLinkedList(){
		Node node = new Node();
		node = warmest.getHead();
		Node tail = new Node();
		tail = warmest.getTail();
		int counter = 0;
		while (node.nextN != tail) {
			counter++;
			node = node.nextN;
		}
		return counter;
	}
	
	@BeforeEach
	void setUp() {
		warmest = new Warmest();
	}
	
	@Test
	@DisplayName("Testing correctness of put function")
	void testPut() {
		warmest.put(1,"Hello");
		assertEquals(warmest.getWarmest(),"Hello"); // check if the function sets a new warmest element
		warmest.put(2,"Flower");
		assertEquals(warmest.getWarmest(),"Flower");// check if the function replace the warmest element
		warmest.put(1,"sun");
		assertEquals(warmest.getWarmest(),"sun"); // check if the function replace the value of the same key
		assertEquals(warmest.remove(1),"sun");
		assertNull(warmest.get(1));// check if the function replace the warmest element
	}
	
	@Test
	@DisplayName("Testing correctness of 'get' function")
	void WarmestElementGetFunctionTest() {
		assertEquals("the returned value should be null",null,warmest.get(1));
		warmest.put(1,"Hello");
		warmest.put(2,"Flower");
		assertEquals("the returned value should be Hello","Hello",warmest.get(1));
		assertEquals("the returned value should be Hello","Hello",warmest.getWarmest());
	}

	@Test
	void WarmestElementRemoveFunctionTest() {
		assertEquals("the returned value should be null",warmest.remove(2),null);
		warmest.put(1,"Hello");
		warmest.put(2,"Flower");
		assertEquals("the returned value should be Hello","Hello",warmest.remove(1));
		assertEquals("the returned value should be Flower","Flower",warmest.remove(2));
		assertEquals("the returned value should be null",null,warmest.remove(1));// 
	}
	
	@Test
	void getWarmestElementAfterGetAndPutTest() {
		assertEquals("the returned value should be null",null,warmest.getWarmest());
		warmest.put(1,"Hello");
		assertEquals("the returned value should be Hello","Hello",warmest.getWarmest());
		warmest.put(2,"Flower");
		assertEquals("the returned value should be Flower","Flower",warmest.getWarmest());
		warmest.get(1);
		assertEquals("the returned value should be Hello","Hello",warmest.getWarmest());
		warmest.get(2);
		assertEquals("the returned value should be Flower","Flower",warmest.getWarmest());
		warmest.remove(2);
		assertEquals("the returned value should be Hello","Hello",warmest.getWarmest());
		warmest.remove(1);
		assertEquals("the returned value should be null",null,warmest.getWarmest());		
	}

	@Test
	void LinkedListSizeTest() {
		 RoutineFlow();
		assertEquals("the list size should be 7",7,SizeOfLinkedList());
	}
	
	
	@Test
	void RoutineFlowTest() {
		 RoutineFlow();
		assertEquals("failed - the warmest element should be Meirav","Meirav",warmest.getWarmest());
	}
	
}



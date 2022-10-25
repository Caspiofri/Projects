package question2;
import java.util.HashMap; 

class Node<K,V>
	{  
		K key;
	    V value;    
	    Node<K,V> nextN;  
	    Node<K,V> prevN;  
	 
	    public Node() {
	    	this.key = null;
	    	this.value = null; 
	    	this.nextN = null; 
	    	this.prevN = null; 
	    }    
	    public Node(K key,V value) {
	    	this.key = key;
	    	this.value = value; 
	    	this.nextN = null; 
	    	this.prevN = null; 
	    }    	    
	}
      
public class Warmest<K,V> {

	HashMap<K, Node<K,V>> map = new HashMap<K,Node<K,V>>();
	private Node<K,V> head = new Node(); 
	private Node<K,V> tail  = new Node();
	
	public Warmest() {
		head.nextN = tail;
		tail.prevN = head;
	};
	
	public void insertNode(Node node) {
			if (head.nextN == tail){
				node.nextN = tail;
				node.prevN = head;
				tail.prevN = node;
				head.nextN = node;
			}
			else { 
				Node previous = head.nextN;
				node.nextN = previous;
				head.nextN = node;
				previous.prevN = node;
				node.prevN = head;	
			}	
		}

		public void removeNode(Node node) {
			if (head.nextN == tail ) 
				return;
			else {
				Node next = node.nextN;
				next.prevN = node.prevN;
				node = node.prevN;
				node.nextN = next;
			}
		}	
		
	public void put (K newKey, V newValue) {
		if (map.containsKey(newKey)){
			Node node = map.get(newKey);
			removeNode(node);
			node.value = newValue;
			insertNode(node);
		}
		else {
			Node node = new Node<K,V>(newKey,newValue);
			insertNode(node);
			map.put(newKey, node);
		}
	}
	
	public Node getHead() {
		return this.head;
	}
	
	public Node getTail() {
		return this.tail;
	}
	
	public V get(K key) {
		Node node = map.get(key);
		if (node == null)
			return null;
		removeNode(node);
		insertNode(node);
		return (V) node.value;
	}
	
	public V remove(K key) {
		Node node = map.get(key);
		if (node== null)
			return null;
		removeNode(node);
		map.remove(key);
		return (V) node.value;
	}
				
	public V getWarmest() {
		if (head.nextN == null)
			return null;
		return head.nextN.value;
	}	
}

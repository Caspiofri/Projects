package question2;
import java.util.HashMap; 

class Node<K,V>
	{  
		K key;
	    V value;    
	    Node<K,V> nextN;  
	    Node<K,V> prevN;  
	 
	    public Node(K key,V value) {
	    	this.key = key;
	    	this.value = value; 	
	    }    
	    
	}
      
public class Warmest<K,V> {
		
	HashMap<K, Node<K,V>> map = new HashMap<K,Node<K,V>>();
	private Node<K,V> head = new Node(null, null); 
	private Node<K,V> tail  = new Node(null, null); 

   public void insertNode(Node node) {
			if (head.nextN == null ) {
				head.nextN = node;
				tail.prevN = node;
				node.nextN =tail;
				node.prevN = head;
			}
			else { 
				head.nextN.prevN = node;
				head.nextN.prevN.nextN = head.nextN;
				head.nextN = node;
				head.nextN.prevN = head;
			}	
		}
		
		public void removeNode(Node node) {
			if (head.nextN == null ) 
				return;
			if (node.nextN == null)
				head.nextN =tail.prevN;
			else {
				node.nextN.prevN = node.prevN;
				node.prevN.nextN = node.nextN;			
			}
		}	
		    
	public void put (K newKey, V newValue) {
		Node node = new Node<K,V>(newKey,newValue);
		if (map.containsKey(newKey)){
			node = map.get(newKey);
			removeNode(node);
			node.value = newValue;
			insertNode(node);
		}
		else
			insertNode(node);
		map.put(newKey, node);
	}
		
	public V get(K key) {
		Node node = map.get(key);
		if (node== null)
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
	
	/*public void printList() {
		if (head.nextN == null)
			System.out.println("empty");
		Node element = head.nextN;
		while (element!=null) {
			System.out.println(element.key);
			element = element.nextN;
		}
		System.out.println("-----------------------");
	}*/
}



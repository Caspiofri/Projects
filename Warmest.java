package question2;

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
		
	private Entry<K, V>[] table; //setting an array of Entry
	private int capacity = 16; //capacity of the modify	LinkedList<Entry> hotElement = new LinkedList();
	private Node<K,V> head = null ; 
	
	class Entry<K, V>{ //Entry class -setting the key-node pairs
		
		K key;
		Node<K, V> hotElement;
		Entry<K, V> next;
		
		public Entry(K key ,V value , Entry<K, V> next) {
			this.key = key;
			this.hotElement = new Node<K,V>(key,value);
			this.next = next;
			}
		
			public K getKey() {
				return key;
			}
			public void setKey(K key) {
				this.key =  key;
			}
			public V getValue() {
				return hotElement.value ;
			}
		
			public void setValue(V value) {
				this.hotElement.value = value;
			}
			
			public void setNode(K key,V value) {
				this.hotElement.key = key;
				this.hotElement.value = value;
			}
			
			public Entry<K, V> getNext() {
				return next;
			}
			public void setNext(Entry<K, V> next) {
				this.next = next;
			}	
		
			public void insertNode(Node node) {
				if (head == null)
					head = node;
				else {
				head.prevN = node;
				node.nextN = head;
				head = node;
				node.prevN = null;
				}	
			}
			
			public void removeNode(Node node) {
				if (head != node) {
					node.nextN.prevN = node.prevN;
					node.prevN.nextN = node.nextN;
				}
				else if (node.nextN != null) {
					node.nextN.prevN = null;
					head = node.nextN;
				}
				else
					head =null;			
			}	
			
		}

	public Warmest() {
		table = new Entry[capacity];
		//Represent the head and tail of the singly linked list    
	}

	public void put (K newKey, V newValue) {
		
		if (newKey == null) {
			return; //avoiding storing null.
		}
		//calculating index of key.
		int index = newKey.hashCode() % capacity; 
		 // creating new entry with the new key and value.
		Entry<K, V> newEntry = new Entry<K, V>(newKey,newValue,null);
		if (table[index] == null) {
			table[index] =  newEntry; //if the index in table doesn't contain any entry - store entry in index.
			newEntry.setNode(newKey, newValue);
			newEntry.insertNode(newEntry.hotElement);
				
		}
		else { //if the key already in the list - change its value.
			Entry<K, V> previous = null;
			Entry<K, V> current = table[index];
			
			while(current != null) {
				if (current.getKey().equals(newKey)) {
					current.setValue(newValue);
					//deleting the hotElement from the list
					current.removeNode(current.hotElement);
					current.insertNode(current.hotElement);
					break;
				}
				previous = current;
				current = current.getNext();
			}
			//creating new slot for the next pair
			if (previous != null)
				previous.setNext(newEntry);			
		}	
	}

	public V get(K key) {
		
		V value = null;
		//calculating index of key.
		int index = key.hashCode() % capacity;
		Entry<K, V> entry = table[index];
		while (entry != null) {
			if (entry.getKey().equals(key)) {
				value = entry.getValue();
				//removing node from list 
				entry.removeNode(entry.hotElement);
				//inserting node at the head of the list 
				entry.insertNode(entry.hotElement);
				break;
			}
			entry = entry.getNext();
		}
		return value;
	}
	
	public V remove(K key) {
		//calculating index of key.
		int index = key.hashCode() % capacity;
		Entry<K, V> previous = null;
		Entry<K, V> entry = table[index];
		if(entry == null)
			return null;
		V value = entry.getValue();
		//deleting the hotElement from the list
		entry.removeNode(entry.hotElement);
		
		while(entry != null) {
			if(entry.getKey().equals(key)) {
				if (previous == null) {
					entry = entry.getNext();
					table[index] = entry;
					break;
				}else {
					previous.setNext(entry.getNext());
					break;
				}	
			}
			previous = entry;
			entry = entry.getNext();
		}
		return value;
	}
		
		
	public V getWarmest() {
		if (head == null)
			return null;
		return head.value;
	}
	
	public void printList() {
		if (head == null)
			System.out.println("empty");
		Node element = head;
		while (element!=null) {
			System.out.println(element.key);
			element = element.nextN;
		}
		
	}
}

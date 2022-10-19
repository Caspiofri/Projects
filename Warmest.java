package question2;

public class Warmest<K,V> {
		
	private Entry<K, V>[] table; //setting an array of Entry
	private int capacity = 16; //capacity of the modify	LinkedList<Entry> hotElement = new LinkedList();
	
	public Node head = null ; 
	public Node tail = null ; 
	
	class Node{    
	    K data;    
        Node nextN;  
        Node prevN;  
     
		// אני לא משתמשת בכל הסטרים\גטרים האלה.. שווה להשאיר ולנסות לעשות בהם שימוש בקוד או למחוק?
	     public Node(K data) {    
	         this.data = data;    
	     } 
	     
	     public K getDataN()
	     {
	    	 return data;
	     }
	     public void setDataN(K newKey)
	     {
	    	this.data =  newKey;
	     }
	  
	     public Node getNextN()
	     {
	    	return this.nextN;
	     }
	     
	     public void setNextN(Node nextN)
	     {
	    	this.nextN =  nextN;
	     }
	     
	     public Node getPrevN()
	     {
	    	return this.prevN;
	     }
	     
	     public void setPrevN(Node PreviousN)
	     {
	    	this.prevN = PreviousN;
	     }
        
	}     
	
	class Entry<K, V>{ //Entry class -setting the key-value pairs
		
		K key;
		V value;
		Entry<K, V> next;
		Node hotElement;
		
		public Entry(K key ,V value,Entry<K, V> next) {
			this.key = key;
			this.value = value;
			this.next = next;
			}
		
		public K getKey() {
			return key;
		}
		public void setKey(K key) {
			this.key =  key;
		}
		public V getValue() {
			return value;
		}
	
		public void setValue(V value) {
			this.value = value;
		}
		
		public Entry getNext() {
			return next;
		}
		public void setNext(Entry<K, V> next) {
			this.next = next;
		}	
	
		public void insertNode(Entry<K, V> entry) {
			if (head == null)
				head = entry.hotElement;
			else {
			head.prevN = entry.hotElement;
			entry.hotElement.nextN = head;
			head = entry.hotElement;
			entry.hotElement.prevN = null;
			}	
		}
		
		public void removeNode(Entry<K, V> entry) {
			if (head != entry.hotElement) {
				entry.hotElement.nextN.prevN = entry.hotElement.prevN;
				entry.hotElement.prevN.nextN =  entry.hotElement.nextN;
			}
			else if (entry.hotElement.nextN != null) {
				entry.hotElement.nextN.prevN = null;
				head = entry.hotElement.nextN;
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
		Entry<K, V> newEntry = new Entry(newKey,newValue,null);
		if (table[index] == null) {
			table[index] =  newEntry; //if the index in table doesn't contain any entry - store entry in index.
			
		}
		else { //if the key already in the list - change its value.
			Entry<K, V> previous = null;
			Entry<K, V> current = table[index];
			
			while(current != null) {
				if (current.getKey().equals(newKey)) {
					current.setValue(newValue);
					break;
				}
				previous = current;
				current = current.getNext();
			}
			//creating new slot for the next pair
			if (previous != null)
				previous.setNext(newEntry);			
		}
		newEntry.hotElement = new Node(newKey);
		if (head == null) {
			head = newEntry.hotElement;
            tail = newEntry.hotElement;
            //head.prevN = null;
            //tail.nextN = null;
		}
		else {
			newEntry.insertNode(newEntry);
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
				entry.removeNode(entry);
				//inserting node at the head of the list 
				entry.insertNode(entry);
				break;
			}
			entry = entry.getNext();
		}
		return value;
	}
	
	public V remove(K key) {
		//calculating index of key.
		int index = key.hashCode() % capacity;
		Entry previous = null;
		Entry<K, V> entry = table[index];
		if(entry == null)
			return null;
		V value = entry.getValue();
		//deleting the hotElement from the list
		entry.removeNode(entry);
		
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
		K key = head.data;
		int index = key.hashCode() % capacity;
		Entry<K, V> entry = table[index];
		if(entry == null)
			return null;
		V value = entry.getValue();	
		return value;	
	}
	
	public void printList() {
		if (head == null)
			System.out.println("empty");
		Node element = head;
		while (element!=null) {
			System.out.println(element.data);
			element = element.nextN;
		}
		
	}
}



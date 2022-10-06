package question2;

import java.util.HashMap;
import java.util.LinkedList;

public class Warmest<K,V> {
	
	private Entry<K, V>[] table; //setting an array of Entry
	private int capacity = 16; //capacity of the modified hashmap.
	HashMap<K, Integer> hotTable = new HashMap<K, Integer>();
	LinkedList<Entry> hotElement = new LinkedList();
	
	static class Entry<K, V>{ //Entry class -setting the key-value pairs
		
		K key;
		V value;
		Entry<K, V> next;
		
		public Entry(K key , V value,Entry<K, V> next) {
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
	}
	
	public Warmest() {
		table = new Entry[capacity];
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
			hotElement.add(newEntry);
			int hotKey = hotElement.indexOf(newEntry);
			hotTable.put(newKey, hotKey);
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
	}

	public V get(K key) {
		
		V value = null;
		//calculating index of key.
		int index = key.hashCode() % capacity;
		Entry<K, V> entry = table[index];
		
		while (entry != null) {
			if (entry.getKey().equals(key)) {
				value = entry.getValue();
				int hotKey = hotTable.get(key);
				
				hotElement.set(hotKey, null);
				hotElement.add(entry);
				hotKey = hotElement.indexOf(entry);
				hotTable.put(key, hotKey);
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
		int hotKey = hotTable.get(key);
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
		if (hotElement.peekLast().key == key )
		{
			hotElement.removeLast();
			hotTable.remove(key);
		}
		else{
			hotElement.set(hotKey, null);
			hotTable.replace(key, null);
		}
		while (hotElement.peekLast() == null && hotElement.isEmpty() == false) {
			hotElement.removeLast();
		}
		return value;
	}
		
		
	public V getWarmest() {
		if (hotElement.isEmpty())
		{
			return null;
		}
		Entry<K,V> hotEntry = hotElement.getLast();
		V value = hotEntry.value;
		
		return value;	
	}

}

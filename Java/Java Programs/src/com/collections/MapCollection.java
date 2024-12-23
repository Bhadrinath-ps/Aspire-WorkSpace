package com.collections;

import java.util.HashMap;
import java.util.Map;

public class MapCollection {

	public static void main(String[] args) {
		Map<Integer, String> map = new HashMap<Integer, String>();

		map.put(01, "Bhadri");
		map.put(02, "Raghul");
		map.put(03, "Gopi");
		map.put(04, "Gowtham");
		map.put(05, "Dhanush");

		System.out.println("Output: " + map);
		System.out.println("Output of key 2: " + map.get(2));
		System.out.println("Contains key 3? " + map.containsKey(3));

		map.remove(5);
		System.out.println("After removing key 5: " + map);
		System.out.println("Keys: " + map.keySet());
		System.out.println("Values: " + map.values());
		System.out.println("Entries: " + map.entrySet());
		map.computeIfAbsent(6, k -> "New User");
		System.out.println("After computeIfAbsent: " + map);
		
		map.merge(1, "Nath", (oldValue, newValue) -> oldValue + newValue);
		System.out.println("After merge: " + map);
		System.out.println("Size of map: " + map.size());
	}

}

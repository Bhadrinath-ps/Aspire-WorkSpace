package com.collections;

import java.util.HashSet;

public class SetCollection {

	public static void main(String[] args) {
		 // Creating a HashSet to store Integer values
        HashSet<Integer> hashSet = new HashSet<>();

        // Add elements to the HashSet
        hashSet.add(10);
        hashSet.add(20);
        hashSet.add(30);
        hashSet.add(40);
        hashSet.add(50);

        // Display the contents of the HashSet
        System.out.println("HashSet contents: " + hashSet);

        // Check if the HashSet contains a specific element
        System.out.println("HashSet contains 20: " + hashSet.contains(20));
        System.out.println("HashSet contains 60: " + hashSet.contains(60));

        // Remove an element from the HashSet
        hashSet.remove(30);
        System.out.println("HashSet after removing 30: " + hashSet);

        // Check the size of the HashSet
        System.out.println("Size of HashSet: " + hashSet.size());

        // Clear all elements from the HashSet
        hashSet.clear();
        System.out.println("HashSet after clearing: " + hashSet);
	}

}

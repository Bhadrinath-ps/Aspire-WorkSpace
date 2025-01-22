package com.collections;

import java.util.LinkedList;
import java.util.Stack;

public class ListCollection {

	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<>();

		System.out.println("Stack List");

		// Add an Integer
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.push(5);
		stack.push(6);

		// Get the Top of an Integer
		System.out.println(stack.peek());

		// Remove the Top of an Integer
		stack.pop();

		// Print Stack stack
		System.out.println(stack);
		System.out.println("");

		// ---------------------------------------------

		LinkedList<Integer> linkedList = new LinkedList<>();

		System.out.println("Linked List");

		// Add an Integer
		linkedList.add(1);
		linkedList.add(2);

		// Get the Top of an Integer
		System.out.println(linkedList);

		// Remove the Index of an Integer
		linkedList.remove(0);

		// Print LinkedList linkedList
		System.out.println(linkedList);
	}

}

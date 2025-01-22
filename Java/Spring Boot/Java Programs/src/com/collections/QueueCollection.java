package com.collections;

import java.util.Queue;
import java.util.LinkedList;

public class QueueCollection {
    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();

        queue.add(1);
        queue.add(3);
        queue.add(5);
        queue.add(7);

        System.out.println("Queue: " + queue);

        // Peek at the front element without removing it
        System.out.println("Peek: " + queue.peek());

        // Remove elements from the front of the queue
        System.out.println("Removed: " + queue.remove());
        System.out.println("Removed: " + queue.remove());

        System.out.println("Queue after removal: " + queue);
        System.out.println("Contains 20? " + queue.contains(20));

        // Poll an element (removes and returns the front element)
        System.out.println("Polled: " + queue.poll());

        System.out.println("Queue after polling: " + queue);
        System.out.println("Size of queue: " + queue.size());
        System.out.println("Is the queue empty? " + queue.isEmpty());

        // Clear the queue
        queue.clear();
        System.out.println("Queue after clearing: " + queue);
    }
}

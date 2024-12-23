package com.aspiresys;

public class ThreadMethodsExample {
    
    private static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        
        // Creating multiple threads to demonstrate wait/notify behavior
        Thread thread1 = new Thread(new Task("Thread 1"));
        Thread thread2 = new Thread(new Task("Thread 2"));
        Thread thread3 = new Thread(new Task("Thread 3"));

        // Start the threads
        thread1.start();
        thread2.start();
        thread3.start();

        // Simulate a scenario where the main thread sleeps for 2 seconds before notifying
        Thread.sleep(2000);
        synchronized (lock) {
            System.out.println("Main thread notifying all threads.");
            lock.notifyAll();  // Notify all waiting threads after 2 seconds
        }

        // Wait for all threads to finish execution
        thread1.join();
        thread2.join();
        thread3.join();
    }

    // Task that simulates waiting and resuming after notification
    static class Task implements Runnable {
        private String threadName;

        public Task(String threadName) {
            this.threadName = threadName;
        }

        @Override
        public void run() {
            synchronized (lock) {
                try {
                    System.out.println(threadName + " is waiting...");
                    lock.wait();  // The thread waits until notified
                    System.out.println(threadName + " resumed after notification!");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // Simulate some work after notification
            try {
                Thread.sleep(3000); // Simulate some work after being notified
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(threadName + " finished work!");
        }
    }
}

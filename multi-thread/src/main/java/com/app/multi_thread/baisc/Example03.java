package com.app.multi_thread.baisc;

public class Example03 {
    public static void main(String[] args) {
        // Create and start 3 threads
        for (int i = 0; i < 3; i++) {
            // Create a new thread
            Thread thread = new Thread(() -> {
                // Each thread prints its own ID
                System.out.println("Thread ID: " + Thread.currentThread().getName());
            });
            // Start the thread
            thread.start();
        }
    }
}

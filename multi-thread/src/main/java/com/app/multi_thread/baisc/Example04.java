package com.app.multi_thread.baisc;
/**
 * Example of using Thread.sleep() to pause a thread.
 * This example demonstrates how to create a thread that sleeps for a specified duration.
 */
public class Example04 {
    public static void main(String[] args) {
   // Create a new thread using a lambda expression
        Thread thread = new Thread(() -> {
            try {
                System.out.println("Thread starting...");
                // Pause the thread for 2 seconds
                Thread.sleep(2000);
                System.out.println("Thread finished after sleeping!");
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted!");
            }
        });
        // Start the thread
        thread.start();
    }
}
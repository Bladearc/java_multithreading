package com.app.multi_thread.baisc;

//Interrupt a thread and handle the interruption gracefully.
public class Example10 {
    public static void main(String[] args) throws InterruptedException {
        Thread worker = new Thread(() -> {
            try {
                System.out.println("Worker thread sleeping...");
                Thread.sleep(5000); // Long sleep to allow interruption
                System.out.println("Worker thread finished naturally.");
            } catch (InterruptedException e) {
                // Handle interruption
                System.out.println("Worker thread interrupted!");
            }
        });
        worker.start();
        Thread.sleep(1000); // Let worker start sleeping
        worker.interrupt(); // Interrupt the worker thread
    }
}
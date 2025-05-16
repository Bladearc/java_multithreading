package com.app.multi_thread.baisc;

public class Example02 implements Runnable{

    @Override
    public void run() {
        // Define the task the thread will perform
        System.out.println("Hello from runnable: " + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        // Create a Runnable object and pass it to a Thread
        Example02 runnable = new Example02();
        // Create a thread with the runnable
        Thread thread = new Thread(runnable);
        // Start the thread
        thread.start();
    }
}

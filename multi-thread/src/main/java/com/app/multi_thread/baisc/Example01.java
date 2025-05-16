package com.app.multi_thread.baisc;

public class Example01 extends Thread{

    @Override
    public void run() {
        // The run() method defines what the thread will do
        System.out.println("Hello from thread: " + Thread.currentThread().getName());
    }

    public static void main(String[] args) {

        // Create an instance of our thread
        Example01 thread = new Example01();

        // Start the thread, which calls run()
        thread.start();
    }

}

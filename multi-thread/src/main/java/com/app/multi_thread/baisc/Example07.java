package com.app.multi_thread.baisc;

//Show a race condition with threads accessing a shared variable.
public class Example07 {
    static int counter = 0;

    // Synchronized method to prevent race conditions
    synchronized static void increment() {
        counter++;
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                increment();
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                increment();
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Final counter: " + counter); // Should be 10000
    }
}

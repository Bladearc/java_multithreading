package com.app.multi_thread.baisc;

//Show a race condition with threads accessing a shared variable
public class Example06 {
    static int counter = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                counter++; // Not thread-safe
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                counter++; // Race condition occurs here
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        // Expected: 2000, but often less due to race condition
        System.out.println("Final counter: " + counter);
    }
}

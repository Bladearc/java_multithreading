package com.app.multi_thread.baisc;

//Use synchronized blocks for more granular control over critical sections.
public class Example08 {
    static int counter = 0;
    static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                // Synchronize only the critical section
                synchronized (lock) {
                    counter++;
                }
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                synchronized (lock) {
                    counter++;
                }
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Final counter: " + counter); // Should be 10000
    }
}
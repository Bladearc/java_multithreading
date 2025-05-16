package com.app.multi_thread.baisc;
/**
 * Example of using Thread.sleep() to pause a thread.
 * Basic inter-thread communication using wait() and notify()
 */
public class Example09 {
    static final Object lock = new Object();

    public static void main(String[] args) {
        Thread waiter = new Thread(() -> {
            synchronized (lock) {
                try {
                    System.out.println("Waiting thread is waiting...");
                    lock.wait(); // Wait for notification
                    System.out.println("Waiting thread resumed!");
                } catch (InterruptedException e) {
                    System.out.println("Interrupted!");
                }
            }
        });
        Thread notifier = new Thread(() -> {
            synchronized (lock) {
                try {
                    Thread.sleep(1000); // Delay before notifying
                    lock.notify(); // Notify the waiting thread
                    System.out.println("Notifier has notified!");
                } catch (InterruptedException e) {
                    System.out.println("Interrupted!");
                }
            }
        });
        waiter.start();
        notifier.start();
    }
}

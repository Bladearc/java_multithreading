package com.app.multi_thread.hard;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.ReentrantLock;


/**
 * The Concurrent Matrix Transpose Race
 * Description
 * Imagine a system where multiple threads must collaboratively transpose a large square matrix (swap elements across the main diagonal) in place. Each thread is assigned a portion of the matrix, but since it’s an in-place operation, threads may overwrite each other’s work, leading to race conditions. The matrix is shared, and no thread should proceed until all are ready to start, ensuring synchronized access without trivializing the problem with a single lock.
 * Key Challenges
 *     Race Conditions: Multiple threads accessing and modifying the same matrix elements simultaneously.
 *     Coordination: Ensuring all threads start at the same time to avoid partial updates skewing results.
 *     Debugging Difficulty: Subtle timing issues may cause incorrect transpositions that are hard to reproduce.
 *     Efficiency: Avoiding excessive synchronization that defeats the purpose of parallelism.
 * **/


public class Example02 {
    private final int[][] matrix;
    private final int size;
    private final ReentrantLock[][] locks;
    private final CyclicBarrier barrier;

    public Example02(int[][] matrix) {
        this.matrix = matrix;
        this.size = matrix.length;
        this.locks = new ReentrantLock[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                locks[i][j] = new ReentrantLock();
            }
        }
        this.barrier = new CyclicBarrier(size); // One thread per row
    }

    public void transpose() throws Exception {
        Thread[] threads = new Thread[size];
        for (int i = 0; i < size; i++) {
            final int row = i;
            threads[i] = new Thread(() -> {
                try {
                    barrier.await(); // Wait for all threads to be ready
                    for (int j = row + 1; j < size; j++) {
                        // Lock both cells in a consistent order to avoid deadlock
                        ReentrantLock lock1 = locks[row][j];
                        ReentrantLock lock2 = locks[j][row];
                        ReentrantLock first = lock1.hashCode() < lock2.hashCode() ? lock1 : lock2;
                        ReentrantLock second = lock1 == first ? lock2 : lock1;

                        first.lock();
                        try {
                            second.lock();
                            try {
                                // Swap elements
                                int temp = matrix[row][j];
                                matrix[row][j] = matrix[j][row];
                                matrix[j][row] = temp;
                            } finally {
                                second.unlock();
                            }
                        } finally {
                            first.unlock();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            threads[i].start();
        }
        for (Thread t : threads) {
            t.join();
        }
    }

    public static void main(String[] args) throws Exception {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        Example02 mtr = new Example02(matrix);
        mtr.transpose();
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }
}


/** * Key Concepts
 * Explanation
 *     CyclicBarrier: Ensures all threads begin transposing simultaneously, simulating a real-world scenario where tasks must align (e.g., distributed matrix operations).
 *     Per-Cell Locks: Each matrix cell has its own ReentrantLock, requiring threads to coordinate access at a granular level. This increases contention and debugging complexity.
 *     Lock Ordering: To prevent deadlocks, locks are acquired in a consistent order based on their hash codes, a subtle detail that could easily be missed.
 *     Debugging Challenge: If timing varies slightly, threads might overwrite swapped values before others read them, leading to inconsistent results. Use logging or breakpoints to trace lock acquisition and element swaps.
 * **/
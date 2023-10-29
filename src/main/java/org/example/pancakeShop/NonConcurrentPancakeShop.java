package org.example.pancakeShop;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class NonConcurrentPancakeShop {
    private static final int MAX_PANCAKES_PER_BATCH = 12;
    private static final int MAX_PANCAKES_PER_USER = 5;
    private static final int NUM_USERS = 3;
    private static final int SIMULATION_TIME_SECONDS = 30;

    public static void main(String[] args) {
        Random random = new Random();
        int pancakesMade = random.nextInt(MAX_PANCAKES_PER_BATCH + 1);

        int[] pancakesPerUser = new int[NUM_USERS];
        int totalPancakesEaten = 0;
        for (int i = 0; i < NUM_USERS; i++) {
            pancakesPerUser[i] = random.nextInt(MAX_PANCAKES_PER_USER + 1);
            totalPancakesEaten += pancakesPerUser[i];
        }

        BlockingQueue<Integer> eventQueue = new ArrayBlockingQueue<>(SIMULATION_TIME_SECONDS);
        startEventSimulator(eventQueue, SIMULATION_TIME_SECONDS);

        long startTime = System.currentTimeMillis();
        System.out.println("Starting time of 30 seconds slot: " + startTime);

        for (int currentSecond = 1; currentSecond <= SIMULATION_TIME_SECONDS; currentSecond++) {
            try {
                Integer event = eventQueue.take();
                System.out.println("Event at second: " + event);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Ending time of 30 seconds slot: " + endTime);

        System.out.println("Number of pancakes made by the shopkeeper: " + pancakesMade);
        System.out.print("Number of pancakes eaten by the 3 users: ");
        for (int i = 0; i < NUM_USERS; i++) {
            System.out.print(pancakesPerUser[i] + " ");
        }
        System.out.println();
        System.out.println("Whether the shopkeeper was able to meet the needs of the 3 users: " +
                (totalPancakesEaten <= MAX_PANCAKES_PER_BATCH ? "Yes" : "No"));
        System.out.println("How many pancakes got wasted: " +
                Math.max(0, pancakesMade - totalPancakesEaten));
        System.out.println("Pancake orders not met: " +
                Math.max(0, totalPancakesEaten - MAX_PANCAKES_PER_BATCH));
    }

    private static void startEventSimulator(BlockingQueue<Integer> eventQueue, int seconds) {
        for (int i = 1; i <= seconds; i++) {
            try {
                eventQueue.put(i);
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

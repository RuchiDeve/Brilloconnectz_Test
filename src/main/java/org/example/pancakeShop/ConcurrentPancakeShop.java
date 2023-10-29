package org.example.pancakeShop;

import java.util.Random;

public class ConcurrentPancakeShop {

    private static final int MAX_PANCAKES_PER_BATCH = 12;
    private static final int MAX_PANCAKES_PER_USER = 5;
    private static final int NUM_USERS = 3;
    private static final int SIMULATION_TIME_SECONDS = 30;

    public static void main(String[] args) {
        Random random = new Random();
        int[] pancakesPerUser = new int[NUM_USERS];
        int totalPancakesEaten = 0;
        int pancakesMade = 0;

        for (int i = 0; i < NUM_USERS; i++) {
            pancakesPerUser[i] = random.nextInt(MAX_PANCAKES_PER_USER + 1);
            totalPancakesEaten += pancakesPerUser[i];

            if (pancakesPerUser[i] > MAX_PANCAKES_PER_USER) {
                pancakesPerUser[i] = MAX_PANCAKES_PER_USER;
                totalPancakesEaten -= (pancakesPerUser[i] - MAX_PANCAKES_PER_USER);
            }
            pancakesMade += pancakesPerUser[i];
        }
        if (pancakesMade > MAX_PANCAKES_PER_BATCH) {
            pancakesMade = MAX_PANCAKES_PER_BATCH;
        }

        long startTime = System.currentTimeMillis();
        System.out.println("Starting time of 30 seconds slot: " + startTime);

        simulateEventsWithin30Seconds();

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

    private static void simulateEventsWithin30Seconds() {
        long startTime = System.currentTimeMillis();
        long currentTime = startTime;

        for (int currentSecond = 1; currentSecond <= SIMULATION_TIME_SECONDS; currentSecond++) {
            long eventTime = startTime + currentSecond * 1000;
            while (System.currentTimeMillis() < eventTime) {

            }

            System.out.println("Event at second: " + currentSecond);
        }
    }
}

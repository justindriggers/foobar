package com.justindriggers.foobar;

public class ZombitInfection {

    public static void main(String[] args) {
        final int[][] population1 = new int[][] { new int[] { 1, 1, 1, 5 }, new int[] { 1, 2, 3 }, new int[] { 1, 2, 3, 4 }, new int[] { 3, 2, 1 }};

        print2DArray(answer(population1, 0, 0, 2));

        System.out.println();

        final int[][] population2 = new int[][] { new int[] { 6, 7, 2, 7, 6 }, new int[] { 6, 3, 1, 4, 7 }, new int[] { 0, 2, 4, 1, 10 },
                                new int[] { 8, 1, 1, 4, 9 }, new int[] { 8, 7, 4, 9, 9 }};

        print2DArray(answer(population2, 0, 2, 0));
    }

    public static int[][] answer(int[][] population, int x, int y, int strength) {
        int[][] result = population.clone();
        infect(result, x, y, strength);
        return result;
    }

    private static void infect(int[][] population, int x, int y, int strength) {
        if (y >= 0 && y < population.length // Check y bounds
                && x >= 0 && x < population[y].length // Check x bounds
                && population[y][x] >= 0 // < 0 is already infected!
                && population[y][x] <= strength) { // <= strength needs to be infected
            population[y][x] = -1;
            infect(population, x - 1, y, strength);
            infect(population, x + 1, y, strength);
            infect(population, x, y - 1, strength);
            infect(population, x, y + 1, strength);
        }
    }

    private static void print2DArray(int[][] array) {
        System.out.print('[');

        for (int i = 0; i < array.length; i++) {
            System.out.print('[');

            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j]);

                if (j < array[i].length - 1) {
                    System.out.print(", ");
                }
            }

            System.out.print(']');

            if (i < array.length - 1) {
                System.out.print(", ");
            }
        }

        System.out.print(']');
    }
}

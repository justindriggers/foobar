package com.justindriggers.foobar;

import java.math.BigInteger;

/**
 * Line up the captives
 * ====================
 *
 * As you ponder sneaky strategies for assisting with the great rabbit escape, you realize that you have an opportunity to fool Professor Booleans guards into thinking there are fewer rabbits total than there actually are.
 *
 * By cleverly lining up the rabbits of different heights, you can obscure the sudden departure of some of the captives.
 *
 * Beta Rabbits statisticians have asked you for some numerical analysis of how this could be done so that they can explore the best options.
 *
 * Luckily, every rabbit has a slightly different height, and the guards are lazy and few in number. Only one guard is stationed at each end of the rabbit line-up as they survey their captive population. With a bit of misinformation added to the facility roster, you can make the guards think there are different numbers of rabbits in holding.
 *
 * To help plan this caper you need to calculate how many ways the rabbits can be lined up such that a viewer on one end sees x rabbits, and a viewer on the other end sees y rabbits, because some taller rabbits block the view of the shorter ones.
 *
 * For example, if the rabbits were arranged in line with heights 30 cm, 10 cm, 50 cm, 40 cm, and then 20 cm, a guard looking from the left side would see 2 rabbits (30 and 50 cm) while a guard looking from the right side would see 3 rabbits (20, 40 and 50 cm).
 *
 * Write a method answer(x,y,n) which returns the number of possible ways to arrange n rabbits of unique heights along an east to west line, so that only x are visible from the west, and only y are visible from the east. The return value must be a string representing the number in base 10.
 *
 * If there is no possible arrangement, return "0".
 *
 * The number of rabbits (n) will be as small as 3 or as large as 40
 * The viewable rabbits from either side (x and y) will be as small as 1 and as large as the total number of rabbits (n).
 *
 * Languages
 * =========
 *
 * To provide a Python solution, edit solution.py
 * To provide a Java solution, edit solution.java
 *
 * Test cases
 * ==========
 *
 * Inputs:
 * (int) x = 2
 * (int) y = 2
 * (int) n = 3
 * Output:
 * (string) "2"
 *
 * Inputs:
 * (int) x = 1
 * (int) y = 2
 * (int) n = 6
 * Output:
 * (string) "24"
 *
 * Use verify [file] to test your solution and see how it does. When you are finished editing your code, use submit [file] to submit your answer. If your solution passes the test cases, it will be removed from your home folder.
 */
public class LineUpTheCaptives {

    private static final BigInteger[] FACTORIAL_CACHE = new BigInteger[40];
    private static final BigInteger[][] POSSIBILITY_CACHE = new BigInteger[40][40];

    public static void main(String[] args) {
        System.out.println(answer(2, 2, 3)); // "2"

        System.out.println(answer(1, 2, 6)); // "24"
    }

    public static String answer(int x, int y, int n) {
        BigInteger result = BigInteger.ZERO;

        for (int i = x; i <= n - y + 1; i++) {
            result = result.add(binomialCoefficient(n - 1, i - 1)
                            .multiply(calculatePossibilities(x - 1, i - 1)) // Western guard
                            .multiply(calculatePossibilities(y - 1, n - i))); // Eastern guard
        }

        return result.toString(10);
    }

    /**
     * n choose k. Runs in O(k).
     *
     * Since calculations are only performed if k < n, this solution
     * is better than solutions which are O(n).
     */
    private static BigInteger binomialCoefficient(int n, int k) {
        if (k == 0 || n == k) {
            return BigInteger.ONE;
        } else if (k == 1 || k == n - 1) {
            return BigInteger.valueOf(n);
        } else if (k > n) {
            return BigInteger.ZERO;
        } else {
            BigInteger nb = BigInteger.valueOf(n);
            BigInteger kb = BigInteger.valueOf(k);

            BigInteger ns = BigInteger.ONE;
            BigInteger ks = BigInteger.ONE;

            for (int i = 0; i < k; i++) {
                ns = ns.multiply(nb.subtract(BigInteger.valueOf(i)));
                ks = ks.multiply(kb.subtract(BigInteger.valueOf(i)));
            }

            return ns.divide(ks);
        }
    }

    private static BigInteger factorial(int n) {
        if (n == 0) {
            return BigInteger.ONE;
        } else {
            if (FACTORIAL_CACHE[n] != null) {
                return FACTORIAL_CACHE[n];
            } else {
                BigInteger result = BigInteger.valueOf(n).multiply(factorial(n - 1));
                FACTORIAL_CACHE[n] = result;
                return result;
            }
        }
    }

    /**
     * Finds the number of possibilities of the guard seeing "toFind" rabbits
     * out of "total" rabbits.
     */
    private static BigInteger calculatePossibilities(int toFind, int total) {
        if (toFind == 0) {
            if (total == 0) {
                return BigInteger.ONE;
            } else {
                return BigInteger.ZERO;
            }
        } else {
            if (POSSIBILITY_CACHE[toFind][total] != null) {
                return POSSIBILITY_CACHE[toFind][total];
            } else {
                BigInteger result = BigInteger.ZERO;

                for (int i = toFind; i <= total; i++) {
                    result = result.add(binomialCoefficient(total - 1, i - 1)
                            .multiply(calculatePossibilities(toFind - 1, i - 1))
                            .multiply(factorial(total - i)));
                }

                POSSIBILITY_CACHE[toFind][total] = result;
                return result;
            }
        }
    }
}

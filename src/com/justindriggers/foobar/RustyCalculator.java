package com.justindriggers.foobar;

/**
 * Rusty calculator
 * ================
 *
 * Lab minion Rusty works for Professor Boolean, a mad scientist. He's been stuck in this dead-end job crunching numbers all day since 1969. And it's not even the cool type of number-crunching - all he does is addition and multiplication. To make matters worse, under strict orders from Professor Boolean, the only kind of calculator minions are allowed to touch is the Unix dc utility, which uses reverse Polish notation.
 *
 * Recall that reverse Polish calculators such as dc push numbers from the input onto a stack. When a binary operator (like "+" or "*") is encountered, they pop the top two numbers, and then push the result of applying the operator to them.
 *
 * For example:
 * 2 3 * => 6
 * 4 9 + 2 * 3 + => 13 2 * 3 + => 26 3 + => 29
 *
 * Each day, Professor Boolean sends the minions some strings representing equations, which take the form of single digits separated by "+" or "*", without parentheses. To make Rusty's work easier, write function called answer(str) that takes such a string and returns the lexicographically largest string representing the same equation but in reverse Polish notation.
 *
 * All numbers in the output must appear in the same order as they did in the input. So, even though "32+" is lexicographically larger than "23+", the expected answer for "2+3" is "23+".
 *
 * Note that all numbers are single-digit, so no spaces are required in the answer. Further, only the characters [0-9+*] are permitted in the input and output.
 *
 * The number of digits in the input to answer will not exceed 100.
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
 * (string) str = "2+3*2"
 * Output:
 * (string) "232*+"
 *
 * Inputs:
 * (string) str = "2*4*3+9*3+5"
 * Output:
 * (string) "243**93*5++"
 *
 * Use verify [file] to test your solution and see how it does. When you are finished editing your code, use submit [file] to submit your answer. If your solution passes the test cases, it will be removed from your home folder.
 */
public class RustyCalculator {

    public static void main(String[] args) {
        System.out.println(answer("2+3*2"));
        System.out.println(answer("2*4*3+9*3+5"));
    }

    public static String answer(String str) {
        StringBuilder result = new StringBuilder();

        String[] additionSegments = str.split("\\+");
        StringBuilder additionSymbolBuilder = new StringBuilder();

        for (int i = 0; i < additionSegments.length; i++) {
            String[] multiplicationSegments = additionSegments[i].split("\\*");
            StringBuilder multiplicationSymbolBuilder = new StringBuilder();

            for (int j = 0; j < multiplicationSegments.length; j++) {
                result.append(multiplicationSegments[j]);

                if (j < multiplicationSegments.length - 1) {
                    multiplicationSymbolBuilder.append('*');
                }
            }

            result.append(multiplicationSymbolBuilder);

            if (i < additionSegments.length - 1) {
                additionSymbolBuilder.append('+');
            }
        }

        result.append(additionSymbolBuilder);

        return result.toString();
    }
}

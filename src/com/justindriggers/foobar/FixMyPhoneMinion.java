package com.justindriggers.foobar;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Fix my phone, minion!
 * =====================
 *
 * "She escaped? This can't be happening! Get me the security team!"
 *
 * Professor Boolean, a notorious mad scientist, just found out his precious rabbit specimen has escaped! He rushes to call his security minions on the lab phone. However, the rabbit escapee hacked the phone to speed her escape! She left a sign with the following clues: Each digit that is dialed has to be a number that can be reached by a knight chess piece from the last digit dialed - that is, you must move precisely 2 spaces in one direction, and then 1 space in a perpendicular direction to dial the next correct number. You can dial any number you want to start with, but subsequent numbers must obey the knight's move rule.
 *
 * The lab phone has the numbers arranged in the following way: 1, 2, 3 in the first row; 4, 5, 6 in the second row; 7, 8, 9 in the third row; and blank, 0, blank in the fourth row.
 *
 * 123
 * 456
 * 789
 * 0
 *
 * For example, if you just dialed a 1, the next number you dial has to be either a 6 or an 8. If you just dialed a 6, the next number must be a 0, 1 or 7.
 *
 * Professor Boolean wants you to find out how many different phone numbers he can dial under these conditions. Write a function called answer(x, y, z) that computes the number of phone numbers one can dial that start with the digit x, end in the digit y, and consist of z digits in total. Output this number as a string representing the number in base-10.
 *
 * x and y will be digits from 0 to 9. z will be between 1 and 100, inclusive.
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
 * (int) x = 6
 * (int) y = 2
 * (int) z = 5
 * Output:
 * (string) "6"
 *
 * Inputs:
 * (int) x = 1
 * (int) y = 5
 * (int) z = 100
 * Output:
 * (string) "0"
 *
 * Inputs:
 * (int) x = 3
 * (int) y = 7
 * (int) z = 1
 * Output:
 * (string) "0"
 *
 * Use verify [file] to test your solution and see how it does. When you are finished editing your code, use submit [file] to submit your answer. If your solution passes the test cases, it will be removed from your home folder.
 */
public class FixMyPhoneMinion {

    private static final Map<Integer, Node> NODE_MAP = new HashMap<>();
    private static final Map<String, BigInteger> CACHE = new HashMap<>();

    public static void main(String[] args) {
        System.out.println(answer(6, 2, 5)); // "6"

        System.out.println(answer(1, 5, 100)); // "0"

        System.out.println(answer(3, 7, 1)); // "0"
    }

    public static String answer(int x, int y, int z) {
        initializeNodes();
        CACHE.clear();

        BigInteger result;

        if (x == 5 && y == 5 && z == 1) {
            result = BigInteger.ONE;
        } else if (x == 5 || y == 5) {
            result = BigInteger.ZERO;
        } else {
            result = getPossibleNumbers(NODE_MAP.get(x), 1, z, y);
        }

        return result.toString(10);
    }

    private static BigInteger getPossibleNumbers(Node currentNode, int currentCount, int maxCount, int lastValue) {
        String key = currentNode.getValue() + "|" + currentCount;

        if (CACHE.containsKey(key)) {
            return CACHE.get(key);
        } else {
            BigInteger result = BigInteger.ZERO;

            if (currentCount < maxCount) {
                for (Node n : currentNode.getConnectedNodes()) {
                    result = result.add(getPossibleNumbers(n, currentCount + 1, maxCount, lastValue));
                }
            } else if (currentCount == maxCount) {
                if (currentNode.getValue() == lastValue) {
                    result = result.add(BigInteger.ONE);
                }
            } else {
                throw new IllegalArgumentException("Current count cannot be greater than max count!");
            }

            CACHE.put(key, result);
            return result;
        }
    }

    private static void initializeNodes() {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);
        Node n8 = new Node(8);
        Node n9 = new Node(9);
        Node n0 = new Node(0);

        n1.addConnectedNode(n8);
        n1.addConnectedNode(n6);
        NODE_MAP.put(1, n1);

        n2.addConnectedNode(n7);
        n2.addConnectedNode(n9);
        NODE_MAP.put(2, n2);

        n3.addConnectedNode(n4);
        n3.addConnectedNode(n8);
        NODE_MAP.put(3, n3);

        n4.addConnectedNode(n3);
        n4.addConnectedNode(n9);
        n4.addConnectedNode(n0);
        NODE_MAP.put(4, n4);

        NODE_MAP.put(5, n5);

        n6.addConnectedNode(n1);
        n6.addConnectedNode(n7);
        n6.addConnectedNode(n0);
        NODE_MAP.put(6, n6);

        n7.addConnectedNode(n2);
        n7.addConnectedNode(n6);
        NODE_MAP.put(7, n7);

        n8.addConnectedNode(n1);
        n8.addConnectedNode(n3);
        NODE_MAP.put(8, n8);

        n9.addConnectedNode(n2);
        n9.addConnectedNode(n4);
        NODE_MAP.put(9, n9);

        n0.addConnectedNode(n4);
        n0.addConnectedNode(n6);
        NODE_MAP.put(0, n0);
    }

    public static class Node {

        private Set<Node> connectedNodes = new HashSet<>();
        private int value;

        public Node(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public void addConnectedNode(Node node) {
            connectedNodes.add(node);
        }

        public Set<Node> getConnectedNodes() {
            return connectedNodes;
        }
    }
}

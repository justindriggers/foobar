package com.justindriggers.foobar;

/**
 * Carrotland
 * ==========
 *
 * The rabbits are free at last, free from that horrible zombie science experiment. They need a happy, safe home, where they can recover.
 *
 * You have a dream, a dream of carrots, lots of carrots, planted in neat rows and columns! But first, you need some land. And the only person who's selling land is Farmer Frida. Unfortunately, not only does she have only one plot of land, she also doesn't know how big it is - only that it is a triangle. However, she can tell you the location of the three vertices, which lie on the 2-D plane and have integer coordinates.
 *
 * Of course, you want to plant as many carrots as you can. But you also want to follow these guidelines: The carrots may only be planted at points with integer coordinates on the 2-D plane. They must lie within the plot of land and not on the boundaries. For example, if the vertices were (-1,-1), (1,0) and (0,1), then you can plant only one carrot at (0,0).
 *
 * Write a function answer(vertices), which, when given a list of three vertices, returns the maximum number of carrots you can plant.
 *
 * The vertices list will contain exactly three elements, and each element will be a list of two integers representing the x and y coordinates of a vertex. All coordinates will have absolute value no greater than 1000000000. The three vertices will not be collinear.
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
 * (int) vertices = [[2, 3], [6, 9], [10, 160]]
 * Output:
 * (int) 289
 *
 * Inputs:
 * (int) vertices = [[91207, 89566], [-88690, -83026], [67100, 47194]]
 * Output:
 * (int) 1730960165
 *
 * Use verify [file] to test your solution and see how it does. When you are finished editing your code, use submit [file] to submit your answer. If your solution passes the test cases, it will be removed from your home folder.
 */
public class Carrotland {

    public static void main(String[] args) {
        System.out.println(
                answer(new int[][] {
                        new int[] { 2, 3 },
                        new int[] { 6, 9 },
                        new int[] { 10, 160 }
                })
        ); // 289

        System.out.println(
                answer(new int[][] {
                        new int[] { 91207, 89566 },
                        new int[] { -88690, -83026 },
                        new int[] { 67100, 47194 }
                })
        ); // 1730960165

        System.out.println(
                answer(new int[][] {
                        new int[] { -1, -1 },
                        new int[] { 1, 0 },
                        new int[] { 0, 1 }
                })
        ); // 1
    }

    /**
     * Return the number of points inside the boundaries of the given triangle using a
     * rearranged version of Pick's Theorem.
     *
     * A = i + b / 2 + 1
     *
     * Where A is the area of the triangle,
     * i is the number of interior points within the triangle, and
     * b is the number of points along the boundary of the triangle.
     *
     * Therefore,
     *
     * i = A - b / 2 + 1
     */
    public static int answer(int[][] vertices) {
        Triangle triangle = new Triangle(vertices);

        int area = (int) Math.round(getArea(triangle));
        return area > 0 ? area - countBoundaryPoints(triangle) / 2 + 1 : 0;
    }

    /**
     * Calculate the area of a triangle using the lengths of its sides (Heron's Formula).
     */
    private static double getArea(Triangle triangle) {
        double a = calculateDistance(triangle.getFirstPoint(), triangle.getSecondPoint());
        double b = calculateDistance(triangle.getSecondPoint(), triangle.getThirdPoint());
        double c = calculateDistance(triangle.getThirdPoint(), triangle.getFirstPoint());

        double s = (a + b + c) / 2;

        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }

    /**
     * Calculate the distance between two points on a 2D plane using the Pythagorean theorem.
     */
    private static double calculateDistance(Point p1, Point p2) {
        int a = Math.abs(p1.getX() - p2.getX());
        int b = Math.abs(p1.getY() - p2.getY());
        return Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
    }

    /**
     * Find the number of points along the boundary of a triangle.
     */
    private static int countBoundaryPoints(Triangle triangle) {
        int gcd1 = getGreatestCommonDivisor(
                Math.abs(triangle.getFirstPoint().getX() - triangle.getSecondPoint().getX()),
                Math.abs(triangle.getFirstPoint().getY() - triangle.getSecondPoint().getY()));

        int gcd2 = getGreatestCommonDivisor(
                Math.abs(triangle.getSecondPoint().getX() - triangle.getThirdPoint().getX()),
                Math.abs(triangle.getSecondPoint().getY() - triangle.getThirdPoint().getY()));

        int gcd3 = getGreatestCommonDivisor(
                Math.abs(triangle.getThirdPoint().getX() - triangle.getFirstPoint().getX()),
                Math.abs(triangle.getThirdPoint().getY() - triangle.getFirstPoint().getY()));

        return gcd1 + gcd2 + gcd3;
    }

    /**
     * Find the greatest common divisor between two integers.
     */
    private static int getGreatestCommonDivisor(int a, int b) {
        if (a == 0 || b == 0) {
            return a + b;
        } else {
            return getGreatestCommonDivisor(b, a % b);
        }
    }

    /**
     * Represents a single Point on a 2D plane.
     */
    public static class Point {

        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point(int[] vertex) {
            this(vertex[0], vertex[1]);
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    /**
     * Represents a set of three points forming a triangle.
     */
    public static class Triangle {

        private Point[] points = new Point[3];

        public Triangle(int[][] vertices) {
            this.points[0] = new Point(vertices[0]);
            this.points[1] = new Point(vertices[1]);
            this.points[2] = new Point(vertices[2]);
        }

        public Point getFirstPoint() {
            return points[0];
        }

        public Point getSecondPoint() {
            return points[1];
        }

        public Point getThirdPoint() {
            return points[2];
        }
    }
}

package com.justindriggers.foobar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Zombit antidote
 * ===============
 *
 * Forget flu season. Zombie rabbits have broken loose and are terrorizing Silicon Valley residents! Luckily, you've managed to steal a zombie antidote and set up a makeshift rabbit rescue station. Anyone who catches a zombie rabbit can schedule a meeting at your station to have it injected with the antidote, turning it back from a zombit to a fluffy bunny. Unfortunately, you have a limited amount of time each day, so you need to maximize these meetings. Every morning, you get a list of requested injection meetings, and you have to decide which to attend fully. If you go to an injection meeting, you will join it exactly at the start of the meeting, and only leave exactly at the end.
 *
 * Can you optimize your meeting schedule? The world needs your help!
 *
 * Write a method called answer(meetings) which, given a list of meeting requests, returns the maximum number of non-overlapping meetings that can be scheduled. If the start time of one meeting is the same as the end time of another, they are not considered overlapping.
 *
 * meetings will be a list of lists. Each element of the meetings list will be a two element list denoting a meeting request. The first element of that list will be the start time and the second element will be the end time of that meeting request.
 *
 * All the start and end times will be non-negative integers, no larger than 1000000.
 * The start time of a meeting will always be less than the end time.
 *
 * The number of meetings will be at least 1 and will be no larger than 100.
 * The list of meetings will not necessarily be ordered in any particular fashion.
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
 * (int) meetings = [[0, 1], [1, 2], [2, 3], [3, 5], [4, 5]]
 * Output:
 * (int) 4
 *
 * Inputs:
 * (int) meetings = [[0, 1000000], [42, 43], [0, 1000000], [42, 43]]
 * Output:
 * (int) 1
 *
 * Use verify [file] to test your solution and see how it does. When you are finished editing your code, use submit [file] to submit your answer. If your solution passes the test cases, it will be removed from your home folder.
 */
public class ZombitAntidote {

    public static void main(String[] args) {
        final int[][] meetings1 = new int[][] { new int[] { 0, 1 }, new int[] { 1, 2 }, new int[] { 2, 3 }, new int[] { 3, 5 }, new int[] { 4, 5 } };
        System.out.println(answer(meetings1)); // 4

        final int[][] meetings2 = new int[][] { new int[] { 0, 1000000 }, new int[] { 42, 43 }, new int[] { 0, 1000000 }, new int[] { 42, 43 } };
        System.out.println(answer(meetings2)); // 1

        final int[][] meetings3 = new int[][] { new int[] { 4, 5 }, new int[] { 3, 6 }, new int[] { 2, 7 }, new int[] { 4, 8 } };
        System.out.println(answer(meetings3)); // 1

        final int[][] meetings3Point5 = new int[][] { new int[] { 3, 6 }, new int[] { 4, 5 } };
        System.out.println(answer(meetings3Point5)); // 1

        final int[][] meetings4 = new int[][] { new int[] { 2, 4 }, new int[] { 3, 4 }, new int[] { 0, 3 }, new int[] { 2, 3 }, new int[] { 1, 2 } };
        System.out.println(answer(meetings4)); // 3

        final int[][] meetings5 = new int[][] { new int[] { 2, 4 }, new int[] { 3, 5 } };
        System.out.println(answer(meetings5)); // 1

        final int[][] meetings6 = new int[][] { new int[] { 3, 5 }, new int[] { 2, 4 } };
        System.out.println(answer(meetings6)); // 1

        final int[][] meetings7 = new int[][] { new int[] { 1, 3 }, new int[] { 1, 2 } };
        System.out.println(answer(meetings7)); // 1

        final int[][] meetings8 = new int[][] { new int[] { 2, 4 }, new int[] { 4, 5 }, new int[] { 5, 6 }, new int[] { 2, 3 }, new int[] { 4, 6 } };
        System.out.println(answer(meetings8)); // 3

        final int[][] meetings9 = new int[][] { new int[] { 4, 6 }, new int[] { 5, 7 }, new int[] { 6, 8 } };
        System.out.println(answer(meetings9)); // 2
    }

    public static int answer(int[][] meetings) {
        List<Meeting> meetingList = convertArrayToList(meetings);

        Collections.sort(meetingList, new Comparator<Meeting>() {
            @Override
            public int compare(Meeting o1, Meeting o2) {
                return o1.getEndTime() - o2.getEndTime();
            }
        });

        List<Meeting> acceptedMeetings = new ArrayList<>();

        for (Meeting meeting : meetingList) {
            boolean timeFilled = false;

            for (Meeting accepted : acceptedMeetings) {
                if (timeFilled = meeting.getStartTime() < accepted.getEndTime()
                        && meeting.getEndTime() > accepted.getStartTime()) {
                    break;
                }
            }

            if (!timeFilled) {
                acceptedMeetings.add(meeting);
            }
        }

        return acceptedMeetings.size();
    }

    private static List<Meeting> convertArrayToList(int[][] meetings) {
        List<Meeting> results = new ArrayList<>();

        for (int i = 0; i < meetings.length; i++) {
            results.add(new Meeting(i, meetings[i][0], meetings[i][1]));
        }

        return results;
    }

    public static class Meeting {

        private int id;
        private int startTime;
        private int endTime;

        public Meeting(int id, int startTime, int endTime) {
            this.id = id;
            this.startTime = startTime;
            this.endTime = endTime;
        }

        public int getId() {
            return id;
        }

        public int getStartTime() {
            return startTime;
        }

        public int getEndTime() {
            return endTime;
        }

        @Override
        public boolean equals(Object obj) {
            return obj instanceof Meeting && this.id == ((Meeting) obj).getId();
        }

        @Override
        public int hashCode() {
            return id;
        }
    }
}

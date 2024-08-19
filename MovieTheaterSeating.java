package org.example;

import java.util.TreeSet;

public class MovieTheaterSeating {
    public boolean canFriendsSitTogether(int[] seatNumbers, int maxIndexDiff, int maxSeatDiff) {
        if (seatNumbers == null || seatNumbers.length < 2) {
            return false;
        }

        TreeSet<Integer> availableSeats = new TreeSet<>();

        for (int i = 0; i < seatNumbers.length; i++) {
            // Find the smallest seat number in the set that is >= seatNumbers[i] - maxSeatDiff
            Integer potentialSeat = availableSeats.ceiling(seatNumbers[i] - maxSeatDiff);
            if (potentialSeat != null && potentialSeat <= seatNumbers[i] + maxSeatDiff) {
                return true; // Friends can sit together based on the given conditions
            }

            // Add the current seat number to the set
            availableSeats.add(seatNumbers[i]);

            // Maintain the sliding window by removing seats that exceed the index difference limit
            if (i >= maxIndexDiff) {
                availableSeats.remove(seatNumbers[i - maxIndexDiff]);
            }
        }

        return false;
    }

    public static void main(String[] args) {
        MovieTheaterSeating movieTheater = new MovieTheaterSeating();

        int[] seatNumbers = {1, 2, 4, 6, 7};
        int maxIndexDiff = 2;
        int maxSeatDiff = 1;

        System.out.println(movieTheater.canFriendsSitTogether(seatNumbers, maxIndexDiff, maxSeatDiff)); // Output: true
    }
}

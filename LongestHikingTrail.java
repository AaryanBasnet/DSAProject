package org.example;

public class LongestHikingTrail {

    public static int findLongestHike(int[] nums, int k) {
        int maxLength = 0; // Tracks the maximum length of the hike
        int currentLength = 1; // Tracks the current length of a valid hike

        for (int i = 1; i < nums.length; i++) {
            // Check if the current segment is uphill and within the allowed elevation gain limit
            if (nums[i] > nums[i - 1] && nums[i] - nums[i - 1] <= k) {
                currentLength++; // Extend the current hike length
            } else {
                // Reset the current hike length as it's no longer valid
                currentLength = 1;
            }
            // Update the maximum hike length found
            maxLength = Math.max(maxLength, currentLength);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        int[] trail = {4, 2, 1, 4, 3, 4, 5, 8, 15};
        int k = 3;

        int longestHike = findLongestHike(trail, k);
        System.out.println("The longest continuous hike length is: " + longestHike);
    }
}

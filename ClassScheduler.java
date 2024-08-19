package org.example;

import java.util.*;

public class ClassScheduler {
    static class Class {
        int startTime;
        int endTime;
        int classSize;

        public Class(int startTime, int endTime, int classSize) {
            this.startTime = startTime;
            this.endTime = endTime;
            this.classSize = classSize;
        }
    }

    public static int findMostUsedClassroom(int numRooms, int[][] classData) {
        List<Class> classList = new ArrayList<>();
        for (int[] c : classData) {
            classList.add(new Class(c[0], c[1], c[2]));
        }

        // Sort classes by start time, then by class size (larger sizes get priority)
        classList.sort((a, b) -> {
            if (a.startTime != b.startTime) return a.startTime - b.startTime;
            return b.classSize - a.classSize;
        });

        // Priority queue to track room end times
        PriorityQueue<int[]> roomAvailability = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        int[] roomUsageCount = new int[numRooms]; // Tracks how many classes are held in each room

        for (Class cls : classList) {
            // Free up rooms that are done before the current class starts
            while (!roomAvailability.isEmpty() && roomAvailability.peek()[0] <= cls.startTime) {
                roomAvailability.poll();
            }

            if (roomAvailability.size() < numRooms) {
                // Use an available room
                int room = roomAvailability.size();
                roomAvailability.add(new int[]{cls.endTime, room});
                roomUsageCount[room]++;
            } else {
                // Delay the class until the earliest room is free
                int[] earliestRoom = roomAvailability.poll();
                int adjustedEndTime = earliestRoom[0] + (cls.endTime - cls.startTime);
                roomAvailability.add(new int[]{adjustedEndTime, earliestRoom[1]});
                roomUsageCount[earliestRoom[1]]++;
            }
        }

        // Determine the room with the highest usage
        int maxUsage = 0;
        int mostUsedRoom = 0;
        for (int i = 0; i < numRooms; i++) {
            if (roomUsageCount[i] > maxUsage) {
                maxUsage = roomUsageCount[i];
                mostUsedRoom = i;
            }
        }

        return mostUsedRoom;
    }

    public static void main(String[] args) {
        int numRooms1 = 2;
        int[][] classData1 = {{0, 10, 30}, {1, 5, 20}, {2, 7, 25}, {3, 4, 15}};
        System.out.println(findMostUsedClassroom(numRooms1, classData1)); // Output: 0

        int numRooms2 = 3;
        int[][] classData2 = {{1, 20, 30}, {2, 10, 25}, {3, 5, 15}, {4, 9, 10}, {6, 8, 20}};
        System.out.println(findMostUsedClassroom(numRooms2, classData2)); // Output: 1
    }
}

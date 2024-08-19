package org.example;

import java.util.Arrays;

public class ReverseInChunks {

    // Definition for singly-linked list.
    static class ListNode {
        int value;
        ListNode nextNode;
        ListNode(int x) { value = x; }
    }

    public static void main(String[] args) {
        System.out.println("Example 1:");
        int[] array1 = {1, 2, 3, 4, 5};
        int chunkSize1 = 2;
        ListNode head1 = arrayToList(array1);
        ListNode reversedHead1 = reverseChunks(head1, chunkSize1);
        int[] reversedArray1 = listToArray(reversedHead1);
        System.out.println("Reversed Sequence: " + Arrays.toString(reversedArray1));

        System.out.println("Example 2:");
        int[] array2 = {1, 2, 3, 4, 5};
        int chunkSize2 = 3;
        ListNode head2 = arrayToList(array2);
        ListNode reversedHead2 = reverseChunks(head2, chunkSize2);
        int[] reversedArray2 = listToArray(reversedHead2);
        System.out.println("Reversed Sequence: " + Arrays.toString(reversedArray2));
    }

    // Function to reverse nodes in chunks of size k
    public static ListNode reverseChunks(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.nextNode = head;
        ListNode previousChunkEnd = dummy;
        ListNode currentNode = head;

        // Count the number of nodes in the linked list
        int totalNodes = 0;
        while (currentNode != null) {
            totalNodes++;
            currentNode = currentNode.nextNode;
        }

        currentNode = dummy.nextNode;
        while (totalNodes >= k) {
            ListNode chunkStart = currentNode;
            ListNode previousNode = null;
            ListNode tempNode = null;

            // Reverse k nodes
            for (int i = 0; i < k; i++) {
                tempNode = currentNode.nextNode;
                currentNode.nextNode = previousNode;
                previousNode = currentNode;
                currentNode = tempNode;
            }

            // Connect with the previous part
            previousChunkEnd.nextNode = previousNode;
            chunkStart.nextNode = currentNode;
            previousChunkEnd = chunkStart;

            totalNodes -= k;
        }

        return dummy.nextNode;
    }

    // Helper method to convert an array to a linked list
    private static ListNode arrayToList(int[] array) {
        ListNode dummy = new ListNode(0);
        ListNode currentNode = dummy;
        for (int num : array) {
            currentNode.nextNode = new ListNode(num);
            currentNode = currentNode.nextNode;
        }
        return dummy.nextNode;
    }

    // Helper method to convert a linked list to an array
    private static int[] listToArray(ListNode head) {
        int size = 0;
        ListNode tempNode = head;
        while (tempNode != null) {
            size++;
            tempNode = tempNode.nextNode;
        }

        int[] array = new int[size];
        tempNode = head;
        int index = 0;
        while (tempNode != null) {
            array[index++] = tempNode.value;
            tempNode = tempNode.nextNode;
        }
        return array;
    }
}

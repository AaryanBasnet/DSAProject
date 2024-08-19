package org.example;

public class SecretDecoderMessage {

    public static String decodeMessage(String message, int[][] shifts) {
        char[] chars = message.toCharArray();
        // Iterate through each shift and update the characters accordingly
        for (int[] shift : shifts) {
            int start = shift[0];
            int end = shift[1];
            int direction = shift[2];

            for (int i = start; i <= end; i++) {
                chars[i] = shiftCharacter(chars[i], direction);
            }
        }

        return new String(chars);
    }
// Shifts the character based on the given direction
    private static char shiftCharacter(char c, int direction) {
        if (direction == 1) { // Shift forward
            return c == 'z' ? 'a' : (char) (c + 1);
        } else { // Shift backward
            return c == 'a' ? 'z' : (char) (c - 1);
        }
    }

    public static void main(String[] args) {
        String message = "hello";
        int[][] shifts = { {0, 1, 1}, {2, 3, 0}, {0, 2, 1} };
        System.out.println(decodeMessage(message, shifts));
// Output: jglko
    }
}
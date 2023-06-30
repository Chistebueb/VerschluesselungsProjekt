package ch.bbw.verschluesselungm114.encryption;

import java.nio.charset.Charset;
import java.util.Random;

public class PlayFair implements Encryption {
    private final int SIZE = 5; // Size of the Playfair matrix
    private char[][] matrix; // Playfair matrix

    public PlayFair() {
        matrix = new char[SIZE][SIZE];
    }

    private void generateMatrix(String key) {
        key = key.replaceAll("[^a-zA-Z]", "").toUpperCase(); // Remove non-alphabetic characters and convert to uppercase
        key = key.replace("J", "I"); // Replace 'J' with 'I'

        // Fill the matrix with the key
        int rowIndex = 0;
        int colIndex = 0;
        boolean[] usedChars = new boolean[26];
        for (char ch : key.toCharArray()) {
            int charIndex = ch - 'A';
            if (!usedChars[charIndex]) {
                matrix[rowIndex][colIndex] = ch;
                usedChars[charIndex] = true;
                colIndex++;
                if (colIndex == SIZE) {
                    colIndex = 0;
                    rowIndex++;
                    if (rowIndex == SIZE)
                        break;
                }
            }
        }

        // Fill the remaining matrix with other alphabetic characters
        char ch = 'A';
        while (rowIndex < SIZE) {
            if (ch != 'J' && !usedChars[ch - 'A']) {
                matrix[rowIndex][colIndex] = ch;
                colIndex++;
                if (colIndex == SIZE) {
                    colIndex = 0;
                    rowIndex++;
                }
            }
            ch++;
        }
    }

    private void getCharIndices(char ch, int[] indices) {
        if (ch == 'J')
            ch = 'I';

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (matrix[i][j] == ch) {
                    indices[0] = i;
                    indices[1] = j;
                    return;
                }
            }
        }
    }

    @Override
    public String encrypt(String key, String input) {
        generateMatrix(key);

        StringBuilder encryptedText = new StringBuilder();
        input = input.replaceAll("[^a-zA-Z]", "").toUpperCase(); // Remove non-alphabetic characters and convert to uppercase
        input = input.replace("J", "I"); // Replace 'J' with 'I'

        // Add a padding 'X' at the end if the length is odd
        if (input.length() % 2 != 0) {
            input += '+';
        }

        int index = 0;
        while (index < input.length()) {
            char ch1 = input.charAt(index);
            char ch2 = input.charAt(index + 1);
            index += 2;

            int[] ch1Indices = new int[2];
            int[] ch2Indices = new int[2];
            getCharIndices(ch1, ch1Indices);
            getCharIndices(ch2, ch2Indices);

            if (ch1Indices[0] == ch2Indices[0]) {
                encryptedText.append(matrix[ch1Indices[0]][(ch1Indices[1] + 1) % SIZE]);
                encryptedText.append(matrix[ch2Indices[0]][(ch2Indices[1] + 1) % SIZE]);
            } else if (ch1Indices[1] == ch2Indices[1]) {
                encryptedText.append(matrix[(ch1Indices[0] + 1) % SIZE][ch1Indices[1]]);
                encryptedText.append(matrix[(ch2Indices[0] + 1) % SIZE][ch2Indices[1]]);
            } else {
                encryptedText.append(matrix[ch1Indices[0]][ch2Indices[1]]);
                encryptedText.append(matrix[ch2Indices[0]][ch1Indices[1]]);
            }
        }

        return encryptedText.toString();
    }

    @Override
    public String decrypt(String key, String encryptedInput) {
        generateMatrix(key);

        StringBuilder decryptedText = new StringBuilder();
        encryptedInput = encryptedInput.replaceAll("[^a-zA-Z]", "").toUpperCase(); // Remove non-alphabetic characters and convert to uppercase

        int index = 0;
        while (index < encryptedInput.length()) {
            char ch1 = encryptedInput.charAt(index);
            char ch2 = encryptedInput.charAt(index + 1);
            index += 2;

            int[] ch1Indices = new int[2];
            int[] ch2Indices = new int[2];
            getCharIndices(ch1, ch1Indices);
            getCharIndices(ch2, ch2Indices);

            if (ch1Indices[0] == ch2Indices[0]) {
                decryptedText.append(matrix[ch1Indices[0]][(ch1Indices[1] + SIZE - 1) % SIZE]);
                decryptedText.append(matrix[ch2Indices[0]][(ch2Indices[1] + SIZE - 1) % SIZE]);
            } else if (ch1Indices[1] == ch2Indices[1]) {
                decryptedText.append(matrix[(ch1Indices[0] + SIZE - 1) % SIZE][ch1Indices[1]]);
                decryptedText.append(matrix[(ch2Indices[0] + SIZE - 1) % SIZE][ch2Indices[1]]);
            } else {
                decryptedText.append(matrix[ch1Indices[0]][ch2Indices[1]]);
                decryptedText.append(matrix[ch2Indices[0]][ch1Indices[1]]);
            }
        }

        if(decryptedText.toString().endsWith("+")) {
            StringBuffer output = new StringBuffer(decryptedText.toString());
            output.deleteCharAt(output.length() - 1);
            return output.toString();
        }
        return decryptedText.toString().toString();
    }
}
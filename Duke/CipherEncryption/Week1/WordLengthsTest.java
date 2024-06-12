package CipherEncryption.Week1;
import edu.duke.*;
import java.io.*;

/**
 * Write a description of WordLengthsTest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordLengthsTest {
     public static void countWordLengths(FileResource resource, int[] counts) {
        for (String word : resource.words()) {
            int wordLength = 0;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (i == 0 || i == word.length() - 1) {
                    if (Character.isLetter(ch)) {
                        wordLength++;
                    }
                } else {
                    if (Character.isLetter(ch)) {
                        wordLength++;
                    }
                }
            }
            if (wordLength >= counts.length) {
                counts[counts.length - 1]++;
            } else {
                counts[wordLength]++;
            }
        }
    }
    
    public int indexOfMax(int[] values) {
    int maxIndex = 0;
    int maxValue = values[0];

    for (int i = 1; i < values.length; i++) {
        if (values[i] > maxValue) {
            maxIndex = i;
            maxValue = values[i];
        }
    }

    return maxIndex;
}

    public static void testCountWordLengths() {
        FileResource resource = new FileResource(); // Prompt the user to select a file
        int[] counts = new int[31]; // Initialize an array to store word length counts

        countWordLengths(resource, counts);

        for (int i = 1; i < counts.length; i++) {
            System.out.println(counts[i] + " Words of length " + i + ": " + counts[i]);
        }
        
        int mostCommonWordLength = indexOfMax(counts);
        System.out.println("The most common word length is: " + mostCommonWordLength);
    }

    public static void main(String[] args) {
        testCountWordLengths();
    }

}

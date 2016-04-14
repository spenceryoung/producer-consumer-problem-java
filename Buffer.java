package start;

import java.util.Random;

public class Buffer {
    
    // Create an integer array that contains 10 elements
    public int ARRAY_SIZE = 10;
    public int[] buffArray = new int[ARRAY_SIZE];
    
    // Create variables for keeping track of the array's index
    public int in = 0;
    public int out = 0;
    
    // Generate a random integer between 10-100 milliseconds
    public static int randNum() {
        
        // Set variables for upper and lower boundries
        int min = 10;
        int max = 100;
        
        // Create a new instance of Random and calculate random integer
        Random rand = new Random();
        int randomMilli = rand.nextInt((max - min) + 1) + min;
        return randomMilli;
    }
}
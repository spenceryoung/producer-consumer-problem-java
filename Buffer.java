package start;

import java.util.Random;

public class Buffer {
    public int ARRAY_SIZE = 10;
    public int[] buffArray = new int[ARRAY_SIZE];
    
    public int in = 0;
    public int out = 0;
    
    public static int randNum() {
        int min = 10;
        int max = 100;
        
        Random rand = new Random();
        int randomMilli = rand.nextInt((max - min) + 1) + min;
        return randomMilli;
    }
}
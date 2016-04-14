package start;

import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static start.Buffer.randNum;

public class Consumer extends Thread {
    public Buffer buffer;

    // Consumer constructor
    public Consumer(Buffer BUFFER) {
        this.buffer = BUFFER;
    }
    
    @Override
    public void run() {
        
        // initialize next consumed
        int next_consumed;
        
        // Set a variable to the current time 
        // Set a variable that is 15 seconds after start
        long start = System.currentTimeMillis();
        long stop = start + 15000;
        
        // Run for 15 seconds
        while (System.currentTimeMillis() < stop) {
            try {
                // Sleep for the results of the randNum function before checking the buffer
                Thread.sleep(randNum());
            }
            catch (InterruptedException e) { }

            // Continue to sleep for random times as long as the buffer is empty
            while (buffer.in == buffer.out) {
                try {
                    Thread.sleep(randNum());
                }
                catch (InterruptedException e) { }
            }

            // Consume next_consmed from 'out' array position
            next_consumed = buffer.buffArray[buffer.out];
            System.out.println(System.currentTimeMillis() + ", Consuming " + next_consumed
                    + " from the buffer location " + buffer.out);
            
            // Write the results to producer.txt
            try (FileWriter writer = new FileWriter("consumer.txt", true)) {
                writer.write(System.currentTimeMillis() + ", Consuming " + next_consumed
                    + " from the buffer location " + buffer.out);
                writer.write("\r\n");
            } catch (IOException ex) {
                Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
            }

            // Update variable to the next position in the array
            // If the current position is the end of the array, go back to the first position
            buffer.out = (buffer.out + 1) % buffer.ARRAY_SIZE;
        }
    }
}
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
            catch (InterruptedException ex) { 
                Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
            }

            // Continue to sleep for random times as long as the buffer is empty
            while (buffer.in == buffer.out) {
                try {
                    Thread.sleep(randNum());
                }
                catch (InterruptedException ex) { 
                    Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            // Consume next_consmed from 'out' array position
            next_consumed = buffer.buffArray[buffer.out];
            System.out.println(System.currentTimeMillis() + ", Consuming " + next_consumed
                    + " from the buffer location " + buffer.out);
            
            // Write the results to producer.txt
            try (FileWriter output = new FileWriter("consumer.txt", true)) {
                output.write(System.currentTimeMillis() + ", Consuming " + next_consumed
                    + " from the buffer location " + buffer.out);
                output.write("\r\n");
            } catch (IOException ex) {
                Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
            }

            // Update variable to the next position in the array
            // If the current position is the end of the array, go back to the first position
            buffer.out = (buffer.out + 1) % buffer.ARRAY_SIZE;
        }
    }
}
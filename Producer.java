package start;

import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static start.Buffer.randNum;

public class Producer extends Thread {
    public Buffer buffer;

    // Producer constructor
    public Producer(Buffer BUFFER) {
        this.buffer = BUFFER;
    }
    
    
    @Override
    public void run() {
        
        // initialize next_produced to zero
        int next_produced = 0;
        
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

            // Continue to sleep for random times as long as the buffer is full
            while (((buffer.in + 1) % buffer.ARRAY_SIZE) == buffer.out) {
                try {
                    Thread.sleep(randNum());
                }
                catch (InterruptedException ex) { 
                    Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            // Place next_produced to 'in' array position
            buffer.buffArray[buffer.in] = next_produced;
            System.out.println(System.currentTimeMillis() + ", Placing " + next_produced
                    + " in the buffer location " + buffer.in);

            // Write the results to producer.txt
            try (FileWriter output = new FileWriter("producer.txt", true)) {
                output.write(System.currentTimeMillis() + ", Placing " + next_produced
                    + " in the buffer location " + buffer.in);
                output.write("\r\n"); 
            } catch (IOException ex) {
                Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
            }

            // Update variable to the next position in the array
            // If the current position is the end of the array, go back to the first position
            buffer.in = (buffer.in + 1) % buffer.ARRAY_SIZE;

            //  generate integers from 0-14 and assign it to next_produced
            if (next_produced == 14){
                next_produced = 0;
            } else {
                next_produced++;
            }
        }
    }
}

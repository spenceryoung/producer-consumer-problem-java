package start;

import static start.Buffer.randNum;
import static java.lang.System.currentTimeMillis;

public class Consumer extends Thread {
    public Buffer buffer;

    public Consumer(Buffer BUFFER) {
        this.buffer = BUFFER;
    }
    
    @Override
    public void run() {
        for(int i=0; i<10000000; i++) {
            int next_consumed = 0;
            
            while (true) {
                try {
                    Thread.sleep(randNum());
                }
                catch (InterruptedException e) { }
                while (buffer.in == buffer.out) {
                    System.out.println("consumer looping");
                }    
                System.out.println(currentTimeMillis() + " Consuming " + next_consumed
                        + " from the buffer location " + buffer.out);
                next_consumed = buffer.buffArray[buffer.out];
                buffer.out = (buffer.out + 1) % buffer.ARRAY_SIZE;
            }
        }
    }
}
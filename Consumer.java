package start;

import static java.lang.System.currentTimeMillis;
import static start.Buffer.randNum;

public class Consumer extends Thread {
    public Buffer buffer;

    public Consumer(Buffer BUFFER) {
        this.buffer = BUFFER;
    }
    
    @Override
    public void run() {
        int next_consumed;
        long start = System.currentTimeMillis();
        long stop = start + 15000;
        
        for(int i=0; i<10000000; i++) {
            while (System.currentTimeMillis() < stop) {
                try {
                    Thread.sleep(randNum());
                }
                catch (InterruptedException e) { }
                while (buffer.in == buffer.out) {
                    try {
                        Thread.sleep(randNum());
                    }
                    catch (InterruptedException e) { }
                }
                next_consumed = buffer.buffArray[buffer.out];
                System.out.println(System.currentTimeMillis() + " Consuming " + next_consumed
                        + " from the buffer location " + buffer.out);
                buffer.out = (buffer.out + 1) % buffer.ARRAY_SIZE;
            }
        }
    }
}
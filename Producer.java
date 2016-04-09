package start;

import static java.lang.System.currentTimeMillis;
import static start.Buffer.randNum;

public class Producer extends Thread {
    public Buffer buffer;

    public Producer(Buffer BUFFER) {
        this.buffer = BUFFER;
    }
    
    @Override
    public void run() {
        for(int i=0; i<10000000; i++) {
            int next_produced = 0;
        
            while (true) {
                try {
                    Thread.sleep(randNum());
                }
                catch (InterruptedException e) { }
                while (((buffer.in + 1) % buffer.ARRAY_SIZE) == buffer.out) {
                    System.out.println("consumer looping");
                }
                System.out.println(currentTimeMillis() + " Placing " + next_produced
                        + " in the buffer location " + buffer.in);
                buffer.buffArray[buffer.in] = next_produced;
                buffer.in = (buffer.in + 1) % buffer.ARRAY_SIZE;
                if (next_produced == 14){
                    next_produced = 0;
                } else {
                    next_produced++;
                }
            }
        }
    }
}

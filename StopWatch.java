package start;

import java.util.Timer;
import java.util.TimerTask;

public class StopWatch {
    Timer clock;
    
    public StopWatch(int seconds) {
        clock = new Timer();
        clock.schedule(new stopWatchTask(), seconds);
    }
    
    class stopWatchTask extends TimerTask {
        public void run(){
            System.exit(0);
        }
    }
}
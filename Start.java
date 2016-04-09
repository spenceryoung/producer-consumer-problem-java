package start;

public class Start {
    public static void main(String[] args) {
        Buffer BUFFER = new Buffer();
        StopWatch watch = new StopWatch(15000);

        Producer p = new Producer(BUFFER);
        Consumer c = new Consumer(BUFFER);

        p.start();
        c.start();
    }
}
package start;

public class Start {
    public static void main(String[] args) {
        
        // Create new instance of Buffer called BUFFER
        Buffer BUFFER = new Buffer();

        // Create new instance of Producer and Consumer
        // Pass BUFFER into both threads
        Producer p = new Producer(BUFFER);
        Consumer c = new Consumer(BUFFER);

        // Start both threads
        p.start();
        c.start();
    }
}
package start;

public class Start {
    public static void main(String[] args) {
        Buffer BUFFER = new Buffer();

        Producer p = new Producer(BUFFER);
        Consumer c = new Consumer(BUFFER);

        p.start();
        c.start();
    }
}
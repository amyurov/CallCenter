
import java.util.concurrent.BlockingQueue;

public class CallCustomer {

    private final static int CALL_HANDLE_TIME = 2000;

    protected BlockingQueue<Integer> queue;

    public CallCustomer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    public void callHandler() {
        while (true) {
            try {
                System.out.printf("%s start work with %d%n", Thread.currentThread().getName(), queue.take());
                Thread.sleep(CALL_HANDLE_TIME);
                System.out.printf("%s finish%n", Thread.currentThread().getName());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}


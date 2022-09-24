
import java.util.concurrent.*;

public class Main {

    static final int THREAD_COUNT = 8;
    static final int CLIENT_TIMEOUT = 1000;

    public static void main(String[] args) {
        // Очередь клиентов
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(32);

        //Поток ATC-Thread выполняет метод act()
        new Thread(() -> atc(queue), "ATC-Thread").start();

        // Создaем threadPool
        var threadPool = Executors.newFixedThreadPool(THREAD_COUNT);

        for (int i = 0; i < THREAD_COUNT; i++) {
            threadPool.execute(new CallCustomer(queue)::callHandler);
        }

    }

    // Метод act() эмулирует поступление входящих звонков. Звонки сохраняются в переданную очередь.
    static void atc(BlockingQueue<Integer> queue) {
        int clientsNumber = 0;
        while (true) {
            try {
                queue.put(clientsNumber);
                System.out.printf("Client-%d, waits for a response%n", clientsNumber++);
                Thread.sleep(CLIENT_TIMEOUT);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

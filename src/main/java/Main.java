
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
        // Очередь клиентов
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();

        //Поток ATC-Thread выполняет метод act()
        new Thread(() -> act(queue), "ATC-Thread").start();

        // Создaем threadPool
        var threadPool = Executors.newFixedThreadPool(16);

            threadPool.execute(new CallCustomer(queue)::callHandler);
            threadPool.execute(new CallCustomer(queue)::callHandler);
            threadPool.execute(new CallCustomer(queue)::callHandler);


    }

    // Метод act() эмулирует поступление входящих звонков. Звонки сохраняются в переданную очередь.
    static void act(BlockingQueue<Integer> queue) {
        for (int i = 0; i < 20; i++) {
            try {
                queue.put(i);
                System.out.printf("Client-%d, waits for a response%n", i);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

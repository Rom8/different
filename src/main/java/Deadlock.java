import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Rom8 on 14.08.2016.
 */
public class Deadlock {

    public static void main(String[] args) {
        BadBoy peter = new BadBoy("Peter");
        BadBoy wolf = new BadBoy("Wolf ");

        new Thread(() -> peter.call(wolf)    ).start();
        new Thread(() -> wolf.call(peter)    ).start();
    }


    private static class BadBoy{

        private final ReentrantLock reentrantLock = new ReentrantLock();

        private String name;

        private BadBoy(String name){
            this.name = name;
        }

        public void call(BadBoy friend){
            reentrantLock.lock();
            try {
                System.out.println(name + " calls his friend " + friend.name);
                Thread.sleep(500);
                friend.reply(this);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                reentrantLock.unlock();
            }
        }

        public void reply(BadBoy badBoy){
            reentrantLock.lock();
            try {
                System.out.println(name + " replies to " + badBoy.name);
            } finally {
                reentrantLock.unlock();
            }
        }
    }
}

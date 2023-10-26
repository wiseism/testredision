package cn.edidada.testredision;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

public class RedissonTest implements  Runnable{

    private static RedissonClient redisson;
    private static int count = 10000;
    private static void init() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://119.23.46.71:6340")
                .setPassword("root")
                .setDatabase(10);
        redisson = Redisson.create(config);
    }
    @Override
    public void run() {
        RLock lock = redisson.getLock("anyLock");
        lock.lock();
        count--;
        System.out.println(count);
        lock.unlock();
    }
    public static void main(String[] args) {
        init();
        for (int i = 0; i < 100; i++) {
            new Thread(new RedissonTest()).start();
        }
    }
}

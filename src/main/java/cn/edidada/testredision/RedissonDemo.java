package cn.edidada.testredision;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

public class RedissonDemo {

    public static void main(String[] args) {
        // 创建配置对象
        Config config = new Config();
        // 设置 Redis 服务器地址和端口
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        config.setLockWatchdogTimeout(30000);
        // 创建 RedissonClient 实例
        RedissonClient redisson = Redisson.create(config);

        // 获取分布式锁对象
        RLock lock = redisson.getLock("myLock");

        try {
            // 尝试获取锁，设置超时时间（单位：毫秒），并启用看门狗自动续期
            if (lock.tryLock(1000, 3000, TimeUnit.MILLISECONDS)) {
                try {
                    // 执行需要同步的代码
                } finally {
                    // 释放锁
                    lock.unlock();
                }
            } else {
                System.out.println("获取锁失败");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 关闭 RedissonClient
            redisson.shutdown();
        }
    }
}

# README

Redisson 是一个在 Redis 上实现的 Java 分布式对象和服务框架。它提供了丰富的分布式锁功能，包括看门狗自动续期。

要使用 Redisson 实现分布式锁的看门狗自动续期，首先需要添加 Redisson 依赖到项目中。以 Maven 为例：

```xml
<dependency>
    <groupId>org.redisson</groupId>
    <artifactId>redisson</artifactId>
    <version>3.16.4</version>
</dependency>
```

接下来，创建一个 RedissonClient 实例，用于连接 Redis 服务器：

```java
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

public class RedissonDemo {

    public static void main(String[] args) {
        // 创建配置对象
        Config config = new Config();
        // 设置 Redis 服务器地址和端口
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
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
```

在这个示例中，我们使用了 Redisson 提供的 `RLock` 接口来获取分布式锁。通过调用 `tryLock` 方法，我们可以设置锁的超时时间和是否启用看门狗自动续期。如果成功获取锁，可以在 `try` 语句块中执行需要同步的代码；最后，在 `finally` 语句块中释放锁。

```shell
D:\Java\jdk1.8.0_231\bin\java.exe "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA 2021.1.1\lib\idea_rt.jar=6393:C:\Program Files\JetBrains\IntelliJ IDEA 2021.1.1\bin" -Dfile.encoding=UTF-8 -classpath D:\Java\jdk1.8.0_231\jre\lib\charsets.jar;D:\Java\jdk1.8.0_231\jre\lib\deploy.jar;D:\Java\jdk1.8.0_231\jre\lib\ext\access-bridge-64.jar;D:\Java\jdk1.8.0_231\jre\lib\ext\cldrdata.jar;D:\Java\jdk1.8.0_231\jre\lib\ext\dnsns.jar;D:\Java\jdk1.8.0_231\jre\lib\ext\jaccess.jar;D:\Java\jdk1.8.0_231\jre\lib\ext\jfxrt.jar;D:\Java\jdk1.8.0_231\jre\lib\ext\localedata.jar;D:\Java\jdk1.8.0_231\jre\lib\ext\nashorn.jar;D:\Java\jdk1.8.0_231\jre\lib\ext\sunec.jar;D:\Java\jdk1.8.0_231\jre\lib\ext\sunjce_provider.jar;D:\Java\jdk1.8.0_231\jre\lib\ext\sunmscapi.jar;D:\Java\jdk1.8.0_231\jre\lib\ext\sunpkcs11.jar;D:\Java\jdk1.8.0_231\jre\lib\ext\zipfs.jar;D:\Java\jdk1.8.0_231\jre\lib\javaws.jar;D:\Java\jdk1.8.0_231\jre\lib\jce.jar;D:\Java\jdk1.8.0_231\jre\lib\jfr.jar;D:\Java\jdk1.8.0_231\jre\lib\jfxswt.jar;D:\Java\jdk1.8.0_231\jre\lib\jsse.jar;D:\Java\jdk1.8.0_231\jre\lib\management-agent.jar;D:\Java\jdk1.8.0_231\jre\lib\plugin.jar;D:\Java\jdk1.8.0_231\jre\lib\resources.jar;D:\Java\jdk1.8.0_231\jre\lib\rt.jar;G:\Blogs\testredision\target\classes;C:\Users\edidada\.m2\repository\org\redisson\redisson\3.23.4\redisson-3.23.4.jar;C:\Users\edidada\.m2\repository\io\netty\netty-common\4.1.96.Final\netty-common-4.1.96.Final.jar;C:\Users\edidada\.m2\repository\io\netty\netty-codec\4.1.96.Final\netty-codec-4.1.96.Final.jar;C:\Users\edidada\.m2\repository\io\netty\netty-buffer\4.1.96.Final\netty-buffer-4.1.96.Final.jar;C:\Users\edidada\.m2\repository\io\netty\netty-transport\4.1.96.Final\netty-transport-4.1.96.Final.jar;C:\Users\edidada\.m2\repository\io\netty\netty-resolver\4.1.96.Final\netty-resolver-4.1.96.Final.jar;C:\Users\edidada\.m2\repository\io\netty\netty-resolver-dns\4.1.96.Final\netty-resolver-dns-4.1.96.Final.jar;C:\Users\edidada\.m2\repository\io\netty\netty-codec-dns\4.1.96.Final\netty-codec-dns-4.1.96.Final.jar;C:\Users\edidada\.m2\repository\io\netty\netty-handler\4.1.96.Final\netty-handler-4.1.96.Final.jar;C:\Users\edidada\.m2\repository\io\netty\netty-transport-native-unix-common\4.1.96.Final\netty-transport-native-unix-common-4.1.96.Final.jar;C:\Users\edidada\.m2\repository\javax\cache\cache-api\1.1.1\cache-api-1.1.1.jar;C:\Users\edidada\.m2\repository\io\projectreactor\reactor-core\3.5.3\reactor-core-3.5.3.jar;C:\Users\edidada\.m2\repository\org\reactivestreams\reactive-streams\1.0.4\reactive-streams-1.0.4.jar;C:\Users\edidada\.m2\repository\io\reactivex\rxjava3\rxjava\3.1.6\rxjava-3.1.6.jar;C:\Users\edidada\.m2\repository\org\jboss\marshalling\jboss-marshalling\2.0.11.Final\jboss-marshalling-2.0.11.Final.jar;C:\Users\edidada\.m2\repository\org\jboss\marshalling\jboss-marshalling-river\2.0.11.Final\jboss-marshalling-river-2.0.11.Final.jar;C:\Users\edidada\.m2\repository\com\esotericsoftware\kryo\5.5.0\kryo-5.5.0.jar;C:\Users\edidada\.m2\repository\com\esotericsoftware\reflectasm\1.11.9\reflectasm-1.11.9.jar;C:\Users\edidada\.m2\repository\org\objenesis\objenesis\3.3\objenesis-3.3.jar;C:\Users\edidada\.m2\repository\com\esotericsoftware\minlog\1.3.1\minlog-1.3.1.jar;C:\Users\edidada\.m2\repository\org\slf4j\slf4j-api\1.7.36\slf4j-api-1.7.36.jar;C:\Users\edidada\.m2\repository\com\fasterxml\jackson\core\jackson-annotations\2.15.2\jackson-annotations-2.15.2.jar;C:\Users\edidada\.m2\repository\com\fasterxml\jackson\dataformat\jackson-dataformat-yaml\2.15.2\jackson-dataformat-yaml-2.15.2.jar;C:\Users\edidada\.m2\repository\org\yaml\snakeyaml\2.0\snakeyaml-2.0.jar;C:\Users\edidada\.m2\repository\com\fasterxml\jackson\core\jackson-core\2.15.2\jackson-core-2.15.2.jar;C:\Users\edidada\.m2\repository\com\fasterxml\jackson\core\jackson-databind\2.15.2\jackson-databind-2.15.2.jar;C:\Users\edidada\.m2\repository\net\bytebuddy\byte-buddy\1.14.5\byte-buddy-1.14.5.jar;C:\Users\edidada\.m2\repository\org\jodd\jodd-bean\5.1.6\jodd-bean-5.1.6.jar;C:\Users\edidada\.m2\repository\org\jodd\jodd-core\5.1.6\jodd-core-5.1.6.jar cn.edidada.testredision.RedissonTest
SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
SLF4J: Defaulting to no-operation (NOP) logger implementation
SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.
Exception in thread "main" org.redisson.client.RedisConnectionException: Unable to connect to Redis server: 119.23.46.71/119.23.46.71:6340
	at org.redisson.connection.pool.ConnectionPool.lambda$createConnection$0(ConnectionPool.java:132)
	at java.util.concurrent.CompletableFuture.uniWhenComplete(CompletableFuture.java:760)
	at java.util.concurrent.CompletableFuture$UniWhenComplete.tryFire(CompletableFuture.java:736)
	at java.util.concurrent.CompletableFuture.postComplete(CompletableFuture.java:474)
	at java.util.concurrent.CompletableFuture.completeExceptionally(CompletableFuture.java:1977)
	at org.redisson.connection.pool.ConnectionPool.promiseFailure(ConnectionPool.java:294)
	at org.redisson.connection.pool.ConnectionPool.lambda$createConnection$6(ConnectionPool.java:253)
	at java.util.concurrent.CompletableFuture.uniWhenComplete(CompletableFuture.java:760)
	at java.util.concurrent.CompletableFuture$UniWhenComplete.tryFire(CompletableFuture.java:736)
	at java.util.concurrent.CompletableFuture.postComplete(CompletableFuture.java:474)
	at java.util.concurrent.CompletableFuture.completeExceptionally(CompletableFuture.java:1977)
	at org.redisson.client.RedisClient$1$2.run(RedisClient.java:257)
	at io.netty.util.concurrent.AbstractEventExecutor.runTask(AbstractEventExecutor.java:174)
	at io.netty.util.concurrent.AbstractEventExecutor.safeExecute(AbstractEventExecutor.java:167)
	at io.netty.util.concurrent.SingleThreadEventExecutor.runAllTasks(SingleThreadEventExecutor.java:470)
	at io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:569)
	at io.netty.util.concurrent.SingleThreadEventExecutor$4.run(SingleThreadEventExecutor.java:997)
	at io.netty.util.internal.ThreadExecutorMap$2.run(ThreadExecutorMap.java:74)
	at io.netty.util.concurrent.FastThreadLocalRunnable.run(FastThreadLocalRunnable.java:30)
	at java.lang.Thread.run(Thread.java:748)
Caused by: java.util.concurrent.CompletionException: io.netty.channel.ConnectTimeoutException: connection timed out: 119.23.46.71/119.23.46.71:6340
	at java.util.concurrent.CompletableFuture.encodeRelay(CompletableFuture.java:326)
	at java.util.concurrent.CompletableFuture.completeRelay(CompletableFuture.java:338)
	at java.util.concurrent.CompletableFuture.uniRelay(CompletableFuture.java:911)
	at java.util.concurrent.CompletableFuture$UniRelay.tryFire(CompletableFuture.java:899)
	... 11 more
Caused by: io.netty.channel.ConnectTimeoutException: connection timed out: 119.23.46.71/119.23.46.71:6340
	at io.netty.channel.nio.AbstractNioChannel$AbstractNioUnsafe$1.run(AbstractNioChannel.java:261)
	at io.netty.util.concurrent.PromiseTask.runTask(PromiseTask.java:98)
	at io.netty.util.concurrent.ScheduledFutureTask.run(ScheduledFutureTask.java:153)
	... 8 more

Process finished with exit code 1
```
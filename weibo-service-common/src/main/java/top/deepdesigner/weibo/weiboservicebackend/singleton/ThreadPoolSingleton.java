/**
 * Copyright(C),2015‐2023,北京清能互联科技有限公司
 */

package top.deepdesigner.weibo.weiboservicebackend.singleton;

import io.netty.util.concurrent.DefaultThreadFactory;
import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <br>
 *
 * @Author: duanrq@tsintergy.com
 * @Date: 2023/9/5 13:10
 * @Version: 1.0.0
 */
public class ThreadPoolSingleton {

    private ThreadPoolSingleton() {
    }

    private static ThreadPoolExecutor threadPoolExecutor;

    public static ThreadPoolExecutor instance() {
        if (Objects.isNull(threadPoolExecutor)) {
            synchronized (ThreadPoolSingleton.class) {
                if (Objects.isNull(threadPoolExecutor)) {
                    threadPoolExecutor = new ThreadPoolExecutor(8, 8, 1, TimeUnit.MILLISECONDS,
                        new ArrayBlockingQueue<>(1)
                        , new DefaultThreadFactory("custom")
                        , new ThreadPoolExecutor.AbortPolicy());
                }
            }
        }
        return threadPoolExecutor;
    }
}
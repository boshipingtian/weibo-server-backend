/**
 * Copyright(C),2015‐2023,北京清能互联科技有限公司
 */

package top.deepdesigner.weibo.weiboservicebackend.service.utils;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * <br>
 *
 * @Author: duanrq@tsintergy.com
 * @Date: 2023/9/5 13:58
 * @Version: 1.0.0
 */
@Component
public class RedisUtil {

    private static final Random RANDOM = new Random(System.currentTimeMillis());

    private static RedisTemplate<String, String> redisTemplate;

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
        RedisUtil.redisTemplate = redisTemplate;
    }

    /**
     * 功能描述: <br>
     *
     * @Version: 1.0.0
     * @Author: duanrq@tsintergy.com.cn
     * @Date: 2023/9/5 14:00
     */
    public static void put(String key, String value, Long time, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, time, timeUnit);
    }

    public static Optional<String> get(String key) {
        return Optional.ofNullable(redisTemplate.opsForValue().get(key));
    }

    public static void delete(String key) {
        redisTemplate.opsForValue().getAndDelete(key);
    }

    public static long randomExpire(int min, int max) {
        return min + RANDOM.nextLong() * (max - min);
    }
}
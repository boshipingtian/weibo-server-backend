/**
 * Copyright(C),2015‐2024,北京清能互联科技有限公司
 */

package top.deepdesigner.weibo.weiboservicebackend.service.cache.impl;

import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import top.deepdesigner.weibo.weiboservicebackend.service.cache.api.CacheService;

/**
 *  <br>
 *
 * @Author: duanrq@tsintergy.com
 * @Date: 2024/3/22 10:27
 * @Version: 1.0.0
 */
@Slf4j
@Service
public class CacheServiceImpl implements CacheService {
    
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

}
package com.yichen.cosmos.cloud.platform.rabbit.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Redis Helper
 * Created by Linq on 2017/3/28 9:59.
 */
@Component
public class RedisHelper {

    private Logger logger = LoggerFactory.getLogger(RedisHelper.class);

    public static RedisHelper redisHelper;

    @Autowired
    private RedisTemplate redisTemplate;

    @PostConstruct
    public void init() {
        redisHelper = this;
    }

    public RedisTemplate redisTemplate() {
        return redisTemplate;
    }

    /**
     * 判断是否有对应的key
     *
     * @param key
     * @return
     */
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }

    public boolean updateExpire(String key, long expire) {
        return redisTemplate.expire(key, expire, TimeUnit.MILLISECONDS);
    }

    /**
     * 删除Redis中key对应的value
     *
     * @param key
     */
    public void remove(final String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
            logger.info("KEY[{}] 删除成功", key);
        }
    }

    /**
     * 批量删除Redis中的key
     *
     * @param keys
     */
    public void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }

    /**
     * 批量删除Redis中的key
     *
     * @param pattern
     */
    public void removePattern(final String pattern) {
        Set<Serializable> keys = redisTemplate.keys(pattern);
        logger.info("delete keys = {}", keys);
        if (keys.size() > 0) {
            redisTemplate.delete(keys);
        }
    }

    /**
     * 通过KEY获取值
     *
     * @param key
     * @return
     */
    public Object get(final String key) {
        ValueOperations operations = redisTemplate.opsForValue();
        return operations.get(key);
    }

    /**
     * key 自增
     *
     * @param key 自增的key
     * @return 当前值
     */
    public Long increment(String key) {
        long nowIndex = redisTemplate.opsForValue().increment(key, 1);
        if (nowIndex == 1) {
            redisTemplate.expire(key, 25, TimeUnit.HOURS);
        }
        return nowIndex;
    }

    public Long increment(String key, int second) {
        long nowIndex = redisTemplate.opsForValue().increment(key, 1);
        if (nowIndex == 1) {
            redisTemplate.expire(key, second, TimeUnit.SECONDS);
        }
        return nowIndex;
    }


    /**
     * 写入Redis
     *
     * @param key
     * @param object
     * @return
     */
    public boolean set(final String key, Object object) {
        try {
            ValueOperations operations = redisTemplate.opsForValue();
            operations.set(key, object);
            logger.info("set key[{}] : value[{}]", key, object);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * 写入Redis
     *
     * @param key        key
     * @param object     value
     * @param expireTime 过期时间（毫秒）
     * @return
     */
    public boolean set(final String key, Object object, Long expireTime) {
        try {
            ValueOperations operations = redisTemplate.opsForValue();
            operations.set(key, object, expireTime, TimeUnit.MILLISECONDS);
            logger.info("set key[{}] : value[{}] - expireTime[{} ms]", key, object, expireTime);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 自减
     *
     * @param key key
     * @return 结果
     */
    public long decrement(String key) {
        return decrement(key, 1L);
    }

    public long decrement(String key, long value) {
        return (long) redisTemplate.execute((RedisCallback<Long>) connection -> connection.decrBy(key.getBytes(), value));
    }

    /**
     * 如不存在，进行设置
     *
     * @param key    key
     * @param value  值
     * @param expire 过期时间 毫秒
     * @return 是否设置成功
     */
    public boolean setNX(String key, Object value, long expire) {

        // 永久
        if (expire <= 0) {
            return setNX(key, value);
        }

        return (Boolean) redisTemplate.execute((RedisCallback<Boolean>) connection -> {
            String valueStr = String.valueOf(value);

            Object conObj = connection.getNativeConnection();
            if (conObj instanceof Jedis) {
                // NX:不存在设置，XX:存在设置
                // EX:过期单位（秒），PX:毫秒
                String result = ((Jedis) conObj).set(key, valueStr, "NX", "PX", expire);
                return "OK".equals(result);
            } else {
                throw new RuntimeException("不是jedis连接类型");
            }
        }, true);
    }

    public boolean setNX(String key, Object value) {
        ValueOperations operations = redisTemplate.opsForValue();
        return operations.setIfAbsent(key, value);
    }

}

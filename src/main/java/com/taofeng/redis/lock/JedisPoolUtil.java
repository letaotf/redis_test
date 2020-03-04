package com.taofeng.redis.lock;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * <p>描述</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2020/2/29 11:37 AM
 * @since V1.0
 */
public class JedisPoolUtil {

    public static JedisPool jedisPool(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(101);
        jedisPoolConfig.setMaxIdle(1000);
        jedisPoolConfig.setMinIdle(1);
        jedisPoolConfig.setMaxWaitMillis(2000);
        jedisPoolConfig.setTestOnBorrow(true);
        jedisPoolConfig.setTestOnReturn(true);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig,"94.191.120.27",6379);
        return jedisPool;
    }
}

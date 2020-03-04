package com.taofeng.redis.lock;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * <p>描述</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2020/2/28 8:57 PM
 * @since V1.0
 */
@Configuration
@ComponentScan("com.taofeng.redis")
public class RedisConfig {

    @Bean
    public  JedisPool jedisPool(){
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

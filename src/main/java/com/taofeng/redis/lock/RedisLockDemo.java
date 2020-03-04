package com.taofeng.redis.lock;

/**
 * <p>描述</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2020/2/29 12:00 PM
 * @since V1.0
 */
public class RedisLockDemo {

    private static RedisLock redisLock = new RedisLock();

    public static void main(String[] args) throws Exception {

        redisLock.lock();

        redisLock.unlock();
    }
}

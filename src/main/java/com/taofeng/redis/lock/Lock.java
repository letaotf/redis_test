package com.taofeng.redis.lock;

import java.util.concurrent.TimeUnit;

/**
 * <p>描述</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2020/2/28 8:58 PM
 * @since V1.0
 */
public interface Lock {

    /**
     * 获取锁
     */
    void lock();

    /**
     * 获取中断锁
     * @throws InterruptedException
     */
    void lockInterruptibly() throws InterruptedException;

    /**
     * 尝试获取锁
     * @return
     */
    boolean tryLock();

    /**
     * 尝试获取锁
     * @param time
     * @param unit
     * @return
     * @throws InterruptedException
     */
    boolean tryLock(long time, TimeUnit unit) throws InterruptedException;

    /**
     * 释放锁
     * @throws Exception
     */
    void unlock() throws Exception;

}

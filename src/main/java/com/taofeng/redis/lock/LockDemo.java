package com.taofeng.redis.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>描述</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2020/2/28 8:10 PM
 * @since V1.0
 */
public class LockDemo {

    /**
     * 进行售票 5个线程同时售卖10张票，不能出现重复售卖的情况
     */

    private static Integer count = 10;

    private static Lock lock = new ReentrantLock();

    private static RedisLock redisLock = new RedisLock();

    public static void main(String[] args) {

        TicketsRunBle ticketsRunBle = new TicketsRunBle();

        Thread thread1 = new Thread(ticketsRunBle,"售票员01");
        Thread thread2 = new Thread(ticketsRunBle,"售票员02");
        Thread thread3 = new Thread(ticketsRunBle,"售票员03");
        Thread thread4 = new Thread(ticketsRunBle,"售票员04");
        Thread thread5 = new Thread(ticketsRunBle,"售票员05");

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
    }

    static class TicketsRunBle implements Runnable{

        @Override
        public void run() {

            while(count > 0){
                redisLock.lock();
                try {
                    if(count>0){
                        System.out.println(Thread.currentThread().getName()+"售出第"+count--+"张票");
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    try {
                        redisLock.unlock();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

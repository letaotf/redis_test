package com.taofeng.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.params.SetParams;

import java.util.UUID;

/**
 * <p>描述</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2020/2/28 9:05 PM
 * @since V1.0
 */

public class RedisLockTest extends BaseTest{

    @Autowired
    private JedisPool jedisPool;

//    @Autowired
//    private RedisLock redisLock;

    private static Integer count = 10;

    @Test
    public void setNx(){
        SetParams setParams=new SetParams();
        setParams.ex(2);  //2s
        setParams.nx();
        String s = UUID.randomUUID().toString();
        Jedis resource = jedisPool.getResource();
        String lock = resource.set("lock", s,setParams);

        System.out.println(lock);
    }

    @Test
    public void test(){

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

        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    class TicketsRunBle implements Runnable{

        @Override
        public void run() {

            while(count > 0){
                //redisLock.lock();
                try {
                    //if(count>0){
                    System.out.println(Thread.currentThread().getName()+"售出第"+count--+"张票");
                    //}
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    try {
                       // redisLock.unlock();
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

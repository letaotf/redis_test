package com.taofeng.redis.lock;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.params.SetParams;

import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * <p>描述</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2020/2/28 8:58 PM
 * @since V1.0
 */
public class RedisLock implements Lock {

    private JedisPool jedisPool = JedisPoolUtil.jedisPool();

    private ThreadLocal<String> threadLocal = new ThreadLocal<>();

    private final String key="lock";

    @Override
    public void lock() {
        boolean b = tryLock();
        if(b){
            // 获取到了锁
            return;
        }
        lock();
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        //设置参数
        SetParams setParams=new SetParams();
        // 过期时间 2s
        setParams.ex(2);
        // 设置锁
        setParams.nx();
        // value 值
        String value = UUID.randomUUID().toString();
        Jedis resource = jedisPool.getResource();
        String lock = resource.set(key, value,setParams);
        if("OK".equals(lock)){
            threadLocal.set(value);
            return true;
        }
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() throws Exception {
        String script="if redis.call(\"get\",KEYS[1])==ARGV[1] then\n" +
                "    return redis.call(\"del\",KEYS[1])\n" +
                "else\n" +
                "    return 0\n" +
                "end";

        Jedis resource = jedisPool.getResource();
        Object eval = resource.eval(script, Arrays.asList(key), Arrays.asList(threadLocal.get()));
        if(Integer.valueOf(eval.toString())==0){
            resource.close();
            throw new Exception("解锁失败");
        }
        resource.close();
    }
}

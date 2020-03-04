package com.taofeng.redis;

import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * <p>描述</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2020/2/28 7:15 PM
 * @since V1.0
 */
public class JedisTest {

    public static void main(String[] args) {

        Jedis jedis = new Jedis("94.191.120.27",6379);
        //查看服务是否运行
        System.out.println("服务正在运行: "+jedis.ping());

        String set = jedis.set("k1", "tao");

        System.out.println("插入缓存的数据:"+ set);

        Set<String> keys = jedis.keys("*");

        for (String key : keys) {
            System.out.println("keys 命令获取的结果:"+ key);
        }
    }
}

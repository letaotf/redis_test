package com.taofeng.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@SpringBootTest
class RedisTestApplicationTests {

	@Autowired
	private JedisPool jedisPool;

	@Test
	void contextLoads() {
		Jedis resource = jedisPool.getResource();
		System.out.println("服务正在运行: "+resource.ping());
	}

}

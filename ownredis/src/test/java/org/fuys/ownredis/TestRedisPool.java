package org.fuys.ownredis;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class TestRedisPool {
	
	private Logger logger = LoggerFactory.getLogger(TestRedisPool.class);

	@Test
	public void test() {
		logger.info("Configure final properties -->");
		final String HOST = "192.168.6.128";
		final int PORT = 6379;
		final String PASSWORD = "fuys0822";
		final int TIMEOUT = 1000;
		final int MAX_TOTAL = 1000;
		final int MAX_IDLE = 300;
		final long MAX_WAIT_MILLIS = 1000;
		final boolean TEST_ON_BORROW = true;
		logger.info("Configure Redis Pool -->");
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(MAX_TOTAL);
		config.setMaxIdle(MAX_IDLE);
		config.setMaxWaitMillis(MAX_WAIT_MILLIS);
		config.setTestOnBorrow(TEST_ON_BORROW);
		JedisPool pool = new JedisPool(config, HOST, PORT, TIMEOUT, PASSWORD, false);
		logger.info("Get jedis -->");
		Jedis jedis = pool.getResource();
		logger.info("Redis set key -->" + jedis.set("RedisPool", "useful"));
		logger.info("Redis get key -->" + jedis.get("RedisPool"));
		logger.info("close Redis Pool -->");
		pool.close();
		
	}

}
package org.fuys.ownredis;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;

public class TestRedisConnection {
	
	private Logger logger = LoggerFactory.getLogger(TestRedisConnection.class);

	@Test
	public void test() {
		String HOST = "192.168.6.128";
		final int PORT = 6379;
		final String PASSWORD = "fuys0822";
		final int TIMEOUT = 1000;
		Jedis jedis = new Jedis(HOST,PORT,TIMEOUT);
		jedis.auth(PASSWORD);
		logger.info("Ping Redis --> " + jedis.ping());
		logger.info("Set key --> " + jedis.set("org-ownredis", "jedis"));
		logger.info("Get key --> " + jedis.get("org-ownredis"));
		jedis.close();
	}

}

package org.fuys.ownredis;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

public class TestRedisData {
	
	private Logger logger = LoggerFactory.getLogger(TestRedisData.class);

	@Test
	public void test() {
		final String HOST = "192.168.6.128";
		final int PORT = 6379;
		final String PASSWORD = "fuys0822";
		final int TIMEOUT = 1000;
		Jedis jedis = new Jedis(HOST,PORT,TIMEOUT);
		jedis.auth(PASSWORD);
//		jedis.flushDB();
		
		// test sortedSet
		Map<String, Double> map = new HashMap<String, Double>();
		map.put("string", 4.0);
		map.put("math", 5.0);
		map.put("date", 6.0);
		map.put("file", 4.0);
		map.put("excel", 6.0);
		logger.info("SortedSet keys --> " + jedis.zadd("org-ownutil", map));
		Set<String> zrange = jedis.zrangeByScore("org-ownutil", 3.0, 6.0);
		logger.info("SortedSet get --> " + zrange);
		
		Set<Tuple> zrangeByScoreWithScores = jedis.zrangeByScoreWithScores("org-ownutil", 3.0, 6.0);
		Iterator<Tuple> tuples = zrangeByScoreWithScores.iterator();
		while(tuples.hasNext()){
			Tuple tuple = tuples.next();
			logger.info("SortedSet element --> " + tuple.getElement() + ", score --> " +tuple.getScore());
		}
		// test keys
//		logger.info("keys --> " + jedis.keys("org-*"));
		// test set data
//		logger.info("Set date key --> " + jedis.sadd("org-own1", "a","b","c","d","e"));
//		logger.info("Set date key --> " + jedis.sadd("org-own2", "b","d","x","y","z"));
//		logger.info("Set data get --> " + jedis.sinter("org-own1","org-own2"));
//		logger.info("Set data get --> " + jedis.sunion("org-own1","org-own2"));
//		logger.info("Set data get --> " + jedis.sinterstore("org-own3","org-own1","org-own2"));
//		logger.info("Set data get --> " + jedis.sunionstore("org-own4","org-own1","org-own2"));
		// test list data
//		logger.info("Lish set key -->" + jedis.lpush("org-ownnosql", "redis", "memcached"));
//		logger.info("Lish set key -->" + jedis.rpush("org-ownnosql", "mongoDB"));
//		logger.info("List get --> " + jedis.lrange("org-ownnosql", 0, -1));
//		logger.info("List lpop --> " + jedis.lpop("org-ownnosql"));
		// test hash data
//		logger.info("Hash set key --> " + jedis.hset("org-owndb", "name", "mybatis"));
//		logger.info("Hash set key --> " + jedis.hset("org-owndb", "version", "4.0"));
//		logger.info("Hash get --> " + jedis.hget("org-owndb", "name"));
//		logger.info("Hash get --> " + jedis.hget("org-owndb", "version"));
		// test set method
//		logger.info("Set key --> " + jedis.set("org-ownredis", "jedis"));
//		logger.info("Get key --> " + jedis.get("org-ownredis"));
		// test setex method
//		logger.info("Set extend time key --> " + jedis.setex("org-own", 3, "fuys"));
//		logger.info("Get value --> " + jedis.get("org-own"));
//		try {
//			Thread.sleep(3500);
//		} catch (InterruptedException e) {
//			logger.error("Time spleep problem --> ", e);
//		}
//		logger.info("Get value after 3.5 seconds --> " + jedis.get("org-own"));
		
		jedis.close();
		
	}

}
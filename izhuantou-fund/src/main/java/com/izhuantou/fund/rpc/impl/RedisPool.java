package com.izhuantou.fund.rpc.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izhuantou.common.utils.RedisUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * RedisUtils 实现类
 *
 * @author fucheng
 * @date 2018-01-30
 */

@Service("redisUtils")
public class RedisPool implements RedisUtils {

	@Autowired
	private JedisPool jedisPool;

	@Override
	public void set(String key, String value) {
		Jedis jedis = this.jedisPool.getResource();

		jedis.set(key, value);
		jedis.close();
	}

	@Override
	public String get(String key) {
		Jedis jedis = this.jedisPool.getResource();

		String result = jedis.get(key);
		jedis.close();

		return result;
	}

	@Override
	public void del(String key) {
		Jedis jedis = this.jedisPool.getResource();

		jedis.del(key);
		jedis.close();

	}

	@Override
	public void expire(String key, Integer seconds) {
		Jedis jedis = this.jedisPool.getResource();

		jedis.expire(key, seconds);
		jedis.close();

	}

	@Override
	public void set(String key, String value, Integer seconds) {
		Jedis jedis = this.jedisPool.getResource();

		jedis.set(key, value);
		jedis.expire(key, seconds);
		jedis.close();

	}

	@Override
	public Long incr(String key) {
		Jedis jedis = this.jedisPool.getResource();

		Long count = jedis.incr(key);
		jedis.close();

		return count;
	}

	@Override
	public String hget(String key, String field) {
		Jedis jedis = this.jedisPool.getResource();

		String result = jedis.hget(key, field);
		jedis.close();

		return result;
	}

	@Override
	public void hmset(String key, Map<String, String> hash) {
		Jedis jedis = this.jedisPool.getResource();

		jedis.hmset(key, hash);
		jedis.close();
	}

	@Override
	public Map<String, String> hgetAll(String key) {
		Jedis jedis = this.jedisPool.getResource();
		Map<String,String> result=jedis.hgetAll(key);
		jedis.close();
		return result;
	}

}

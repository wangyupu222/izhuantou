package com.koomii.redismq;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.util.Pool;

public class RedisUtil
{
  private static final Log logger = LogFactory.getLog(RedisUtil.class);
  private volatile Pool<Jedis> pool;
  
  public RedisUtil(Pool<Jedis> pool)
  {
    this.pool = pool;
  }
  
  public Jedis getConnent()
  {
    Jedis jedis = (Jedis)this.pool.getResource();
    return jedis;
  }
  
  public void close(Jedis jedis)
  {
    try
    {
      if (jedis != null) {
        jedis.close();
      }
    }
    catch (Exception e)
    {
      logger.error("close jedis failed!", e);
    }
  }
  
  public Long del(String key)
  {
    Jedis jedis = null;
    try
    {
      jedis = getConnent();
      return jedis.del(key);
    }
    catch (Exception e)
    {
      logger.error("redis get data failed!", e);
    }
    finally
    {
      close(jedis);
    }
    return null;
  }
  
  public <T> T blpop(String key, int waitSeconds, Class<T> clazz)
  {
    Jedis jedis = null;
    try
    {
      jedis = getConnent();
      List<byte[]> values = jedis.blpop(waitSeconds, new byte[][] { key.getBytes() });
      if ((values != null) && (values.size() > 0))
      {
    	  byte[] value = (byte[])values.get(1);
        return ConvertUtil.unserialize(value, clazz);
      }
      return null;
    }
    catch (Exception e)
    {
      byte[] value;
      logger.error("redis get data failed!", e);
      return null;
    }
    finally
    {
      close(jedis);
    }
  }
  
  public <T> Long rpush(String key, T value, int second)
  {
    Jedis jedis = null;
    Long ret = null;
    try
    {
      jedis = getConnent();
      byte[] bytes = ConvertUtil.serialize(value);
      ret = jedis.rpush(key.getBytes(), new byte[][] { bytes });
      if (second > 0) {
        jedis.expire(key, second);
      }
    }
    catch (Exception e)
    {
      logger.error("redis lpush data failed , key = " + key, e);
    }
    finally
    {
      close(jedis);
    }
    return ret;
  }
  
  public <T> void rpushGroup(Map<String, T> map, int second)
  {
    Jedis jedis = null;
    try
    {
      jedis = getConnent();
      
      Pipeline pipeline = jedis.pipelined();
      for (String key : map.keySet())
      {
        byte[] bytes = ConvertUtil.serialize(map.get(key));
        pipeline.rpush(key.getBytes(), new byte[][] { bytes });
        if (second > 0) {
          pipeline.expire(key, second);
        }
      }
      pipeline.sync();
    }
    catch (Exception e)
    {
      logger.error("redis lpush data failed , keys = " + map.keySet(), e);
    }
    finally
    {
      close(jedis);
    }
  }
  
  public Long llen(String key)
  {
    Jedis jedis = null;
    Long ret = null;
    try
    {
      jedis = getConnent();
      return jedis.llen(key);
    }
    catch (Exception e)
    {
      logger.error("redis llen data failed , key = " + key, e);
    }
    finally
    {
      close(jedis);
    }
    return ret;
  }
  
  public Long sadd(String key, String value)
  {
    Jedis jedis = null;
    Long ret = null;
    try
    {
      jedis = getConnent();
      ret = jedis.sadd(key, new String[] { value });
    }
    catch (Exception e)
    {
      logger.error("redis sadd data failed , key = " + key, e);
    }
    finally
    {
      close(jedis);
    }
    return ret;
  }
  
  public Set<String> smembers(String key)
  {
    Jedis jedis = null;
    Set<String> ret = null;
    try
    {
      jedis = getConnent();
      ret = jedis.smembers(key);
    }
    catch (Exception e)
    {
      logger.error("redis smembers failed , key = " + key, e);
    }
    finally
    {
      close(jedis);
    }
    return ret;
  }
  
  public Long srem(String key, String memeber)
  {
    Jedis jedis = null;
    Long ret = null;
    try
    {
      jedis = getConnent();
      ret = jedis.srem(key, new String[] { memeber });
    }
    catch (Exception e)
    {
      logger.error("redis srem failed , key = " + key, e);
    }
    finally
    {
      close(jedis);
    }
    return ret;
  }
  
  public Long hincrby(String key, String field, Long value)
  {
    Jedis jedis = null;
    Long ret = null;
    try
    {
      jedis = getConnent();
      ret = jedis.hincrBy(key, field, value.longValue());
    }
    catch (Exception e)
    {
      logger.error("redis hincrBy failed , key = " + key + ",field=" + field, e);
    }
    finally
    {
      close(jedis);
    }
    return ret;
  }
  
  public Map<String, String> hgetAll(String key)
  {
    Jedis jedis = null;
    Map<String, String> ret = null;
    try
    {
      jedis = getConnent();
      ret = jedis.hgetAll(key);
    }
    catch (Exception e)
    {
      logger.error("redis hincrBy failed , key = " + key, e);
    }
    finally
    {
      close(jedis);
    }
    return ret;
  }
  
  public Long hset(String key, String field, String value)
  {
    Jedis jedis = null;
    Long ret = null;
    try
    {
      jedis = getConnent();
      ret = jedis.hset(key, field, value);
    }
    catch (Exception e)
    {
      logger.error("redis hset failed , key = " + key + ",field=" + field, e);
    }
    finally
    {
      close(jedis);
    }
    return ret;
  }
}

package com.koomii.redismq;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

@Service
public class RedisMQMessageSender
{
  Logger logger = LoggerFactory.getLogger(RedisMQMessageSender.class);
  @Autowired
  RedisUtil redisUtil;
  @Autowired
  @Qualifier("messageTrunktaskExecutor")
  private ThreadPoolTaskExecutor threadPool;
  private int failRetryTimes = 3;
  private int threadPoolFullSleepSeconds = 1;
  
  public ThreadPoolTaskExecutor getThreadPool()
  {
    return this.threadPool;
  }
  
  public void setThreadPool(ThreadPoolTaskExecutor threadPool)
  {
    this.threadPool = threadPool;
  }
  
  public int getFailRetryTimes()
  {
    return this.failRetryTimes;
  }
  
  public void setFailRetryTimes(int failRetryTimes)
  {
    this.failRetryTimes = failRetryTimes;
  }
  
  public int getThreadPoolFullSleepSeconds()
  {
    return this.threadPoolFullSleepSeconds;
  }
  
  public void setThreadPoolFullSleepSeconds(int threadPoolFullSleepSeconds)
  {
    this.threadPoolFullSleepSeconds = threadPoolFullSleepSeconds;
  }
  
  public Long put(Message message)
  {
    String queue = "QUEUE." + message.getDestination();
    
    this.redisUtil.sadd("QUEUE_NAMES", queue);
    
    this.redisUtil.hincrby("QUEUE_IN_NUMBERS", queue, Long.valueOf(1L));
    
    return this.redisUtil.rpush(queue, message, 0);
  }
  
  public boolean groupput(List<Message> messageList)
  {
    List<String> keys = new ArrayList();
    
    Jedis jedis = null;
    try
    {
      jedis = this.redisUtil.getConnent();
      
      Pipeline pipeline = jedis.pipelined();
      for (Message message : messageList)
      {
        String queue = "QUEUE." + message.getDestination();
        keys.add(queue);
        
        pipeline.sadd("QUEUE_NAMES", new String[] { queue });
        
        this.redisUtil.hincrby("QUEUE_IN_NUMBERS", queue, Long.valueOf(1L));
        
        byte[] bytes = ConvertUtil.serialize(message);
        pipeline.rpush(queue.getBytes(), new byte[][] { bytes });
      }
      pipeline.sync();
    }
    catch (Exception e)
    {
      this.logger.error("redis lpush data failed , keys = " + keys.toString(), e);
      return false;
    }
    finally
    {
      this.redisUtil.close(jedis);
    }
    return true;
  }
}

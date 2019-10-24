package com.koomii.redismq;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageMonitor
{
  @Autowired
  RedisUtil redisUtil;
  
  public List<RedisQueue> getAllQueues()
  {
    List<RedisQueue> queueList = new ArrayList();
    





    Set<String> queueSet = this.redisUtil.smembers("QUEUE_NAMES");
    
    Map<String, String> inCountMap = this.redisUtil.hgetAll("QUEUE_IN_NUMBERS");
    
    Map<String, String> outCountMap = this.redisUtil.hgetAll("QUEUE_OUT_NUMBERS");
    for (String queueName : queueSet)
    {
      int count = getQueueMessageCount(queueName);
      
      Integer inNum = Integer.valueOf(0);Integer outNum = Integer.valueOf(0);
      try
      {
        inNum = Integer.valueOf(Integer.parseInt((String)inCountMap.get(queueName)));
      }
      catch (Exception localException) {}
      try
      {
        outNum = Integer.valueOf(Integer.parseInt((String)outCountMap.get(queueName)));
      }
      catch (Exception localException1) {}
      RedisQueue rq = new RedisQueue(queueName, Integer.valueOf(count), inNum, outNum);
      queueList.add(rq);
    }
    return queueList;
  }
  
  public Long purgeAll(String queueName)
  {
    Long ret = this.redisUtil.del(queueName);
    return ret;
  }
  
  public Long deleteQueue(String queueName)
  {
    this.redisUtil.srem("QUEUE_NAMES", queueName);
    this.redisUtil.hset("QUEUE_IN_NUMBERS", queueName, "0");
    this.redisUtil.hset("QUEUE_OUT_NUMBERS", queueName, "0");
    Long ret = this.redisUtil.del(queueName);
    return ret;
  }
  
  public int getQueueMessageCount(String queueName)
  {
    Integer length = Integer.valueOf(this.redisUtil.llen(queueName).intValue());
    return length.intValue();
  }
}

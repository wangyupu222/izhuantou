package com.koomii.redismq;

public class RedisQueue
{
  String queueName;
  Integer length;
  Integer inNum;
  Integer outNum;
  
  public RedisQueue() {}
  
  public RedisQueue(String queueName, Integer length, Integer inNum, Integer outNum)
  {
    this.queueName = queueName;
    this.length = length;
    this.inNum = inNum;
    this.outNum = outNum;
  }
  
  public String getQueueName()
  {
    return this.queueName;
  }
  
  public void setQueueName(String queueName)
  {
    this.queueName = queueName;
  }
  
  public Integer getLength()
  {
    return this.length;
  }
  
  public void setLength(Integer length)
  {
    this.length = length;
  }
  
  public Integer getInNum()
  {
    return this.inNum;
  }
  
  public void setInNum(Integer inNum)
  {
    this.inNum = inNum;
  }
  
  public Integer getOutNum()
  {
    return this.outNum;
  }
  
  public void setOutNum(Integer outNum)
  {
    this.outNum = outNum;
  }
}

package com.koomii.redismq;

import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskRejectedException;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

public abstract class AbstarctMessageHandler
  implements Runnable
{
  private static Logger logger = LoggerFactory.getLogger(AbstarctMessageHandler.class);
  @Autowired
  RedisUtil redisUtil;
  @Autowired
  RedisMQMessageSender messageSender;
  private String queueName;
  private boolean monitor;
  
  @PostConstruct
  public void startListen()
  {
    new Thread(this).start();
  }
  
  private boolean single = false;
  private int retryTimes = 3;
  
  public AbstarctMessageHandler(String queueName, int retryTimes)
  {

    this.queueName = ("QUEUE." + queueName);
    this.retryTimes = retryTimes;
  }
  
  public AbstarctMessageHandler(String queueName, int retryTimes, boolean single)
  {
    this.queueName = ("QUEUE." + queueName);
    this.retryTimes = retryTimes;
    this.single = single;
  }
  
  public AbstarctMessageHandler(String queueName)
  {
    this.queueName = ("QUEUE." + queueName);
  }
  
  public void run()
  {
    logger.info("----启动redis消息监听：-------------queue：" + this.queueName);
    
    this.redisUtil.sadd("QUEUE_NAMES", this.queueName);
    try
    {
      for (;;)
      {
        listen();
      }
    }
    catch (Exception e)
    {
      logger.error(e.getMessage(), e);
    }
  }
  
  public void listen()
  {
    String queue = this.queueName;
    logger.debug("=========blpop读取队列前：" + this.queueName);
    
    final Message message = (Message)this.redisUtil.blpop(queue, 2147483647, Message.class);
    
    logger.debug("=========blpop读取到消息：" + message);
    if (message == null)
    {
      this.monitor = false;
      logger.warn("消息分发器获取redis连接失败或消息格式非法");
      try
      {
        Thread.sleep(5000L);
      }
      catch (InterruptedException e)
      {
        logger.warn("消息分发器线程暂停失败");
      }
      return;
    }
    this.redisUtil.hincrby("QUEUE_OUT_NUMBERS", queue, Long.valueOf(1L));
    if (!this.monitor)
    {
      logger.warn("消息分发开始");
      this.monitor = true;
    }
    if (this.single) {
      handlerMessage(message);
    } else {
      try
      {
        this.messageSender.getThreadPool().submit(new Runnable()
        {
          public void run()
          {
            AbstarctMessageHandler.this.handlerMessage(message);
          }
        });
      }
      catch (TaskRejectedException ex)
      {
        logger.warn("线程池已满，准备回写任务，暂停本线程");
        
        this.messageSender.put(message);
        try
        {
          Thread.sleep(this.messageSender.getThreadPoolFullSleepSeconds() * 1000);
        }
        catch (InterruptedException e)
        {
          logger.warn("生产者暂停异常", ex);
        }
      }
      catch (Exception ex)
      {
        logger.error("消息总线发生异常", ex);
      }
    }
  }
  
  void handlerMessage(Message message)
  {
    try
    {
      handle(message.getContent());
    }
    catch (Exception ex)
    {
      logger.error(ex.getMessage(), ex);
      if (message.getFailTimes().intValue() >= this.retryTimes)
      {
        handleFailed(message.getContent());
      }
      else
      {
        message.setFailTimes(Integer.valueOf(message.getFailTimes().intValue() + 1));
        
        this.messageSender.put(message);
        if (logger.isDebugEnabled())
        {
          StringBuilder sb = new StringBuilder();
          sb.append("msg:[").append(message).append("], 执行失败，准备重试。");
          logger.debug(sb.toString());
        }
      }
    }
  }
  
  public abstract void handle(String paramString);
  
  public abstract void handleFailed(String paramString);
  
  public String getMessageType()
  {
    return this.queueName;
  }
}

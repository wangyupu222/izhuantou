package com.koomii.redismq;

import java.io.Serializable;

public class Message
  implements Serializable
{
  private static final long serialVersionUID = -8657613687306891080L;
  private String destination;
  private String content;
  private Integer failTimes;
  
  public Message(String destination, String content)
  {
    this.destination = destination;
    this.content = content;
    this.failTimes = new Integer(0);
  }
  
  public Message() {}
  
  public String getContent()
  {
    return this.content;
  }
  
  public void setContent(String content)
  {
    this.content = content;
  }
  
  public Integer getFailTimes()
  {
    return this.failTimes;
  }
  
  public void setFailTimes(Integer failTimes)
  {
    this.failTimes = failTimes;
  }
  
  public String toString()
  {
    StringBuilder builder = new StringBuilder();
    builder.append("Message [destination=");
    builder.append(this.destination);
    builder.append(", content=");
    builder.append(this.content);
    builder.append(", failTimes=");
    builder.append(this.failTimes);
    builder.append("]");
    return builder.toString();
  }
  
  public String getDestination()
  {
    return this.destination;
  }
  
  public void setDestination(String destination)
  {
    this.destination = destination;
  }
}

package com.izhuantou.common.utils;

/**
 * RedisUtils 接口
 *
 * @author fucheng
 * @date 2018-01-30
 */
public interface RedisUtils {

    /**
     * 保存
     * 
     * @param key
     * @param value
     */
    public void set(String key, String value);

    /**
     * 根据key查询
     * 
     * @param key
     * @return
     */
    public String get(String key);

    /**
     * 删除
     * 
     * @param key
     */
    public void del(String key);

    /**
     * 根据key设置生存时间
     * 
     * @param key
     * @param seconds
     */
    public void expire(String key, Integer seconds);

    /**
     * 保存并设置生存时间
     * 
     * @param key
     * @param value
     * @param seconds
     */
    public void set(String key, String value, Integer seconds);

    /**
     * value 加一
     * 
     * @param key
     * @return
     */
    public Long incr(String key);
}

package com.ssmshiro.common.utils;

import com.ssmshiro.common.thirdutils.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 缓冲池操作类
 */
public class RedisPools {
    private static final JedisPool jedisPool;

    private RedisPools(){

    }

    static {
        JedisPoolConfig config = new JedisPoolConfig();

        config.setMaxTotal(Integer.valueOf(PropertiesLoader.getProperty("jedis.pool.maxActive")));
        config.setMaxIdle(Integer.valueOf(PropertiesLoader.getProperty("jedis.pool.maxIdle")));
        config.setMinIdle(Integer.valueOf(PropertiesLoader.getProperty("jedis.pool.minIdle")));
        config.setMaxWaitMillis(Long.valueOf(PropertiesLoader.getProperty("jedis.pool.maxWait")));
        config.setTestOnBorrow(Boolean.valueOf(PropertiesLoader.getProperty("jedis.pool.testOnBorrow")));
        config.setTestOnReturn(Boolean.valueOf(PropertiesLoader.getProperty("jedis.pool.testOnReturn")));

        if(StringUtils.isNotEmpty(PropertiesLoader.getProperty("redis.password"))){
            jedisPool = new JedisPool(config, PropertiesLoader.getProperty("redis.host"),Integer.valueOf(PropertiesLoader.getProperty("redis.port"))
                    ,Integer.valueOf(PropertiesLoader.getProperty("redis.timeout")),PropertiesLoader.getProperty("redis.password"));
        }else{
            jedisPool = new JedisPool(config, PropertiesLoader.getProperty("redis.host"),Integer.valueOf(PropertiesLoader.getProperty("redis.port")));
        }
    }
    /**
     * 释放连接
     * @param jedis
     */
    public static void release(Jedis jedis){
        jedis.close();
    }

    /**
     * 获取连接
     * @return redis连接
     */
    public static Jedis getJedis(){
        return jedisPool.getResource();
    }

}

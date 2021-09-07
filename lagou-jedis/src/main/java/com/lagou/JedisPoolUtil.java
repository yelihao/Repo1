package com.lagou;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 单例模式优化jedis连接池
 */
public class JedisPoolUtil {

    //声明连接池
    private volatile static JedisPool jedisPool = null;
    private volatile static Jedis jedis = null;


    private JedisPoolUtil(){
    }

    //返回连接池
    private static JedisPool getInstance(){
        //双层检测锁（企业中用的非常频繁）
        if (jedisPool == null){ //检测体温
            synchronized (JedisPoolUtil.class){ //排队进站
                if (jedisPool == null){     //查看健康码
                    JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
                    jedisPoolConfig.setMaxTotal(1000);  //最大连接数量1000
                    jedisPoolConfig.setMaxIdle(30);     //设置最大等待数量30
                    jedisPoolConfig.setMaxWaitMillis(60*1000);  //设置最大等待时间
                    jedisPoolConfig.setTestOnBorrow(true);      //设置为后台运行
                    jedisPool = new JedisPool(jedisPoolConfig,"127.0.0.1",6379);
                }
            }
        }


        return jedisPool;
    }

    //返回redis对象；提供所有人调用 所以public
    public static Jedis getJedis(){
        if (jedis==null){
            jedis = getInstance().getResource();
        }

        return jedis;
    }



}

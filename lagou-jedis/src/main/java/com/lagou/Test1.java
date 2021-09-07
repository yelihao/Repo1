package com.lagou;

import redis.clients.jedis.Jedis;

/**
 * 测试连接redis
 */
public class Test1 {
    public static void main(String[] args) {
        //我们使用这个构造方法:    public Jedis(String host, int port) {super(host, port);}
        Jedis jedis = new Jedis("127.0.0.1",6379);
        String pong = jedis.ping();
        System.out.println(pong);


    }


}

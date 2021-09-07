package com.lagou;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * 测试事务transaction
 *
 */
public class Test3 {
    public static void main(String[] args) throws Exception{
        Jedis jedis = new Jedis("127.0.0.1", 6379);

        int yue =Integer.parseInt(jedis.get("yue"));
        int zhichu = 10;

        jedis.watch("yue");     //监控余额
        Thread.sleep(5000);     //模拟网络延迟

        if (yue < zhichu){
            jedis.unwatch();        //接触监控
            System.out.println("余额不足！");
        }else {
            Transaction transaction = jedis.multi();
            transaction.decrBy("yue",zhichu);   //余额减少
            transaction.incrBy("zhichu",zhichu);    //累计消费增加
            transaction.exec();
            System.out.println("余额生育"+jedis.get("yue"));
            System.out.println("支出总过"+jedis.get("zhichu"));

        }

    }







}

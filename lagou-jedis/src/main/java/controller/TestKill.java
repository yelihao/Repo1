package controller;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.TimeUnit;

/**
 * 测试秒杀
 */

@Controller
public class TestKill {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private Redisson redisson;


    @RequestMapping("/kill")
    //这个锁只能解决一个服务器一个进程下的线程并发
    //如果分布式环境，多个进程并发，此方法失效
    //使用redis的分布式锁来解决问题
    public synchronized String kill(){

        //定义商品id
        String productKey = "huaweiP40";
        //通过redisson获取锁
        RLock lock = redisson.getLock(productKey);  //底层源码就是继承了setnx，过期时间等操作

        //上锁(过期时间为30秒)
        lock.lock(30, TimeUnit.SECONDS);

        try {
            //1.从redis中获取手机的库存数量
            int phonecount = Integer.parseInt(stringRedisTemplate.opsForValue().get("phone"));
            //2.判断手机的数量是否够秒杀
            if (phonecount > 0) {
                phonecount--;
                //库存减少后，再将库存的值保存回redis
                stringRedisTemplate.opsForValue().set("phone", phonecount + "");
                System.out.println("库存-1，剩余:" + phonecount);
            } else {
                System.out.println("库存不足");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return "over!";
    }


    @Bean
    public Redisson redisson(){
        Config config = new Config();
        //使用单个redis服务器
        config.useSingleServer().setAddress("redis://127.0.0.1:6379").setDatabase(0);
        //使用集群redis
        /*
        config.useClusterServers().setScanInterval(2000).addNodeAddress("redis://127.0.0.1:6379",
                "redis://127.0.0.1:6380","redis://127.0.0.1:6381");
        */
        return (Redisson) Redisson.create(config);

    }





}

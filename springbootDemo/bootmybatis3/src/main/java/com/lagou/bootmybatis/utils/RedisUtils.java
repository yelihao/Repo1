package com.lagou.bootmybatis.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisUtils {

    @Autowired
    private RedisTemplate redisTemplate;

    public Object get(final String key){
        return redisTemplate.opsForValue().get(key);
    }

    public boolean set(String key,Object value){
        boolean resule = false;
        try{
            redisTemplate.opsForValue().set(key,value,1, TimeUnit.DAYS);
            resule = true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return resule;
    }

    public boolean getAndSet(final String key,String value){
        boolean resule = false;
        try{
            redisTemplate.opsForValue().getAndSet(key,value);
            resule = true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return resule;
    }

    public boolean delete(final String key){
        boolean result = false;
        try{
            redisTemplate.delete(key);
            result = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }


}

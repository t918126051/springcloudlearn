package com.example.demo.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;


import javax.annotation.Nullable;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     *
     * 指定缓存失效时间
     * @param key
     * @param time
     * @param timeUnit
     * @return
     */
    public boolean expires(String key, long time, TimeUnit timeUnit){
        try {
            if (time > 0) {
                redisTemplate.expire(key,time,timeUnit);
            }
            return  true;

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 根据key获取当前时间
     * @param key
     * @param timeUnit
     * @return
     */
    public long getExpire(String key,TimeUnit timeUnit){
       return redisTemplate.getExpire(key,timeUnit);
    }


    /**
     * 判断key是否存在
     * @param key
     * @return
     */
    public boolean hasKey(String key){
        try{
            return  redisTemplate.hasKey(key);
        }catch(Exception e){
            e.printStackTrace();
            return  false;
        }
    }

    /**
     * 删除缓存
     *
     * @param key 可以传一个值 或多个
     */
    public void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    /**
     * 获取普通缓存
     * @param key
     * @return
     */
    public Object get(String key){
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 普通缓存放入
     * @param key
     * @param value
     * @return
     */

//    public boolean set (String key ,Object value){
//        try {
//            redisTemplate.opsForValue().set(key,value);
//            return true;
//        }
//        catch (Exception e){
//            e.printStackTrace();
//            return  false;
//        }
//    }

    /**
     * 普通缓存放入并设置时间
     * @param key
     * @param value
     * @param time
     * @return
     */
    public boolean set(String key, Object value, @Nullable long time){
        try {
            if (time > 0){
                redisTemplate.opsForValue().set(key,value,time,TimeUnit.SECONDS);
            }
            else{
                redisTemplate.opsForValue().set(key,value);
            }
            return  true;
        }
        catch (Exception e){
            e.printStackTrace();
            return  false;
        }
    }
}

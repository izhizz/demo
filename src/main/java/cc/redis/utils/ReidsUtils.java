package cc.redis.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class ReidsUtils  {

    @Autowired
    protected static RedisTemplate< Serializable ,Object> redisTemplate ;

    public static void setKey(){

        Collection collection = new ArrayList<>();

        redisTemplate.opsForValue().multiGet(collection);
//        redisTemplate.opsForValue().
    }
//    protected RedisTemplate<le ,Object> redisTemplate ;

}

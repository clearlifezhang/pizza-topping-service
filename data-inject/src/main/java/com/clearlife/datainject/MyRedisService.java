package com.clearlife.datainject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class MyRedisService {
    @Autowired
    private @Qualifier("myTemplate") RedisTemplate<String, Object> redisTemplate;
    public void storeUserFavoriteCount(String user, String feature, int count) {
        String key = user + "_" + feature;
        redisTemplate.opsForValue().set(key, count);
    }
    public int getUserFavoriteCount(String user, String feature) {
        String key = user + "_" + feature;
        Integer count = (Integer) redisTemplate.opsForValue().get(key);
        return count != null ? count.intValue() : 0;
    }
}
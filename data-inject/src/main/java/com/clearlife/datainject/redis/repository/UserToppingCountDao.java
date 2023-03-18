package com.clearlife.datainject.redis.repository;

import com.clearlife.datainject.redis.config.RedisConfig;
import com.clearlife.datainject.redis.entity.UserToppingCount;
import com.fasterxml.jackson.databind.deser.std.ObjectArrayDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository

public class UserToppingCountDao {
    public static final String HASH_KEY = "Topping";
    @Autowired
    private @Qualifier("myTemplate") RedisTemplate<String, Object> template;
    public void save(UserToppingCount utc){
        template.opsForHash().put(HASH_KEY, utc.getUserFavorite(), utc.getUserFavorite());
    }
    public void increase(UserToppingCount utc, long delta){
        template.opsForHash().increment(HASH_KEY, utc.getUserFavorite(), delta);
    }
    public long getCountByUserFavorite(String userfavorite){
        UserToppingCount uc = (UserToppingCount) template.opsForHash().get(HASH_KEY, userfavorite);
        return uc.getCount();
    }
}

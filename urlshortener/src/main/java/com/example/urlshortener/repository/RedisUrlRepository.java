package com.example.urlshortener.repository;

import com.example.urlshortener.model.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RedisUrlRepository {

    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public RedisUrlRepository(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void save(Url url) {
        redisTemplate.opsForHash().put("URL", url.getShortUrl(), url);
    }

    public Url findByShortUrl(String shortUrl) {
        return (Url) redisTemplate.opsForHash().get("URL", shortUrl);
    }
}

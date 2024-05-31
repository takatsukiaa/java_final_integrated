package com.example.urlshortener.service;

import com.example.urlshortener.model.Url;
import com.example.urlshortener.repository.DatabaseUrlRepository;
import com.example.urlshortener.repository.RedisUrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UrlService {

    private final RedisUrlRepository redisUrlRepository;
    private final DatabaseUrlRepository databaseUrlRepository;
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int SHORT_URL_LENGTH = 8;
    private static final int MAX_ATTEMPTS = 5;  // Maximum attempts to resolve a collision

    @Autowired
    public UrlService(RedisUrlRepository redisUrlRepository, DatabaseUrlRepository databaseUrlRepository) {
        this.redisUrlRepository = redisUrlRepository;
        this.databaseUrlRepository = databaseUrlRepository;
    }

    public String shortenUrl(String originalUrl) {
        Url existingUrl = databaseUrlRepository.findByOriginalUrl(originalUrl);
        if (existingUrl != null) {
            redisUrlRepository.save(existingUrl);
            return existingUrl.getShortUrl();
        }

        String shortUrl;
        int attempts = 0;
        do {
            shortUrl = generateShortUrl();
            attempts++;
        } while (databaseUrlRepository.findByShortUrl(shortUrl) != null && attempts < MAX_ATTEMPTS);

        if (attempts == MAX_ATTEMPTS) {
            throw new RuntimeException("Unable to generate a unique short URL after " + MAX_ATTEMPTS + " attempts.");
        }

        Url newUrl = new Url();
        newUrl.setOriginalUrl(originalUrl);
        newUrl.setShortUrl(shortUrl);
        databaseUrlRepository.save(newUrl);
        redisUrlRepository.save(newUrl);
        return shortUrl;
    }

    public String getOriginalUrl(String shortUrl) {
        Url url = redisUrlRepository.findByShortUrl(shortUrl);
        if (url != null) {
            return url.getOriginalUrl();
        }
        url = databaseUrlRepository.findByShortUrl(shortUrl);
        if (url != null) {
            redisUrlRepository.save(url);
            return url.getOriginalUrl();
        }
        return null;
    }

    private String generateShortUrl() {
        Random random = new Random();
        StringBuilder shortUrl = new StringBuilder(SHORT_URL_LENGTH);
        for (int i = 0; i < SHORT_URL_LENGTH; i++) {
            shortUrl.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return shortUrl.toString();
    }
}

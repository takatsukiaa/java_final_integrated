package com.example.urlshortener.repository;

import com.example.urlshortener.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatabaseUrlRepository extends JpaRepository<Url, Long> {
    Url findByShortUrl(String shortUrl);
    Url findByOriginalUrl(String originalUrl);
}
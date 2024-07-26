package com.example.boilerplate.services;

import com.example.boilerplate.models.entities.LongShortUrlMapping;
import com.example.boilerplate.repositories.LongShortUrlMappingRepository;
import jakarta.transaction.Transactional;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShortUrlService {

    private final LongShortUrlMappingRepository longShortUrlMappingRepository;

    @Autowired
    public ShortUrlService(LongShortUrlMappingRepository sampleRepository) {
        this.longShortUrlMappingRepository = sampleRepository;
    }


    @Transactional(rollbackOn = Exception.class)
    public String createShortUrl(String longUrl) {
        LongShortUrlMapping mapping = LongShortUrlMapping.builder()
                                          .longUrl(longUrl)
                                          .shortUrl(DigestUtils.sha256Hex(longUrl))
                                          .build();

        return longShortUrlMappingRepository.save(mapping).getShortUrl();
    }

    public Optional<String> retrieveLongUrlFor(String shortUrl) {
        Optional<LongShortUrlMapping> mapping
            = longShortUrlMappingRepository.findByShortUrl(shortUrl);

        return mapping.map(LongShortUrlMapping::getLongUrl);
    }

}

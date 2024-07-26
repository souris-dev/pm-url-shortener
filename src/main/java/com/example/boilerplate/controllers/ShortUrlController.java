package com.example.boilerplate.controllers;

import com.example.boilerplate.config.URLConstantsConfiguration;
import com.example.boilerplate.models.dto.ShortUrlCreationRequest;
import com.example.boilerplate.models.dto.ShortUrlCreationResponse;
import com.example.boilerplate.services.ShortUrlService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@RestController
@Slf4j
public class ShortUrlController {

    private final ShortUrlService shortUrlService;
    private final URLConstantsConfiguration constantsConfiguration;

    @Autowired
    public ShortUrlController(ShortUrlService shortUrlService,
                              URLConstantsConfiguration constantsConfiguration) {
        this.shortUrlService = shortUrlService;
        this.constantsConfiguration = constantsConfiguration;
    }

    @PostMapping("/shortUrl")
    public ShortUrlCreationResponse createShortUrl(
        @RequestBody ShortUrlCreationRequest creationRequest
        ) {
        String shortUrl = shortUrlService.createShortUrl(creationRequest.getLongUrl());

        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append("http://").append(constantsConfiguration.DOMAIN_NAME)
            .append("/r/").append(shortUrl);

        return ShortUrlCreationResponse.builder().url(urlBuilder.toString()).build();
    }

    @RequestMapping("/r/{shortUrl}")
    public ResponseEntity<?> redirectToShortUrl(
        @PathVariable("shortUrl") String shortUrl,
        HttpServletResponse response
    ) throws IOException {
        Optional<String> longUrl = shortUrlService.retrieveLongUrlFor(shortUrl);

        if (longUrl.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        response.sendRedirect(longUrl.get());
        return null;
    }

}

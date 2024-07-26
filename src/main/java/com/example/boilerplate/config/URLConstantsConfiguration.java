package com.example.boilerplate.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class URLConstantsConfiguration {

    /**
     * The domain name of the service. Do not include slashes in the end.
     */
    @Value("${url-config.domain-name}")
    public String DOMAIN_NAME = "s.ly";

}

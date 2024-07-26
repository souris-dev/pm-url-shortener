package com.example.boilerplate.models.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShortUrlCreationResponse {

    private String url;

}

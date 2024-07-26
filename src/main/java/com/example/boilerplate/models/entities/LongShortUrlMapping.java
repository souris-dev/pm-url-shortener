package com.example.boilerplate.models.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sample_entity")
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LongShortUrlMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "short_url")
    private String shortUrl;

    @Column(name = "long_url")
    private String longUrl;

}

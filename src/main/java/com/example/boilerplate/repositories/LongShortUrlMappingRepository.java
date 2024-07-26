package com.example.boilerplate.repositories;

import com.example.boilerplate.models.entities.LongShortUrlMapping;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LongShortUrlMappingRepository extends CrudRepository<LongShortUrlMapping, Long> {

    @Query("select m from LongShortUrlMapping m where m.shortUrl = :shortUrl")
    Optional<LongShortUrlMapping> findByShortUrl(String shortUrl);

}

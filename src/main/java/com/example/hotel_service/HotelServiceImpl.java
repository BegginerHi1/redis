package com.example.hotel_service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {
    private static final Logger log = LoggerFactory.getLogger(HotelServiceImpl.class);
    private final HotelRepository repository;

    public HotelServiceImpl(HotelRepository repository) {
        this.repository = repository;
    }

    @Override
    @Cacheable(value = "hotels")
    @CircuitBreaker(name = "redisCache", fallbackMethod = "fallbackGetAll")
    public List<Hotel> getAll() {
        return repository.findAll();
    }

    public List<Hotel> fallbackGetAll(Throwable t) {
        log.warn("Redis unavailable, falling back to DB: {}", t.getMessage());
        return repository.findAll();
    }
}

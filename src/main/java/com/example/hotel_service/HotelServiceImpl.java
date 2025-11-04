package com.example.hotel_service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class HotelServiceImpl implements HotelService {
    private final HotelRepository repository;

    public HotelServiceImpl(HotelRepository repository) {
        this.repository = repository;
    }

    @Override
    @Cacheable(value = "hotels", key = "#page + '-' + #size")
    public HotelPageDTO getHotels(int page, int size) {
        Page<Hotel> hotels = repository.findAll(PageRequest.of(page, size));
        return new HotelPageDTO(
                hotels.getContent(),
                hotels.getNumber(),
                hotels.getSize(),
                hotels.getTotalElements(),
                hotels.getTotalPages()
        );
    }
}

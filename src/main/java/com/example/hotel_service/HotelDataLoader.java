package com.example.hotel_service;

import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
public class HotelDataLoader implements CommandLineRunner {

    private final HotelRepository hotelRepository;

    public HotelDataLoader(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        for (int i = 1; i <= 25_000; i++) {
            Hotel hotel = new Hotel(
                    "Hotel " + i,
                    "Address " + i,
                    "+1-555-" + i,
                    "www.hotel" + i + ".com"
            );
            hotelRepository.save(hotel);
        }
    }
}

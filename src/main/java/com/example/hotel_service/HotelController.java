package com.example.hotel_service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class HotelController {
    private final HotelService service;

    public HotelController(HotelService service) {
        this.service = service;
    }

    @GetMapping
    ResponseEntity<List<Hotel>> getHotels() {
        return ResponseEntity.ok().body(service.getAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<Hotel> getHotel(@PathVariable int id) {
        return ResponseEntity.ok().body(service.getHotel(id));
    }
}

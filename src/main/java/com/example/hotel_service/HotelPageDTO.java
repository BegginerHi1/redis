package com.example.hotel_service;

import java.util.List;

public record HotelPageDTO(List<Hotel> content, int page, int size, long totalElements, int totalPages) {}


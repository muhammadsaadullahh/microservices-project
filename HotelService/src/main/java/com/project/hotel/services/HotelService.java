package com.project.hotel.services;

import com.project.hotel.entities.HotelEntity;

import java.util.List;

public interface HotelService {

    HotelEntity create(HotelEntity hotel);
    HotelEntity update(HotelEntity hotel);
    HotelEntity delete(String id);
    HotelEntity getHotelById(String id);
    List<HotelEntity> getAll();
    boolean existsByHotelName(String hotelName);
    boolean existsByHotelId(String hotelId);
}

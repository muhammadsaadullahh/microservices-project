package com.project.hotel.services.impl;

import com.project.hotel.entities.HotelEntity;
import com.project.hotel.exceptions.ResourceNotFoundException;
import com.project.hotel.repositories.HotelRepository;
import com.project.hotel.services.HotelService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public HotelEntity create(HotelEntity hotel) {
        hotel.setHotelId(UUID.randomUUID().toString());
        return hotelRepository.save(hotel);
    }

    @Override
    public HotelEntity update(HotelEntity hotel) {
        return hotelRepository.save(hotel);
    }

    @Override
    public HotelEntity delete(String id) {
        HotelEntity hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with id: " + id));
        hotelRepository.delete(hotel);
        return hotel;
    }

    @Override
    public HotelEntity getHotelById(String id) {
        return hotelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with id: " + id));
    }

    @Override
    public List<HotelEntity> getAll() {
        return hotelRepository.findAll();
    }

    @Override
    public boolean existsByHotelName(String hotelName) {
        if (StringUtils.isBlank(hotelName)) {
            return false;
        }
        return hotelRepository.existsByNameIgnoreCase(hotelName);
    }

    @Override
    public boolean existsByHotelId(String hotelId) {
        if (StringUtils.isBlank(hotelId)) {
            return false;
        }
        return hotelRepository.existsByHotelIdIgnoreCase(hotelId);
    }
}

package com.project.hotel.repositories;

import com.project.hotel.entities.HotelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<HotelEntity, String> {

    boolean existsByNameIgnoreCase(String hotelName);

    boolean existsByHotelIdIgnoreCase(String hotelId);
}

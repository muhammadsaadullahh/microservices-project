package com.project.hotel.controller;

import com.project.hotel.entities.HotelEntity;
import com.project.hotel.exceptions.ResourceNotFoundException;
import com.project.hotel.payload.ApiResponse;
import com.project.hotel.services.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hotels")
public class HotelController {
    
    private final HotelService hotelService;

    @GetMapping("/{hotelId}")
    public ResponseEntity<ApiResponse<HotelEntity>> getHotelById(@PathVariable String hotelId) throws ResourceNotFoundException {
        if (hotelId == null || hotelId.isBlank()) {
            throw new ResourceNotFoundException("Hotel ID must not be blank");
        }
        HotelEntity hotel = hotelService.getHotelById(hotelId);
        return ResponseEntity.ok(
                ApiResponse
                        .<HotelEntity>builder()
                        .success(true)
                        .message("Hotel fetched successfully")
                        .entity(hotel)
                        .build()
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<HotelEntity>>> getAllHotels() throws ResourceNotFoundException {
        List<HotelEntity> allHotels = hotelService.getAll();
        return ResponseEntity.ok(
                ApiResponse
                        .<List<HotelEntity>>builder()
                        .success(true)
                        .message("All Hotels fetched successfully")
                        .entity(allHotels)
                        .build()
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<HotelEntity>> createHotel(@RequestBody HotelEntity hotel) {
        validateHotel(hotel);
        final boolean hotelNameExists = hotelService.existsByHotelName(hotel.getName());
        if (hotelNameExists) {
            throw new ResourceNotFoundException("Hotel already exists with hotel Name: " + hotel.getName());
        }
        HotelEntity createHotel = hotelService.create(hotel);
        return ResponseEntity.ok(
                ApiResponse
                        .<HotelEntity>builder()
                        .success(true)
                        .message("Hotel created successfully")
                        .entity(createHotel)
                        .build()
        );
    }

    @DeleteMapping("/{hotelId}")
    public ResponseEntity<ApiResponse<HotelEntity>> deleteHotel(@PathVariable String hotelId) throws  ResourceNotFoundException {
        if(hotelId == null || hotelId.isBlank()) {
            throw new ResourceNotFoundException("Hotel ID must not be blank");
        }
        final boolean hotelExists = hotelService.existsByHotelId(hotelId);        if(!hotelExists) {
            throw new ResourceNotFoundException("Hotel not found with ID: " + hotelId);
        }
        HotelEntity deleteHotel = hotelService.delete(hotelId);
        return ResponseEntity.ok(
                ApiResponse
                        .<HotelEntity>builder()
                        .success(true)
                        .message("Hotel with ID " + hotelId + " deleted successfully")
                        .entity(deleteHotel)
                        .build()
        );
    }

    private void validateHotel(HotelEntity hotel) {
        if (hotel == null) {
            throw new ResourceNotFoundException("Hotel payload must not be null");
        }
        if (hotel.getName() == null || hotel.getName().isBlank()) {
            throw new ResourceNotFoundException("Hotel name must not be blank");
        }
        if (hotel.getLocation() == null || hotel.getLocation().isBlank()) {
            throw new ResourceNotFoundException("Hotel location must not be blank");
        }
    }
}

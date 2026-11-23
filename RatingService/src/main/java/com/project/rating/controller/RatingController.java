package com.project.rating.controller;

import com.project.rating.entities.RatingEntity;
import com.project.rating.exceptions.ResourceNotFoundException;
import com.project.rating.payload.ApiResponse;
import com.project.rating.services.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ratings")
public class RatingController {
    
    private final RatingService ratingService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<RatingEntity>>> getAllRatings() throws ResourceNotFoundException {
        List<RatingEntity> allRatings = ratingService.getAllRatings();
        return ResponseEntity.ok(
                ApiResponse
                        .<List<RatingEntity>>builder()
                        .success(true)
                        .message("All Ratings fetched successfully")
                        .entity(allRatings)
                        .build()
        );
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<ApiResponse<List<RatingEntity>>> getRatingByUserId(@PathVariable String userId) throws ResourceNotFoundException {
        if (userId == null || userId.isBlank()) {
            throw new ResourceNotFoundException("User ID must not be blank");
        }
        List<RatingEntity> userRatings = ratingService.getRatingsByUserId(userId);
        return ResponseEntity.ok(
                ApiResponse
                        .<List<RatingEntity>>builder()
                        .success(true)
                        .message("User Ratings fetched successfully")
                        .entity(userRatings)
                        .build()
        );
    }

    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<ApiResponse<List<RatingEntity>>> getRatingByHotelId(@PathVariable String hotelId) throws ResourceNotFoundException {
        if (hotelId == null || hotelId.isBlank()) {
            throw new ResourceNotFoundException("Hotel ID must not be blank");
        }
        List<RatingEntity> hotelRatings = ratingService.getRatingsByHotelId(hotelId);
        return ResponseEntity.ok(
                ApiResponse
                        .<List<RatingEntity>>builder()
                        .success(true)
                        .message("Hotel Ratings fetched successfully")
                        .entity(hotelRatings)
                        .build()
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<RatingEntity>> createRatings(@RequestBody RatingEntity ratingEntity) throws ResourceNotFoundException {
        validateRating(ratingEntity);
        RatingEntity rating = ratingService.create(ratingEntity);
        return ResponseEntity.ok(
                ApiResponse
                        .<RatingEntity>builder()
                        .success(true)
                        .message("Rating created successfully")
                        .entity(rating)
                        .build()
        );
    }

    private void validateRating(RatingEntity ratingEntity) {
        if (ratingEntity == null) {
            throw new ResourceNotFoundException("Rating payload must not be null");
        }
        if (ratingEntity.getUserId() == null || ratingEntity.getUserId().isBlank()) {
            throw new ResourceNotFoundException("User ID must not be blank");
        }
        if (ratingEntity.getHotelId() == null || ratingEntity.getHotelId().isBlank()) {
            throw new ResourceNotFoundException("Hotel ID must not be blank");
        }
    }
}

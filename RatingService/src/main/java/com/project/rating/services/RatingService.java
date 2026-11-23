package com.project.rating.services;

import com.project.rating.entities.RatingEntity;

import java.util.List;

public interface RatingService {

    RatingEntity create(RatingEntity ratingEntity);

    RatingEntity update(RatingEntity ratingEntity);

    List<RatingEntity> getAllRatings();

    List<RatingEntity> getRatingsByHotelId(String hotelId);

    List<RatingEntity> getRatingsByUserId(String userId);
}

package com.project.rating.repositories;

import com.project.rating.entities.RatingEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RatingRepository extends MongoRepository<RatingEntity, String> {

    List<RatingEntity> findByHotelId(String hotelId);

    List<RatingEntity> findByUserId(String userId);

}

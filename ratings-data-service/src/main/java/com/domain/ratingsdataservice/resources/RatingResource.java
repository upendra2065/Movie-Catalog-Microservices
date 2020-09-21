package com.domain.ratingsdataservice.resources;

import com.domain.ratingsdataservice.models.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/ratingsdata")
public class RatingResource {

    @RequestMapping("/{movieId}")
    public Rating getRating(@PathVariable String movieId){
        return new Rating(movieId, 4);
    }

    @RequestMapping("/users/{userId}")
    public UserRating getUserRating(@PathVariable String userId){
        UserRating userRating=new UserRating();
        userRating.setUserRating(Arrays.asList(
                new Rating("1234", 4),
                new Rating("5678", 3)
        ));
        return userRating;
    }
}

package com.domain.moviecatalogservice.resources;

import com.domain.moviecatalogservice.models.CatalogItem;
import com.domain.moviecatalogservice.models.Movie;
import com.domain.moviecatalogservice.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class CatalogResource {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable String userId){

        UserRating ratings=restTemplate.getForObject("http://ratings-data-service/ratingsdata/users/" + userId, UserRating.class);
        // for each movie ID, call movie info service and get details
        return ratings.getUserRating().stream().map(rating -> {
            Movie movie=restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
            // Put them all together
            return new CatalogItem(movie.getName(), "Desc", rating.getRating());
        }).collect(Collectors.toList());
    }
}

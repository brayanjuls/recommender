package com.brayanjules.recommender.repo;

import com.brayanjules.recommender.PlacesPost;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by brayan on 09-09-18
 */
public interface PostRepository extends CrudRepository<PlacesPost,String> {

    Iterable<PlacesPost> findAllByPriceLessThanEqual(double price);
}

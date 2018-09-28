package com.brayanjules.recommender.repo;

import com.brayanjules.recommender.PostRequest;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by brayan on 08-09-18
 */
public interface PostRequestRepository
        extends CrudRepository<PostRequest,Long> {

    Iterable<PostRequest> findAllByUserId(long userId);
}

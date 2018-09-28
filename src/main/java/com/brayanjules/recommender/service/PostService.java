package com.brayanjules.recommender.service;

import com.brayanjules.recommender.DTO.DTOPost;
import com.brayanjules.recommender.PlacesPost;
import com.brayanjules.recommender.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by brayan on 09-09-18
 */
@Service
public class PostService implements IPostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<DTOPost> findPostByPrice(double price) {
        List<DTOPost> posts=new ArrayList<>();
        Iterable<PlacesPost> foundPosts = postRepository.findAllByPriceLessThanEqual(price);
        foundPosts.forEach(p->posts.add(new DTOPost(p.getDescription(),p.getTitle(),p.getUserId(),p.getStart(),p.getEnd(),p.getPrice(),
                p.getCountryId(),p.getImagePath())));
        return posts;
    }
}

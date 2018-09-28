package com.brayanjules.recommender.service;

import com.brayanjules.recommender.DTO.DTOPost;

import java.util.List;

/**
 * Created by brayan on 09-09-18
 */
public interface IPostService {

    List<DTOPost> findPostByPrice(double price);
}

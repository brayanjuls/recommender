package com.brayanjules.recommender.controller;

import com.brayanjules.recommender.DTO.DTOPost;
import com.brayanjules.recommender.DTO.DTOResponse;
import com.brayanjules.recommender.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by brayan on 09-09-18
 */
@RestController
@RequestMapping("/post")
public class PostController {
    private final IPostService service;

    @Autowired
    public PostController(IPostService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity getByPrice(@RequestParam double price){
        List<DTOPost> result = service.findPostByPrice(price);
        return new ResponseEntity<>(new DTOResponse<>("OK",result), HttpStatus.OK);
    }
}

package com.brayanjules.recommender.controller;

import com.brayanjules.recommender.DTO.DTOPost;
import com.brayanjules.recommender.DTO.DTOPostRequest;
import com.brayanjules.recommender.DTO.DTOPostRequestUpdate;
import com.brayanjules.recommender.DTO.DTORequestIdentity;
import com.brayanjules.recommender.DTO.DTOResponse;
import com.brayanjules.recommender.service.IRequestPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by brayan on 08-09-18
 */
@RestController
@RequestMapping("/postrequest")
public class PostRequestController {

    private IRequestPostService postService;

    @Autowired
    public PostRequestController(IRequestPostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody DTOPost post){
        postService.create(post);
        return new ResponseEntity<>(new DTOResponse<>("OK",null), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity update(@RequestBody DTOPostRequestUpdate post){
        postService.update(post);
        return new ResponseEntity<>(new DTOResponse<>("OK",null), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestBody List<DTORequestIdentity> requestIds){
        postService.delete(requestIds);
        return new ResponseEntity<>(new DTOResponse<>("OK",null), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getAll(){
        List<DTOPostRequest> response = postService.findAll();
        return new ResponseEntity<>(new DTOResponse<>("OK",response),HttpStatus.OK);
    }

    @GetMapping(path = "/{userId}")
    public ResponseEntity getByUserId(@PathVariable long userId){
        List<DTOPostRequest> response=postService.findByUserId(userId);
        return new ResponseEntity<>(new DTOResponse<>("OK",response),HttpStatus.OK);
    }

    @PostMapping(path = "/accept")
    public ResponseEntity acceptPost(@RequestBody List<DTORequestIdentity> ids){
        postService.acceptPostRequests(ids);
        return new ResponseEntity<>(new DTOResponse<>("OK",null), HttpStatus.OK);
    }

    @PostMapping(path = "/reject")
    public ResponseEntity rejectPost(@RequestBody List<DTORequestIdentity> ids){
        postService.rejectPostRequests(ids);
        return new ResponseEntity<>(new DTOResponse<>("OK",null), HttpStatus.OK);
    }
}

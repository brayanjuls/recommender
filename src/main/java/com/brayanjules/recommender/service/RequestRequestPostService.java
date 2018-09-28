package com.brayanjules.recommender.service;

import com.brayanjules.recommender.DTO.DTOPost;
import com.brayanjules.recommender.DTO.DTOPostRequest;
import com.brayanjules.recommender.DTO.DTOPostRequestUpdate;
import com.brayanjules.recommender.DTO.DTORequestIdentity;
import com.brayanjules.recommender.PlacesPost;
import com.brayanjules.recommender.Post;
import com.brayanjules.recommender.PostRequest;
import com.brayanjules.recommender.PostRequestManager;
import com.brayanjules.recommender.PostRequestStatusEnum;
import com.brayanjules.recommender.repo.PostRepository;
import com.brayanjules.recommender.repo.PostRequestRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by brayan on 08-09-18
 */
@Service
public class RequestRequestPostService
        implements IRequestPostService {

    private PostRequestRepository postRequestRepository;
    private PostRepository postRepository;
    private PostRequestManager manager;
    private ObjectMapper objectMapper;
    @Autowired
    public void setPostRequestRepository(PostRequestRepository postRequestRepository,PostRepository postRepository) {
        this.postRequestRepository = postRequestRepository;
        this.postRepository=postRepository;
    }

    @Override
    public void create(DTOPost post){

        try {
            PostRequestManager manager = getPostRequestManager();
            Post requestPost= MapPostToDTO(post);
            Optional<PostRequest> postRequest=manager.createRequest(requestPost);
            postRequest.ifPresent(p-> postRequestRepository.save(p));

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(DTOPostRequestUpdate post) {
        getPostRequestManager();
        Post requestPost=new PlacesPost(post.getDescription(),post.getTitle(),post.getUserId(),post.getStart(),
                post.getEnd(),post.getPrice(),post.getCountryId(),post.isStatus(),post.getImagePath());
        Optional<PostRequest> postRequest=postRequestRepository.findById(post.getRequestId());
        if(postRequest.isPresent()){
            PostRequest existingPostRequest=postRequest.get();
            try {
                Optional<PostRequest> currentPost=manager.createRequest(requestPost);
                currentPost.ifPresent(postRequest1 -> existingPostRequest.setData(postRequest1.getData()));
                postRequestRepository.save(existingPostRequest);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(List<DTORequestIdentity> ids) {
        List<Long> requestIds = ids.stream().map(DTORequestIdentity::getId).collect(Collectors.toList());
        Iterable<PostRequest> requests = postRequestRepository.findAllById(requestIds);
        requests.forEach(p->p.setStatus(PostRequestStatusEnum.DELETED));
        requests.forEach(p->postRequestRepository.save(p));
    }

    @Override
    public List<DTOPostRequest> findAll() {
        List<DTOPostRequest> response=new ArrayList<>();
        Iterable<PostRequest> requests = postRequestRepository.findAll();
        MapPostRequestoDTO(response, requests);
        return response;
    }


    @Override
    public List<DTOPostRequest> findByUserId(long userId) {
        List<DTOPostRequest> response=new ArrayList<>();
        Iterable<PostRequest> requestsByUser = postRequestRepository.findAllByUserId(userId);
        MapPostRequestoDTO(response, requestsByUser);
        return response;
    }

    @Override
    public void acceptPostRequests(List<DTORequestIdentity> ids) {
        PostRequestManager manager = getPostRequestManager();
        List<Long> requestIds=ids.stream().map(DTORequestIdentity::getId).collect(Collectors.toList());
        Iterable<PostRequest> requestsToAccept = postRequestRepository.findAllById(requestIds);
        List<PostRequest> acceptedRequest=new ArrayList<>();
        List<PlacesPost> acceptedPost=new ArrayList<>();
        requestsToAccept.forEach(p->manager.accept(p,acceptedPost,acceptedRequest));
        postRequestRepository.saveAll(acceptedRequest);
        postRepository.saveAll(acceptedPost);

    }

    @Override
    public void rejectPostRequests(List<DTORequestIdentity> ids) {
        PostRequestManager manager = getPostRequestManager();
        List<Long> requestIds=ids.stream().map(DTORequestIdentity::getId).collect(Collectors.toList());
        Iterable<PostRequest> requestsToReject = postRequestRepository.findAllById(requestIds);
        requestsToReject.forEach(manager::reject);
        postRequestRepository.saveAll(requestsToReject);
    }

    private PostRequestManager getPostRequestManager() {
        if(manager==null){
            manager=new PostRequestManager();
        }
        return manager;
    }

    private void MapPostRequestoDTO(List<DTOPostRequest> response, Iterable<PostRequest> requests) {
        for (PostRequest request : requests) {
            try {
                Post currentPost = getObjectMapper().readValue(request.getData(), PlacesPost.class);
                response.add(new DTOPostRequest(currentPost.getDescription(), currentPost.getTitle(),
                        currentPost.getUserId(), currentPost.getStart(), currentPost.getEnd(), currentPost.getPrice(),
                        currentPost.getCountryId(), currentPost.isStatus(), currentPost.getImagePath(), request.getId(),
                        request.getStatus().name()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private ObjectMapper getObjectMapper(){
        if(objectMapper==null){
            objectMapper=new ObjectMapper();
        }
        return objectMapper;
    }

    private PlacesPost MapPostToDTO(DTOPost post) {
        return new PlacesPost(post.getDescription(),post.getTitle(),post.getUserId(),post.getStart(),
                post.getEnd(),post.getPrice(),post.getCountryId(),true,post.getImagePath());
    }
}

package com.brayanjules.recommender;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Created by brayan on 05-09-18
 */
public class PostRequestManager {

    private ObjectMapper objectMapper;
    public Optional<PostRequest> createRequest(Post post)
    throws JsonProcessingException {
        Optional<PostRequest> postRequest = Optional.empty();
        if(post!=null && post.getStart().getTime()<post.getEnd().getTime()){
            if(post.getEnd().getTime()>System.currentTimeMillis())
            postRequest= Optional.of(new PostRequest(getObjectMapper().writeValueAsString(post),post.getUserId()));
        }
        return postRequest;
    }

    public void accept(PostRequest request, List<PlacesPost> acceptedPosts, List<PostRequest> acceptedRequest) {
        if(request.getStatus().equals(PostRequestStatusEnum.WAITING)){
            try {
                request.setStatus(PostRequestStatusEnum.ACCEPTED);
                PlacesPost currentPost = getObjectMapper().readValue(request.getData(), PlacesPost.class);
                acceptedPosts.add(currentPost);
                acceptedRequest.add(request);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void reject(PostRequest request) {
        if(request.getStatus().equals(PostRequestStatusEnum.WAITING))
        request.setStatus(PostRequestStatusEnum.REJECTED); // i should persist the update status and send an email to the requester
    }

    public void delete(PostRequest request) {
        if(request.getStatus().equals(PostRequestStatusEnum.WAITING))
        request.setStatus(PostRequestStatusEnum.DELETED); // i should persist the update status and limit all the queries to avoid request with status deleted
    }

    private ObjectMapper getObjectMapper(){
        if(objectMapper==null){
            objectMapper=new ObjectMapper();
        }
        return objectMapper;
    }
}

package com.brayanjules.recommender.service;

import com.brayanjules.recommender.DTO.DTOPost;
import com.brayanjules.recommender.DTO.DTOPostRequest;
import com.brayanjules.recommender.DTO.DTOPostRequestUpdate;
import com.brayanjules.recommender.DTO.DTORequestIdentity;

import java.util.List;

/**
 * Created by brayan on 08-09-18
 */
public interface IRequestPostService {
    void create(DTOPost post);

    void update(DTOPostRequestUpdate post);

    void delete(List<DTORequestIdentity> requestIds);

    List<DTOPostRequest> findAll();

    List<DTOPostRequest> findByUserId(long userId);

    void acceptPostRequests(List<DTORequestIdentity> ids);

    void rejectPostRequests(List<DTORequestIdentity> ids);
}

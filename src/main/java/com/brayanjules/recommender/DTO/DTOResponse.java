package com.brayanjules.recommender.DTO;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by brayan on 08-09-18
 */
@Data
public class DTOResponse<T> implements Serializable {
    private String message;
    private Date timeStamp;
    private T response;

    public DTOResponse(String message, T response) {
        this.timeStamp=new Date();
        this.message = message;
        this.response = response;
    }
}

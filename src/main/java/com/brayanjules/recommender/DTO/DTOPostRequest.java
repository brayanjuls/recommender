package com.brayanjules.recommender.DTO;

import lombok.Data;

import java.util.Date;

/**
 * Created by brayan on 08-09-18
 */
@Data
public class DTOPostRequest {

    private String description;
    private String title;
    private long userId;
    private Date start;
    private Date end;
    private double price;
    private long countryId;
    private boolean status;
    private String imagePath;
    private long requestId;
    private String requestStatus;

    public DTOPostRequest() {
    }

    public DTOPostRequest(String description, String title, long userId, Date start, Date end, double price, long countryId, boolean status, String imagePath, long requestId,
                          String requestStatus) {
        this.description = description;
        this.title = title;
        this.userId = userId;
        this.start = start;
        this.end = end;
        this.price = price;
        this.countryId = countryId;
        this.status = status;
        this.imagePath = imagePath;
        this.requestId = requestId;
        this.requestStatus=requestStatus;
    }
}

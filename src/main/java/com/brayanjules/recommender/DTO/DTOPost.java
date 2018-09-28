package com.brayanjules.recommender.DTO;

import lombok.Data;

import java.util.Date;

/**
 * Created by brayan on 08-09-18
 */
@Data
public class DTOPost {

    private String description;
    private String title;
    private long userId;
    private Date start;
    private Date end;
    private double price;
    private long countryId;
    private String imagePath;

    public DTOPost() {
    }

    public DTOPost(String description, String title, long userId, Date start, Date end, double price, long countryId, String imagePath) {
        this.description = description;
        this.title = title;
        this.userId = userId;
        this.start = start;
        this.end = end;
        this.price = price;
        this.countryId = countryId;
        this.imagePath = imagePath;
    }
}

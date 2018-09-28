package com.brayanjules.recommender.DTO;

import lombok.Data;

import java.util.Date;

/**
 * Created by brayan on 08-09-18
 */
@Data
public class DTOPostRequestUpdate {

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

}

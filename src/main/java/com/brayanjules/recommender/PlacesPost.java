package com.brayanjules.recommender;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by brayan on 05-09-18
 */
@Entity
public class PlacesPost implements Post{

    @Getter
    @Setter
    @Column
    private String description;

    @Getter
    @Setter
    @Column
    private String title;

    @Getter
    @Setter
    @Column
    private long userId;

    @Getter
    @Setter
    @Column
    private Date start;

    @Getter
    @Setter
    @Column
    private Date end;

    @Getter
    @Setter
    @Column
    private double price;

    @Getter
    @Setter
    @Column
    private long countryId;

    @Getter
    @Setter
    @Column
    private boolean status;

    @Getter
    @Setter
    @Column
    private String imagePath;

    @Getter
    @Setter
    @Id
    private String id;

    private String generatePrimaryKey(){
        return start.getTime()+"|"+end.getTime()+"|"+userId+"|"+System.currentTimeMillis();
    }

    public PlacesPost() {
    }

    public PlacesPost(String description, String title, long userId, Date start, Date end, double price, long countryId, boolean status, String imagePath) {
        this.description = description;
        this.title = title;
        this.userId = userId;
        this.start = start;
        this.end = end;
        this.price = price;
        this.countryId = countryId;
        this.status = status;
        this.imagePath = imagePath;
        this.id=generatePrimaryKey();

    }

}

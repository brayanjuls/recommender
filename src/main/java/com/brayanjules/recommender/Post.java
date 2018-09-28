package com.brayanjules.recommender;

import java.util.Date;

/**
 * Created by brayan on 08-09-18
 */
public interface Post {
    String getDescription();
    void setDescription(String description);
    String getTitle();
    void setTitle(String title);
    long getUserId();
    void setUserId(long userId);
    Date getStart();
    void setStart(Date start);
    Date getEnd();
    void setEnd(Date end);
    String getId();
    void setId(String id);
    double getPrice();
    void setPrice(double price);
    long getCountryId();
    void setCountryId(long countryId);
    String getImagePath();
    void setImagePath(String path);
    boolean isStatus();
    void setStatus(boolean status);

}

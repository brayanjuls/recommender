package com.brayanjules.recommender;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

/**
 * Created by brayan on 06-09-18
 */
@Data
@Entity
public class PostRequest {
    @Column
    private PostRequestStatusEnum status=PostRequestStatusEnum.WAITING; // o- waiting 1- accepted 2- rejected 3- deleted

    @Lob
    @Column
    private String data;

    @Column
    private long userId;

    @Column
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    public PostRequest() {
    }

    public PostRequest(String data, long userId) {
        this.data=data;
        this.userId=userId;
    }
}

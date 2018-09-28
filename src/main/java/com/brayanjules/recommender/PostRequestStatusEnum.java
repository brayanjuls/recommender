package com.brayanjules.recommender;

/**
 * Created by brayan on 09-09-18
 */

public enum PostRequestStatusEnum {

    WAITING(0),
    ACCEPTED(1),
    REJECTED(2),
    DELETED(3);

    private int status;
    PostRequestStatusEnum(int status){
        this.status=status;
    }

}

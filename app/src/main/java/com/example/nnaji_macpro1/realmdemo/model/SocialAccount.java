package com.example.nnaji_macpro1.realmdemo.model;

import io.realm.RealmObject;

/**
 * Created by Nnaji-MacPro1 on 10/9/17.
 */

public class SocialAccount extends RealmObject{

    private String name;
    private String status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

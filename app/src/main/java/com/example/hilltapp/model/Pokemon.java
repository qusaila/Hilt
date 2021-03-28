package com.example.hilltapp.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;


public class Pokemon implements Serializable {
    String name, url;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

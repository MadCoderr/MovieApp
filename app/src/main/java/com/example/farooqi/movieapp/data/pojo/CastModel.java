package com.example.farooqi.movieapp.data.pojo;

/**
 * Created by SAMSUNG on 3/19/2018.
 */

public class CastModel {
    public int castId;
    public int id;
    public String character;
    public String name;
    public String creditId;
    public String profilePath;


    public CastModel(int castId, int id, String character, String name, String creditId, String profilePath) {
        this.castId = castId;
        this.id = id;
        this.character = character;
        this.name = name;
        this.creditId = creditId;
        this.profilePath = profilePath;
    }


    public String getProfilePath() {
        return "https://image.tmdb.org/t/p/w500" + profilePath;
    }
}

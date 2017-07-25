package com.example.amal.movieproject.model;

import java.io.Serializable;

/**
 * Created by emyahmed_96 on 7/19/2017.
 */

public class Movie implements Serializable
{
    private String title;
    private String posterPath;
    private String overview;


    public void setName(String name)
    {
        this.title=name;
    }

    public void setPosterPath(String rate)
    {
        this.posterPath=rate;
    }

    public void setOverView(String desc)
    {
        this.overview=desc;
    }

    public String getName()
    {
        return title;
    }

    public String getPosterPath()
    {
        return posterPath;
    }

    public String getOverView()
    {
        return  overview;
    }

}



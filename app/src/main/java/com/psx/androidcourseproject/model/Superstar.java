package com.psx.androidcourseproject.model;

/**
 * Created by Pranav on 06-03-2017.
 */

public class Superstar {
    // POJO class for a Superstar profile
    private String name;
    private int profile_pic_id;
    private boolean isMale;
    private String description;

    public Superstar (String name, int profile_pic_id, boolean isMale,String description){
        this.name = name;
        this.isMale = isMale;
        this.profile_pic_id = profile_pic_id;
        this.description = description;
    }


    public Superstar(String name, boolean isMale, String description){
        this.name = name;
        this.isMale = isMale;
        this.description = description;
    }

    public int getProfile_pic_id ()
    {
        return this.profile_pic_id;
    }
    public String getName ()
    {
        return this.name;
    }
    public String getDescription ()
    {
        return this.description;
    }
    public boolean isMale (){
        return this.isMale;
    }
}

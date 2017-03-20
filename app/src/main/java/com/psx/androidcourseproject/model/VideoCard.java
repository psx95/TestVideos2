package com.psx.androidcourseproject.model;

/**
 * Created by Pranav on 07-02-2017.
 */

public class VideoCard {
    // a simple java object class
    public String video_code="nRiOw3qGYq4";
    public String video_title = "";
    public VideoCard (){
        // empty Constructor
    }
    public VideoCard (String video_code){
        this.video_code = video_code;
    }
    public String getVideo_code (){
        return this.video_code;
    }
    public void setVideo_title (String title) {
        this.video_title = title;
    }

    public String getVideo_title (){
        return this.video_title;
    }
}

package com.psx.androidcourseproject.model;

/**
 * Created by Pranav on 06-03-2017.
 */

public class RawVideos {
    // POJO class for videos that appear in the RAW
    private String title;
    private String video_code="";

    public RawVideos (String title, String video_code)
    {
        this.title = title;
        this.video_code = video_code;
    }

    public String getVideo_code ()
    {
        return  this.video_code;
    }
    public String getTitle () {
        return this.title;
    }
}

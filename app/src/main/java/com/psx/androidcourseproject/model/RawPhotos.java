package com.psx.androidcourseproject.model;

/**
 * Created by Pranav on 07-03-2017.
 */

public class RawPhotos {
    // POJO class for Raw Photos

    int image_id;

    public RawPhotos (int image_id)
    {
        this.image_id = image_id;
    }

    public int getImage_id ()
    {
        return this.image_id;
    }
}

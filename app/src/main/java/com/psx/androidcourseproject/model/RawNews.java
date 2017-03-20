package com.psx.androidcourseproject.model;

/**
 * Created by Pranav on 06-03-2017.
 */

public class RawNews {
    // POJO class for RAW News
    String title;
    String date;
    String content;
    String description;

    public RawNews (String title,String date, String content){
        this.title = title;
        this.date = date;
        this.content = content;
        this.description = fetchDesc();
    }

    // getters

    public String getTitle (){
        return  this.title;
    }
    public String getDate (){
        return this.date;
    }
    public String getContent (){
        return this.content;
    }
    public String getDescription (){
        return this.description;
    }

    public String fetchDesc (){
        String s = "";
        int count = 0;
        char x;
        for (int i = 0;i<this.content.length();i++){
            x = this.content.charAt(i);
            s+=x;
            if (this.content.charAt(i) == '.'){
                count++;
            }
            if (count == 2){
                return s;
            }
        }
        return s;
    }
}

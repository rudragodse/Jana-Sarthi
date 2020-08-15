package com.example.janasarthi.AarogyaSarthi;

public class Model {

    private int image;
    private String title;
    private String desc;
    private String buttontext;


    public Model(int image, String tilte, String desc, String buttontext){
        this.image=image;
        this.title=title;
        this.desc=desc;
        this.buttontext=buttontext;

    }

    public int getImage(){
        return image;
    }
    public String getTitle(){
        return title;
    }
    public String getDesc(){
        return desc;
    }
    public String getButtontext(){
        return buttontext;
    }


}

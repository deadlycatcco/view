package com.example.myjavafxapp;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CookModel extends BaseModel{

    public int currentPoint = 0;
    public CookModel(){
        this.setGif_url("D:\\навчання\\3 курс\\view\\view2\\view\\src\\main\\resources\\Images\\Drawing 4 (4).png");
        this.setGifImage(new Image(this.getGif_url()));
        this.setGifImageView(new ImageView(this.getGifImage()));
    }
}

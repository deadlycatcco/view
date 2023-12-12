package com.example.myjavafxapp;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CookModel extends BaseModel{

    public int currentPoint = 0;
    public int id;
    public CookModel(){
        this.setGif_url("/Images/Drawing 4 (4).png");
        this.setGifImage(new Image(getClass().getResource(this.getGif_url()).toExternalForm()));
        this.setGifImageView(new ImageView(this.getGifImage()));
    }
}

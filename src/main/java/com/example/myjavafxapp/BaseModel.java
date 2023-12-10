package com.example.myjavafxapp;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BaseModel {
    //private String Gif_url = "https://img.itch.zone/aW1nLzMzMzY4OTguZ2lm/original/0Ut41Y.gif";

    private String Gif_url ="D:\\навчання\\3 курс\\view\\view2\\view\\src\\main\\resources\\Images\\customer1.png";
    private Image gifImage;
    private ImageView gifImageView;

    private int positionX;
    private int positionY;

    public BaseModel(){
        this.positionY = 0;
        this.positionX = 0;
        this.gifImage = new Image(Gif_url);
        this.gifImageView = new ImageView(gifImage);
    }
    public BaseModel(String gif_url){
        this.Gif_url = gif_url;
    }


    public String getGif_url() {
        return Gif_url;
    }
    public void setGif_url(String gif_url) {
        Gif_url = gif_url;
    }
    public Image getGifImage() {
        return gifImage;
    }
    public void setGifImage(Image gifImage) {
        this.gifImage = gifImage;
    }
    public ImageView getGifImageView() {
        return gifImageView;
    }
    public void setGifImageView(ImageView gifImageView) {
        this.gifImageView = gifImageView;
    }
    public int getPositionX() {
        return positionX;
    }
    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }
    public int getPositionY() {
        return positionY;
    }
    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

}

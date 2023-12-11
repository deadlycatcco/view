package com.example.myjavafxapp;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CookPoint {

    private String Gif_url ="/Images/cookingPoint.png";
    private Image gifImage;
    private ImageView gifImageView;

    private int positionX;
    private int positionY;

    public CookPoint(){
        this.positionY = 0;
        this.positionX = 0;
        this.gifImage = new Image(getClass().getResource(Gif_url).toExternalForm());
        this.gifImageView = new ImageView(gifImage);
    }
    public CookPoint(String gif_url){
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

package com.example.myjavafxapp;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
public class CheckoutModel {
    private String Gif_url = "D:\\MyJavaFXApp\\src\\main\\resources\\Images\\checkout.png";
    private Image gifImage;
    private ImageView gifImageView;

    private int positionX;
    private int positionY;

    public CheckoutModel(){
        this.gifImage = new Image(Gif_url);
        this.gifImageView = new ImageView(gifImage);
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
}

package com.example.myjavafxapp;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class CookPoint {
    private String Gif_url = "/Images/cookingPoint_1.png";
    private String gifUrlTemplate = "/Images/cookingPoint_%d.png"; // Assuming you have cookingPoint_1.png, cookingPoint_2.png, etc.
    private Image gifImage;
    private ImageView gifImageView;

    private int positionX;
    private int positionY;

    public CookPoint(int number) {
        this.positionY = 0;
        this.positionX = 0;
        String imageUrl = String.format(gifUrlTemplate, number);
        System.out.println("Image URL: " + imageUrl);
        this.gifImage = new Image(getClass().getResource(gifUrlTemplate).toExternalForm());
        this.gifImageView = new ImageView(gifImage);
    }

    public CookPoint() {
        this.positionY = 0;
        this.positionX = 0;
    }


    public CookPoint(String gif_url){
        this.gifUrlTemplate = gif_url;
    }


    public String getGif_url() {
        return gifUrlTemplate;
    }

    public void setGif_url(String gif_url, int number) {
        System.out.println(gif_url);
        if (gif_url == null) {
            throw new IllegalArgumentException("gif_url must not be null");
        }
        this.gifUrlTemplate = gif_url;
        String imageUrl = String.format(gifUrlTemplate, number);
        System.out.println(imageUrl);
        try {
            this.gifImage = new Image(Objects.requireNonNull(getClass().getResource(imageUrl)).toExternalForm());
        } catch (NullPointerException e) {
            System.err.println("Failed to load image: " + imageUrl);
            e.printStackTrace();
        }
        this.gifImageView = (new ImageView(gifImage));
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

package com.adgvit.appathon.model;

public class SponsorsModel {
    String name;
    String image;
    String website;

    public SponsorsModel(String name, String image, String website) {
        this.name = name;
        this.image = image;
        this.website = website;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public SponsorsModel(){

    }

}

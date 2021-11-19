package com.adgvit.appathon.model;

public class SpeakerModel {
    String picture;
    String name;
    String speakerDesignation;

    public SpeakerModel(String picture, String name, String speakerDesignation) {
        this.picture = picture;
        this.name = name;
        this.speakerDesignation = speakerDesignation;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpeakerDesignation() {
        return speakerDesignation;
    }

    public void setSpeakerDesignation(String speakerDesignation) {
        this.speakerDesignation = speakerDesignation;
    }

    public SpeakerModel(){

    }
}

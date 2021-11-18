package com.adgvit.appathon.model;

public class trackDomain {
    private String track_name;
    private String heading;
    private String content;

    public trackDomain(String track_name, String heading, String content) {
        this.track_name = track_name;
        this.heading = heading;
        this.content = content;
    }

    public String getTrack_name() {
        return track_name;
    }

    public void setTrack_name(String track_name) {
        this.track_name = track_name;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
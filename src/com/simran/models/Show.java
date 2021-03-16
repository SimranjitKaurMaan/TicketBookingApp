package com.simran.models;


import java.time.LocalDateTime;

public class Show
{
    private String id;
    private String movieName;
    private LocalDateTime startTime;
    private int duration;
    private Screen screen;

    public Show(String id, String movieName, LocalDateTime startTime, int duration, Screen screen) {
        this.id = id;
        this.movieName = movieName;
        this.startTime = startTime;
        this.duration = duration;
        this.screen = screen;
    }

    public Screen getScreen() {
        return screen;
    }

}

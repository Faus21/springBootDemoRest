package com.example.demo.templates;


import com.example.demo.entities.Session;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class MovieRequest {

    private Timestamp startTime;

    private Timestamp endTime;

    private String title;

    public static List<MovieRequest> createListOfMovieRequests(List<Session> sessions){
        List<MovieRequest> movieRequests = new ArrayList<>();

        for (Session s: sessions) {
            movieRequests.add(new MovieRequest(s.getStartTime(),
                                                s.getEndTime(),
                                                s.getFilm().getTitle()));
        }

        return movieRequests;
    }

    public MovieRequest(Timestamp startTime, Timestamp endTime, String title) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.title = title;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}

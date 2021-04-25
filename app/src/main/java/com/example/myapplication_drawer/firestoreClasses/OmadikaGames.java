package com.example.myapplication_drawer.firestoreClasses;

public class OmadikaGames {
    private String OmadaA;
    private String OmadaB;
    private int ScoreA;
    private int ScoreB;
    private String city;
    private String country;
    private String date;
    private String sport;

    public OmadikaGames(){}

    public String getOmadaA() {
        return OmadaA;
    }

    public void setOmadaA(String omadaA) {
        OmadaA = omadaA;
    }

    public String getOmadaB() {
        return OmadaB;
    }

    public void setOmadaB(String omadaB) {
        OmadaB = omadaB;
    }

    public int getScoreA() {
        return ScoreA;
    }

    public void setScoreA(int scoreA) {
        ScoreA = scoreA;
    }

    public int getScoreB() {
        return ScoreB;
    }

    public void setScoreB(int scoreB) {
        ScoreB = scoreB;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }
}

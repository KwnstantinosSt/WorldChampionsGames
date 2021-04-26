package com.example.myapplication_drawer.firestoreClasses;

import java.util.ArrayList;

public class AtomikaGames {
    private String city;
    private String country;
    private String date;
    private String sport;
    private String gameId;
    private ArrayList<String> athletes;
    private ArrayList<Double> epidoseis;

    public AtomikaGames(){}

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

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }


    public ArrayList<String> getAthletes() {
        return athletes;
    }

    public void setAthletes(ArrayList<String> athletes) {
        this.athletes = athletes;
    }

    public ArrayList<Double> getEpidoseis() {
        return epidoseis;
    }

    public void setEpidoseis(ArrayList<Double> epidoseis) {
        this.epidoseis = epidoseis;
    }
}

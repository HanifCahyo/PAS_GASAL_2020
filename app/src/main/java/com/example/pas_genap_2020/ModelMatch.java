package com.example.pas_genap_2020;

import java.util.Date;

public class ModelMatch {
    String strHomeTeam;
    String strAwayTeam;
    String strLeague;
    String strVenue;
    int intHomeScore;
    int intAwayScore;
    int id;

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHome_name() {
        return strHomeTeam;
    }

    public void setHome_name(String strHomeTeam) {
        this.strHomeTeam = strHomeTeam;
    }

    public String getAway_name() {
        return strAwayTeam;
    }

    public void setAway_name(String strAwayTeam) {
        this.strAwayTeam = strAwayTeam;
    }

    public String getLeague() {
        return strLeague;
    }

    public void setLeague(String strLeague) {
        this.strLeague = strLeague;
    }

    public String getVenue() {
        return strVenue;
    }

    public void setVenue(String strVenue) {
        this.strVenue = strVenue;
    }

    public Integer getHome_score() {
        return intHomeScore;
    }

    public void setHome_score(Integer intHomeScore) {
        this.intHomeScore = intHomeScore;
    }

    public Integer getAway_score() {
        return intAwayScore;
    }

    public void setAway_score(Integer intAwayScore) {
        this.intAwayScore = intAwayScore;
    }
}

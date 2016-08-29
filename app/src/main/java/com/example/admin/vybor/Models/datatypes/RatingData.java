package com.example.admin.vybor.Models.datatypes;


public class RatingData {
    private String faction;
    private Integer rating;

    public RatingData(String faction, Integer rating) {
        this.faction = faction;
        this.rating = rating;
    }

    public String getFaction() {
        return faction + '-' + getRating();
    }

    public Integer getRating() {
        return rating;
    }
}

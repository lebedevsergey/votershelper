package com.example.admin.vybor.Models.datatypes;


public class RatingData {
    private String faction;
    private Integer rating;

    public RatingData(String faction, Integer rating) {
        this.faction = faction;
        this.rating = rating;
    }

    public String getFactionWithRate() {
        return getRating() + " баллов: " + this.getFactionPrintableName();
    }

    public String getFactionPrintableName() {
        String FactionPrintName = "";
        if (faction.equals("ЕР")) {
            FactionPrintName = "Единая Россия";
        } else if (faction.equals("СР")) {
            FactionPrintName = "Справедливая Россия";
        } else {
            FactionPrintName = faction;
        }

        return FactionPrintName;
    }

    public Integer getRating() {
        return rating;
    }
}

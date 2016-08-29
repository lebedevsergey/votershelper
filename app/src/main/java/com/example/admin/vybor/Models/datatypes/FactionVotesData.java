package com.example.admin.vybor.Models.datatypes;

public class FactionVotesData {

    private String faction;
    private Integer voteValue;
    private Integer votePercent;
    private Integer id;

    public FactionVotesData(String faction, Integer voteValue, Integer votePercent, Integer id) {
        this.faction = faction;
        this.voteValue = voteValue;
        this.votePercent = votePercent;
        this.id = id;
    }

    public String getFaction() {
        return faction;
    }

    public Integer getVoteValue() {
        return voteValue;
    }

    public Integer getVotePercent() {
        return votePercent;
    }

    public Integer getId() {
        return id;
    }
}

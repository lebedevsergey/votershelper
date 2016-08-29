package com.example.admin.vybor.Models.datatypes;

public class LawData {

    private String informal_name;
    private String official_name;
    private int __id;
    private boolean state;

    public LawData(String informal_name, String official_name, int __id) {
        this.informal_name = informal_name;
        this.official_name = official_name;
        this.__id = __id;
        state = false;
    }

    public boolean getState() {
        return state;
    }

    public boolean isApprovedByUser() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public void toggleState(){
        this.state = !this.state;
    }

    public int getId() {
        return __id;
    }

    public void setId(int __id) {
        this.__id = __id;
    }

    public String getOfficial_name() {
        return official_name;
    }

    public void setOfficial_name(String official_name) {
        this.official_name = official_name;
    }

    public String getInformal_name() {
        return informal_name;
    }

    public void setInformal_name(String informal_name) {
        this.informal_name = informal_name;
    }
}
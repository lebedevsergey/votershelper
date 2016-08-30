package com.example.admin.vybor.Models.datatypes;

public class LawData {

    static final public int DONTCARE = 0;
    static final public int DISAPPROVE = 1;
    static final public int APPROVE = 2;

    private String informal_name;
    private String official_name;
    private int __id;
    private int state;

    public LawData(String informal_name, String official_name, int __id) {
        this.informal_name = informal_name;
        this.official_name = official_name;
        this.__id = __id;
        state = DONTCARE;
    }


    public int getUserApproveState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getState() {
        return this.state;
    }


    public void toggleState(){
        this.state = this.state + 1;
        if (this.state > APPROVE) {    // wrap max value
            this.state = DONTCARE;
        }
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
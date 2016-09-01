package com.example.admin.vybor.Models;

import android.content.Context;

import com.example.admin.vybor.util.DataBaseHelper;

import java.util.ArrayList;
import java.util.List;

import com.example.admin.vybor.Models.datatypes.LawData;

public class LawsListModel {

    static private List<LawData> model = new ArrayList<>();

    static public List<LawData> get() {
        return model;
    }

    static public void setFromDb(Context context) {
        model = (new DataBaseHelper(context)).getLawsData();
    }

    static public void getSavedStates(ArrayList<Integer> savedStates) {
        if (savedStates != null) {
            setUserVotesFromSaving(savedStates);
        }
    }

    static public void setAllTo(int state) {
        for (LawData item : model) {
            item.setState(state);
        }
    }

    static public ArrayList<Integer> getUserVotesForSaving() {
        ArrayList<Integer> votes = new ArrayList<>();
        for (LawData item : model) {
            votes.add(item.getState());
        }
        return votes;
    }

    static public void setUserVotesFromSaving(ArrayList<Integer> votes) {
        Integer i =0;
        for (LawData item : model) {
            item.setState(votes.get(i));
            i++;
        }
    }
}
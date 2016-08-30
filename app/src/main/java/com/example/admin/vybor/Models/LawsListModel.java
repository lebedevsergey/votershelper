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

    static public void setAllTo(int state) {
        for (LawData item : model) {
            item.setState(state);
        }
    }
}
package com.example.admin.vybor.Models;


import android.support.v4.util.ArrayMap;

import com.example.admin.vybor.Models.datatypes.RatingData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RatingModel{

    static private List<RatingData> model = new ArrayList<>();

    static public List<RatingData> get() {
        return model;
    }

    static public void set(Map<String, Integer> data) {
        model.clear();
        for (Map.Entry<String, Integer>  item : data.entrySet()) {  // average and convert to 10-points scale
            model.add(new RatingData(item.getKey(), item.getValue()));
        }

    }
}

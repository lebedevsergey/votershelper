package com.example.admin.vybor.Models;

import android.content.Context;

import com.example.admin.vybor.Models.datatypes.FactionVotesData;
import com.example.admin.vybor.Models.datatypes.LawData;
import com.example.admin.vybor.util.DataBaseHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FactionsVotesModel {
    final static private int PRO = -1;
    final static private int CONTRA = 1;
    final static private int DONTCARE = 0;
    final static private int DONTVOTED = 2;

    static private DataBaseHelper db;

    static private List<FactionVotesData> model = new ArrayList<>();

    static public List<FactionVotesData> get() {
        return model;
    }

    static public void setFromDb(Context context) {
        db = new DataBaseHelper(context);
        model = (new DataBaseHelper(context)).getFactionsVotes();
    }

    static public Integer calcRateDelta(FactionVotesData factionVoteResult, int userVote) {
        if (userVote == LawData.DONTCARE) {
            return 0;
        }

        Integer voteValue = factionVoteResult.getVoteValue();
        Integer rateDelta = 0;
        if (voteValue == PRO) {
            rateDelta = factionVoteResult.getVotePercent();
        } else if (voteValue == CONTRA) {
            rateDelta = -factionVoteResult.getVotePercent();
        }

        // воздержавшиеся, и тем более отсутствующие депутаты на рейтинг  не влияют (по крайней мере, на политический)

        // учитываем мнение пользователя
        if (userVote == LawData.DISAPPROVE) {
            rateDelta = - rateDelta;
        };

        return rateDelta;
    }

    static public Double convertTo100PointsScale(Double rate) {
        final Double offsetCoef = 100.0;
        final Double normalizeCoef = 2.0;
        return (rate + offsetCoef) / normalizeCoef;
    }

    static public Map<String, Integer> calcFactionsRates(List<LawData> laws) {

        Map<String, Integer> result = new HashMap<>();

            for (LawData law : laws) {

                for (FactionVotesData factionVoteResult : model) {  // голоса фракций  сгруппированы по результату
                    if (law.getId() != factionVoteResult.getId()) {
                        continue;
                    }

                    final String faction = factionVoteResult.getFaction();
                    if (!result.containsKey(faction)) {
                        result.put(faction, 0);
                    }

                    Integer rateDelta = FactionsVotesModel.calcRateDelta(factionVoteResult, law.getUserApproveState());
                    result.put(faction, result.get(faction) + rateDelta);
                }
        }

        Double averageCoef = (laws.size() != 0 ? laws.size() : 1.0);
        for (Map.Entry<String, Integer>  item : result.entrySet()) {  // average result and convert to 10-points scale
            Integer rate = (int)Math.round(convertTo100PointsScale((float)item.getValue()/averageCoef));
            result.put(item.getKey(), rate);
        }

        return result;
    }

}

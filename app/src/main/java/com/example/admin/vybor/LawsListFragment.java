package com.example.admin.vybor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.admin.vybor.Adapters.LawsListArrayAdapter;
import com.example.admin.vybor.Adapters.LawsListInfoModeArrayAdapter;
import com.example.admin.vybor.Adapters.RatingsArrayAdapter;
import com.example.admin.vybor.Models.LawsListModel;
import com.example.admin.vybor.Models.RatingModel;
import com.example.admin.vybor.Models.datatypes.LawData;
import com.example.admin.vybor.Models.datatypes.RatingData;

public class LawsListFragment extends android.support.v4.app.ListFragment {

    private int mCurPageNum;

    /**
     * Create a new instance of CountingFragment, providing "num"
     * as an argument.
     */
    static LawsListFragment newInstance(int num) {
        LawsListFragment f = new LawsListFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("num", num);
        f.setArguments(args);

        return f;
    }

    /**
     * When creating, retrieve this instance's number from its arguments.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCurPageNum = getArguments() != null ? getArguments().getInt("num") : 1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.page, container, false);

        ((TextView) view.findViewById(R.id.pageTitle)).setText(getTitle(mCurPageNum));

        if (mCurPageNum == 0) {
            ArrayAdapter<LawData> adapter1 = new LawsListArrayAdapter(getActivity(), LawsListModel.get());
            setListAdapter(adapter1);
        } else if (mCurPageNum == 1) {
            ArrayAdapter<RatingData> adapter1 = new RatingsArrayAdapter(getActivity(), RatingModel.get());
            setListAdapter(adapter1);
        } else if (mCurPageNum == 2) {
            ArrayAdapter<LawData> adapter2 = new LawsListInfoModeArrayAdapter(getActivity(), LawsListModel.get());
            setListAdapter(adapter2);
        }

        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private String getTitle(Integer pageNum) {
        switch (pageNum) {
            case 0:
                return "Что вы думаете об этих законах?";
            case 1:
                return "Рейтинг партий";
            case 2:
                return "Об этих законах";
            default:
                return "";
        }
    }
}
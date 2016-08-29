package com.example.admin.vybor;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.TextView;

import com.example.admin.vybor.Adapters.LawsListArrayAdapter;
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

        final String pageTitle = this.getTitle(mCurPageNum);
        View view = inflater.inflate(R.layout.page, container, false);

        View tv = view.findViewById(R.id.pageTitle);
        ((TextView)tv).setText(pageTitle);

        if (mCurPageNum == 1) {
            ArrayAdapter<RatingData> adapter1 = new RatingsArrayAdapter(getActivity(), RatingModel.get());
            setListAdapter(adapter1);
//            // определяем массив типа String
//            final String[] catnames = new String[] {
//                    "Рыжик", "Барсик", "Мурзик"
//            };
//
//            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),  android.R.layout.simple_list_item_1, catnames);
//            setListAdapter(adapter);
        } else {
            ArrayAdapter<LawData> adapter2 = new LawsListArrayAdapter(getActivity(), LawsListModel.get());
            setListAdapter(adapter2);
        }

        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    private String getTitle(Integer pageNum) {
        String result;
        switch (pageNum) {
            case 0:
                result = "Выберите свое мнение";
            break;
            case 1:
                result = "Рейтинг фракций Госдумы, согласно их голосованию";
            break;
            case 2:
                result = "Информация о законах";
            break;
            case 3:
                result = "Статистика";
            break;
            default:
                result = "";
        }
        return result;
    }

}
package com.example.admin.vybor.Adapters;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.admin.vybor.Models.RatingModel;
import com.example.admin.vybor.Models.datatypes.RatingData;
import com.example.admin.vybor.R;

public class RatingsArrayAdapter extends ArrayAdapter<RatingData> {

    private final List<RatingData> list;
    private final Activity context;

    public RatingsArrayAdapter(Activity context, List<RatingData> list) {
        super(context, R.layout.page2rowlayout, list);

        this.context = context;
        this.list = list;
    }

    static class ViewHolder {
        protected TextView label;
        protected TextView rate;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        final List<RatingData> list2 = RatingModel.get();

        if (convertView == null) {

            LayoutInflater inflater = context.getLayoutInflater();
            view = inflater.inflate(R.layout.page2rowlayout, null);

            final ViewHolder viewHolder = new ViewHolder();
            viewHolder.label = (TextView) view.findViewById(R.id.label);
            viewHolder.rate = (TextView) view.findViewById(R.id.rate);
            view.setTag(viewHolder);
            viewHolder.label.setTag(list2.get(position));
        } else {
            view = convertView;
            ((ViewHolder) view.getTag()).label.setTag(list2.get(position));
        }
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.label.setText(list2.get(position).getFactionPrintableName());
        holder.rate.setText(list2.get(position).getRating().toString());


        return view;
    }
}
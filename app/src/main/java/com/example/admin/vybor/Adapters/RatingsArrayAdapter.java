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
        protected TextView text;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        final List<RatingData> list2 = RatingModel.get();

        if (convertView == null) {

            LayoutInflater inflater = context.getLayoutInflater();
            view = inflater.inflate(R.layout.page2rowlayout, null);

            final ViewHolder viewHolder = new ViewHolder();
            viewHolder.text = (TextView) view.findViewById(R.id.label);
            view.setTag(viewHolder);
            viewHolder.text.setTag(list2.get(position));
        } else {
            view = convertView;
            ((ViewHolder) view.getTag()).text.setTag(list2.get(position));
        }
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.text.setText(list2.get(position).getFactionWithRate());

        return view;
    }
}
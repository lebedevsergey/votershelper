package com.example.admin.vybor.Adapters;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.admin.vybor.Models.datatypes.LawData;
import com.example.admin.vybor.R;

public class LawsListInfoModeArrayAdapter extends ArrayAdapter<LawData> {

    private final List<LawData> list;
    private final Activity context;

    public LawsListInfoModeArrayAdapter(Activity context, List<LawData> list) {
        super(context, R.layout.page1rowlayout, list);

        this.context = context;
        this.list = list;
    }

    static class ViewHolder {
        protected TextView text;
        protected TextView description;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {

            LayoutInflater inflater = context.getLayoutInflater();
            view = inflater.inflate(R.layout.page3rowlayout, null);

            final ViewHolder viewHolder = new ViewHolder();
            viewHolder.text = (TextView) view.findViewById(R.id.label);
            viewHolder.description = (TextView) view.findViewById(R.id.description);

            view.setTag(viewHolder);
            viewHolder.text.setTag(list.get(position));
        } else {
            view = convertView;
            ((ViewHolder) view.getTag()).text.setTag(list.get(position));
        }
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.text.setText(list.get(position).getInformal_name());
        holder.description.setText(list.get(position).getOfficial_name());

        return view;
    }
}
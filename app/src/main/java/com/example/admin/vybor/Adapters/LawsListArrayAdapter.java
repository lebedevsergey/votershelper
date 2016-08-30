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

public class LawsListArrayAdapter extends ArrayAdapter<LawData> {

    private final List<LawData> list;
    private final Activity context;

    public LawsListArrayAdapter(Activity context, List<LawData> list) {
        super(context, R.layout.page1rowlayout, list);

        this.context = context;
        this.list = list;
    }

    static class ViewHolder {
        protected TextView text;
        protected Spinner checkbox;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {

            LayoutInflater inflater = context.getLayoutInflater();
            view = inflater.inflate(R.layout.page1rowlayout, null);

            final ViewHolder viewHolder = new ViewHolder();
            viewHolder.text = (TextView) view.findViewById(R.id.label);
            viewHolder.text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    LawData element = (LawData) viewHolder.checkbox.getTag();
                    element.toggleState();
                    viewHolder.checkbox.setSelection(element.getState());
                }
            });

            viewHolder.checkbox = (Spinner) view.findViewById(R.id.check);
            viewHolder.checkbox.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> parent,
                                           View itemSelected, int selectedItemPosition, long selectedId) {
                    LawData element = (LawData) viewHolder.checkbox.getTag();
                    element.setState(selectedItemPosition);

                }
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

            view.setTag(viewHolder);
            viewHolder.checkbox.setTag(list.get(position));
        } else {
            view = convertView;
            ((ViewHolder) view.getTag()).checkbox.setTag(list.get(position));
        }
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.text.setText(list.get(position).getInformal_name());
        holder.checkbox.setSelection(list.get(position).getState());

        return view;
    }
}
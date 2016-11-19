package com.example.kevin.wear_where;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.kevin.wear_where.MapInformation.IntervalInformation;
import com.example.kevin.wear_where.MapInformation.MapInformation;

/**
 * Created by Hermes on 11/13/2016.
 */

public class IntervalAdapter extends BaseAdapter {

    private Context _context;
    private MapInformation _mapInformation;

    public IntervalAdapter(Context context, MapInformation mapInformation) {
        this._context = context;
        this._mapInformation = mapInformation;
    }

    @Override
    public int getCount() {
        return _mapInformation.intervalTitles.size();
    }

    @Override
    public Object getItem(int position) {
        return new IntervalInformation(_mapInformation.intervalTitles.get(position), _mapInformation.intervalDetails.get(position));
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.interval_information, null);
        }
        TextView intervalNameTextView = (TextView) convertView.findViewById(R.id.interval_title);
        TextView intervalDetailTextView = (TextView) convertView.findViewById(R.id.interval_subtitle);

        IntervalInformation intervalInformation = (IntervalInformation) getItem(position);

        intervalNameTextView.setText(intervalInformation.intervalTitle);
        intervalDetailTextView.setText(intervalInformation.intervalDetails);
        return convertView;
    }
}

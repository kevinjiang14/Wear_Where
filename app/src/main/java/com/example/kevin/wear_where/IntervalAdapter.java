package com.example.kevin.wear_where;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.kevin.wear_where.MapInformation.IntervalInformation;
import com.example.kevin.wear_where.MapInformation.MapInformation;
import com.example.kevin.wear_where.wear.Clothing;

import java.util.ArrayList;

/**
 * Created by Hermes on 11/13/2016.
 */

public class IntervalAdapter extends BaseAdapter {

    private Context _context;
    private MapInformation _mapInformation;
    private Clothing _clothes;

    public IntervalAdapter(Context context, MapInformation mapInformation, Clothing clothes) {
        this._context = context;
        this._mapInformation = mapInformation;
        this._clothes = clothes;
    }

    @Override
    public int getCount() {
        return _mapInformation.intervalTitles.size();
    }

    @Override
    public Object getItem(int position) {
        return new IntervalInformation(_mapInformation.intervalTitles.get(position), _mapInformation.intervalDetails.get(position), _mapInformation.intervalTemperatures.get(position));
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
        TextView intervalClothingTitle = (TextView) convertView.findViewById(R.id.interval_clothing_title);
        TextView intervalClothingTextView = (TextView) convertView.findViewById(R.id.interval_clothing);

        IntervalInformation intervalInformation = (IntervalInformation) getItem(position);

        intervalNameTextView.setText(intervalInformation.intervalTitle);
        intervalDetailTextView.setText(intervalInformation.intervalDetail);
        intervalClothingTitle.setPaintFlags(intervalClothingTitle.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        String misc = _clothes.getMisc(intervalInformation.intervalTemperature).toString();
        if (misc.length() == 2) {
            misc = "[N/A]";
        }
        String upperbody = _clothes.getUpperBody(intervalInformation.intervalTemperature).toString();
        if (upperbody.length() == 2) {
            upperbody = "[N/A]";
        }
        String lowerbody = _clothes.getLowerBody(intervalInformation.intervalTemperature).toString();
        if (lowerbody.length() == 2) {
            lowerbody = "[N/A]";
        }
        String shoes = _clothes.getShoes(intervalInformation.intervalTemperature).toString();
        if (shoes.length() == 2) {
            shoes = "[N/A]";
        }
        intervalClothingTextView.setText("Miscellaneous:" + '\n' + misc.substring(1, misc.length() - 1) + "\n\n" +
                                            "Upperbody:" + '\n' +  upperbody.substring(1, upperbody.length() - 1) + "\n\n" +
                                            "Lowerbody:" + '\n' + lowerbody.substring(1, lowerbody.length() - 1) + "\n\n" +
                                            "Shoes:" + '\n' + shoes.substring(1, shoes.length() - 1) + '\n');

        return convertView;
    }
}

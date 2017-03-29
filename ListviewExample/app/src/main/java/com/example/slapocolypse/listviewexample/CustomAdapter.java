package com.example.slapocolypse.listviewexample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by slapocolypse on 3/15/17.
 */

public class CustomAdapter extends ArrayAdapter {

    public CustomAdapter(Context context, int resource, List<Item> objects) {
        super(context, resource, objects);


    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View theView = convertView;
        Item item = (Item) getItem(position);
        return super.getView(position, convertView, parent);
    }




}

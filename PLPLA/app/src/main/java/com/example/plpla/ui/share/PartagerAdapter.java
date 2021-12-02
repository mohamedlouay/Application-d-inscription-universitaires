package com.example.plpla.ui.share;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.plpla.R;

import java.util.ArrayList;



public class PartagerAdapter extends BaseAdapter  {


    private ArrayList<String> mListItems;
    private LayoutInflater mLayoutInflater;

    public PartagerAdapter( ArrayList<String> arrayList){

        mListItems = arrayList;

    }

    @Override
    public int getCount() {
        return mListItems.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override

    public View getView(int position, View view, ViewGroup viewGroup) {

        ViewHolder holder;

        if (view == null) {
            holder = new ViewHolder();

            view = mLayoutInflater.inflate(R.layout.list_item, null);
            holder.itemName = (TextView) view.findViewById(R.id.list_item_text_view);

            view.setTag(holder);
        } else {
            holder = (ViewHolder)view.getTag();
        }

        String stringItem = mListItems.get(position);
        if (stringItem != null) {
            if (holder.itemName != null) {
                holder.itemName.setText(stringItem);
            }
        }

        return view;

    }

    private static class ViewHolder {

        protected TextView itemName;

    }
}



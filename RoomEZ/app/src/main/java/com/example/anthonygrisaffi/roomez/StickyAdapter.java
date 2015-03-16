package com.example.anthonygrisaffi.roomez;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by anthonygrisaffi on 3/15/15.
 */
public class StickyAdapter extends RecyclerView.Adapter {

    private ArrayList<String> strings;

    public StickyAdapter(ArrayList<String> stringArray) {
        strings = stringArray;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}

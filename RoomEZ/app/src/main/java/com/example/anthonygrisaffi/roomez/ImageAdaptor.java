package com.example.anthonygrisaffi.roomez;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;

/**
 * Created by anthonygrisaffi on 2/17/15.
 */
public class ImageAdaptor extends BaseAdapter {
    private Context context;
    public ImageAdaptor(Context context)
    {
           this.context =context;
    }

    /*@Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }*/

    public int getCount()
    {
        return mThumbsIds.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    /*@Override
    public boolean hasStableIds() {
        return false;
    }*/

    public View getView(int position, View convertView, ViewGroup arg2)
    {
        ImageView imageView;

        if(convertView==null)
        {
            imageView = new ImageView(context);
            //imageView.setPadding(8,8,8,8);
            //imageView.setLayoutParams(new GridView.LayoutParams(85,85));
            //imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
        else
        {
            imageView = (ImageView)convertView;
        }

        imageView.setImageResource(mThumbsIds[position]);
        return imageView;
    }

    /*@Override
    public int getItemViewType(int position) {
        return position % 1;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }*/

    private Integer[] mThumbsIds={
            R.drawable.ran1,
            R.drawable.ran2,
            R.drawable.ran3,
            R.drawable.ran4,
            R.drawable.ran5,
            R.drawable.ran6,
            R.drawable.ran7,
            R.drawable.ran8,
            R.drawable.ran9,
            R.drawable.ran10,
            R.drawable.ran11,
            R.drawable.ran12
    };

    /*@Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }*/
}

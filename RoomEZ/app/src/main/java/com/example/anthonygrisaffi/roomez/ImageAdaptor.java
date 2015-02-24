package com.example.anthonygrisaffi.roomez;

import android.app.FragmentManager;
import android.content.Context;
import android.database.DataSetObserver;
import android.support.v4.app.FragmentActivity;
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

        // /FragmentActivity fragment = new FragmentActivity();
        //fraigment.setContentView(R.layout.sticky);
        //Add the fragment itself here, then
        //1)update data in adapter
        //2)tell the list view adapter to reload by notify dataset changed"
        //3)scroll button listview so you can see it in the bottom of the page.
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
}

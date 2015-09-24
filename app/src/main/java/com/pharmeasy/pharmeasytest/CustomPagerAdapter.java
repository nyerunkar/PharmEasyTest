package com.pharmeasy.pharmeasytest;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Nilesh on 24/09/15.
 */
public class CustomPagerAdapter extends PagerAdapter {

    Context mContext;
    LayoutInflater mLayoutInflater;
    String[][] saData = null;


    public CustomPagerAdapter(Context context,String[][] saData)
    {
        mContext = context;
        this.saData = saData;

        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);



    }

    @Override
    public int getCount()
    {
        return saData.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position)
    {
        View itemView = mLayoutInflater.inflate(R.layout.pager_item, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.app_icon);

        TextView tvLabel = (TextView) itemView.findViewById(R.id.label);
        tvLabel.setText("Label: "+saData[position][4]);

        TextView tvManufacturer = (TextView) itemView.findViewById(R.id.manufacturer);
        tvManufacturer.setText("Manufacturer: "+saData[position][8]);

        TextView tvUprice = (TextView) itemView.findViewById(R.id.uPrice);
        tvUprice.setText("uPrice: "+saData[position][9]);
        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
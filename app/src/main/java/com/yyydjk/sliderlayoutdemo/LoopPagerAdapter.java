package com.yyydjk.sliderlayoutdemo;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

public class LoopPagerAdapter extends PagerAdapter {
    private Context mContext;
    private List<View> views;
    private List<MainActivity.ItemBean> beanList;
    private ImageLoader imageLoader;

    LoopPagerAdapter(Context context, List<View> views, List<MainActivity.ItemBean> beanList) {
        this.mContext = context;
        this.views = views;
        this.beanList = beanList;
    }

    @Override
    public int getCount() {
        //Integer.MAX_VALUE = 2147483647
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if (views.size() > 0) {
            //position % view.size()是指虚拟的position会在[0，view.size()）之间循环
            position %= views.size();
            View view = views.get(position);
            TextView item_tv = (TextView) view.findViewById(R.id.item_tv);
            ImageView item_iv = (ImageView) view.findViewById(R.id.item_iv);
            String text = beanList.get(position).getText();
            item_tv.setText(text);
            imageLoader.displayImage(mContext, beanList.get(position).getUrl(), item_iv);
            if (container.equals(view.getParent())) {
                container.removeView(view);
            }
            container.addView(view);
            return view;
        }
        return null;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    }

    public interface ImageLoader extends Serializable {
        void displayImage(Context context, String path, ImageView imageView);
    }

    public void setImageLoader(ImageLoader imageLoader) {
        this.imageLoader = imageLoader;
    }
}


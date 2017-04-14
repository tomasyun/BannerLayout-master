package com.yyydjk.sliderlayoutdemo;


import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class GlideImageLoader implements LoopPagerAdapter.ImageLoader{
    @Override
    public void displayImage(Context context, String path, ImageView imageView) {
        Glide.with(context).load(path).centerCrop().into(imageView);
    }
}

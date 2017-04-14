package com.yyydjk.sliderlayoutdemo;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.yyydjk.library.BannerLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<ItemBean> beanList;
    private List<View> views;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        beanList = new ArrayList<>();
        initBeanList();
        count = beanList.size();

        views = new ArrayList<>();
        initViewList(count);

        BannerLayout bannerLayout = (BannerLayout) findViewById(R.id.banner);
        bannerLayout.setItemCount(count);

        LoopPagerAdapter pagerAdapter = new LoopPagerAdapter(this, views, beanList);
        pagerAdapter.setImageLoader(new GlideImageLoader());
        bannerLayout.setViews(pagerAdapter);

        //添加监听事件
        bannerLayout.setOnBannerItemClickListener(new BannerLayout.OnBannerItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(MainActivity.this, String.valueOf(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public class ItemBean {
        private String url;
        private String text;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }

    public List<ItemBean> initBeanList() {

        ItemBean bean1 = new ItemBean();
        bean1.setUrl("http://img3.imgtn.bdimg.com/it/u=2674591031,2960331950&fm=23&gp=0.jpg");
        bean1.setText("东方明珠");

        beanList.add(bean1);

        ItemBean bean2 = new ItemBean();
        bean2.setUrl("http://img5.imgtn.bdimg.com/it/u=3639664762,1380171059&fm=23&gp=0.jpg");
        bean2.setText("南京路步行街");

        beanList.add(bean2);

        ItemBean bean3 = new ItemBean();
        bean3.setUrl("http://img0.imgtn.bdimg.com/it/u=1095909580,3513610062&fm=23&gp=0.jpg");
        bean3.setText("杜夫人蜡像馆");

        beanList.add(bean3);

        return beanList;
    }

    public List<View> initViewList(int count) {
        if (count > 0) {
            for (int i = 0; i < count; i++) {
                View view = LayoutInflater.from(this).inflate(R.layout.item_pager, null);
                views.add(view);
            }
        }
        return views;
    }
}

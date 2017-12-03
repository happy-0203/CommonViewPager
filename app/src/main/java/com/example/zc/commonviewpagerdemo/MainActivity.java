package com.example.zc.commonviewpagerdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zc.commonviewpagerdemo.interfaces.ViewPagerHolder;
import com.example.zc.commonviewpagerdemo.interfaces.ViewPagerHolderCreator;
import com.example.zc.commonviewpagerdemo.views.CommonViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ViewPagerHolderCreator {

    private CommonViewPager mCommonViewPager;
    private List<Integer> mDatas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCommonViewPager = (CommonViewPager) findViewById(R.id.view_pager);
        for (int i = 0; i < 6; i++) {
            mDatas.add(R.mipmap.ic_launcher);
        }
        initView();
    }

    private void initView() {
        mCommonViewPager.setPageDatas(mDatas,this);
    }

    @Override
    public ViewPagerHolder createViewHolder() {
        return new ViewImageHolder();
    }


    public static class ViewImageHolder implements ViewPagerHolder<List<Integer>>{

        private ImageView mImageView;
        private TextView mTextView;

        @Override
        public View createView(Context context) {

            View view = LayoutInflater.from(context).inflate(R.layout.item_commonviewpager_layout,null);
            mImageView = (ImageView) view.findViewById(R.id.viewPager_item_image);
            mTextView = (TextView) view.findViewById(R.id.item_desc);
            return view;
        }

        @Override
        public void onBind(Context context, int position, List<Integer> data) {
            mImageView.setImageResource(data.get(position));
        }
    }
}

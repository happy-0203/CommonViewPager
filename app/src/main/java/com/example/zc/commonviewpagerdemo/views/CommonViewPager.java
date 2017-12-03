package com.example.zc.commonviewpagerdemo.views;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.zc.commonviewpagerdemo.R;
import com.example.zc.commonviewpagerdemo.adapter.CommonViewPagerAdapter;
import com.example.zc.commonviewpagerdemo.interfaces.ViewPagerHolderCreator;

import java.util.List;

/**
 * Created by zengli18 on 2017/12/4.
 */

public class CommonViewPager<T> extends RelativeLayout {

    private ViewPager mViewPager;
    private CommonViewPagerAdapter mAdapter;

    public CommonViewPager(Context context) {
        this(context,null);
    }

    public CommonViewPager(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CommonViewPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.common_viewpager_layout, this, true);
        mViewPager = (ViewPager) view.findViewById(R.id.common_view_pager);
    }

    /**
     * 设置数据
     * @param datas
     * @param creator
     */
    public void setPageDatas(List<T> datas, ViewPagerHolderCreator creator){
        mAdapter = new CommonViewPagerAdapter(datas,creator);
        mViewPager.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    public void setCurrentItem(int currentItem){
        mViewPager.setCurrentItem(currentItem);
    }

    public int getCurrentItem(){
        return mViewPager.getCurrentItem();
    }

    public void setOffscreenPageLimit(int limit){
        mViewPager.setOffscreenPageLimit(limit);
    }


    /**
     * 设置切换动画，see {@link ViewPager#setPageTransformer(boolean, ViewPager.PageTransformer)}
     * @param reverseDrawingOrder
     * @param transformer
     */
    public void setPageTransformer(boolean reverseDrawingOrder, ViewPager.PageTransformer transformer){
        mViewPager.setPageTransformer(reverseDrawingOrder,transformer);
    }

    /**
     * see {@link ViewPager#setPageTransformer(boolean, ViewPager.PageTransformer)}
     * @param reverseDrawingOrder
     * @param transformer
     * @param pageLayerType
     */
    public void setPageTransformer(boolean reverseDrawingOrder, ViewPager.PageTransformer transformer,
                                   int pageLayerType) {
        mViewPager.setPageTransformer(reverseDrawingOrder,transformer,pageLayerType);
    }

    /**
     * see {@link ViewPager#addOnPageChangeListener(ViewPager.OnPageChangeListener)}
     * @param listener
     */
    public void addOnPageChangeListener(ViewPager.OnPageChangeListener listener){
        mViewPager.addOnPageChangeListener(listener);
    }


    public ViewPager getViewPager() {
        return mViewPager;
    }


}

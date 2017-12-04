package com.example.zc.commonviewpagerdemo.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.example.zc.commonviewpagerdemo.R;
import com.example.zc.commonviewpagerdemo.interfaces.ViewPagerHolder;
import com.example.zc.commonviewpagerdemo.interfaces.ViewPagerHolderCreator;

import java.util.List;

/**
 * Created by zengli18 on 2017/12/3.
 *
 * 封装Adapter
 */

public class CommonViewPagerAdapter<T> extends PagerAdapter {

    private List<T> mDatas;
    private ViewPagerHolderCreator mCreator;

    public CommonViewPagerAdapter(List<T> datas, ViewPagerHolderCreator creator) {
        mDatas = datas;
        mCreator = creator;
    }

    @Override
    public int getCount() {
        return mDatas != null ? mDatas.size() : 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        //重点就在这儿了，不再是把布局写死，而是用接口提供的布局
        // 也不在这里绑定数据，数据绑定交给Api调用者

        View view = getView(position, null, container);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    private View getView(int position, View view, ViewGroup container) {

        ViewPagerHolder holder = null;
        if (view == null){
            //创建holder
            holder = mCreator.createViewHolder();
            view = holder.createView(container.getContext());
            view.setTag(R.id.common_view_pager_item_tag,holder);
        }else {
          holder = (ViewPagerHolder) view.getTag();
        }
        //绑定数据
        if (holder !=null && mDatas !=null && mDatas.size()>0){
            holder.onBind(container.getContext(),position,mDatas);
        }


        return view;
    }

}

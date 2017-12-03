package com.example.zc.commonviewpagerdemo.interfaces;

/**
 * Created by zengli18 on 2017/12/3.
 * ViewHolder生成器,接受一个泛型,必须是ViewPagerHolder的子类
 */

public interface ViewPagerHolderCreator<VH extends ViewPagerHolder> {

    /**
     * 创建ViewHolder
     * @return
     */
    VH createViewHolder();
}

package com.example.zc.commonviewpagerdemo.interfaces;

import android.content.Context;
import android.view.View;

/**
 * Created by zengli18 on 2017/12/3.
 *
 * 提供布局和绑定数据
 *
 */

public interface ViewPagerHolder<T> {

    /**
     * 创建布局
     * @param context
     * @return
     */
    View createView(Context context);

    /**
     * 绑定数据
     * @param context
     * @param position
     * @param data
     */
    void onBind(Context context,int position,T data);
}

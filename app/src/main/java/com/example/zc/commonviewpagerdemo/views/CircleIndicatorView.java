package com.example.zc.commonviewpagerdemo.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

import com.example.zc.commonviewpagerdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cc on 2017/12/4.
 */

public class CircleIndicatorView extends View implements ViewPager.OnPageChangeListener {

    private int mRadius;
    private int mStrokeWidth;
    private int mSpace;
    private int mTextColor;
    private int mDotNormalColor;
    private int mDotSelectedColor;
    private boolean mIsEnableClickSwith;
    private FillMode mFillMode = FillMode.NONE;
    private Paint mCirclePaint;
    private Paint mTextPaint;
    private int mCount;//小圆点的个数
    private List<Indicator> mIndicators;
    private int mSelectPosition = 0;


    private static final String LETTER[] = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "G", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private ViewPager mViewPager;

    public CircleIndicatorView(Context context) {
        this(context, null);
    }

    public CircleIndicatorView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleIndicatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getAttr(context, attrs);
        init();
    }

    private void getAttr(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleIndicatorView);
        mRadius = typedArray.getDimensionPixelSize(R.styleable.CircleIndicatorView_indicatorRadius, DisplayUtils.dpToPx(6));
        mStrokeWidth = typedArray.getDimensionPixelSize(R.styleable.CircleIndicatorView_indicatorBorderWidth, DisplayUtils.dpToPx(2));
        mSpace = typedArray.getDimensionPixelSize(R.styleable.CircleIndicatorView_indicatorSpace, DisplayUtils.dpToPx(5));

        //颜色
        mTextColor = typedArray.getColor(R.styleable.CircleIndicatorView_indicatorTextColor, Color.BLACK);
        mDotNormalColor = typedArray.getColor(R.styleable.CircleIndicatorView_indicatorColor, Color.WHITE);
        mDotSelectedColor = typedArray.getColor(R.styleable.CircleIndicatorView_indicatorSelectColor, Color.GRAY);

        mIsEnableClickSwith = typedArray.getBoolean(R.styleable.CircleIndicatorView_enableIndicatorSwitch, false);

        int fillMode = typedArray.getInt(R.styleable.CircleIndicatorView_fill_mode, 2);

        if (fillMode == 0) {
            mFillMode = FillMode.LETTER;
        } else if (fillMode == 1) {
            mFillMode = FillMode.NUMBER;
        } else {
            mFillMode = FillMode.NONE;
        }

        typedArray.recycle();
    }

    private void init() {
        //初始化画笔
        //画圆圈
        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint.setDither(true);//抗抖动
        mCirclePaint.setColor(mDotNormalColor);
        mCirclePaint.setStrokeWidth(mStrokeWidth);
        mCirclePaint.setStyle(Paint.Style.FILL_AND_STROKE);


        //画文字
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setDither(true);
        mTextPaint.setColor(mTextColor);
        mTextPaint.setTextSize(mRadius);


        mIndicators = new ArrayList<>();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = (mRadius + mStrokeWidth) * 2 * mCount + mSpace + mSpace * (mCount - 1);
        int height = (mRadius + mStrokeWidth) * 2;
        setMeasuredDimension(width, height);
        measureIndicator();
    }

    /**
     * 测量每个圆点的位置
     */
    private void measureIndicator() {
        mIndicators.clear();
        float cx = 0;
        for (int i = 0; i < mCount; i++) {
            Indicator indicator = new Indicator();
            if (i == 0) {
                cx = mRadius + mStrokeWidth;
            } else {
                cx += (mRadius + mStrokeWidth) * 2 + mSpace;
            }

            indicator.cx = cx;
            indicator.cy = getMeasuredHeight() / 2;

            mIndicators.add(indicator);
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        for (int i = 0; i < mIndicators.size(); i++) {
            Indicator indicator = mIndicators.get(i);
            float cx = indicator.cx;
            float cy = indicator.cy;

            if (mSelectPosition == i) {
                mCirclePaint.setStyle(Paint.Style.FILL);
                mCirclePaint.setColor(mDotSelectedColor);
            } else {
                mCirclePaint.setColor(mDotNormalColor);
                if (mFillMode != FillMode.NONE) {
                    mCirclePaint.setStyle(Paint.Style.STROKE);
                } else {
                    mCirclePaint.setStyle(Paint.Style.FILL);
                }
            }

            canvas.drawCircle(cx, cy, mRadius, mCirclePaint);


            //绘制圆圈里面的文字
            if (mFillMode != FillMode.NONE) {
                String text = "";
                if (mFillMode == FillMode.LETTER) {
                    if (i >= 0 && i < LETTER.length) {
                        text = LETTER[i];
                    }
                } else {
                    text = String.valueOf(i + 1);
                }

                Rect bounds = new Rect();
                mTextPaint.getTextBounds(text, 0, text.length(), bounds);
                int textWidth = bounds.width();
                int textHeight = bounds.height();

                float textStartX = cx - textWidth / 2;
                float textStartY = cy + textHeight / 2;
                canvas.drawText(text, textStartX, textStartY, mTextPaint);
            }
        }

    }


    /**
     * 设置 border
     *
     * @param borderWidth
     */
    public void setBorderWidth(int borderWidth) {
        mStrokeWidth = borderWidth;
        mCirclePaint.setStrokeWidth(borderWidth);
    }

    /**
     * 设置文字的颜色
     *
     * @param textColor
     */
    public void setTextColor(int textColor) {
        mTextColor = textColor;
        mTextPaint.setColor(textColor);
    }

    /**
     * 设置选中指示器的颜色
     *
     * @param selectColor
     */
    public void setSelectColor(int selectColor) {
        mDotSelectedColor = selectColor;
    }

    /**
     * 设置指示器默认颜色
     *
     * @param dotNormalColor
     */
    public void setDotNormalColor(int dotNormalColor) {
        mDotNormalColor = dotNormalColor;
        mCirclePaint.setColor(dotNormalColor);
    }

    /**
     * 设置选中的位置
     *
     * @param selectPosition
     */
    public void setSelectPosition(int selectPosition) {
        mSelectPosition = selectPosition;
    }

    /**
     * 设置Indicator 模式
     *
     * @param fillMode
     */
    public void setFillMode(FillMode fillMode) {
        mFillMode = fillMode;
    }

    /**
     * 设置Indicator 半径
     *
     * @param radius
     */
    public void setRadius(int radius) {
        mRadius = radius;
        mTextPaint.setTextSize(radius);
    }

    public void setSpace(int space) {
        mSpace = space;
    }

    public void setEnableClickSwitch(boolean enableClickSwitch) {
        mIsEnableClickSwith = enableClickSwitch;
    }


    public void setUpWithViewPager(ViewPager viewPager) {
        releaseViewPager();
        if (viewPager == null) {
            return;
        }
        mViewPager = viewPager;
        mViewPager.addOnPageChangeListener(this);
        int count = mViewPager.getAdapter().getCount();
        setCount(count);
    }

    private void releaseViewPager() {
        if (mViewPager != null) {
            mViewPager.removeOnPageChangeListener(this);
            mViewPager = null;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        mSelectPosition = position;
        invalidate();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void setCount(int count) {
        mCount = count;
    }


    public static class Indicator {
        public float cx; // 圆心x坐标
        public float cy; // 圆心y 坐标
    }

    public enum FillMode {
        LETTER,
        NUMBER,
        NONE
    }
}

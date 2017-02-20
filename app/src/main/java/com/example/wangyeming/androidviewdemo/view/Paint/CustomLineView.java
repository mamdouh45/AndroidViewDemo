package com.example.wangyeming.androidviewdemo.view.Paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.wangyeming.androidviewdemo.DisplayUtil;

/**
 * Created by wangyeming on 2017/2/19.
 */
public class CustomLineView extends View {

    private Paint mPaint;

    private int mLineStartPositionX;
    private int mLineStartPositionY;
    private int mLineLength;
    private int mLeanHeight;
    private static int NORMAL_HEIGHT;

    public CustomLineView(Context context) {
        this(context, null);
    }

    public CustomLineView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomLineView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        NORMAL_HEIGHT = DisplayUtil.dip2px(getContext(), 30);
        mLineStartPositionX = DisplayUtil.dip2px(getContext(), 8);
        mLineStartPositionY = DisplayUtil.dip2px(getContext(), 16);
        mLeanHeight = DisplayUtil.dip2px(getContext(), 5);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        mLineLength = width / 2 - 20;
        setMeasuredDimension(width, NORMAL_HEIGHT);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.RED);
        canvas.drawLine(mLineStartPositionX, mLineStartPositionY,
                mLineStartPositionX + mLineLength, mLineStartPositionY, mPaint);
        mPaint.setColor(Color.rgb(255, 239, 213));
        canvas.drawLine(mLineStartPositionX + mLineLength + 20, mLineStartPositionY,
                mLineStartPositionX + 2 * mLineLength, mLineStartPositionY + mLeanHeight,
                mPaint);
    }
}

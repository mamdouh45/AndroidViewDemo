package com.example.wangyeming.androidviewdemo.view.Paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.example.wangyeming.androidviewdemo.DisplayUtil;

/**
 * Created by wangyeming on 2017/2/20.
 */
public class CustomTextPaintView extends View {

    private TextPaint mTextPaint;

    private int mTextPositionX;
    private int mTextPositionY;
    private static int NORMAL_HEIGHT;

    public CustomTextPaintView(Context context) {
        this(context, null);
    }

    public CustomTextPaintView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomTextPaintView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        NORMAL_HEIGHT = DisplayUtil.dip2px(getContext(), 30);
        mTextPositionX = DisplayUtil.dip2px(getContext(), 8);
        mTextPositionY = DisplayUtil.dip2px(getContext(), 16);

        mTextPaint = new TextPaint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(Color.parseColor("#9900FF"));
        mTextPaint.setTextSize(DisplayUtil.sp2dip(getContext(), 16));

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), NORMAL_HEIGHT);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText("use canvas drawText() to show text", mTextPositionX, mTextPositionY, mTextPaint);
    }
}

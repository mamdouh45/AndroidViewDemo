package com.example.wangyeming.androidviewdemo.view.Paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.example.wangyeming.androidviewdemo.DisplayUtil;

/**
 *  View for draw rectF, oval, and other polygon
 *
 * Created by wangyeming on 2017/2/20.
 */
public class CustomArcView extends View {

    private Paint mPaint;
    private RectF mArcRectF1;
    private RectF mArcRectF2;
    private RectF mArcRectF3;

    private static int NORMAL_WIGHT;
    private static int NORMAL_HEIGHT;


    public CustomArcView(Context context) {
        this(context, null);
    }

    public CustomArcView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomArcView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        NORMAL_WIGHT = DisplayUtil.dip2px(getContext(), 100);
        NORMAL_HEIGHT = DisplayUtil.dip2px(getContext(), 80);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        mArcRectF1 = new RectF();
        mArcRectF2 = new RectF();
        mArcRectF3 = new RectF();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureDimension(widthMeasureSpec, NORMAL_WIGHT),
                measureDimension(heightMeasureSpec, NORMAL_HEIGHT)
        );
    }

    private int measureDimension(int measureSpec, int normalLength) {
        int result;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (specMode == MeasureSpec.EXACTLY) {//精确地，代表宽高为定值或者match_parent时
            result = specSize;
        } else {
            result = normalLength;
            if (specMode == MeasureSpec.AT_MOST) {//最大地，代表宽高为wrap_content时
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mArcRectF1.set(0, 0, getWidth() / 3, getHeight());
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.WHITE);
        canvas.drawArc(mArcRectF1, 0, 180, false, mPaint);

        mArcRectF2.set(getWidth() / 3, 0, 2 * getWidth() / 3, getHeight());
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.parseColor("#CC9909"));
        canvas.drawArc(mArcRectF2, 30, 230, false, mPaint);

        mArcRectF3.set(2 * getWidth() / 3, 0, getWidth(), getHeight());
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setColor(Color.parseColor("#99FF99"));
        canvas.drawArc(mArcRectF3, 0, -100, false, mPaint);

    }
}

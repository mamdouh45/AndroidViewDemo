package com.example.wangyeming.androidviewdemo.view.Paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import com.example.wangyeming.androidviewdemo.DisplayUtil;

/***
 * Created by wangyeming on 2017/2/20.
 */
public class CustomShapeView extends View {
    private static int NORMAL_WIGHT;
    private static int NORMAL_HEIGHT;

    private int mMargin;
    private int mCircleRadius;
    private int mSquareWidth;

    private Paint mPaint;
    private RectF mOvalRectf;
    private Path mPath;

    public CustomShapeView(Context context) {
        this(context, null);
    }

    public CustomShapeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomShapeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        NORMAL_WIGHT = DisplayUtil.dip2px(getContext(), 100);
        NORMAL_HEIGHT = DisplayUtil.dip2px(getContext(), 80);
        mMargin = DisplayUtil.dip2px(getContext(), 10);
        mSquareWidth = DisplayUtil.dip2px(getContext(), 20);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mOvalRectf = new RectF();
        mPath = new Path();
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

        mPaint.setColor(Color.parseColor("#33CCFF"));
        canvas.drawRect(0, 0, getWidth() / 4, getHeight() / 4, mPaint);

        int squareX = getWidth() / 4 + mMargin;
        mPaint.setColor(Color.parseColor("#CCCCFF"));
        canvas.drawRect(squareX, 0, squareX + mSquareWidth, mSquareWidth, mPaint);

        mPaint.setColor(Color.parseColor("#FF6600"));
        int circleX = squareX + mSquareWidth + mMargin + mCircleRadius;
        int circleY = mCircleRadius;
        mCircleRadius = getHeight() / 8;
        canvas.drawCircle(circleX, circleY, mCircleRadius, mPaint);

        mPaint.setColor(Color.parseColor("#9900FF"));
        int ovalX = circleX + mCircleRadius + mMargin;
        mOvalRectf.set(ovalX, 0, ovalX + getWidth() / 4, getHeight() / 4);
        canvas.drawOval(mOvalRectf, mPaint);

        mPaint.setColor(Color.parseColor("#FF33CC"));
        int triangleX = ovalX + getWidth() / 4 + mMargin;
        mPath.reset();
        mPath.moveTo(triangleX, 0);
        mPath.lineTo(triangleX, getHeight() / 4);
        mPath.lineTo(triangleX + getWidth() / 8, getHeight() / 4);
        mPath.close();
        canvas.drawPath(mPath, mPaint);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mPaint.setColor(Color.parseColor("#FF3399"));
            int roundRectY = getHeight() / 4 + mMargin;
            canvas.drawRoundRect(0, roundRectY, getWidth() / 4, roundRectY + getHeight() / 4, 20, 15, mPaint);
        }

    }


}

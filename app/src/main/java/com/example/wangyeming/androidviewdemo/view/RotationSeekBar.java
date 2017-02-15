package com.example.wangyeming.androidviewdemo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.wangyeming.androidviewdemo.DisplayUtil;
import com.example.wangyeming.androidviewdemo.R;

/**
 * 选择进度条
 * <p>
 * Created by wangyeming on 2017/2/14.
 */
public class RotationSeekBar extends LinearLayout implements SeekBar.OnSeekBarChangeListener {

    private static final float DEFAULT_MIN_VALUE = 0f;
    private static final float DEFAULT_MAX_VALUE = 180f;
    private static final int DEFAULT_INIT_PROGRESS = 0;

    private TextView vTitle;
    private SeekBar vSeekbar;
    private TextView vValue;

    private float maxValue = DEFAULT_MAX_VALUE;
    private float minValue = DEFAULT_MIN_VALUE;

    private float initMaxValue = DEFAULT_MAX_VALUE;
    private float initMinValue = DEFAULT_MIN_VALUE;
    private int initInitProgress = DEFAULT_INIT_PROGRESS;

    private OnProgressChangeListener mListener;

    private boolean mListenerOnChange = true;

    public RotationSeekBar(Context context) {
        this(context, null);
    }

    public RotationSeekBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RotationSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        LayoutInflater.from(getContext()).inflate(R.layout.view_rotation_seek_bar, this, true);
        vTitle = (TextView) findViewById(R.id.title);
        vSeekbar = (SeekBar) findViewById(R.id.rotation_bar);
        vValue = (TextView) findViewById(R.id.value);
        if (attrs != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.RotationSeekBar);
            setTitle(typedArray.getString(R.styleable.RotationSeekBar_title));
            initMaxValue = typedArray.getFloat(R.styleable.RotationSeekBar_maxValue, DEFAULT_MAX_VALUE);
            initMinValue = typedArray.getFloat(R.styleable.RotationSeekBar_minValue, DEFAULT_MIN_VALUE);
            setValue(initMaxValue, initMinValue);
            initInitProgress = typedArray.getInt(R.styleable.RotationSeekBar_initProgress, DEFAULT_INIT_PROGRESS);
            setInitProgress(initInitProgress);
            setValue(initInitProgress);
            typedArray.recycle();
        }
        vSeekbar.setOnSeekBarChangeListener(this);

        LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                DisplayUtil.dip2px(getContext(), 40));
        setLayoutParams(layoutParams);

        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);
    }

    public void setTitle(String title) {
        vTitle.setText(title);
    }

    public void setValue(float maxValue, float minValue) {
        this.maxValue = maxValue;
        this.minValue = minValue;
    }

    public void setInitProgress(int progress) {
        if (progress >= 0 && progress <= 100) {
            vSeekbar.setProgress(progress);
        }
    }

    public float setValue(int progress) {
        float percent = (maxValue - minValue) / 100f;
        float value = minValue + percent * (float) progress;
        vValue.setText(String.valueOf(value));
        return value;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        float value = setValue(progress);
        if (mListener != null && mListenerOnChange) {
            mListener.onProgressChange(value);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        float value = setValue(seekBar.getProgress());
        if (mListener != null && !mListenerOnChange) {
            mListener.onProgressChange(value);
        }
    }

    public void setOnProgressChangeListener(OnProgressChangeListener listener) {
        mListener = listener;
    }

    public void setOnProgressChangeListener(OnProgressChangeListener listener, boolean listenerOnChange) {
        mListener = listener;
        mListenerOnChange = listenerOnChange;
    }

    public void reset() {
        vSeekbar.setProgress(initInitProgress);
        onStopTrackingTouch(vSeekbar);
    }
}

package com.example.wangyeming.androidviewdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wangyeming.androidviewdemo.view.OnProgressChangeListener;
import com.example.wangyeming.androidviewdemo.view.RotationSeekBar;

/**
 * Demo for View method, for instance
 * setRotation(float rotation), setRotationX(float rotationX), setRotationY(float rotationY),
 * setScaleX(float scaleX), setScaleY(float scaleY),
 * setTranslationX(float translationX), setTranslationY(float translationY),
 * setPivotX(float pivotX), setPivotY(float pivotY), setCameraDistance(float distance)
 *
 * Created by wangyeming on 2017/2/14.
 */
public class ViewPositionDemoActivity extends AppCompatActivity {

    private ViewGroup vDisplay;
    private ImageView vStrip;
    private TextView vInfo;
    private ImageView vPivotPoint;

    private CheckBox vShowInfo;
    private View vReset;
    private RotationSeekBar vRotation;
    private RotationSeekBar vRotationX;
    private RotationSeekBar vRotationY;
    private RotationSeekBar vScaleX;
    private RotationSeekBar vScaleY;
    private RotationSeekBar vTranslationX;
    private RotationSeekBar vTranslationY;
    private RotationSeekBar vPrvotX;
    private RotationSeekBar vPrvotY;
    private RotationSeekBar vCameraDistance;

    private int mStripLeft;
    private int mStripTop;

    private float mScale;
    private int mCenterPointSize;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotation);

        mCenterPointSize = DisplayUtil.dip2px(this, 2.5f);
        mScale = getResources().getDisplayMetrics().density;

        vDisplay = (ViewGroup) findViewById(R.id.display_group);
        vStrip = (ImageView) findViewById(R.id.strip);
        vInfo = (TextView) findViewById(R.id.info);
        vShowInfo = (CheckBox) findViewById(R.id.info_toggle);
        vReset = findViewById(R.id.reset);
        vRotation = (RotationSeekBar) findViewById(R.id.rotation);
        vRotationX = (RotationSeekBar) findViewById(R.id.rotation_x);
        vRotationY = (RotationSeekBar) findViewById(R.id.rotation_y);
        vScaleX = (RotationSeekBar) findViewById(R.id.scale_x);
        vScaleY = (RotationSeekBar) findViewById(R.id.scale_y);
        vTranslationX = (RotationSeekBar) findViewById(R.id.translation_x);
        vTranslationY = (RotationSeekBar) findViewById(R.id.translation_y);
        vCameraDistance = (RotationSeekBar) findViewById(R.id.camera_distance);
        vPrvotX = (RotationSeekBar) findViewById(R.id.prvot_x);
        vPrvotY = (RotationSeekBar) findViewById(R.id.prvot_y);

        vShowInfo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                vInfo.setVisibility(isChecked ? View.VISIBLE : View.INVISIBLE);
            }
        });

        vStrip.post(new Runnable() {
            @Override
            public void run() {
                vPrvotX.setValue(vStrip.getPivotX() + 100, vStrip.getPivotX() - 100);
                vPrvotX.setValue(50);
                vPrvotY.setValue(vStrip.getPivotY() + 100, vStrip.getPivotY() - 100);
                vPrvotY.setValue(50);

                float realCameraDistance = vStrip.getCameraDistance();
                vInfo.setText(getString(R.string.info, vStrip.getPivotX(), vStrip.getPivotY(), mScale, realCameraDistance / mScale));
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                mStripLeft = vStrip.getLeft();
                mStripTop = vStrip.getTop();
                vPivotPoint = new ImageView(ViewPositionDemoActivity.this);
                vPivotPoint.setImageResource(R.drawable.highlight_spot);
                vPivotPoint.setLayoutParams(layoutParams);
                vPivotPoint.setX(vStrip.getPivotX() + mStripLeft - mCenterPointSize);
                vPivotPoint.setY(vStrip.getPivotY() + mStripTop - mCenterPointSize);
                vDisplay.addView(vPivotPoint);
            }
        });

        vReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vRotation.reset();
                vRotationX.reset();
                vRotationY.reset();
                vScaleX.reset();
                vScaleY.reset();
                vTranslationX.reset();
                vTranslationY.reset();
                vPrvotX.reset();
                vPrvotY.reset();
                vCameraDistance.reset();
            }
        });

        vRotation.setOnProgressChangeListener(new OnProgressChangeListener() {
            @Override
            public void onProgressChange(float value) {
                vStrip.setRotation(value);
            }
        });

        vRotationX.setOnProgressChangeListener(new OnProgressChangeListener() {
            @Override
            public void onProgressChange(float value) {
                vStrip.setRotationX(value);
            }
        });

        vRotationY.setOnProgressChangeListener(new OnProgressChangeListener() {
            @Override
            public void onProgressChange(float value) {
                vStrip.setRotationY(value);
            }
        });

        vScaleX.setOnProgressChangeListener(new OnProgressChangeListener() {
            @Override
            public void onProgressChange(float value) {
                vStrip.setScaleX(value);
            }
        });

        vScaleY.setOnProgressChangeListener(new OnProgressChangeListener() {
            @Override
            public void onProgressChange(float value) {
                vStrip.setScaleY(value);
            }
        });

        vTranslationX.setOnProgressChangeListener(new OnProgressChangeListener() {
            @Override
            public void onProgressChange(float value) {
                vStrip.setTranslationX(value);
            }
        });

        vTranslationY.setOnProgressChangeListener(new OnProgressChangeListener() {
            @Override
            public void onProgressChange(float value) {
                vStrip.setTranslationY(value);
            }
        });

        vPrvotX.setOnProgressChangeListener(new OnProgressChangeListener() {
            @Override
            public void onProgressChange(float value) {
                vStrip.setPivotX(value);
                vPivotPoint.setX(value + mStripLeft - mCenterPointSize);
            }
        });

        vPrvotY.setOnProgressChangeListener(new OnProgressChangeListener() {
            @Override
            public void onProgressChange(float value) {
                vStrip.setPivotY(value);
                vPivotPoint.setY(value + mStripTop - mCenterPointSize);
            }
        });

        vCameraDistance.setOnProgressChangeListener(new OnProgressChangeListener() {
            @Override
            public void onProgressChange(float value) {
                vStrip.setCameraDistance(value * mScale);
                float realCameraDistance = vStrip.getCameraDistance();
                vInfo.setText(getString(R.string.info, vStrip.getPivotX(), vStrip.getPivotY(), mScale, realCameraDistance / mScale));
            }
        }, false);

    }
}

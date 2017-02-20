package com.example.wangyeming.androidviewdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void gotoRotationDemo(View view) {
        Intent intent = new Intent(this, ViewPositionDemoActivity.class);
        startActivity(intent);
    }

    public void gotoCanvasAndPaintDemo(View view) {
        Intent intent = new Intent(this, CanvasAndPaintDemoActivity.class);
        startActivity(intent);
    }
}

package com.onlylemi.colorid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity implements PreviewSurface.OnColorListener {

    private static final String TAG = "MainActivity";

    private CircleCrossView crossView; // 颜色环
    private PreviewSurface previewSurface; //camera预览视图

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "onCreate");

//        crossView = (CircleCrossView) findViewById(R.id.cross_view);
//        Log.i(TAG, "crossView");
//        crossView.setColor(Color.GREEN);
//        crossView.refresh();
//        Log.i(TAG, "refresh");

        crossView = (CircleCrossView) findViewById(R.id.cross_view);
        previewSurface = (PreviewSurface) findViewById(R.id.preview_surface);

        //设置颜色识别的监听器
        previewSurface.setOnColorListener(this);
    }

    @Override
    public void onColor(int color) {
        crossView.setColor(color);
        crossView.refresh();
    }
}

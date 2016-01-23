package com.onlylemi.colorid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";
    private CircleCrossView crossView;

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
    }
}

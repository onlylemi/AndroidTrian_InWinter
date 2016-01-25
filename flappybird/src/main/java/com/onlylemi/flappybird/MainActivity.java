package com.onlylemi.flappybird;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.onlylemi.game.GameSurface;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        setContentView(new GameSurface(this));
    }
}

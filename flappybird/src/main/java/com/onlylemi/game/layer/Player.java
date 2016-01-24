package com.onlylemi.game.layer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.onlylemi.flappybird.R;
import com.onlylemi.game.GameSurface;
import com.onlylemi.utils.Assist;

/**
 * Player
 *
 * @author: onlylemi
 * @time: 2016-01-24 10:01
 */
public class Player extends BaseLayer {

    private float x, y;
    private float radius;

    private float speed;
    private float acc;

    public Player(GameSurface surface) {
        super(surface);

        x = screenW / 2;
        y = screenH / 2;

        radius = 60;
        speed = 15;
        acc = 4;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(Assist.getColor(res, R.color.Black));

        canvas.drawCircle(x, y, radius, paint);
    }

    @Override
    public void logic() {
        y += speed;
        speed += acc;
    }

    @Override
    public void onTouchEvent(MotionEvent event) {
        speed -= 20;
    }

    @Override
    public void onKeyDown(int keyCode, KeyEvent event) {

    }
}

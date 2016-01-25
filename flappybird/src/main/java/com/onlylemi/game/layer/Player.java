package com.onlylemi.game.layer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.onlylemi.flappybird.R;
import com.onlylemi.game.GameSurface;
import com.onlylemi.utils.Assist;
import com.onlylemi.utils.Constants;

/**
 * Player
 *
 * @author: onlylemi
 * @time: 2016-01-24 10:01
 */
public class Player extends BaseLayer {

    private float playerX, playerY;
    private float radius;

    private float speed;
    private float acc;

    public Player(GameSurface surface) {
        super(surface);

        playerX = screenW / 3;
        playerY = screenH / 2;

        radius = 60;
        speed = 20;
        acc = 4;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(Assist.getColor(res, R.color.Black));
        switch (surface.getGameState()) {
            case Constants.GAME_START:
                canvas.drawCircle(screenW / 3, screenH / 2, radius, paint);
                break;
            case Constants.GAMING:
                canvas.drawCircle(playerX, playerY, radius, paint);
                break;
            case Constants.GAME_END:
                canvas.drawCircle(screenW / 3, screenH / 2, radius, paint);
                break;
            default:
                break;
        }
    }

    @Override
    public void logic() {
        playerY += speed;
        speed += acc;

        // 与 上/下 边界碰撞
        if (playerY - radius <= 0 || playerY + radius >= screenH) {
            surface.setGameState(Constants.GAME_END); // 游戏结束
        }

        if (speed >= 50){
            speed = 20;
        }
        if(speed <= -50){
            speed = -20;
        }

    }

    @Override
    public void onTouchEvent(MotionEvent event) {
        speed = -25;
    }

    @Override
    public void onKeyDown(int keyCode, KeyEvent event) {

    }

    public float getPlayerX() {
        return playerX;
    }

    public float getPlayerY() {
        return playerY;
    }

    public float getRadius() {
        return radius;
    }
}

package com.onlylemi.game.layer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.onlylemi.flappybird.R;
import com.onlylemi.game.GameSurface;
import com.onlylemi.utils.Assist;
import com.onlylemi.utils.Constants;

import java.util.Random;

/**
 * Barrier
 *
 * @author: onlylemi
 * @time: 2016-01-24 10:03
 */
public class Barrier extends BaseLayer {


    private static final String TAG = "Barrier";

    private float spaceH; // 障碍的间隙
    private float distance; //障碍间的距离
    private float barrierW; // 障碍的宽
    private float barrierY; // 障碍的宽

    private float speed; // 障碍移动的速度

    private float barrier1X; // 第一个障碍的x、y
    private float barrier1H; // 第一个障碍的h

    private float barrier2X; // 第二个障碍的x、y
    private float barrier2H; // 第二个障碍的h

    private float playerX, playerY; //主角的x,y
    private float radius; // 主角的半径


    public Barrier(GameSurface surface) {
        super(surface);

        spaceH = screenH / 3;
        barrierW = 130;
        distance = screenW / 2 - barrierW / 2;
        barrierY = 0;
        speed = 15;

        barrier1X = screenW + 200;
        barrier1H = getBarrierRandomH();

        barrier2X = barrier1X + distance + barrierW;
        barrier2H = getBarrierRandomH();


        Log.i(TAG, "spaceH:" + spaceH);
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(Assist.getColor(res, R.color.SpringGreen));

        // 第一个 上
        canvas.drawRect(barrier1X, barrierY, barrier1X + barrierW, barrierY + barrier1H, paint);
        // 第一个 下
        canvas.drawRect(barrier1X, barrier1H + spaceH, barrier1X + barrierW, screenH,
                paint);

        // 第二个 上
        canvas.drawRect(barrier2X, barrierY, barrier2X + barrierW, barrierY + barrier2H, paint);
        // 第二个 下
        canvas.drawRect(barrier2X, barrier2H + spaceH, barrier2X + barrierW, screenH,
                paint);
    }

    @Override
    public void logic() {
        barrier1X -= speed;
        barrier2X -= speed;

        if (barrier1X + barrierW <= 0) {
            barrier1X = screenW;
            barrier1H = getBarrierRandomH();
        }

        if (barrier2X + barrierW <= 0) {
            barrier2X = screenW;
            barrier2H = getBarrierRandomH();
        }

        // 主角 与 障碍的碰撞检测
        boolean isColl1 = circleAndRect(playerX, playerY, radius, barrier1X, barrierY, barrierW,
                barrier1H);
        boolean isColl2 = circleAndRect(playerX, playerY, radius, barrier1X, barrier1H + spaceH,
                barrierW, screenH - barrier1H - spaceH);
        boolean isColl3 = circleAndRect(playerX, playerY, radius, barrier2X, barrierY, barrierW,
                barrier2H);
        boolean isColl4 = circleAndRect(playerX, playerY, radius, barrier2X, barrier2H + spaceH,
                barrierW, screenH - barrier2H - spaceH);

        if (isColl1 || isColl2 || isColl3 || isColl4) {
            surface.setGameState(Constants.GAME_END); // 游戏结束
        }
    }

    @Override
    public void onTouchEvent(MotionEvent event) {

    }

    @Override
    public void onKeyDown(int keyCode, KeyEvent event) {

    }

    private float getBarrierRandomH() {
        return new Random().nextInt((int) (screenH - spaceH - 50));
    }

    public void setPlayerX(float playerX) {
        this.playerX = playerX;
    }
    public void setPlayerY(float playerY) {
        this.playerY = playerY;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    /**
     * 圆与矩形间的碰撞检测
     *
     * @param circleX
     * @param circleY
     * @param circleR
     * @param rectX
     * @param rectY
     * @param rectW
     * @param rectH
     * @return
     */
    private boolean circleAndRect(float circleX, float circleY, float circleR, float rectX, float
            rectY, float rectW, float rectH) {
        if (circleX + circleR < rectX) {
            return false;
        } else if (circleX - circleR > rectX + rectW) {
            return false;
        } else if (circleY + circleR < rectY) {
            return false;
        } else if (circleY - circleR > rectY + rectH) {
            return false;
        } else if (Math.pow(rectX - circleX, 2) + Math.pow(rectY - circleY, 2) > circleR *
                circleR && circleX < rectX && circleY < rectX) {
            return false;
        } else if (Math.pow(rectX + rectW - circleX, 2) + Math.pow(rectY - circleY, 2) > circleR *
                circleR && circleX > rectX + rectW && circleY < rectY) {
            return false;
        } else if (Math.pow(rectX - circleX, 2) + Math.pow(rectY + rectH - circleY, 2) > circleR *
                circleR && circleX < rectX && circleY > rectY + rectH) {
            return false;
        } else if (Math.pow(rectX + rectW - circleX, 2) + Math.pow(rectY + rectH - circleY, 2) >
                circleR * circleR && circleX > rectX + rectW && circleY > rectY + rectH) {
            return false;
        }

        return true;
    }
}

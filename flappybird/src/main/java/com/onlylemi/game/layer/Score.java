package com.onlylemi.game.layer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.onlylemi.flappybird.R;
import com.onlylemi.game.GameSurface;
import com.onlylemi.utils.Assist;
import com.onlylemi.utils.Constants;

/**
 * Score
 *
 * @author: onlylemi
 * @time: 2016-01-24 10:04
 */
public class Score extends BaseLayer {

    private float scoreX, scoreY;

    private int scoreMax;

    private int score;

    private long startTime; // gaming 刚开始时间

    private boolean isStart;

    public Score(GameSurface surface) {
        super(surface);

        score = 0;

        scoreX = screenW / 2;
        scoreY = screenH / 3;

        isStart = true;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(Color.BLUE);
        paint.setTextSize(100);

        switch (surface.getGameState()) {
            case Constants.GAME_START:
                canvas.drawText(scoreMax + "s", scoreX, scoreY, paint);
                break;
            case Constants.GAMING:
                canvas.drawText(score + "s", scoreX, scoreY, paint);
                break;
            default:
                break;
        }
    }

    @Override
    public void logic() {
        if (isStart) {
            startTime = System.currentTimeMillis();
            isStart = false;
        }
        long endTime = System.currentTimeMillis();

        score = (int) ((endTime - startTime) / 1000);

        // 保存最高分
        if (score > scoreMax) {
            surface.setScoreMax(score);
        }
    }

    @Override
    public void onTouchEvent(MotionEvent event) {

    }

    @Override
    public void onKeyDown(int keyCode, KeyEvent event) {

    }

    public void setScoreMax(int scoreMax) {
        this.scoreMax = scoreMax;
    }

    public int getScoreMax() {
        return scoreMax;
    }
}

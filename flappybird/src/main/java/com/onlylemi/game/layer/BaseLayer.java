package com.onlylemi.game.layer;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.onlylemi.game.GameSurface;

/**
 * BaseLayer
 *
 * @author: onlylemi
 * @time: 2016-01-24 10:12
 */
public abstract class BaseLayer {

    protected GameSurface surface;

    protected Resources res;

    /**
     * 当前surface视图的宽
     */
    protected int screenW;
    /**
     * 当前surface视图的高
     */
    protected int screenH;

    public BaseLayer(GameSurface surface) {
        this.surface = surface;

        this.screenW = surface.getWidth();
        this.screenH = surface.getHeight();

        res = surface.getResources();
    }

    /**
     * 画图
     *
     * @param canvas 画布
     * @param paint  画笔
     */
    public abstract void draw(Canvas canvas, Paint paint);

    /**
     * 逻辑
     */
    public abstract void logic();

    /**
     * 触摸事件
     *
     * @param event
     */
    public abstract void onTouchEvent(MotionEvent event);

    /**
     * 按键点击事件
     *
     * @param keyCode
     * @param event
     */
    public abstract void onKeyDown(int keyCode, KeyEvent event);

}

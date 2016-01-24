package com.onlylemi.colorid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * CircleCrossView
 *
 * @author: onlylemi
 * @time: 2016-01-23 8:19
 */
public class CircleCrossView extends SurfaceView implements SurfaceHolder.Callback {

    public final static String TAG = "CircleCrossView";

    private SurfaceHolder holder;

    private Canvas canvas;
    private Paint paint;

    private int color;

    public CircleCrossView(Context context) {
        super(context);
        init();
    }

    public CircleCrossView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        Log.i(TAG, "init");

        holder = getHolder();
        holder.addCallback(this);
        holder.setFormat(PixelFormat.TRANSPARENT);
        setKeepScreenOn(true);
        setZOrderOnTop(true);

        paint = new Paint();
        paint.setAntiAlias(true);

        color = Color.RED;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.i(TAG, "surfaceCreated");
        refresh();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    /**
     * 画图
     *
     * @param canvas
     */
    private void myDraw(Canvas canvas) {
        // 绘制背景
//        paint.setColor(Color.WHITE);
//        canvas.drawRect(0, 0, getWidth(), getHeight(), paint);
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);

        canvas.save();
        canvas.translate(getWidth() / 2, getHeight() / 2); //设置坐标系为view中心
        paint.setStyle(Paint.Style.STROKE); //设置画笔不填充
        paint.setColor(color);
        paint.setStrokeWidth(15);

        float radius = getHeight() / 4;
        canvas.drawCircle(0, 0, radius, paint); //画圆

        paint.setStrokeWidth(4);
        canvas.drawLine(0, paint.getStrokeWidth() / 2, 0, -radius, paint); //画上线
        canvas.drawLine(-paint.getStrokeWidth() / 2, 0, radius, 0, paint); //画右线
        canvas.restore();
    }

    /**
     * 逻辑
     */
    private void logic() {

    }

    /**
     * 刷新界面
     */
    public void refresh() {
        canvas = holder.lockCanvas();
        if (null != canvas) {
            myDraw(canvas);
            holder.unlockCanvasAndPost(canvas);
        }
        logic();
    }

    public void setColor(int color) {
        this.color = color;
    }
}

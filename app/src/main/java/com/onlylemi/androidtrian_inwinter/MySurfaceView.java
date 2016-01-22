package com.onlylemi.androidtrian_inwinter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Random;

/**
 * MySurfaceView
 *
 * @author: only乐秘
 * @time: 2016-01-22 10:17
 */
public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    public final static String TAG = "MySurfaceView";

    private SurfaceHolder holder;

    private Canvas canvas;
    private Paint paint;

    private Thread thread;
    private boolean flag;

    private float x, y;
    private float speedX, speedY;
    private float radius;
    private int color;

    public MySurfaceView(Context context) {
        super(context);
        init();
    }

    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        holder = getHolder();
        holder.addCallback(this);

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
    }

    /**
     * 初始化游戏
     */
    private void initGame() {
        x = 0;
        y = 0;
        speedX = 10;
        speedY = 20;
        radius = 50;
        color = Color.RED;
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.i(TAG, "surfaceCreated");

        initGame();

        flag = true;

        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.i(TAG, "surfaceChanged");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.i(TAG, "surfaceDestroyed");
        flag = false;
    }

    /**
     * 画图
     *
     * @param canvas
     */
    private void myDraw(Canvas canvas) {
        paint.setColor(Color.WHITE);
        canvas.drawRect(0, 0, getWidth(), getHeight(), paint);

        paint.setColor(color);
        canvas.drawCircle(x, y, radius, paint);
    }

    /**
     * 逻辑
     */
    private void logic() {
        x += speedX;
        y += speedY;

        if (x >= getWidth() || x < 0) {
            speedX = -speedX;
        }
        if (y >= getHeight() || y < 0) {
            speedY = -speedY;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        int[] colors = new int[]{Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW};

        color = colors[new Random().nextInt(colors.length)];

        radius = new Random().nextInt(30) + 50;

        return super.onTouchEvent(event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void run() {
        while (flag) {
            long start = System.currentTimeMillis(); //开始时间

            canvas = holder.lockCanvas(); //加锁
            if (null != canvas) {
                myDraw(canvas);
                holder.unlockCanvasAndPost(canvas); //解锁
            }
            logic();

            long end = System.currentTimeMillis(); //结束时间

            if (end - start < 50) {
                try {
                    Thread.sleep(50 - (end - start));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

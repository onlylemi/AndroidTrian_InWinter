package com.onlylemi.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.onlylemi.game.layer.Background;
import com.onlylemi.game.layer.Player;
import com.onlylemi.game.layer.Start;
import com.onlylemi.utils.Constants;

/**
 * GameSurface
 *
 * @author: onlylemi
 * @time: 2016-01-24 9:28
 */
public class GameSurface extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    public final static String TAG = "GameSurface";

    private int gameState; //游戏当前的状态

    private SurfaceHolder holder;

    private Canvas canvas;
    private Paint paint;

    private Thread thread;
    private boolean flag;

    //游戏图层
    private Background background; //背景
    private Player player; //主角
    private Start start; //开始

    public GameSurface(Context context) {
        super(context);
        init();
    }

    public GameSurface(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        holder = getHolder();
        holder.addCallback(this);
        setKeepScreenOn(true);

        paint = new Paint();
        paint.setAntiAlias(true);
    }

    /**
     * 初始化游戏
     */
    private void initGame() {
        gameState = Constants.GAME_START; //设置初始状态为 游戏开始

        background = new Background(this);
        player = new Player(this);
        start = new Start(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        initGame();

        flag = true;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        flag = false;
    }


    /**
     * 画图
     *
     * @param canvas
     */
    private void myDraw(Canvas canvas) {
        background.draw(canvas, paint);

        switch (gameState) {
            case Constants.GAME_START:
                player.draw(canvas, paint);
                start.draw(canvas, paint);
                break;
            case Constants.GAMING:
                player.draw(canvas, paint);
                break;
            case Constants.GAME_END:

                break;
            default:
                break;
        }
    }

    /**
     * 逻辑
     */
    private void logic() {
        switch (gameState) {
            case Constants.GAME_START:

                break;
            case Constants.GAMING:
                player.logic();
                break;
            case Constants.GAME_END:

                break;
            default:
                break;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (gameState) {
            case Constants.GAME_START:
                start.onTouchEvent(event);
                break;
            case Constants.GAMING:
                player.onTouchEvent(event);
                break;
            case Constants.GAME_END:

                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void run() {
        while (flag) {
            long start = System.currentTimeMillis();

            canvas = holder.lockCanvas();
            if (null != canvas) {
                myDraw(canvas);
                holder.unlockCanvasAndPost(canvas);
            }
            logic();

            long end = System.currentTimeMillis();

            if (end - start < 50) {
                try {
                    Thread.sleep(50 - (end - start));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public int getGameState() {
        return gameState;
    }

    public void setGameState(int gameState) {
        this.gameState = gameState;
    }
}

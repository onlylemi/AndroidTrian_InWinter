package com.onlylemi.androidtrian_inwinter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * MyView
 *
 * @author: onlylemi
 * @time: 2016-01-22 8:21
 */
public class MyView extends View {

    private Paint paint;

    private int x, y;

    public MyView(Context context) {
        super(context);
        init();
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        paint = new Paint();

        paint.setColor(Color.RED); // 设置画笔的颜色
        paint.setAntiAlias(true); // 设置是否抗锯齿

        x = 600;
        y = 600;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        paint.setStrokeWidth(15);
        canvas.drawLine(0, 0, x, y, paint);

        canvas.drawCircle(500, 100, 100, paint);


//        drawTest(canvas);

//        drawPath(canvas);

//        drawBitmap(canvas);
    }

    /**
     * 基础绘图
     *
     * @param canvas 画布
     */
    private void drawTest(Canvas canvas) {
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE); //设置画笔的style
        paint.setStrokeWidth(15); //设置画笔的宽
        // 画线
        canvas.drawLine(0, 0, getWidth(), getHeight(), paint);

        // 画圆
        canvas.drawCircle(200, 100, 100, paint);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.GREEN);
        canvas.drawCircle(200, 100, 100, paint);

        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(500, 100, 100, paint);

        // 画矩形
        paint.setColor(Color.BLUE);
        canvas.drawRect(100, 500, 200, 600, paint);
    }

    /**
     * 路径绘制
     *
     * @param canvas
     */
    private void drawPath(Canvas canvas) {
        Path path = new Path();
        path.moveTo(100, 100); //初始点
        path.lineTo(100, 300);
        path.lineTo(200, 250);

        paint.setColor(Color.RED);
        canvas.drawPath(path, paint);

        Path path1 = new Path();
        path1.addCircle(500, 500, 180, Path.Direction.CCW);
//        path1.addCircle(500, 500, 200, Path.Direction.CW);
        path1.moveTo(500, 300);
        path1.lineTo(500, 700);
        path1.moveTo(300, 500);
        path1.lineTo(700, 500);

        paint.setColor(Color.YELLOW);
        paint.setStrokeWidth(10);
        //paint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path1, paint);
    }

    /**
     * 画图片
     */
    private void drawBitmap(Canvas canvas) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);

        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(10);

        //canvas.drawBitmap(bitmap, 500, 500, paint);
        canvas.drawBitmap(bitmap, 0, 0, paint);

        canvas.save();
        canvas.translate(getWidth() / 2, getHeight() / 2); //设置坐标系的原点的位置
        canvas.drawCircle(0, 0, 20, paint);
        canvas.drawLine(0, -getHeight() / 2, 0, getHeight() / 2, paint);
        canvas.drawLine(-getWidth() / 2, 0, getWidth(), 0, paint);
        canvas.drawBitmap(bitmap, 0, 0, paint);
        canvas.restore();

//        canvas.save();
        canvas.rotate(60);
        canvas.drawBitmap(bitmap, 0, 0, paint);
//        canvas.restore();

//        canvas.drawCircle(200, 200, 50, paint);
        canvas.drawRect(200, 200, 300, 300, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        x = (int) event.getX();
        y = (int) event.getY();

        invalidate();

        return super.onTouchEvent(event);
    }
}

package com.onlylemi.colorid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * PreviewSurface
 *
 * @author: onlylemi
 * @time: 2016-01-23 10:48
 */
public class PreviewSurface extends CameraSurface implements Camera.PreviewCallback {

    public PreviewSurface(Context context) {
        super(context);
    }

    public PreviewSurface(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        super.surfaceCreated(holder);
        camera.setPreviewCallback(this);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        super.surfaceChanged(holder, format, width, height);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        camera.setPreviewCallback(null);
        super.surfaceDestroyed(holder);
    }

    @Override
    public void onPreviewFrame(byte[] data, Camera camera) {
        // camera的尺寸
        Camera.Size size = camera.getParameters().getPreviewSize();
        // 把data转换为yuvimage
        YuvImage image = new YuvImage(data, ImageFormat.NV21, size.width, size.height, null);
        // 新建一个输出流对象
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        if (null != image) {
            // 转换为jpeg
            image.compressToJpeg(new Rect(0, 0, size.width, size.height), 100, outputStream);
            try {
                outputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Bitmap bitmap = BitmapFactory.decodeStream(new ByteArrayInputStream(outputStream
                .toByteArray()));
        int color = bitmap.getPixel(size.width / 2, size.height / 2);

        Log.i(TAG, "color:" + color);
    }


}

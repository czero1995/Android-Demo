package com.example.czero.jannote;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.LinearLayout;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by zake on 4/12/16.
 */
public class Draw extends SurfaceView implements Callback {

    private Paint paint = new Paint();
    private Path path = new Path();
    private LinearLayout layout;
    private Context context;
    private Button clear;
    private Canvas cacheCanvas;
    private Bitmap cachebBitmap;


    public Draw(Context context, AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);
        paint.setColor(Color.BLUE);
        paint.setTextSize(10);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        cachebBitmap=Bitmap.createBitmap(480,800, Bitmap.Config.ARGB_8888);
        cacheCanvas = new Canvas(cachebBitmap);
    }

    public void draw() {
        Canvas canvas = getHolder().lockCanvas();
        canvas.drawColor(Color.WHITE);
        canvas.drawPath(path, paint);
        getHolder().unlockCanvasAndPost(canvas);
        canvas.drawBitmap(cachebBitmap,0,0,null);
    }

    public void clear() {
        path.reset();
        draw();
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        draw();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch ((event.getAction())) {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(event.getX(), event.getY());
                draw();
                break;

            case MotionEvent.ACTION_MOVE:
                path.lineTo(event.getX(), event.getY());
               draw();
                break;
        }
        return true;
    }
//    public void saveToFile(String filename) throws FileNotFoundException {
//        File f = new File(filename);
//        if(f.exists())
//            throw new RuntimeException("文件：" + filename + " 已存在！");
//
//        FileOutputStream fos = new FileOutputStream(new File(filename));
//        //将 bitmap 压缩成其他格式的图片数据
//        cachebBitmap.compress(Bitmap.CompressFormat.PNG, 50, fos);
//        try {
//            fos.close();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }

}

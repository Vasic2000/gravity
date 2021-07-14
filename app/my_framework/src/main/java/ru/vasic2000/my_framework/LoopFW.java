package ru.vasic2000.my_framework;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class LoopFW extends SurfaceView implements Runnable {
    private final float FPS = 60;
    private final float SECOND = 1000000000;
    private final float UPDATE_TIME = SECOND/FPS;

    private boolean runing = false;

    Thread gameThread = null;
    CoreFW coreFW;
    Bitmap frameBuffer;
    SurfaceHolder surfaceHolder;
    Canvas canvas;
    Rect rect;

    public LoopFW(CoreFW coreFW, Bitmap frameBuffer) {
        super(coreFW);
        this.frameBuffer = frameBuffer;
        this.coreFW = coreFW;
        this.surfaceHolder = getHolder();
        rect = new Rect();
        canvas = new Canvas();
    }

//    TEMP
    float nowTime;
    float updates = 0;
    float drawings = 0;
    long timer = 0;

//    TEMP

    @Override
    public void run() {
        float lastTime = System.nanoTime();
        float delta = 0;
        float elapsedTime;
        timer = System.currentTimeMillis();

        while (runing) {
            nowTime = System.nanoTime();
            elapsedTime = nowTime - lastTime;
            lastTime = nowTime;
            delta += elapsedTime / UPDATE_TIME;
            if (delta > 1) {
                updateGame();
                drawingGame();
                delta--;
            }

            if ((System.currentTimeMillis() - timer) > 1000) {
                System.out.println("UPDATES = " + updates + ", DRAWINGS " + drawings);
                timer += 1000;
                updates = 0;
                drawings = 0;
            }
        }
    }

    private void updateGame() {
        updates++;
        coreFW.getCurrentScene().update();
    }

    private void drawingGame() {
        drawings++;
        if(surfaceHolder.getSurface().isValid()) {
            canvas = surfaceHolder.lockCanvas();
            canvas.getClipBounds(rect);
            canvas.drawBitmap(frameBuffer, null, rect, null);
            coreFW.getCurrentScene().drawing();
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    public void startGame() {
        if(runing) return;
        else {
            runing = true;
            gameThread = new Thread(this);
            gameThread.start();
        }
    }

    public void stopGame() {
        if(!runing) return;
        else {
            runing = false;
            try {
                gameThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

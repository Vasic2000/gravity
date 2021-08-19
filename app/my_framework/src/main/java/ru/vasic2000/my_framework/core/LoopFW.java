package ru.vasic2000.my_framework.core;

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

    private Thread mGameThread = null;
    private CoreFW mCoreFW;
    private Bitmap mFrameBuffer;
    private SurfaceHolder mSurfaceHolder;
    private Canvas mCanvas;
    private Rect mRect;

    public LoopFW(CoreFW coreFW, Bitmap frameBuffer) {
        super(coreFW);
        this.mFrameBuffer = frameBuffer;
        this.mCoreFW = coreFW;
        this.mSurfaceHolder = getHolder();
        mRect = new Rect();
        mCanvas = new Canvas();
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
        mCoreFW.getCurrentScene().update();
    }

    private void drawingGame() {
        drawings++;
        if(mSurfaceHolder.getSurface().isValid()) {
            mCanvas = mSurfaceHolder.lockCanvas();
            mCanvas.getClipBounds(mRect);
            mCanvas.drawBitmap(mFrameBuffer, null, mRect, null);
            mCoreFW.getCurrentScene().drawing();
            mSurfaceHolder.unlockCanvasAndPost(mCanvas);
        }
    }

    public void startGame() {
        if(runing) return;
        else {
            runing = true;
            mGameThread = new Thread(this);
            mGameThread.start();
        }
    }

    public void stopGame() {
        if(!runing) return;
        else {
            runing = false;
            try {
                mGameThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

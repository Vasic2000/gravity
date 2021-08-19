package ru.vasic2000.my_framework.core;

import android.view.MotionEvent;
import android.view.View;

public class TouchListenerFW implements View.OnTouchListener {

    private float mTouchX;
    private float mTouchY;

    private boolean mIsTouchDown, mIsTouchUp;
    private float mSceneWidth, mSceneHeight;

    public TouchListenerFW(View view, float sceneWidth, float sceneHeight) {
        view.setOnTouchListener(this);
        this.mSceneWidth = sceneWidth;
        this.mSceneHeight = sceneHeight;
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {

        synchronized (this) {
            mIsTouchDown = false;
            mIsTouchUp = false;
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mTouchX = event.getX() * mSceneWidth;
                    mTouchY = event.getY() * mSceneHeight;
                    mIsTouchDown = true;
                    mIsTouchUp = false;
                    break;
                case MotionEvent.ACTION_UP:
                    mTouchX = event.getX() * mSceneWidth;
                    mTouchY = event.getY() * mSceneHeight;
                    mIsTouchDown = false;
                    mIsTouchUp = true;
                    break;
            }
        }
        return true;
    }

    public boolean getTuchDown(int x, int y, int touchWidth, int touchHeight) {
        if(mIsTouchDown) {
            if(mTouchX >= x && mTouchX <= (x + touchWidth - 1) &&
                    mTouchY <= y && mTouchY >= (y - touchHeight + 1)) {
                mIsTouchDown = false;
                return true;
            }
        }
        return false;
    }

    public boolean getTuchUp(int x, int y, int touchWidth, int touchHeight) {
        if(mIsTouchUp) {
            if(mTouchX >= x && mTouchX <= (x + touchWidth - 1) &&
                    mTouchY <= y && mTouchY >= (y - touchHeight + 1)) {
                mIsTouchUp = false;
                return true;
            }
        }
        return false;
    }
}

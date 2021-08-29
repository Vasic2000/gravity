package ru.vasic2000.my_framework.core;

import android.graphics.Bitmap;

public class Animation_9_Frames {
    private double mSpeedAnimation;
    private int mDelayIndex;
    private int mCountFrame;
    private int mFrames;

    private Bitmap mSprite;
    private Bitmap mSprite1;
    private Bitmap mSprite2;
    private Bitmap mSprite3;
    private Bitmap mSprite4;
    private Bitmap mSprite5;
    private Bitmap mSprite6;
    private Bitmap mSprite7;
    private Bitmap mSprite8;
    private Bitmap mSprite9;

    public Animation_9_Frames(double speedAnimation,
                              Bitmap sprite1,
                              Bitmap sprite2,
                              Bitmap sprite3,
                              Bitmap sprite4,
                              Bitmap sprite5,
                              Bitmap sprite6,
                              Bitmap sprite7,
                              Bitmap sprite8,
                              Bitmap sprite9) {

        mSprite = sprite1;

        this.mSpeedAnimation = speedAnimation;
        this.mSprite1 = sprite1;
        this.mSprite2 = sprite2;
        this.mSprite3 = sprite3;
        this.mSprite4 = sprite4;
        this.mSprite5 = sprite5;
        this.mSprite6 = sprite6;
        this.mSprite7 = sprite7;
        this.mSprite8 = sprite8;
        this.mSprite9 = sprite9;
        mFrames = 9;
    }

    public void runAnimation() {
        mDelayIndex++;
        if(mDelayIndex > mSpeedAnimation) {
            mDelayIndex = 0;
            nextFrame();
        }
    }

    private void nextFrame() {
        switch (mCountFrame) {
            case 0:
                mSprite = mSprite1;
                break;

            case 1:
                mSprite = mSprite2;
                break;

            case 2:
                mSprite = mSprite3;
                break;

            case 3:
                mSprite = mSprite4;
                break;

            case 4:
                mSprite = mSprite5;
                break;

            case 5:
                mSprite = mSprite6;
                break;

            case 6:
                mSprite = mSprite7;
                break;

            case 7:
                mSprite = mSprite8;
                break;

            case 8:
                mSprite = mSprite9;
                break;
        }

        mCountFrame++;
        if(mCountFrame > mFrames) {
            mCountFrame = 0;
        }
    }

    public void drawAnimation(GraphicsFW graphicsFW, int x, int y) {
        graphicsFW.drawTexture(mSprite, x, y);
    }
}

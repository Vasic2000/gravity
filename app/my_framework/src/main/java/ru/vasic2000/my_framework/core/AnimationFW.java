package ru.vasic2000.my_framework.core;

import android.graphics.Bitmap;

public class AnimationFW {
    private double mSpeedAnimation;
    private int mDelayIndex;
    private int mCountFrame;
    private int mFrames;

    private Bitmap mSprite;
    private Bitmap mSprite1;
    private Bitmap mSprite2;
    private Bitmap mSprite3;
    private Bitmap mSprite4;

    public AnimationFW(double speedAnimation,
                       Bitmap sprite1,
                       Bitmap sprite2,
                       Bitmap sprite3,
                       Bitmap sprite4) {

        mSprite = sprite1;

        this.mSpeedAnimation = speedAnimation;
        this.mSprite1 = sprite1;
        this.mSprite2 = sprite2;
        this.mSprite3 = sprite3;
        this.mSprite4 = sprite4;
        mFrames = 4;
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

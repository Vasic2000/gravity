package ru.vasic2000.my_framework;

import android.graphics.Bitmap;

public class AnimationFW {
    double speedAnimation;
    int delayIndex;
    int countFrame;
    int frames;

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

        this.speedAnimation = speedAnimation;
        this.mSprite1 = sprite1;
        this.mSprite2 = sprite2;
        this.mSprite3 = sprite3;
        this.mSprite4 = sprite4;
        frames = 4;
    }

    public void runAnimation() {
        delayIndex++;
        if(delayIndex > speedAnimation) {
            delayIndex = 0;
            nextFrame();
        }
    }

    private void nextFrame() {
        switch (countFrame) {
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

        countFrame++;
        if(countFrame>frames) {
            countFrame = 0;
        }
    }

    public void drawAnimation(GraphicsFW graphicsFW, int x, int y) {
        graphicsFW.drawTexture(mSprite, x, y);
    }
}

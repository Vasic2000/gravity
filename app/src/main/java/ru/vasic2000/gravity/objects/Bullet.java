package ru.vasic2000.gravity.objects;

import android.graphics.Rect;

import ru.vasic2000.gravity.classes.GameManager;
import ru.vasic2000.gravity.utilites.UtilResourse;
import ru.vasic2000.my_framework.core.Animation_4_Frames;
import ru.vasic2000.my_framework.core.GraphicsFW;
import ru.vasic2000.my_framework.core.ObjectFW;
import ru.vasic2000.my_framework.utils.UtilRandomFW;

public class Bullet extends ObjectFW {

    private Animation_4_Frames mAnimProtector;

    public Bullet(int maxScreenX, int playerX, int playerY) {
        init(maxScreenX, playerX, playerY);

        pHitBox = new Rect(pX, pY,
                UtilResourse.sSpriteBullet.get(0).getWidth(),
                UtilResourse.sSpriteBullet.get(0).getHeight());

        mAnimProtector = new Animation_4_Frames(GameManager.SPEED_ANIMATION, UtilResourse.sSpriteBullet.get(0),
                UtilResourse.sSpriteBullet.get(1),
                UtilResourse.sSpriteBullet.get(2),
                UtilResourse.sSpriteBullet.get(3));
    }

    private void init(int maxScreenX, int playerX, int playerY) {
        this.pMaxScreenX = maxScreenX + UtilResourse.sSpriteProtector.get(0).getWidth();

        pSpeed = 16;

        pX = playerX + 10;
        pY = playerY;

        pRadius = (float) UtilResourse.sSpriteBullet.get(0).getWidth() / 2;
    }

    public void update(double playerSpeed) {
        pX += pSpeed;
        pX -= playerSpeed;

        if(pX > pMaxScreenX) {

        }

        mAnimProtector.runAnimation();

        pHitBox = new Rect(pX, pY,
                UtilResourse.sSpriteProtector.get(0).getWidth(),
                UtilResourse.sSpriteProtector.get(0).getHeight());
    }

    public void drawing(GraphicsFW graphicsFW){
        mAnimProtector.drawAnimation(graphicsFW, pX, pY);
    }

}

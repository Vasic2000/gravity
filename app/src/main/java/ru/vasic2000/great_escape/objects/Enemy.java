package ru.vasic2000.great_escape.objects;

import static ru.vasic2000.great_escape.classes.GameManager.SPEED_ANIMATION;

import android.graphics.Rect;

import ru.vasic2000.great_escape.utilites.UtilResourse;
import ru.vasic2000.my_framework.core.Animation_9_Frames;
import ru.vasic2000.my_framework.core.GraphicsFW;
import ru.vasic2000.my_framework.core.ObjectFW;
import ru.vasic2000.my_framework.utils.UtilRandomFW;

public class Enemy extends ObjectFW {

    private Animation_9_Frames mAnimEnemy;

    public Enemy(int maxScreenX, int maxScreenY, int minScreenY, int enemyType) {
        init(maxScreenX, maxScreenY, minScreenY);
        initTypeEnamy(enemyType);
    }

    private void init(int maxScreenX, int maxScreenY, int minScreenY) {
        this.pMaxScreenX = maxScreenX;
        this.pMaxScreenY = maxScreenY - UtilResourse.sSpriteEnemy1.get(0).getHeight();
        this.pMinScreenY = minScreenY;
        this.pMinScreenX = - UtilResourse.sSpriteEnemy1.get(0).getWidth();

        pX = maxScreenX + UtilRandomFW.getGap(0, maxScreenX /3);
        pY = UtilRandomFW.getGap(minScreenY, maxScreenY - UtilResourse.sSpriteEnemy1.get(0).getHeight());

        pRadius = UtilResourse.sSpriteEnemy1.get(0).getWidth() / 4;
    }

    private void initTypeEnamy(int enemyType) {
        switch (enemyType) {
            case 1 :
                pSpeed = UtilRandomFW.getGap(1,4);
                mAnimEnemy = new Animation_9_Frames(SPEED_ANIMATION, UtilResourse.sSpriteEnemy1.get(0),
                        UtilResourse.sSpriteEnemy1.get(1),
                        UtilResourse.sSpriteEnemy1.get(2),
                        UtilResourse.sSpriteEnemy1.get(3),
                        UtilResourse.sSpriteEnemy1.get(4),
                        UtilResourse.sSpriteEnemy1.get(5),
                        UtilResourse.sSpriteEnemy1.get(6),
                        UtilResourse.sSpriteEnemy1.get(7),
                        UtilResourse.sSpriteEnemy1.get(8));
                break;
            case 2 :
                pSpeed = UtilRandomFW.getGap(4,6);
                mAnimEnemy = new Animation_9_Frames(SPEED_ANIMATION, UtilResourse.sSpriteEnemy2.get(0),
                        UtilResourse.sSpriteEnemy2.get(1),
                        UtilResourse.sSpriteEnemy2.get(2),
                        UtilResourse.sSpriteEnemy2.get(3),
                        UtilResourse.sSpriteEnemy2.get(4),
                        UtilResourse.sSpriteEnemy2.get(5),
                        UtilResourse.sSpriteEnemy2.get(6),
                        UtilResourse.sSpriteEnemy2.get(7),
                        UtilResourse.sSpriteEnemy2.get(8));
                break;
            case 3 :
                pSpeed = UtilRandomFW.getGap(6,8);
                mAnimEnemy = new Animation_9_Frames(SPEED_ANIMATION, UtilResourse.sSpriteEnemy3.get(0),
                        UtilResourse.sSpriteEnemy3.get(1),
                        UtilResourse.sSpriteEnemy3.get(2),
                        UtilResourse.sSpriteEnemy3.get(3),
                        UtilResourse.sSpriteEnemy3.get(4),
                        UtilResourse.sSpriteEnemy3.get(5),
                        UtilResourse.sSpriteEnemy3.get(6),
                        UtilResourse.sSpriteEnemy3.get(7),
                        UtilResourse.sSpriteEnemy3.get(8));
                break;
        }

    }

    public void update(double playerSpeed, int mPassedDistance) {
        pX -= pSpeed;
        pX -= playerSpeed;
        if(pX < pMinScreenX) {
            changeEnamy(mPassedDistance);
        }
        mAnimEnemy.runAnimation();
        pHitBox = new Rect(pX, pY,
                UtilResourse.sSpriteEnemy1.get(0).getWidth(),
                UtilResourse.sSpriteEnemy1.get(0).getWidth());
    }

    public void drawing(GraphicsFW graphicsFW){
        mAnimEnemy.drawAnimation(graphicsFW, pX, pY);
    }

    public void hitPlayer(int mPassedDistance) {
        changeEnamy(mPassedDistance);
    }

    private void changeEnamy(int mPassedDistance) {
        pX = pMaxScreenX + UtilRandomFW.getGap(0, pMaxScreenX / 3);
        pY = UtilRandomFW.getGap(pMinScreenY, pMaxScreenY);

        if(mPassedDistance > 15000) {
            initTypeEnamy(UtilRandomFW.getGap(1,3));
            pSpeed = UtilRandomFW.getGap(6,8);
        } else if(mPassedDistance > 7000) {
            initTypeEnamy(UtilRandomFW.getGap(1,2));
            pSpeed = UtilRandomFW.getGap(4,7);
        } else {
            initTypeEnamy(1);
            pSpeed = UtilRandomFW.getGap(1,4);
        }
    }
}

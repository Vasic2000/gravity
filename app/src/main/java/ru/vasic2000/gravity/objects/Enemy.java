package ru.vasic2000.gravity.objects;

import android.graphics.Rect;

import ru.vasic2000.gravity.classes.GameManager;
import ru.vasic2000.gravity.utilites.UtilResourse;
import ru.vasic2000.my_framework.core.AnimationFW;
import ru.vasic2000.my_framework.core.GraphicsFW;
import ru.vasic2000.my_framework.core.ObjectFW;
import ru.vasic2000.my_framework.utils.UtilRandomFW;

public class Enemy extends ObjectFW {

    private AnimationFW mAnimEnemy;

    public Enemy(int maxScreenX, int maxScreenY, int minScreenY, int enemyType) {
        init(maxScreenX, maxScreenY, minScreenY);
        initTypeEnamy(enemyType);
    }

    private void init(int maxScreenX, int maxScreenY, int minScreenY) {
        this.pMaxScreenX = maxScreenX;
        this.pMaxScreenY = maxScreenY - UtilResourse.sSpriteEnemy.get(0).getHeight();
        this.pMinScreenY = minScreenY;
        this.pMinScreenX = -UtilResourse.sSpriteEnemy.get(0).getWidth();

        pX = maxScreenX + UtilRandomFW.getGap(0, maxScreenX /3);
        pY = UtilRandomFW.getGap(minScreenY, maxScreenY);

        pRadius = UtilResourse.sSpriteEnemy.get(0).getWidth() / 4;
    }

    private void initTypeEnamy(int enemyType) {
        switch (enemyType) {
            case 1 :
                pSpeed = UtilRandomFW.getGap(1,5);
                mAnimEnemy = new AnimationFW(GameManager.SPEED_ANIMATION, UtilResourse.sSpriteEnemy.get(0),
                        UtilResourse.sSpriteEnemy.get(1),
                        UtilResourse.sSpriteEnemy.get(2),
                        UtilResourse.sSpriteEnemy.get(3));
                break;
            case 2 :
                pSpeed = UtilRandomFW.getGap(4,9);
                break;
        }
    }

    public void update(double playerSpeed) {
        pX -= pSpeed;
        pX -= playerSpeed;
        if(pX < pMinScreenX) {
            pX = pMaxScreenX + UtilRandomFW.getGap(0, pMaxScreenX / 3);
            pY = UtilRandomFW.getGap(pMinScreenY, pMaxScreenY - UtilResourse.sSpriteEnemy.get(0).getHeight());
            pSpeed = UtilRandomFW.getGap(1,5);
        }
        mAnimEnemy.runAnimation();
        pHitBox = new Rect(pX, pY,
                UtilResourse.sSpriteEnemy.get(0).getWidth(),
                UtilResourse.sSpriteEnemy.get(0).getWidth());
    }

    public void drawing(GraphicsFW graphicsFW){
        mAnimEnemy.drawAnimation(graphicsFW, pX, pY);
    }

    public void hitPlayer() {
        pX = pMaxScreenX + UtilRandomFW.getGap(0, pMaxScreenX / 3);
        pY = UtilRandomFW.getGap(pMinScreenY, pMaxScreenY - UtilResourse.sSpriteEnemy.get(0).getHeight());
        pSpeed = UtilRandomFW.getGap(1,5);
    }
}

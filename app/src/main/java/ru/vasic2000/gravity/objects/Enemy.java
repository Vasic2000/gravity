package ru.vasic2000.gravity.objects;

import android.graphics.Rect;

import ru.vasic2000.gravity.classes.GameManager;
import ru.vasic2000.gravity.utilites.UtilResourse;
import ru.vasic2000.my_framework.AnimationFW;
import ru.vasic2000.my_framework.GraphicsFW;
import ru.vasic2000.my_framework.ObjectFW;
import ru.vasic2000.my_framework.utils.UtilRandomFW;

public class Enemy extends ObjectFW {

    private AnimationFW mAnimEnemy;

    public Enemy(int maxScreenX, int maxScreenY, int minScreenY, int enemyType) {
        init(maxScreenX, maxScreenY, minScreenY);
        initTypeEnamy(enemyType);
    }

    private void init(int maxScreenX, int maxScreenY, int minScreenY) {
        this.maxScreenX = maxScreenX;
        this.maxScreenY = maxScreenY - UtilResourse.sSpriteEnemy.get(0).getHeight();
        this.minScreenY = minScreenY;
        this.minScreenX = -UtilResourse.sSpriteEnemy.get(0).getWidth();

        x = maxScreenX + UtilRandomFW.getGap(0, maxScreenX /3);
        y = UtilRandomFW.getGap(minScreenY, maxScreenY);

        radius = UtilResourse.sSpriteEnemy.get(0).getWidth() / 4;
    }

    private void initTypeEnamy(int enemyType) {
        switch (enemyType) {
            case 1 :
                speed = UtilRandomFW.getGap(1,5);
                mAnimEnemy = new AnimationFW(GameManager.SPEED_ANIMATION, UtilResourse.sSpriteEnemy.get(0),
                        UtilResourse.sSpriteEnemy.get(1),
                        UtilResourse.sSpriteEnemy.get(2),
                        UtilResourse.sSpriteEnemy.get(3));
                break;
            case 2 :
                speed = UtilRandomFW.getGap(4,9);
                break;
        }
    }

    public void update(double playerSpeed) {
        x -= speed;
        x -= playerSpeed;
        if(x < minScreenX) {
            x = maxScreenX + UtilRandomFW.getGap(0, maxScreenX / 3);
            y = UtilRandomFW.getGap(minScreenY, maxScreenY - UtilResourse.sSpriteEnemy.get(0).getHeight());
            speed = UtilRandomFW.getGap(1,5);
        }
        mAnimEnemy.runAnimation();
        hitBox = new Rect(x,y,
                UtilResourse.sSpriteEnemy.get(0).getWidth(),
                UtilResourse.sSpriteEnemy.get(0).getWidth());
    }

    public void drawing(GraphicsFW graphicsFW){
        mAnimEnemy.drawAnimation(graphicsFW, x, y);
    }

    public void hitPlayer() {
        x = maxScreenX + UtilRandomFW.getGap(0, maxScreenX / 3);
        y = UtilRandomFW.getGap(minScreenY, maxScreenY - UtilResourse.sSpriteEnemy.get(0).getHeight());
        speed = UtilRandomFW.getGap(1,5);
    }
}

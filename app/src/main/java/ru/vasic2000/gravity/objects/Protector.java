package ru.vasic2000.gravity.objects;

import android.graphics.Rect;

import ru.vasic2000.gravity.classes.GameManager;
import ru.vasic2000.gravity.utilites.UtilResourse;
import ru.vasic2000.my_framework.AnimationFW;
import ru.vasic2000.my_framework.GraphicsFW;
import ru.vasic2000.my_framework.ObjectFW;
import ru.vasic2000.my_framework.utils.UtilRandomFW;

public class Protector extends ObjectFW {

    private AnimationFW mAnimProtector;

    public Protector(int maxScreenX, int maxScreenY, int minScreenY) {
        init(maxScreenX, maxScreenY, minScreenY);

        hitBox = new Rect(x, y,
                UtilResourse.sSpriteProtector.get(0).getWidth(),
                UtilResourse.sSpriteProtector.get(0).getHeight());

        mAnimProtector = new AnimationFW(GameManager.SPEED_ANIMATION, UtilResourse.sSpriteProtector.get(0),
                UtilResourse.sSpriteProtector.get(1),
                UtilResourse.sSpriteProtector.get(2),
                UtilResourse.sSpriteProtector.get(3));
    }

    private void init(int maxScreenX, int maxScreenY, int minScreenY) {
        this.maxScreenX = maxScreenX;
        this.maxScreenY = maxScreenY - UtilResourse.sSpriteProtector.get(0).getHeight();
        this.minScreenY = minScreenY;
        this.minScreenX = 0 - UtilResourse.sSpriteProtector.get(0).getWidth();

        x = maxScreenX + UtilRandomFW.getGap(0, maxScreenX /3);
        y = UtilRandomFW.getGap(minScreenY, maxScreenY);

        radius = UtilResourse.sSpriteProtector.get(0).getWidth() / 2;
    }

    public void update(double playerSpeed) {
        x -= speed;
        x -= playerSpeed;

        if(x < minScreenX) {
            x = maxScreenX + UtilRandomFW.getGap(0, maxScreenX / 3);
            y = UtilRandomFW.getGap(minScreenY, maxScreenY - UtilResourse.sSpriteProtector.get(0).getHeight());
            speed = UtilRandomFW.getGap(1,5);
        }

        mAnimProtector.runAnimation();

        hitBox = new Rect(x,y,
                UtilResourse.sSpriteProtector.get(0).getWidth(),
                UtilResourse.sSpriteProtector.get(0).getWidth());
    }

    public void drawing(GraphicsFW graphicsFW){
        mAnimProtector.drawAnimation(graphicsFW, x, y);
    }

    public void hitPlayer() {
        x = maxScreenX + UtilRandomFW.getGap(0, maxScreenX / 3);
        y = UtilRandomFW.getGap(minScreenY, maxScreenY - UtilResourse.sSpriteEnemy.get(0).getHeight());
        speed = UtilRandomFW.getGap(1,5);
    }

}

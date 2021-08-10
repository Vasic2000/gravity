package ru.vasic2000.gravity.objects;

import android.graphics.Rect;

import ru.vasic2000.gravity.classes.GameManager;
import ru.vasic2000.gravity.utilites.UtilResourse;
import ru.vasic2000.my_framework.AnimationFW;
import ru.vasic2000.my_framework.GraphicsFW;
import ru.vasic2000.my_framework.ObjectFW;
import ru.vasic2000.my_framework.utils.UtilRandomFW;

public class Protector extends ObjectFW {

    AnimationFW animProtector;

    public Protector(int maxScreenX, int maxScreenY, int minScreenY) {
        this.maxScreenX = maxScreenX;
        this.maxScreenY = maxScreenY - UtilResourse.spriteProtector.get(0).getHeight();
        this.minScreenY = minScreenY;
        this.minScreenX = 0 - UtilResourse.spriteProtector.get(0).getWidth();

        x = maxScreenX + UtilRandomFW.getGap(0, maxScreenX/3);
        y = UtilRandomFW.getGap(minScreenY, maxScreenY);

        radius = UtilResourse.spriteProtector.get(0).getWidth() / 2;

        hitBox = new Rect(x, y,
                UtilResourse.spriteProtector.get(0).getWidth(),
                UtilResourse.spriteProtector.get(0).getHeight());

        animProtector = new AnimationFW(GameManager.SPEED_ANIMATION, UtilResourse.spriteProtector.get(0),
                UtilResourse.spriteProtector.get(1),
                UtilResourse.spriteProtector.get(2),
                UtilResourse.spriteProtector.get(3));
    }

    public void update(double playerSpeed) {
        x -= speed;
        x -= playerSpeed;

        if(x < minScreenX) {
            x = maxScreenX + UtilRandomFW.getGap(0, maxScreenX / 3);
            y = UtilRandomFW.getGap(minScreenY, maxScreenY - UtilResourse.spriteProtector.get(0).getHeight());
            speed = UtilRandomFW.getGap(1,5);
        }

        animProtector.runAnimation();

        hitBox = new Rect(x,y,
                UtilResourse.spriteProtector.get(0).getWidth(),
                UtilResourse.spriteProtector.get(0).getWidth());
    }

    public void drawing(GraphicsFW graphicsFW){
        animProtector.drawAnimation(graphicsFW, x, y);
    }

    public void hitPlayer() {
        x = maxScreenX + UtilRandomFW.getGap(0, maxScreenX / 3);
        y = UtilRandomFW.getGap(minScreenY, maxScreenY - UtilResourse.spriteEnemy.get(0).getHeight());
        speed = UtilRandomFW.getGap(1,5);
    }

}

package ru.vasic2000.gravity.objects;

import ru.vasic2000.gravity.utilites.UtilResourse;
import ru.vasic2000.my_framework.AnimationFW;
import ru.vasic2000.my_framework.GraphicsFW;
import ru.vasic2000.my_framework.ObjectFW;
import ru.vasic2000.my_framework.utils.UtilRandomFW;

public class Enemy extends ObjectFW {

    AnimationFW animEnemy;

    public Enemy(int maxScreenX, int maxScreenY, int minScreenY, int enemyType) {
        this.maxScreenX = maxScreenX;
        this.maxScreenY = maxScreenY - UtilResourse.spriteEnemy.get(0).getHeight();
        this.minScreenY = minScreenY;
        this.minScreenX = 0 - UtilResourse.spriteEnemy.get(0).getWidth();

        x = maxScreenX + UtilRandomFW.getGap(0, maxScreenX/3);
        y = UtilRandomFW.getGap(minScreenY, maxScreenY);

        switch (enemyType) {
            case 1 :
                speed = UtilRandomFW.getGap(1,5);
                animEnemy = new AnimationFW(3, UtilResourse.spriteEnemy.get(0),
                        UtilResourse.spriteEnemy.get(1),
                        UtilResourse.spriteEnemy.get(2),
                        UtilResourse.spriteEnemy.get(3));
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
            y = UtilRandomFW.getGap(minScreenY, maxScreenY);
            speed = UtilRandomFW.getGap(1,5);
        }
        animEnemy.runAnimation();
    }

    public void drawing(GraphicsFW graphicsFW){
        animEnemy.drawAnimation(graphicsFW, x, y);
    }
}

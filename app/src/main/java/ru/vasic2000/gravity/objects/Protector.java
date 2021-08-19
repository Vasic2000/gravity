package ru.vasic2000.gravity.objects;

import android.graphics.Rect;

import ru.vasic2000.gravity.classes.GameManager;
import ru.vasic2000.gravity.utilites.UtilResourse;
import ru.vasic2000.my_framework.core.AnimationFW;
import ru.vasic2000.my_framework.core.GraphicsFW;
import ru.vasic2000.my_framework.core.ObjectFW;
import ru.vasic2000.my_framework.utils.UtilRandomFW;

public class Protector extends ObjectFW {

    private AnimationFW mAnimProtector;

    public Protector(int maxScreenX, int maxScreenY, int minScreenY) {
        init(maxScreenX, maxScreenY, minScreenY);

        pHitBox = new Rect(pX, pY,
                UtilResourse.sSpriteProtector.get(0).getWidth(),
                UtilResourse.sSpriteProtector.get(0).getHeight());

        mAnimProtector = new AnimationFW(GameManager.SPEED_ANIMATION, UtilResourse.sSpriteProtector.get(0),
                UtilResourse.sSpriteProtector.get(1),
                UtilResourse.sSpriteProtector.get(2),
                UtilResourse.sSpriteProtector.get(3));
    }

    private void init(int maxScreenX, int maxScreenY, int minScreenY) {
        this.pMaxScreenX = maxScreenX;
        this.pMaxScreenY = maxScreenY - UtilResourse.sSpriteProtector.get(0).getHeight();
        this.pMinScreenY = minScreenY;
        this.pMinScreenX = 0 - UtilResourse.sSpriteProtector.get(0).getWidth();

        pX = maxScreenX + UtilRandomFW.getGap(0, maxScreenX /3);
        pY = UtilRandomFW.getGap(minScreenY, maxScreenY);

        pRadius = UtilResourse.sSpriteProtector.get(0).getWidth() / 2;
    }

    public void update(double playerSpeed) {
        pX -= pSpeed;
        pX -= playerSpeed;

        if(pX < pMinScreenX) {
            pX = pMaxScreenX + UtilRandomFW.getGap(0, pMaxScreenX / 3);
            pY = UtilRandomFW.getGap(pMinScreenY, pMaxScreenY - UtilResourse.sSpriteProtector.get(0).getHeight());
            pSpeed = UtilRandomFW.getGap(1,5);
        }

        mAnimProtector.runAnimation();

        pHitBox = new Rect(pX, pY,
                UtilResourse.sSpriteProtector.get(0).getWidth(),
                UtilResourse.sSpriteProtector.get(0).getWidth());
    }

    public void drawing(GraphicsFW graphicsFW){
        mAnimProtector.drawAnimation(graphicsFW, pX, pY);
    }

    public void hitPlayer() {
        pX = pMaxScreenX + UtilRandomFW.getGap(0, pMaxScreenX / 3);
        pY = UtilRandomFW.getGap(pMinScreenY, pMaxScreenY - UtilResourse.sSpriteEnemy.get(0).getHeight());
        pSpeed = UtilRandomFW.getGap(1,5);
    }

}

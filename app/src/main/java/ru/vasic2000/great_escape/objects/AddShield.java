package ru.vasic2000.great_escape.objects;

import android.graphics.Rect;

import ru.vasic2000.great_escape.classes.GameManager;
import ru.vasic2000.great_escape.utilites.UtilResourse;
import ru.vasic2000.my_framework.core.Animation_4_Frames;
import ru.vasic2000.my_framework.core.GraphicsFW;
import ru.vasic2000.my_framework.core.ObjectFW;
import ru.vasic2000.my_framework.utils.UtilRandomFW;

public class AddShield extends ObjectFW {
    private Animation_4_Frames mAnimAddShield;

    public AddShield(int maxScreenX, int maxScreenY, int minScreenY) {
        init(maxScreenX, maxScreenY, minScreenY);

        pHitBox = new Rect(pX, pY,
                UtilResourse.sSpriteAddShield.get(0).getWidth(),
                UtilResourse.sSpriteAddShield.get(0).getHeight());

        mAnimAddShield = new Animation_4_Frames(GameManager.SPEED_ANIMATION, UtilResourse.sSpriteAddShield.get(0),
                UtilResourse.sSpriteAddShield.get(1),
                UtilResourse.sSpriteAddShield.get(2),
                UtilResourse.sSpriteAddShield.get(3));
    }

    private void init(int maxScreenX, int maxScreenY, int minScreenY) {
        this.pMaxScreenX = maxScreenX;
        this.pMaxScreenY = maxScreenY - UtilResourse.sSpriteAddShield.get(0).getHeight();
        this.pMinScreenY = minScreenY;
        this.pMinScreenX = 0 - UtilResourse.sSpriteAddShield.get(0).getWidth();

        pX = maxScreenX + UtilRandomFW.getGap(0, maxScreenX /3);
        pY = UtilRandomFW.getGap(minScreenY, maxScreenY - UtilResourse.sSpriteAddShield.get(0).getHeight());

        pRadius = UtilResourse.sSpriteAddShield.get(0).getWidth() / 2;
    }

    public void update(double playerSpeed) {
        pX -= pSpeed;
        pX -= playerSpeed;

        if(pX < pMinScreenX) {
            pX = pMaxScreenX + UtilRandomFW.getGap(0, pMaxScreenX / 3);
            pY = UtilRandomFW.getGap(pMinScreenY, pMaxScreenY - UtilResourse.sSpriteAddShield.get(0).getHeight());
            pSpeed = UtilRandomFW.getGap(1,5);
        }

        mAnimAddShield.runAnimation();

        pHitBox = new Rect(pX, pY,
                UtilResourse.sSpriteAddShield.get(0).getWidth(),
                UtilResourse.sSpriteAddShield.get(0).getWidth());
    }

    public void drawing(GraphicsFW graphicsFW){
        mAnimAddShield.drawAnimation(graphicsFW, pX, pY);
    }

}

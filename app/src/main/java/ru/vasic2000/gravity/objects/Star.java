package ru.vasic2000.gravity.objects;

import ru.vasic2000.my_framework.core.ObjectFW;
import ru.vasic2000.my_framework.utils.UtilRandomFW;

public class Star extends ObjectFW {
    public Star(int sceneWidth, int sceneHeight, int minScreenY) {
        init(sceneWidth, sceneHeight, minScreenY);
    }

    private void init(int sceneWidth, int sceneHeight, int minScreenY) {
        this.pMaxScreenX = sceneWidth;
        this.pMaxScreenY = sceneHeight;
        this.pMinScreenX = minScreenY;
        this.pMinScreenY = minScreenY;
        this.pX = UtilRandomFW.getCasualNumber(pMaxScreenX);
        this.pY = UtilRandomFW.getGap(minScreenY, pMaxScreenY);
    }

    public void update(double playerSpeed) {
        pX -=playerSpeed;
        if(pX < 0) {
            pX = pMaxScreenX;
            pY = UtilRandomFW.getGap(pMinScreenY, pMaxScreenY);
        }
    }

    public int getX() {
        return pX;
    }

    public int getY() {
        return pY;
    }
}

package ru.vasic2000.gravity.objects;

import ru.vasic2000.my_framework.ObjectFW;
import ru.vasic2000.my_framework.utils.UtilRandomFW;

public class Star extends ObjectFW {
    public Star(int sceneWidth, int sceneHeight) {
        this.maxScreenX = sceneWidth;
        this.maxScreenY = sceneHeight;
        this.minScreenX = 0;
        this.minScreenY = 0;
        this.speed = 2;
        this.x = UtilRandomFW.getCasualNumber(maxScreenX);
        this.y = UtilRandomFW.getCasualNumber(maxScreenY);
    }

    public void update() {
        x-=speed;
        if(x < 0) {
            x = maxScreenX;
            y = UtilRandomFW.getCasualNumber(maxScreenY);
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

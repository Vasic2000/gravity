package ru.vasic2000.gravity.generators;

import ru.vasic2000.gravity.objects.MainPlayer;
import ru.vasic2000.gravity.objects.Protector;
import ru.vasic2000.my_framework.GraphicsFW;
import ru.vasic2000.my_framework.utils.UtilTimerDelay;

public class GiftGenerators {

    Protector protector;
    UtilTimerDelay protectorTimer;

    private int maxScreenX;
    private int maxScreenY;
    private int minScreenX;
    private int minScreenY;

    public GiftGenerators(int sceneWidth, int sceneHeight, int minScreenY) {
        this.maxScreenX = sceneWidth;
        this.maxScreenY = sceneHeight;
        this.minScreenX = 0;
        this.minScreenY = minScreenY;

        protector = new Protector(maxScreenX, maxScreenY, minScreenY);

        protectorTimer = new UtilTimerDelay();
        protectorTimer.startTimer();
    }

    public void update(double playerSpeed) {
        if (protectorTimer.timerDelay(8) && (!MainPlayer.isShieldsOn())) {
            protector.update(playerSpeed);
            if (protector.getX() < minScreenX) {
                protector = null;
                protector = new Protector(maxScreenX, maxScreenY, minScreenY);
                protectorTimer.startTimer();
            }
        }
    }

    public void drawing(GraphicsFW graphicsFW) {
        protector.drawing(graphicsFW);
    }

    public Protector getProtector() {
        return protector;
    }

    public void receiveProtector() {
        protector = null;
        protector = new Protector(maxScreenX, maxScreenY, minScreenY);
        protectorTimer.startTimer();
    }
}

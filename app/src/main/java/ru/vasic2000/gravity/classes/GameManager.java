package ru.vasic2000.gravity.classes;

import ru.vasic2000.gravity.generators.BacgroundGenerator;
import ru.vasic2000.gravity.objects.MainPlayer;
import ru.vasic2000.my_framework.CoreFW;
import ru.vasic2000.my_framework.GraphicsFW;

public class GameManager {
    private int maxScreenX;
    private int maxScreenY;
    private int minScreenX;
    private int minScreenY;

    MainPlayer mainPlayer;
    BacgroundGenerator bacgroundGenerator;

    public GameManager(CoreFW coreFW, int sceneWidth, int sceneHeight) {
        maxScreenX = sceneWidth;
        maxScreenY = sceneHeight;
        minScreenX = 0;
        minScreenY = 0;

        mainPlayer = new MainPlayer(coreFW, maxScreenX, maxScreenY, minScreenY);

        bacgroundGenerator = new BacgroundGenerator(sceneWidth, sceneHeight);
    }

    public void update() {
        mainPlayer.update();
        bacgroundGenerator.update(mainPlayer.getPlayerSpeed());
    }

    public void drawing(CoreFW coreFW, GraphicsFW graphicsFW) {
        mainPlayer.drawing(graphicsFW);
        bacgroundGenerator.drawing(graphicsFW);
    }

}

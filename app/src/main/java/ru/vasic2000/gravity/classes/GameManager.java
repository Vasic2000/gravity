package ru.vasic2000.gravity.classes;

import ru.vasic2000.gravity.generators.BacgroundGenerator;
import ru.vasic2000.gravity.generators.EnemyGenerator;
import ru.vasic2000.gravity.objects.HUD;
import ru.vasic2000.gravity.objects.MainPlayer;
import ru.vasic2000.my_framework.CoreFW;
import ru.vasic2000.my_framework.GraphicsFW;

public class GameManager {
    private int maxScreenX;
    private int maxScreenY;
    private int minScreenX;
    private int minScreenY;

    private int passedDistaence;
    private double currentPlayerSpeed;
    private int currentPlayerShields;

    MainPlayer mainPlayer;
    BacgroundGenerator bacgroundGenerator;
    EnemyGenerator enemyGenerator;
    HUD hud;

    public GameManager(CoreFW coreFW, int sceneWidth, int sceneHeight) {
        hud = new HUD(coreFW);
        this.maxScreenX = sceneWidth;
        this.maxScreenY = sceneHeight;
        minScreenY = hud.HUD_HEIGHT;
        minScreenX = 0;

        mainPlayer = new MainPlayer(coreFW, maxScreenX, maxScreenY, minScreenY);
        bacgroundGenerator = new BacgroundGenerator(sceneWidth, sceneHeight, minScreenY);
        enemyGenerator = new EnemyGenerator(maxScreenX, maxScreenY, minScreenY);
    }

    public void update() {
        mainPlayer.update();
        bacgroundGenerator.update(mainPlayer.getPlayerSpeed());
        enemyGenerator.update(mainPlayer.getPlayerSpeed());
        passedDistaence += mainPlayer.getPlayerSpeed();
        currentPlayerShields = mainPlayer.getPlayerShields();
        currentPlayerSpeed = mainPlayer.getPlayerSpeed();

        hud.update(passedDistaence, currentPlayerSpeed, currentPlayerShields);

    }

    public void drawing(CoreFW coreFW, GraphicsFW graphicsFW) {
        mainPlayer.drawing(graphicsFW);
        bacgroundGenerator.drawing(graphicsFW);
        enemyGenerator.drawing(graphicsFW);
        hud.drawing(graphicsFW);
    }

}

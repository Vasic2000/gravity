package ru.vasic2000.gravity.classes;

import ru.vasic2000.gravity.generators.BacgroundGenerator;
import ru.vasic2000.gravity.generators.EnemyGenerator;
import ru.vasic2000.gravity.generators.GiftGenerators;
import ru.vasic2000.gravity.objects.Enemy;
import ru.vasic2000.gravity.objects.HUD;
import ru.vasic2000.gravity.objects.MainPlayer;
import ru.vasic2000.gravity.utilites.UtilResourse;
import ru.vasic2000.my_framework.CollisionsDetect;
import ru.vasic2000.my_framework.CoreFW;
import ru.vasic2000.my_framework.GraphicsFW;

public class GameManager {

    public final static double SPEED_ANIMATION = 2;

    private int maxScreenX;
    private int maxScreenY;
    private int minScreenX;
    private int minScreenY;
    private int passedDistaence;
    private double currentPlayerSpeed;
    private int currentPlayerShields;

    public static boolean gameOver;

    MainPlayer mainPlayer;
    BacgroundGenerator bacgroundGenerator;
    EnemyGenerator enemyGenerator;
    GiftGenerators giftGenerators;
    HUD hud;

    public GameManager(CoreFW coreFW, int sceneWidth, int sceneHeight) {
        hud = new HUD(coreFW);
        this.maxScreenX = sceneWidth;
        this.maxScreenY = sceneHeight;
        minScreenY = hud.HUD_HEIGHT;
        minScreenX = 0;

        gameOver = false;

        mainPlayer = new MainPlayer(coreFW, maxScreenX, maxScreenY, minScreenY);
        bacgroundGenerator = new BacgroundGenerator(sceneWidth, sceneHeight, minScreenY);
        enemyGenerator = new EnemyGenerator(maxScreenX, maxScreenY, minScreenY);
        giftGenerators = new GiftGenerators(sceneWidth, sceneHeight, minScreenY);
    }

    public void update() {
        mainPlayer.update();
        bacgroundGenerator.update(mainPlayer.getPlayerSpeed());
        enemyGenerator.update(mainPlayer.getPlayerSpeed());
        giftGenerators.update(mainPlayer.getPlayerSpeed());
        passedDistaence += mainPlayer.getPlayerSpeed();
        currentPlayerShields = mainPlayer.getPlayerShields();
        currentPlayerSpeed = mainPlayer.getPlayerSpeed();

        hud.update(passedDistaence, currentPlayerSpeed, currentPlayerShields);

        checkHit();
    }

    private void checkHit() {
        for (Enemy enemy : enemyGenerator.enemyArrayList) {
            if(CollisionsDetect.collisionDetect(mainPlayer, enemy)) {
                UtilResourse.hit.play(1);
                mainPlayer.hitEnemy();
                enemy.hitPlayer();
            }
            if(CollisionsDetect.collisionDetect(mainPlayer, giftGenerators.getProtector())) {
                takeProtector();
            }
        }
    }

    public void drawing(CoreFW coreFW, GraphicsFW graphicsFW) {
        mainPlayer.drawing(graphicsFW);
        bacgroundGenerator.drawing(graphicsFW);
        enemyGenerator.drawing(graphicsFW);
        giftGenerators.drawing(graphicsFW);
        hud.drawing(graphicsFW);
    }

    private void takeProtector() {
        mainPlayer.takeProtector();
        giftGenerators.receiveProtector();
    }

    public int getPassedDistaence() {
        return passedDistaence;
    }

}

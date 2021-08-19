package ru.vasic2000.gravity.classes;

import java.util.ArrayList;

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
    private int mPassedDistaence;
    public static boolean gameOver;

    MainPlayer mainPlayer;
    BacgroundGenerator bacgroundGenerator;
    EnemyGenerator enemyGenerator;
    GiftGenerators giftGenerators;
    HUD hud;

    public GameManager(CoreFW coreFW, int sceneWidth, int sceneHeight) {
        hud = new HUD(coreFW);
        int mMaxScreenX  = sceneWidth;
        int mMaxScreenY = sceneHeight;
        int mMinScreenY = hud.getHUD_HEIGHT();

        gameOver = false;

        mainPlayer = new MainPlayer(coreFW, mMaxScreenX, mMaxScreenY, mMinScreenY);
        bacgroundGenerator = new BacgroundGenerator(sceneWidth, sceneHeight, mMinScreenY);
        enemyGenerator = new EnemyGenerator(mMaxScreenX, mMaxScreenY, mMinScreenY);
        giftGenerators = new GiftGenerators(sceneWidth, sceneHeight, mMinScreenY);
    }

    public void update() {
        mainPlayer.update();
        bacgroundGenerator.update(mainPlayer.getPlayerSpeed());
        enemyGenerator.update(mainPlayer.getPlayerSpeed());
        giftGenerators.update(mainPlayer.getPlayerSpeed());
        mPassedDistaence += mainPlayer.getPlayerSpeed();
        int mCurrentPlayerShields = mainPlayer.getPlayerShields();
        double mCurrentPlayerSpeed = mainPlayer.getPlayerSpeed();

        hud.update(mPassedDistaence, mCurrentPlayerSpeed, mCurrentPlayerShields);

        checkHit();
    }

    private void checkHit() {
        for (Enemy enemy : enemyGenerator.getEnemyArrayList()) {
            if (CollisionsDetect.collisionDetect(mainPlayer, enemy)) {
                UtilResourse.sHit.play(1);
                mainPlayer.hitEnemy();
                enemy.hitPlayer();
            }
            if (CollisionsDetect.collisionDetect(mainPlayer, giftGenerators.getProtector())) {
                takeProtector();
            }
        }
    }

    public void drawing(GraphicsFW graphicsFW) {
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
        return mPassedDistaence;
    }

}

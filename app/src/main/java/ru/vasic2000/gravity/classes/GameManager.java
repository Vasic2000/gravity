package ru.vasic2000.gravity.classes;

import ru.vasic2000.gravity.generators.BacgroundGenerator;
import ru.vasic2000.gravity.generators.BulletGenerators;
import ru.vasic2000.gravity.generators.EnemyGenerator;
import ru.vasic2000.gravity.generators.GiftGenerators;
import ru.vasic2000.gravity.objects.Enemy;
import ru.vasic2000.gravity.objects.HUD;
import ru.vasic2000.gravity.objects.MainPlayer;
import ru.vasic2000.gravity.utilites.SettingsGame;
import ru.vasic2000.gravity.utilites.UtilResourse;
import ru.vasic2000.my_framework.utils.UtilCollisionsDetect;
import ru.vasic2000.my_framework.core.CoreFW;
import ru.vasic2000.my_framework.core.GraphicsFW;

public class GameManager {

    public enum BossState {
        LEVEL,
        BOSS,
        GAME_OVER;
    }

    public final static double SPEED_ANIMATION = 2;
    private int mPassedDistaence;
    public static boolean gameOver;

    private MainPlayer mMainPlayer;
    private BacgroundGenerator mBacgroundGenerator;
    private EnemyGenerator mEnemyGenerator;
    private GiftGenerators mGiftGenerators;
    private BulletGenerators mBulletGenerator;
    private HUD mHud;

    private BossState mBossStete;

    public GameManager(CoreFW coreFW, int sceneWidth, int sceneHeight) {
        init(coreFW, sceneWidth, sceneHeight);
    }

    private void init(CoreFW coreFW, int sceneWidth, int sceneHeight) {
        mHud = new HUD(coreFW);
        int mMaxScreenX  = sceneWidth;
        int mMaxScreenY = sceneHeight;
        int mMinScreenY = mHud.getHUD_HEIGHT();

        mPassedDistaence = 16600;

        gameOver = false;

        mMainPlayer = new MainPlayer(coreFW, mMaxScreenX, mMaxScreenY, mMinScreenY);
        mBacgroundGenerator = new BacgroundGenerator(sceneWidth, sceneHeight, mMinScreenY);
        mEnemyGenerator = new EnemyGenerator(mMaxScreenX, mMaxScreenY, mMinScreenY);
        mGiftGenerators = new GiftGenerators(sceneWidth, sceneHeight, mMinScreenY);
        mBulletGenerator = new BulletGenerators(sceneWidth, sceneWidth);

        mBossStete = BossState.LEVEL;
    }

    public void update() {
        updateObjects(mPassedDistaence, mBossStete);
        mPassedDistaence += mMainPlayer.getPlayerSpeed();
        int mCurrentPlayerShields = mMainPlayer.getPlayerShields();
        double mCurrentPlayerSpeed = mMainPlayer.getPlayerSpeed();
        mHud.update(mPassedDistaence, mCurrentPlayerSpeed, mCurrentPlayerShields);
        checkHit();
    }

    private void updateObjects(int mPassedDistaence, BossState mBossStete) {

        if(mPassedDistaence >= 17000) {
            mBossStete = BossState.BOSS;
        }

        mMainPlayer.update(mBossStete);
        mBacgroundGenerator.update(mMainPlayer.getPlayerSpeed());
        mEnemyGenerator.update(mMainPlayer.getPlayerSpeed(), mBossStete, mPassedDistaence);
        mGiftGenerators.update(mMainPlayer.getPlayerSpeed());
        mBulletGenerator.update(mMainPlayer.getPlayerSpeed(), mBossStete, mMainPlayer.getX(), mMainPlayer.getY());
    }

    private void checkHit() {
        for (Enemy enemy : mEnemyGenerator.getEnemyArrayList()) {
            if (UtilCollisionsDetect.collisionDetect(mMainPlayer, enemy)) {
                if(SettingsGame.sSoundOn) {
                    UtilResourse.sHit.play(1);
                }
                mMainPlayer.hitEnemy();
                enemy.hitPlayer(mPassedDistaence);
            }
            if (UtilCollisionsDetect.collisionDetect(mMainPlayer, mGiftGenerators.getProtector())) {
                takeProtector();
            }
            if (UtilCollisionsDetect.collisionDetect(mMainPlayer, mGiftGenerators.getShield())) {
                takeShield();
            }
        }
    }

    public void drawing(GraphicsFW graphicsFW) {
        mMainPlayer.drawing(graphicsFW);
        mBacgroundGenerator.drawing(graphicsFW);
        mEnemyGenerator.drawing(graphicsFW);
        mGiftGenerators.drawing(graphicsFW);
        mBulletGenerator.drawing(graphicsFW);
        mHud.drawing(graphicsFW);
    }

    private void takeProtector() {
        mMainPlayer.takeProtector();
        mGiftGenerators.receiveProtector();
    }

    private void takeShield() {
        mMainPlayer.takeShield();
        mGiftGenerators.receiveShield();
    }

    public int getPassedDistaence() {
        return mPassedDistaence;
    }

}

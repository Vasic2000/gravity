package ru.vasic2000.gravity.generators;

import java.util.ArrayList;
import java.util.Iterator;

import ru.vasic2000.gravity.classes.GameManager;
import ru.vasic2000.gravity.objects.Bullet;
import ru.vasic2000.gravity.utilites.UtilResourse;
import ru.vasic2000.my_framework.core.GraphicsFW;
import ru.vasic2000.my_framework.utils.UtilTimerDelay;

public class BulletGenerators {

    private Bullet bullet;
    private ArrayList<Bullet> mBulletArrayList;

    private UtilTimerDelay mBulletTimer;

    private int mMaxScreenX;

    public BulletGenerators(int sceneWidth, int maxScreenX) {
        init(sceneWidth, maxScreenX);
    }

    private void init(int sceneWidth, int maxScreenX) {
        this.mMaxScreenX = sceneWidth + UtilResourse.sSpriteBullet.get(0).getWidth();
        this.mMaxScreenX = maxScreenX + UtilResourse.sSpriteBullet.get(0).getWidth();

        mBulletArrayList = new ArrayList<>();
        mBulletTimer = new UtilTimerDelay();
        mBulletTimer.startTimer();
    }

    public void update(double playerSpeed, GameManager.BossState mBossState, int playerX, int playerY) {

        if(mBossState == GameManager.BossState.BOSS) {
            if (mBulletTimer.timerDelay(2)) {
                bullet = new Bullet(mMaxScreenX, playerX, playerY);
                mBulletArrayList.add(bullet);
                mBulletTimer.startTimer();
            }

            Iterator<Bullet> bulletIterator = mBulletArrayList.iterator();
            while (bulletIterator.hasNext()) {
                bullet = bulletIterator.next();
                bullet.update(playerSpeed);
                if (bullet.getX() > mMaxScreenX) {
                    bulletIterator.remove();
                    mBulletTimer.startTimer();
                }
            }
        }
    }

    public void drawing(GraphicsFW graphicsFW) {
        for (Bullet bullet : mBulletArrayList) {
            bullet.drawing(graphicsFW);
        }
    }
}

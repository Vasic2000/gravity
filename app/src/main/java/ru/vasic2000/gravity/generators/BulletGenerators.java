package ru.vasic2000.gravity.generators;

import java.util.ArrayList;

import ru.vasic2000.gravity.classes.GameManager;
import ru.vasic2000.gravity.objects.AddShield;
import ru.vasic2000.gravity.objects.Bullet;
import ru.vasic2000.gravity.objects.Enemy;
import ru.vasic2000.gravity.objects.MainPlayer;
import ru.vasic2000.gravity.objects.Protector;
import ru.vasic2000.gravity.utilites.UtilResourse;
import ru.vasic2000.my_framework.core.GraphicsFW;
import ru.vasic2000.my_framework.core.SceneFW;
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

    public void update(double playerSpeed, int playerX, int playerY) {

        if(mBulletTimer.timerDelay(2)) {
            bullet = new Bullet(mMaxScreenX, playerX, playerY);
            mBulletArrayList.add(bullet);
            mBulletTimer.startTimer();
        }

        for (Bullet bullet : mBulletArrayList) {
            bullet.update(playerSpeed);
            if (bullet.getX() > mMaxScreenX) {
                mBulletArrayList.remove(bullet);
                mBulletTimer.startTimer();
            }
        }
    }

    public void drawing(GraphicsFW graphicsFW) {
        for (Bullet bullet : mBulletArrayList) {
            bullet.drawing(graphicsFW);
        }
    }
}

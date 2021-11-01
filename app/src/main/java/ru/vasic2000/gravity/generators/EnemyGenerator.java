package ru.vasic2000.gravity.generators;

import java.util.ArrayList;
import java.util.Iterator;

import ru.vasic2000.gravity.classes.GameManager;
import ru.vasic2000.gravity.objects.Bullet;
import ru.vasic2000.gravity.objects.Enemy;
import ru.vasic2000.gravity.utilites.UtilResourse;
import ru.vasic2000.my_framework.core.GraphicsFW;
import ru.vasic2000.my_framework.utils.UtilRandomFW;


public class EnemyGenerator {

    private int mMaxScreenX;
    private int mMaxScreenY;
    private int mMinScreenY;

    private Enemy enemy;

    private ArrayList<Enemy> mEnemyArrayList;

    public EnemyGenerator(int sceneWidth, int sceneHeight, int minScreenY) {
        this.mMaxScreenX = sceneWidth;
        this.mMaxScreenY = sceneHeight;
        this.mMinScreenY = minScreenY;
        mEnemyArrayList = new ArrayList<>();
    }

    public void update(double playerSpeed, GameManager.BossState mBossState, int mPassedDistance) {

        if(mBossState == GameManager.BossState.LEVEL) {
            if(mEnemyArrayList.size() == 0) {
                addEnemy();
                addEnemy();
            }

            if ((mEnemyArrayList.size() < 10) && (mPassedDistance / ((2000 * mEnemyArrayList.size())) > 1)) {
                addEnemy();
            }
        }

        Iterator<Enemy> enemyIterator = mEnemyArrayList.iterator();
        while (enemyIterator.hasNext()) {
            enemy = enemyIterator.next();
            enemy.update(playerSpeed);
            if (enemy.getX() < - UtilResourse.sSpriteEnemy1.get(0).getWidth()) {
                enemyIterator.remove();
            }
        }
    }

    private void addEnemy() {
            mEnemyArrayList.add(new Enemy(mMaxScreenX,
                    mMaxScreenY, mMinScreenY, 1));
    }

    public void drawing(GraphicsFW graphicsFW) {
        for (Enemy enemy : mEnemyArrayList) {
            enemy.drawing(graphicsFW);
        }
    }

    public ArrayList<Enemy> getEnemyArrayList() {
        return mEnemyArrayList;
    }
}

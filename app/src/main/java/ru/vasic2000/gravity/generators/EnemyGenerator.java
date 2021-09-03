package ru.vasic2000.gravity.generators;

import java.util.ArrayList;

import ru.vasic2000.gravity.objects.Enemy;
import ru.vasic2000.gravity.utilites.UtilResourse;
import ru.vasic2000.my_framework.core.GraphicsFW;
import ru.vasic2000.my_framework.utils.UtilRandomFW;


public class EnemyGenerator {

    private int mMaxScreenX;
    private int mMaxScreenY;
    private int mMinScreenY;

    private ArrayList<Enemy> mEnemyArrayList;

    public EnemyGenerator(int sceneWidth, int sceneHeight, int minScreenY) {
        this.mMaxScreenX = sceneWidth;
        this.mMaxScreenY = sceneHeight;
        this.mMinScreenY = minScreenY;
        mEnemyArrayList = new ArrayList<>();
    }

    public void update(double playerSpeed, int mPassedDistance) {
        if(mEnemyArrayList.size() == 0) {
            addEnemy();
            addEnemy();
        }

        if((mEnemyArrayList.size() < 10) && (mPassedDistance / (2000 * mEnemyArrayList.size())) > 1) {
                addEnemy();
        }

        for (Enemy enemy : mEnemyArrayList) {
            enemy.update(playerSpeed, mPassedDistance);
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

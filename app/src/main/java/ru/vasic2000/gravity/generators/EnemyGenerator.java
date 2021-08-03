package ru.vasic2000.gravity.generators;

import java.util.ArrayList;

import ru.vasic2000.gravity.objects.Enemy;
import ru.vasic2000.my_framework.GraphicsFW;


public class EnemyGenerator {
    private int maxScreenX;
    private int maxScreenY;
    private int minScreenX;
    private int minScreenY;

    public ArrayList<Enemy> enemyArrayList;

    public EnemyGenerator(int sceneWidth, int sceneHeight, int minScreenY) {
        this.maxScreenX = sceneWidth;
        this.maxScreenY = sceneHeight;
        this.minScreenX = 0;
        this.minScreenY = minScreenY;
        enemyArrayList = new ArrayList<>();
    }

    public void update(double playerSpeed) {

        while(enemyArrayList.size() < 3) {
            addEnemy(playerSpeed);
        }

        for (Enemy enemy : enemyArrayList) {
            enemy.update(playerSpeed);
        }
    }


    private void addEnemy(double playerSpeed) {
        enemyArrayList.add(new Enemy(maxScreenX, maxScreenY, minScreenY, 1));
    }

    public void drawing(GraphicsFW graphicsFW) {
        for (Enemy enemy : enemyArrayList) {
            enemy.drawing(graphicsFW);
        }
    }
}

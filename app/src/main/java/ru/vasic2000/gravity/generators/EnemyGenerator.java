package ru.vasic2000.gravity.generators;

import java.util.ArrayList;

import ru.vasic2000.gravity.objects.Enemy;
import ru.vasic2000.my_framework.GraphicsFW;


public class EnemyGenerator {
    private int maxScreenX;
    private int maxScreenY;
    private int minScreenX;
    private int minScreenY;

    ArrayList<Enemy> enemyArrayList;

    public EnemyGenerator(int sceneWidth, int sceneHeight, int minScreenY) {
        this.maxScreenX = sceneWidth;
        this.maxScreenY = sceneHeight;
        this.minScreenX = 0;
        this.minScreenY = minScreenY;
        enemyArrayList = new ArrayList<>();
    }

    public void update(double playerSpeed) {
//        if(enemyArrayList.size() < 3) {
//            addEnemies(playerSpeed, 3);
//        }

        while(enemyArrayList.size() < 3) {
            addEnemy(playerSpeed);
        }
        for (Enemy enemy : enemyArrayList) {
            enemy.update(playerSpeed);
        }
    }

    private void addEnemies(double playerSpeed, int ammountEnemy) {
        for (int j = 0; j < ammountEnemy; j++) {
            enemyArrayList.add(new Enemy(maxScreenX, maxScreenY, minScreenY,1));
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

package ru.vasic2000.gravity.generators;

import android.graphics.Color;

import java.util.ArrayList;
import ru.vasic2000.gravity.objects.Star;
import ru.vasic2000.my_framework.core.GraphicsFW;

public class BacgroundGenerator {
    private ArrayList<Star> mStarArrayList = new ArrayList<Star>();


    public BacgroundGenerator(int sceneWidth, int sceneHeight, int minScreenY) {
        int starsSpeak = 50;
        for (int i = 0; i < starsSpeak; i++) {
            mStarArrayList.add(new Star(sceneWidth, sceneHeight, minScreenY));
        }
    }

    public void update(double playerSpeed) {
        for (Star star : mStarArrayList) {
            star.update(playerSpeed);
        }
    }

    public void drawing(GraphicsFW graphicsFW) {
        for (Star star : mStarArrayList) {
            graphicsFW.drawPixel(star.getX(), star.getY(), Color.WHITE);
        }
    }

}

package ru.vasic2000.gravity.generators;

import android.graphics.Color;

import java.util.ArrayList;

import ru.vasic2000.gravity.objects.Star;
import ru.vasic2000.my_framework.GraphicsFW;

public class BacgroundGenerator {
    public ArrayList<Star> starArrayList = new ArrayList<Star>();


    public BacgroundGenerator(int sceneWidth, int sceneHeight) {
        int starsSpeak = 50;
        for (int i = 0; i < starsSpeak; i++) {
            starArrayList.add(new Star(sceneWidth, sceneHeight));
        }
    }

    public void update() {
        for (Star star : starArrayList) {
            star.update();
        }
    }

    public void drawing(GraphicsFW graphicsFW) {
        for (Star star : starArrayList) {
            graphicsFW.drawPixel(star.getX(), star.getY(), Color.WHITE);
        }
    }

}

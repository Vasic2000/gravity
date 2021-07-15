package ru.vasic2000.gravity.scenes;

import android.graphics.Color;

import ru.vasic2000.my_framework.CoreFW;
import ru.vasic2000.my_framework.SceneFW;

public class GameScene extends SceneFW {

    public GameScene(CoreFW coreFW) {
        super(coreFW);
    }

    @Override
    public void update() {

    }

    @Override
    public void drawing() {
        graficsFW.clearScene(Color.BLACK);
        graficsFW.drawText("Game Scene",
                100, 150, Color.GREEN, 60,null);
    }

    @Override
    public void dispose() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }
}

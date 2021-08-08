package ru.vasic2000.gravity.scenes;

import android.graphics.Color;

import java.util.SplittableRandom;

import ru.vasic2000.gravity.R;
import ru.vasic2000.gravity.utilites.SettingsGame;
import ru.vasic2000.my_framework.CoreFW;
import ru.vasic2000.my_framework.SceneFW;

public class TopDistance extends SceneFW {

    String[] numbers = new String[5];

    public TopDistance(CoreFW coreFW) {
        super(coreFW);
        for (int i = 0; i < 5; i++) {
            this.numbers[i] = " " + (i+1) + " = " + SettingsGame.distance[i];
        }
    }

    @Override
    public void update() {
        if(coreFW.getTouchListenerFW().getTuchUp(0, sceneHeight, sceneWidth, sceneHeight)) {
            coreFW.setScene(new MainMenuScene(coreFW));
            SettingsGame.saveScore(coreFW);
        }
    }

    @Override
    public void drawing() {
        graficsFW.drawText(coreFW.getString(R.string.txt_top_distance),
                120, 200, Color.GREEN, 60, null);
        graficsFW.drawText(String.valueOf(numbers[0]),
                120, 270, Color.YELLOW, 45, null);
        graficsFW.drawText(String.valueOf(numbers[1]),
                120, 320, Color.YELLOW, 45, null);
        graficsFW.drawText(String.valueOf(numbers[2]),
                120, 370, Color.YELLOW, 45, null);
        graficsFW.drawText(String.valueOf(numbers[3]),
                120, 420, Color.YELLOW, 45, null);
        graficsFW.drawText(String.valueOf(numbers[4]),
                120, 470, Color.YELLOW, 45, null);

    }

    @Override
    public void dispose() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
        graficsFW.clearScene(Color.BLACK);
    }
}

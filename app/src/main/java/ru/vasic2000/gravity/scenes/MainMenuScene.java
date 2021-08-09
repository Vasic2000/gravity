package ru.vasic2000.gravity.scenes;

import android.graphics.Color;

import ru.vasic2000.gravity.R;
import ru.vasic2000.my_framework.CoreFW;
import ru.vasic2000.my_framework.SceneFW;

public class MainMenuScene extends SceneFW {

    public MainMenuScene(CoreFW coreFW) {
        super(coreFW);
    }

    @Override
    public void update() {
        if(coreFW.getTouchListenerFW().getTuchUp(20, 300, 250, 50)) {
            coreFW.setScene(new GameScene(coreFW));
        }
        if(coreFW.getTouchListenerFW().getTuchUp(20, 400, 145, 50)) {
            coreFW.setScene(new TopDistance(coreFW));
        }

    }

    @Override
    public void drawing() {
        graficsFW.clearScene(Color.BLACK);
        graficsFW.drawText(coreFW.getString(R.string.txt_mainMenu_nameGame),
                100, 100, Color.WHITE, 60, null);
        graficsFW.drawText(coreFW.getString(R.string.txt_mainMenu_newGame),
                20, 300, Color.WHITE, 40, null);
        graficsFW.drawText(coreFW.getString(R.string.txt_mainMenu_settings),
                20, 350, Color.WHITE, 40, null);
        graficsFW.drawText(coreFW.getString(R.string.txt_mainMenu_results),
                20, 400, Color.WHITE, 40, null);
        graficsFW.drawText(coreFW.getString(R.string.txt_mainMenu_exitGame),
                20, 450, Color.WHITE, 40, null);
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

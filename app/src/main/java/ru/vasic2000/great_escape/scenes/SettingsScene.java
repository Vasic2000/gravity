package ru.vasic2000.great_escape.scenes;

import android.graphics.Color;

import ru.vasic2000.great_escape.R;
import ru.vasic2000.great_escape.utilites.SettingsGame;
import ru.vasic2000.great_escape.utilites.UtilResourse;
import ru.vasic2000.my_framework.core.CoreFW;
import ru.vasic2000.my_framework.core.SceneFW;

class SettingsScene extends SceneFW {

    public SettingsScene(CoreFW coreFW) {
        super(coreFW);
    }

    @Override
    public void update() {
        if (pCoreFW.isKeyBackPressed()) {
            pCoreFW.setKeyBackPressed(false);
            UtilResourse.sTouch.play(1);
            pCoreFW.setScene(new MainMenuScene(pCoreFW));
        }

        if (pCoreFW.getTouchListenerFW().getTuchUp(400, 300, 100, 55)) {
            SettingsGame.sSoundOn = !SettingsGame.sSoundOn;
            UtilResourse.sTouch.play(1);
            SettingsGame.saveScore(pCoreFW);
        }

        if (pCoreFW.getTouchListenerFW().getTuchUp(400, 400, 100, 55)) {
            SettingsGame.sMusicOn = !SettingsGame.sMusicOn;
            UtilResourse.sTouch.play(1);
            SettingsGame.saveScore(pCoreFW);
        }

        if (pCoreFW.getTouchListenerFW().getTuchUp(0, 200, 800, 200) ||
                pCoreFW.getTouchListenerFW().getTuchUp(0, 0, 800, 200) ||
                pCoreFW.getTouchListenerFW().getTuchUp(0, 600, 800, 600) ||
                pCoreFW.getTouchListenerFW().getTuchUp(500, 600, 800, 600)
        ) {
            pCoreFW.setScene(new MainMenuScene(pCoreFW));
            UtilResourse.sTouch.play(1);
            SettingsGame.saveScore(pCoreFW);
        }
    }

    @Override
    public void drawing() {
        pCoreFW.getGraphicsFW().clearScene(Color.BLACK);
        pCoreFW.getGraphicsFW().drawText(pCoreFW.getString(R.string.txt_settingScene_settings),
                250, 200, Color.GREEN, 40, UtilResourse.sMainMenuFont);
        pCoreFW.getGraphicsFW().drawText(pCoreFW.getString(R.string.txt_settingScene_sound),
                250, 300, Color.GREEN, 35, UtilResourse.sMainMenuFont);
        pCoreFW.getGraphicsFW().drawText(pCoreFW.getString(R.string.txt_settingScene_music),
                250, 400, Color.GREEN, 35, UtilResourse.sMainMenuFont);

        if (SettingsGame.sSoundOn)
            pCoreFW.getGraphicsFW().drawText("ON",
                    400, 300, Color.GREEN, 35, UtilResourse.sMainMenuFont);
        else pCoreFW.getGraphicsFW().drawText("OFF",
                400, 300, Color.GRAY, 35, UtilResourse.sMainMenuFont);
        if (SettingsGame.sMusicOn)
            pCoreFW.getGraphicsFW().drawText("ON",
                    400, 400, Color.GREEN, 35, UtilResourse.sMainMenuFont);
        else pCoreFW.getGraphicsFW().drawText("OFF",
                400, 400, Color.GRAY, 35, UtilResourse.sMainMenuFont);
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

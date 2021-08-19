package ru.vasic2000.gravity.scenes;

import android.graphics.Color;

import ru.vasic2000.gravity.R;
import ru.vasic2000.my_framework.core.CoreFW;
import ru.vasic2000.my_framework.core.SceneFW;

class SettingsScene extends SceneFW {

    public SettingsScene(CoreFW coreFW) {
        super(coreFW);
    }

    @Override
    public void update() {

    }

    @Override
    public void drawing() {
        pCoreFW.getGraphicsFW().clearScene(Color.BLACK);
        pCoreFW.getGraphicsFW().drawText(pCoreFW.getString(R.string.txt_settingScene_settings),
                250, 200, Color.GREEN, 40, null);
        pCoreFW.getGraphicsFW().drawText(pCoreFW.getString(R.string.txt_settingScene_sound),
                250, 300, Color.GREEN, 35, null);
        pCoreFW.getGraphicsFW().drawText(pCoreFW.getString(R.string.txt_settingScene_music),
                250, 400, Color.GREEN, 35, null);

        pCoreFW.getGraphicsFW().drawText("ON",
                400, 300, Color.GREEN, 35, null);
        pCoreFW.getGraphicsFW().drawText("ON",
                400, 400, Color.GREEN, 35, null);
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

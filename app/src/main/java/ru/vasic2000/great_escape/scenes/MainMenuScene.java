package ru.vasic2000.great_escape.scenes;

import android.graphics.Color;

import ru.vasic2000.great_escape.R;
import ru.vasic2000.great_escape.utilites.UtilResourse;
import ru.vasic2000.my_framework.core.CoreFW;
import ru.vasic2000.my_framework.core.SceneFW;

public class MainMenuScene extends SceneFW {

    public MainMenuScene(CoreFW coreFW) {
        super(coreFW);
    }

    @Override
    public void update() {
        if(pCoreFW.getTouchListenerFW().getTuchUp(50, 300, 230, 50)) {
            pCoreFW.setScene(new GameScene(pCoreFW));
            UtilResourse.sTouch.play(1);
        }
        if(pCoreFW.getTouchListenerFW().getTuchUp(50, 350, 210, 50)) {
            pCoreFW.setScene(new SettingsScene(pCoreFW));
            UtilResourse.sTouch.play(1);
        }
        if(pCoreFW.getTouchListenerFW().getTuchUp(50, 400, 240, 35)) {
            pCoreFW.setScene(new TopDistance(pCoreFW));
            UtilResourse.sTouch.play(1);
        }
        if(pCoreFW.getTouchListenerFW().getTuchUp(50, 450, 200, 35)) {
            pCoreFW.setScene(new ExitScene(pCoreFW));
            UtilResourse.sTouch.play(1);
        }
    }

    @Override
    public void drawing() {
        pGraficsFW.clearScene(Color.BLACK);
        pGraficsFW.drawText(pCoreFW.getString(R.string.txt_mainMenu_nameGame),
                100, 100, Color.WHITE, 60, UtilResourse.sMainMenuFont);
        pGraficsFW.drawText(pCoreFW.getString(R.string.txt_mainMenu_newGame),
                50, 300, Color.WHITE, 40, UtilResourse.sMainMenuFont);
        pGraficsFW.drawText(pCoreFW.getString(R.string.txt_mainMenu_settings),
                50, 350, Color.WHITE, 40, UtilResourse.sMainMenuFont);
        pGraficsFW.drawText(pCoreFW.getString(R.string.txt_mainMenu_results),
                50, 400, Color.WHITE, 40, UtilResourse.sMainMenuFont);
        pGraficsFW.drawText(pCoreFW.getString(R.string.txt_mainMenu_exitGame),
                50, 450, Color.WHITE, 40, UtilResourse.sMainMenuFont);
    }

    @Override
    public void dispose() {

    }

    @Override
    public void pause() {
        UtilResourse.sGameMusic.stop();
    }

    @Override
    public void resume() {

    }
}

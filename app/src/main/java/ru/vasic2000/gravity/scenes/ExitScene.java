package ru.vasic2000.gravity.scenes;

import android.graphics.Color;

import ru.vasic2000.gravity.utilites.UtilResourse;
import ru.vasic2000.my_framework.core.CoreFW;
import ru.vasic2000.my_framework.core.SceneFW;

class ExitScene extends SceneFW {

    public ExitScene(CoreFW coreFW) {
        super(coreFW);
    }

    @Override
    public void update() {
        if(pCoreFW.getTouchListenerFW().getTuchUp(150,300,100,50)) {
            pCoreFW.finish();
            UtilResourse.sTouch.play(1);
        }
        if(pCoreFW.getTouchListenerFW().getTuchUp(350,300,100,50)) {
            pCoreFW.setScene(new MainMenuScene(pCoreFW));
            UtilResourse.sTouch.play(1);
        }
    }

    @Override
    public void drawing() {
        pCoreFW.getGraphicsFW().clearScene(Color.BLACK);
        pCoreFW.getGraphicsFW().drawText("Are you shure?",
                150, 200, Color.WHITE, 50, UtilResourse.sMainMenuFont);
        pCoreFW.getGraphicsFW().drawText("YES",
                150, 300, Color.WHITE, 35, UtilResourse.sMainMenuFont);
        pCoreFW.getGraphicsFW().drawText("NO",
                350, 300, Color.WHITE, 35, UtilResourse.sMainMenuFont);
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

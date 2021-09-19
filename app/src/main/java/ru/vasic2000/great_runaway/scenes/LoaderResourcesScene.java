package ru.vasic2000.great_runaway.scenes;

import android.graphics.Color;

import ru.vasic2000.great_runaway.R;
import ru.vasic2000.great_runaway.interfaces.TaskCompleteListener;
import ru.vasic2000.great_runaway.tasks.LoaderTask;
import ru.vasic2000.great_runaway.utilites.UtilResourse;
import ru.vasic2000.my_framework.core.CoreFW;
import ru.vasic2000.my_framework.core.SceneFW;

public class LoaderResourcesScene extends SceneFW implements TaskCompleteListener {

    private static int mProgressLoader;

    public LoaderResourcesScene(CoreFW coreFW) {
        super(coreFW);
        mProgressLoader = 0;
        LoaderTask loaderTask = new LoaderTask(coreFW, this);
        loaderTask.execute();
    }

    @Override
    public void onComplete() {
        pCoreFW.setScene(new MainMenuScene(pCoreFW));
    }

    @Override
    public void update() {

    }

    @Override
    public void drawing() {
        pCoreFW.getGraphicsFW().clearScene(Color.BLACK);
        pCoreFW.getGraphicsFW().drawText(pCoreFW.getString(R.string.loading),
                100,100, Color.GREEN, 50, UtilResourse.sMainMenuFont);
        pCoreFW.getGraphicsFW().drawLine(0, 500, mProgressLoader, 500, Color.GREEN);
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

    public static void setmProgressLoader(int mProgressLoader) {
        LoaderResourcesScene.mProgressLoader = mProgressLoader;
    }
}

package ru.vasic2000.gravity.scenes;

import android.graphics.Color;

import ru.vasic2000.gravity.R;
import ru.vasic2000.gravity.interfaces.TaskCompleteListener;
import ru.vasic2000.gravity.tasks.LoaderTask;
import ru.vasic2000.my_framework.CoreFW;
import ru.vasic2000.my_framework.SceneFW;

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
        coreFW.setScene(new MainMenuScene(coreFW));
    }

    @Override
    public void update() {

    }

    @Override
    public void drawing() {
        coreFW.getGraphicsFW().clearScene(Color.BLACK);
        coreFW.getGraphicsFW().drawText(coreFW.getString(R.string.loading),
                100,100, Color.GREEN, 50, null);
        coreFW.getGraphicsFW().drawLine(0, 500, mProgressLoader, 500, Color.GREEN);
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

package ru.vasic2000.gravity;

import ru.vasic2000.gravity.classes.LoaderAssets;
import ru.vasic2000.gravity.scenes.MainMenuScene;
import ru.vasic2000.my_framework.CoreFW;
import ru.vasic2000.my_framework.SceneFW;

public class Main extends CoreFW {

    public SceneFW getStartScene() {
        LoaderAssets loaderAssets = new LoaderAssets(
                this, this.getGraphicsFW());
        return new MainMenuScene(this);
    }

}
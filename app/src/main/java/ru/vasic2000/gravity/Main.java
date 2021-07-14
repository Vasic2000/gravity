package ru.vasic2000.gravity;

import ru.vasic2000.gravity.scenes.MainMenuScene;
import ru.vasic2000.my_framework.CoreFW;
import ru.vasic2000.my_framework.SceneFW;

public class Main extends CoreFW {
    public SceneFW getStartScene() {
        return new MainMenuScene(this);
    }

}
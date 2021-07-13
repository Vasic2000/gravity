package ru.vasic2000.my_framework;

public abstract class SceneFW {
    private CoreFW coreFW;
    public int sceneWidth;
    public int sceneHeight;
    public GraphicsFW graficsFW;

    public SceneFW(CoreFW coreFW) {
        this.coreFW = coreFW;
    }

    public abstract void update();
    public abstract void drawing();
    public abstract void dispose();
    public abstract void oause();
    public abstract void resume();
}

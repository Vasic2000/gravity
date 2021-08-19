package ru.vasic2000.my_framework.core;

public abstract class SceneFW {
    protected CoreFW pCoreFW;
    protected int pSceneWidth;
    protected int pSceneHeight;
    protected GraphicsFW pGraficsFW;

    public SceneFW(CoreFW coreFW) {
        this.pCoreFW = coreFW;
        pSceneWidth = coreFW.getGraphicsFW().getWidthFrameBuffer();
        pSceneHeight = coreFW.getGraphicsFW().getHeightFrameBuffer();
        pGraficsFW = coreFW.getGraphicsFW();
    }

    public abstract void update();
    public abstract void drawing();
    public abstract void dispose();
    public abstract void pause();
    public abstract void resume();
}

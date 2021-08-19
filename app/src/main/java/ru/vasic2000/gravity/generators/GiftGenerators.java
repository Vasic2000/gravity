package ru.vasic2000.gravity.generators;

import ru.vasic2000.gravity.objects.MainPlayer;
import ru.vasic2000.gravity.objects.Protector;
import ru.vasic2000.my_framework.GraphicsFW;
import ru.vasic2000.my_framework.utils.UtilTimerDelay;

public class GiftGenerators {

    private Protector mProtector;
    private UtilTimerDelay mProtectorTimer;

    private int mMaxScreenX;
    private int mMaxScreenY;
    private int mMinScreenX;
    private int mMinScreenY;

    public GiftGenerators(int sceneWidth, int sceneHeight, int minScreenY) {
        init(sceneWidth, sceneHeight, minScreenY);
    }

    private void init(int sceneWidth, int sceneHeight, int minScreenY) {
        this.mMaxScreenX = sceneWidth;
        this.mMaxScreenY = sceneHeight;
        this.mMinScreenX = 0;
        this.mMinScreenY = minScreenY;

        mProtector = new Protector(mMaxScreenX, mMaxScreenY, minScreenY);

        mProtectorTimer = new UtilTimerDelay();
        mProtectorTimer.startTimer();
    }

    public void update(double playerSpeed) {
        if (mProtectorTimer.timerDelay(8) && (!MainPlayer.isShieldsOn())) {
            mProtector.update(playerSpeed);
            if (mProtector.getX() < mMinScreenX) {
                mProtector = null;
                mProtector = new Protector(mMaxScreenX, mMaxScreenY, mMinScreenY);
                mProtectorTimer.startTimer();
            }
        }
    }

    public void drawing(GraphicsFW graphicsFW) {
        mProtector.drawing(graphicsFW);
    }

    public Protector getProtector() {
        return mProtector;
    }

    public void receiveProtector() {
        mProtector = null;
        mProtector = new Protector(mMaxScreenX, mMaxScreenY, mMinScreenY);
        mProtectorTimer.startTimer();
    }
}

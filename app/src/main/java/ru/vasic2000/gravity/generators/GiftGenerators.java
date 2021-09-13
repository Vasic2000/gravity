package ru.vasic2000.gravity.generators;

import ru.vasic2000.gravity.objects.AddShield;
import ru.vasic2000.gravity.objects.MainPlayer;
import ru.vasic2000.gravity.objects.Protector;
import ru.vasic2000.my_framework.core.GraphicsFW;
import ru.vasic2000.my_framework.utils.UtilTimerDelay;

public class GiftGenerators {

    private Protector mProtector;
    private AddShield mAddShield;
    private UtilTimerDelay mProtectorTimer;
    private UtilTimerDelay mAddShieldTimer;

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
        mAddShield = new AddShield(mMaxScreenX, mMaxScreenY, minScreenY);

        mProtectorTimer = new UtilTimerDelay();
        mAddShieldTimer = new UtilTimerDelay();
        mProtectorTimer.startTimer();
        mAddShieldTimer.startTimer();
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

        if (mAddShieldTimer.timerDelay(11)) {
            mAddShield.update(playerSpeed);
            if (mAddShield.getX() < mMinScreenX) {
                mAddShield = null;
                mAddShield = new AddShield(mMaxScreenX, mMaxScreenY, mMinScreenY);
                mAddShieldTimer.startTimer();
            }
        }
    }

    public void drawing(GraphicsFW graphicsFW) {
        mProtector.drawing(graphicsFW);
        mAddShield.drawing(graphicsFW);
    }

    public Protector getProtector() {
        return mProtector;
    }

    public AddShield getShield() {
        return mAddShield;
    }

    public void receiveProtector() {
        mProtector = null;
        mProtector = new Protector(mMaxScreenX, mMaxScreenY, mMinScreenY);
        mProtectorTimer.startTimer();
    }

    public void receiveShield() {
        mAddShield = null;
        mAddShield = new AddShield(mMaxScreenX, mMaxScreenY, mMinScreenY);
        mAddShieldTimer.startTimer();
    }
}

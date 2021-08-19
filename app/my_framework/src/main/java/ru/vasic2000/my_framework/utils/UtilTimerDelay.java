package ru.vasic2000.my_framework.utils;

public class UtilTimerDelay {
    private double mStartTime;
    private double mNowTime;
    private double mElapsedTime;
    private final double SECOND = 1000000000;

    public void startTimer() {
        mStartTime = System.nanoTime()/SECOND;
    }

    public Boolean timerDelay(double second) {
        mNowTime = System.nanoTime()/SECOND;
        mElapsedTime = mNowTime - mStartTime;
        return mElapsedTime > second;
    }
}

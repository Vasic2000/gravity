package ru.vasic2000.my_framework.utils;

public class UtilTimerDelay {
    double startTime;
    double nowTime;
    double elapsedTime;
    final double SECOND = 1000000000;

    public void setStartTime() {
        startTime = System.nanoTime()/SECOND;
    }

    public Boolean timerDelay(double second) {
        nowTime = System.nanoTime()/SECOND;
        elapsedTime = nowTime - startTime;
        return elapsedTime > second;
    }
}

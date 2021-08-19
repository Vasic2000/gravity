package ru.vasic2000.my_framework.core;

import android.graphics.Rect;

public abstract class ObjectFW {
    protected int pMaxScreenX, pMaxScreenY;
    protected int pMinScreenX, pMinScreenY;

    protected int pX, pY;
    protected double pSpeed;

    protected Rect pHitBox;
    protected double pRadius;

    public int getMaxScreenX() {
        return pMaxScreenX;
    }

    public void setMaxScreenX(int maxScreenX) {
        this.pMaxScreenX = maxScreenX;
    }

    public int getMaxScreenY() {
        return pMaxScreenY;
    }

    public void setMaxScreenY(int maxScreenY) {
        this.pMaxScreenY = maxScreenY;
    }

    public int getMinScreenX() {
        return pMinScreenX;
    }

    public void setMinScreenX(int minScreenX) {
        this.pMinScreenX = minScreenX;
    }

    public int getpMinScreenY() {
        return pMinScreenY;
    }

    public void setpMinScreenY(int pMinScreenY) {
        this.pMinScreenY = pMinScreenY;
    }

    public int getX() {
        return pX;
    }

    public void setX(int x) {
        this.pX = x;
    }

    public int getY() {
        return pY;
    }

    public void setY(int y) {
        this.pY = y;
    }

    public double getSpeed() {
        return pSpeed;
    }

    public void setSpeed(double speed) {
        this.pSpeed = speed;
    }

    public Rect getHitBox() {
        return pHitBox;
    }

    public void setHitBox(Rect hitBox) {
        this.pHitBox = hitBox;
    }

    public double getRadius() {
        return pRadius;
    }

    public void setRadius(double radius) {
        this.pRadius = radius;
    }
}

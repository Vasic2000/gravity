package ru.vasic2000.gravity.objects;

import android.graphics.Rect;

import ru.vasic2000.my_framework.AnimationFW;
import ru.vasic2000.my_framework.CoreFW;
import ru.vasic2000.my_framework.GraphicsFW;
import ru.vasic2000.my_framework.ObjectFW;
import ru.vasic2000.gravity.utilites.UtilResourse;

public class MainPlayer extends ObjectFW {
    final int GRAVITY = -3;
    final int MAX_SPEED = 15;
    final int MIN_SPEED = 1;

    AnimationFW animMainPlayer;
    AnimationFW animMainPlayerBoost;
    CoreFW coreFW;

    Boolean boosting;
    private int playerShields;

    public MainPlayer(CoreFW coreFW, int maxScreenX, int maxScreenY, int minScreenY) {
        x = 20;
        y = 200;
        speed = 2;
        playerShields = 3;

        boosting = false;

        radius = UtilResourse.spritePlayer.get(0).getWidth() / 4;

        this.coreFW = coreFW;
        this.maxScreenX = maxScreenX;
        this.maxScreenY = maxScreenY - UtilResourse.spritePlayer.get(0).getHeight();
        this.minScreenY = minScreenY;
        animMainPlayer = new AnimationFW(3, UtilResourse.spritePlayer.get(0),
                UtilResourse.spritePlayer.get(1),
                UtilResourse.spritePlayer.get(2),
                UtilResourse.spritePlayer.get(3));
        animMainPlayerBoost = new AnimationFW(3, UtilResourse.spritePlayerBoost.get(0),
                UtilResourse.spritePlayerBoost.get(1),
                UtilResourse.spritePlayerBoost.get(2),
                UtilResourse.spritePlayerBoost.get(3));
    }

    public void update() {

        if(coreFW.getTouchListenerFW().getTuchDown(0, maxScreenY, maxScreenX, maxScreenY)) {
            startBoosting();
        }

        if(coreFW.getTouchListenerFW().getTuchUp(0, maxScreenY, maxScreenX, maxScreenY)) {
            stopBoosting();
        }

        if(boosting)
            speed += 0.1;
        else
            speed -= 3;

        y-=(speed + GRAVITY);
        if(y < minScreenY) y = minScreenY;
        if(y > maxScreenY) y = maxScreenY;

        if(boosting)
            animMainPlayerBoost.runAnimation();
        else
            animMainPlayer.runAnimation();

        if(speed > MAX_SPEED) speed = MAX_SPEED;
        if(speed < MIN_SPEED) speed = MIN_SPEED;

        hitBox = new Rect(x,y,
                UtilResourse.spritePlayer.get(0).getWidth(),
                UtilResourse.spritePlayer.get(0).getWidth());
    }

    private void startBoosting() {
        boosting = true;
    }

    private void stopBoosting() {
        boosting = false;
    }

    public void drawing(GraphicsFW graphicsFW) {
        if(boosting)
            animMainPlayerBoost.drawAnimation(graphicsFW, x, y);
        else
            animMainPlayer.drawAnimation(graphicsFW, x, y);
    }

    public double getPlayerSpeed() {
        return speed;
    }
    public int getPlayerShields() {
        return playerShields;
    }

    public void hitEnemy() {
        playerShields--;
    }
}

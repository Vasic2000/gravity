package ru.vasic2000.gravity.objects;

import android.graphics.Rect;

import ru.vasic2000.gravity.classes.GameManager;
import ru.vasic2000.my_framework.AnimationFW;
import ru.vasic2000.my_framework.CoreFW;
import ru.vasic2000.my_framework.GraphicsFW;
import ru.vasic2000.my_framework.ObjectFW;
import ru.vasic2000.gravity.utilites.UtilResourse;
import ru.vasic2000.my_framework.utils.UtilTimerDelay;

public class MainPlayer extends ObjectFW {
    final int GRAVITY = -3;
    final int MAX_SPEED = 15;
    final int MIN_SPEED = 1;

    AnimationFW animMainPlayer;
    AnimationFW animMainPlayerBoost;
    AnimationFW animMainPlayerExplose;
    AnimationFW animMainPlayerShieldsOn;
    AnimationFW animMainPlayerShieldsBoostOn;


    CoreFW coreFW;

    UtilTimerDelay timerShieldHitON;
    UtilTimerDelay timerGameOwerON;
    UtilTimerDelay timerShieldOn;

    boolean boosting;
    boolean hitEnemy;
    boolean isGameOver;

    static boolean shieldsOn;

    private int playerShields;

    public MainPlayer(CoreFW coreFW, int maxScreenX, int maxScreenY, int minScreenY) {
        x = 20;
        y = 200;
        speed = GameManager.SPEED_ANIMATION;
        playerShields = 3;

        boosting = false;
        hitEnemy = false;
        isGameOver = false;
        shieldsOn = false;

        radius = UtilResourse.sSpritePlayer.get(0).getWidth() / 4;

        timerShieldHitON = new UtilTimerDelay();
        timerGameOwerON = new UtilTimerDelay();
        timerShieldOn = new UtilTimerDelay();

        this.coreFW = coreFW;
        this.maxScreenX = maxScreenX;
        this.maxScreenY = maxScreenY - UtilResourse.sSpritePlayer.get(0).getHeight();
        this.minScreenY = minScreenY;

        initAnimations();
    }

    private void initAnimations() {
        animMainPlayer = new AnimationFW(speed, UtilResourse.sSpritePlayer.get(0),
                UtilResourse.sSpritePlayer.get(1),
                UtilResourse.sSpritePlayer.get(2),
                UtilResourse.sSpritePlayer.get(3));
        animMainPlayerBoost = new AnimationFW(speed, UtilResourse.sSpritePlayerBoost.get(0),
                UtilResourse.sSpritePlayerBoost.get(1),
                UtilResourse.sSpritePlayerBoost.get(2),
                UtilResourse.sSpritePlayerBoost.get(3));
        animMainPlayerExplose = new AnimationFW(speed, UtilResourse.sSpritePlayerExplose.get(0),
                UtilResourse.sSpritePlayerExplose.get(1),
                UtilResourse.sSpritePlayerExplose.get(2),
                UtilResourse.sSpritePlayerExplose.get(3));
        animMainPlayerShieldsOn = new AnimationFW(speed, UtilResourse.sSpritePlayerShieldsOn.get(0),
                UtilResourse.sSpritePlayerShieldsOn.get(1),
                UtilResourse.sSpritePlayerShieldsOn.get(2),
                UtilResourse.sSpritePlayerShieldsOn.get(3));
        animMainPlayerShieldsBoostOn = new AnimationFW(speed, UtilResourse.sSpritePlayerShieldsBoost.get(0),
                UtilResourse.sSpritePlayerShieldsBoost.get(1),
                UtilResourse.sSpritePlayerShieldsBoost.get(2),
                UtilResourse.sSpritePlayerShieldsBoost.get(3));
    }

    public void update() {

        if(coreFW.getTouchListenerFW().getTuchDown(0, maxScreenY, maxScreenX, maxScreenY)) {
            startBoosting();
        }

        if(coreFW.getTouchListenerFW().getTuchUp(0, maxScreenY, maxScreenX, maxScreenY)) {
            stopBoosting();
        }

        if (isGameOver) {
            animMainPlayerExplose.runAnimation();
        }


        if (boosting)
            speed += 0.1;
        else
            speed -= 3;
        y -= (speed + GRAVITY);
        if (y < minScreenY) y = minScreenY;
        if (y > maxScreenY) y = maxScreenY;
        updateBoosting();
        hitBox = new Rect(x, y,
                UtilResourse.sSpritePlayer.get(0).getWidth(),
                UtilResourse.sSpritePlayer.get(0).getWidth());

        if (isGameOver) {
            animMainPlayerExplose.runAnimation();
        }
    }

    private void updateBoosting() {
        if (boosting) {
            if (shieldsOn) {
                animMainPlayerShieldsBoostOn.runAnimation();
            } else {
                animMainPlayerBoost.runAnimation();
            }
        } else if (shieldsOn) {
            animMainPlayerShieldsOn.runAnimation();
        } else animMainPlayer.runAnimation();

        if (speed > MAX_SPEED) speed = MAX_SPEED;
        if (speed < MIN_SPEED) speed = MIN_SPEED;
    }

    private void startBoosting() {
        boosting = true;
    }

    private void stopBoosting() {
        boosting = false;
    }

    public void drawing(GraphicsFW graphicsFW) {
        if (!isGameOver) {
            if (!hitEnemy) {
                if (boosting) {
                    if (shieldsOn) {
                        animMainPlayerShieldsBoostOn.drawAnimation(graphicsFW, x, y);
                    } else animMainPlayerBoost.drawAnimation(graphicsFW, x, y);
                } else if (shieldsOn) {
                    animMainPlayerShieldsOn.drawAnimation(graphicsFW, x, y);
                } else animMainPlayer.drawAnimation(graphicsFW, x, y);
            } else graphicsFW.drawTexture(UtilResourse.sShieldHitEnamy, x, y);

            hitEnemy = !timerShieldHitON.timerDelay(0.3);
        } else {
            animMainPlayerExplose.drawAnimation(graphicsFW, x, y);
            if (timerGameOwerON.timerDelay(0.6)) {
                GameManager.gameOver = true;
            }
        }
    }

    public double getPlayerSpeed() {
        return speed;
    }
    public int getPlayerShields() {
        return playerShields;
    }

    public void hitEnemy() {
        if(!shieldsOn) {
            playerShields--;
            if (playerShields < 0) {
                UtilResourse.sExplode.play(3);
                isGameOver = true;
                timerGameOwerON.startTimer();
            }
            hitEnemy = true;
            timerShieldHitON.startTimer();
        }
    }

    public void takeProtector() {
        shieldsOn = true;
        timerShieldOn.startTimer();
    }

    public static boolean isShieldsOn() {
        return shieldsOn;
    }
}

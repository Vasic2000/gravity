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

        radius = UtilResourse.spritePlayer.get(0).getWidth() / 4;

        timerShieldHitON = new UtilTimerDelay();
        timerGameOwerON = new UtilTimerDelay();
        timerShieldOn = new UtilTimerDelay();

        this.coreFW = coreFW;
        this.maxScreenX = maxScreenX;
        this.maxScreenY = maxScreenY - UtilResourse.spritePlayer.get(0).getHeight();
        this.minScreenY = minScreenY;

        initAnimations();
    }

    private void initAnimations() {
        animMainPlayer = new AnimationFW(speed, UtilResourse.spritePlayer.get(0),
                UtilResourse.spritePlayer.get(1),
                UtilResourse.spritePlayer.get(2),
                UtilResourse.spritePlayer.get(3));
        animMainPlayerBoost = new AnimationFW(speed, UtilResourse.spritePlayerBoost.get(0),
                UtilResourse.spritePlayerBoost.get(1),
                UtilResourse.spritePlayerBoost.get(2),
                UtilResourse.spritePlayerBoost.get(3));
        animMainPlayerExplose = new AnimationFW(speed, UtilResourse.spritePlayerExplose.get(0),
                UtilResourse.spritePlayerExplose.get(1),
                UtilResourse.spritePlayerExplose.get(2),
                UtilResourse.spritePlayerExplose.get(3));
        animMainPlayerShieldsOn = new AnimationFW(speed, UtilResourse.spritePlayerShieldsOn.get(0),
                UtilResourse.spritePlayerShieldsOn.get(1),
                UtilResourse.spritePlayerShieldsOn.get(2),
                UtilResourse.spritePlayerShieldsOn.get(3));
        animMainPlayerShieldsBoostOn = new AnimationFW(speed, UtilResourse.spritePlayerShieldsBoost.get(0),
                UtilResourse.spritePlayerShieldsBoost.get(1),
                UtilResourse.spritePlayerShieldsBoost.get(2),
                UtilResourse.spritePlayerShieldsBoost.get(3));
    }

    public void update() {

        if(coreFW.getTouchListenerFW().getTuchDown(0, maxScreenY, maxScreenX, maxScreenY)) {
            startBoosting();
        }

        if(coreFW.getTouchListenerFW().getTuchUp(0, maxScreenY, maxScreenX, maxScreenY)) {
            stopBoosting();
        }

        if (boosting)
            speed += 0.1;
        else
            speed -= 3;

        y -= (speed + GRAVITY);
        if (y < minScreenY) y = minScreenY;
        if (y > maxScreenY) y = maxScreenY;

        if(timerShieldOn.timerDelay(5)) {
            shieldsOn = false;
        }

        if (boosting)
            if (shieldsOn) {
                animMainPlayerShieldsBoostOn.runAnimation();
            } else {
                animMainPlayerBoost.runAnimation();
            }
        else if (shieldsOn) {
            animMainPlayerShieldsOn.runAnimation();
        } else animMainPlayer.runAnimation();

        if (speed > MAX_SPEED) speed = MAX_SPEED;
        if (speed < MIN_SPEED) speed = MIN_SPEED;

        hitBox = new Rect(x, y,
                UtilResourse.spritePlayer.get(0).getWidth(),
                UtilResourse.spritePlayer.get(0).getWidth());

        if (isGameOver) {
            animMainPlayerExplose.runAnimation();
        }
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
            } else graphicsFW.drawTexture(UtilResourse.shieldHitEnamy, x, y);

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
                UtilResourse.explode.play(3);
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

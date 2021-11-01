package ru.vasic2000.gravity.objects;

import android.graphics.Rect;

import ru.vasic2000.gravity.classes.GameManager;
import ru.vasic2000.gravity.utilites.SettingsGame;
import ru.vasic2000.my_framework.core.Animation_4_Frames;
import ru.vasic2000.my_framework.core.CoreFW;
import ru.vasic2000.my_framework.core.GraphicsFW;
import ru.vasic2000.my_framework.core.ObjectFW;
import ru.vasic2000.gravity.utilites.UtilResourse;
import ru.vasic2000.my_framework.utils.UtilTimerDelay;

public class MainPlayer extends ObjectFW {
    private final int GRAVITY = -4;
    private final int MAX_SPEED = 15;
    private final int MIN_SPEED = 1;
    private final int PLAYER_SHIELDS = 5;
    private final int VERTICAL_SPEED = 2;

    private Animation_4_Frames mAnimMainPlayer;
    private Animation_4_Frames mAnimMainPlayerBoost;
    private Animation_4_Frames mAnimMainPlayerExplose;
    private Animation_4_Frames mAnimMainPlayerShieldsOn;
    private Animation_4_Frames mAnimMainPlayerShieldsBoostOn;

    private CoreFW mCoreFW;

    private UtilTimerDelay mTimerShieldHitON;
    private UtilTimerDelay mTimerGameOwerON;
    private UtilTimerDelay mTimerShieldOn;

    private boolean mBoosting;
    private boolean mHitEnemy;
    private boolean mIsGameOver;

    private static boolean sShieldsOn;

    private int mPlayerShields;

    public MainPlayer(CoreFW coreFW, int maxScreenX, int maxScreenY, int minScreenY) {
        init(coreFW, maxScreenX, maxScreenY, minScreenY);
        initAnimations();
    }

    private void init(CoreFW coreFW, int maxScreenX, int maxScreenY, int minScreenY) {
        pX = 20;
        pY = 200;
        pSpeed = (int) GameManager.SPEED_ANIMATION/1.5;
        mPlayerShields = PLAYER_SHIELDS;

        mBoosting = false;
        mHitEnemy = false;
        mIsGameOver = false;
        sShieldsOn = false;

        pRadius = UtilResourse.sSpritePlayer.get(0).getWidth() / 4;

        mTimerShieldHitON = new UtilTimerDelay();
        mTimerGameOwerON = new UtilTimerDelay();
        mTimerShieldOn = new UtilTimerDelay();

        this.mCoreFW = coreFW;
        this.pMaxScreenX = maxScreenX;
        this.pMaxScreenY = maxScreenY - UtilResourse.sSpritePlayer.get(0).getHeight();
        this.pMinScreenY = minScreenY;
    }

    private void initAnimations() {
        mAnimMainPlayer = new Animation_4_Frames(pSpeed, UtilResourse.sSpritePlayer.get(0),
                UtilResourse.sSpritePlayer.get(1),
                UtilResourse.sSpritePlayer.get(2),
                UtilResourse.sSpritePlayer.get(3));
        mAnimMainPlayerBoost = new Animation_4_Frames(pSpeed, UtilResourse.sSpritePlayerBoost.get(0),
                UtilResourse.sSpritePlayerBoost.get(1),
                UtilResourse.sSpritePlayerBoost.get(2),
                UtilResourse.sSpritePlayerBoost.get(3));
        mAnimMainPlayerExplose = new Animation_4_Frames(pSpeed, UtilResourse.sSpritePlayerExplose.get(0),
                UtilResourse.sSpritePlayerExplose.get(1),
                UtilResourse.sSpritePlayerExplose.get(2),
                UtilResourse.sSpritePlayerExplose.get(3));
        mAnimMainPlayerShieldsOn = new Animation_4_Frames(pSpeed, UtilResourse.sSpritePlayerShieldsOn.get(0),
                UtilResourse.sSpritePlayerShieldsOn.get(1),
                UtilResourse.sSpritePlayerShieldsOn.get(2),
                UtilResourse.sSpritePlayerShieldsOn.get(3));
        mAnimMainPlayerShieldsBoostOn = new Animation_4_Frames(pSpeed, UtilResourse.sSpritePlayerShieldsBoost.get(0),
                UtilResourse.sSpritePlayerShieldsBoost.get(1),
                UtilResourse.sSpritePlayerShieldsBoost.get(2),
                UtilResourse.sSpritePlayerShieldsBoost.get(3));
    }

    public void update(GameManager.BossState mBossState) {
        if(mBossState == GameManager.BossState.BOSS) {
            pSpeed = 0;
        }

        if(mCoreFW.getTouchListenerFW().getTuchDown(0, pMaxScreenY, pMaxScreenX, pMaxScreenY)) {
            startBoosting();
        }

        if(mCoreFW.getTouchListenerFW().getTuchUp(0, pMaxScreenY, pMaxScreenX, pMaxScreenY)) {
            stopBoosting();
        }

        if (mTimerShieldOn.timerDelay(5)) {
            sShieldsOn = false;
        }

        if (mIsGameOver) {
            mAnimMainPlayerExplose.runAnimation();
        }

        if (mBossState == GameManager.BossState.LEVEL) {
            if (mBoosting) {
                pSpeed += 0.2;
                pY -= (VERTICAL_SPEED + pSpeed);
            } else {
                pSpeed -= 1.5;
                pY -= (pSpeed + GRAVITY);
            }
        } else {
            if (mBoosting) {
                pY -= (VERTICAL_SPEED + 3);
            } else {
                pY -= GRAVITY;
            }
        }

        if (pY < pMinScreenY) pY = pMinScreenY;
        if (pY > pMaxScreenY) pY = pMaxScreenY;

        updateBoosting(mBossState);
        pHitBox = new Rect(pX, pY,
                UtilResourse.sSpritePlayer.get(0).getWidth(),
                UtilResourse.sSpritePlayer.get(0).getWidth());

        if (mIsGameOver) {
            mAnimMainPlayerExplose.runAnimation();
        }
    }

    private void updateBoosting(GameManager.BossState mBossState) {
        if (mBoosting) {
            if (sShieldsOn) {
                mAnimMainPlayerShieldsBoostOn.runAnimation();
            } else {
                mAnimMainPlayerBoost.runAnimation();
            }
        } else if (sShieldsOn) {
            mAnimMainPlayerShieldsOn.runAnimation();
        } else mAnimMainPlayer.runAnimation();
        if(mBossState == GameManager.BossState.LEVEL) {
            if (pSpeed > MAX_SPEED) pSpeed = MAX_SPEED;
            if (pSpeed < MIN_SPEED) pSpeed = MIN_SPEED;
        }
    }

    private void startBoosting() {
        mBoosting = true;
    }

    private void stopBoosting() {
        mBoosting = false;
    }

    public void drawing(GraphicsFW graphicsFW) {
        if (!mIsGameOver) {
            if (!mHitEnemy) {
                if (mBoosting) {
                    if (sShieldsOn) {
                        mAnimMainPlayerShieldsBoostOn.drawAnimation(graphicsFW, pX, pY);
                    } else mAnimMainPlayerBoost.drawAnimation(graphicsFW, pX, pY);
                } else if (sShieldsOn) {
                    mAnimMainPlayerShieldsOn.drawAnimation(graphicsFW, pX, pY);
                } else mAnimMainPlayer.drawAnimation(graphicsFW, pX, pY);
            } else graphicsFW.drawTexture(UtilResourse.sShieldHitEnamy, pX, pY);

            mHitEnemy = !mTimerShieldHitON.timerDelay(0.3);
        } else {
            mAnimMainPlayerExplose.drawAnimation(graphicsFW, pX, pY);
            if (mTimerGameOwerON.timerDelay(0.6)) {
                GameManager.gameOver = true;
                UtilResourse.sGameMusic.stop();
                UtilResourse.sLooseSound.play(3);
            }
        }
    }

    public double getPlayerSpeed() {
        return pSpeed;
    }

    public int getPlayerShields() {
        return mPlayerShields;
    }

    public void hitEnemy() {
        if(!sShieldsOn) {
            mPlayerShields--;
            if (mPlayerShields < 0) {
                if(SettingsGame.sSoundOn) {
                    UtilResourse.sExplode.play(3);
                }
                mIsGameOver = true;
                mTimerGameOwerON.startTimer();
            }
            mHitEnemy = true;
            mTimerShieldHitON.startTimer();
        }
        pSpeed = MIN_SPEED;
    }

    public void takeProtector() {
        sShieldsOn = true;
        UtilResourse.sSchieldSound.play(3);
        mTimerShieldOn.startTimer();
    }

    public void takeShield() {
        mPlayerShields++;
        UtilResourse.sTakeSound.play(3);
    }

    public static boolean isShieldsOn() {
        return sShieldsOn;
    }
}

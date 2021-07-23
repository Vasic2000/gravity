package ru.vasic2000.gravity.objects;

import java.time.chrono.MinguoChronology;

import ru.vasic2000.gravity.classes.AnimationGame;
import ru.vasic2000.my_framework.GraphicsFW;
import ru.vasic2000.my_framework.ObjectFW;
import ru.vasic2000.my_framework.utils.UtilResourse;

public class MainPlayer extends ObjectFW {
    final int GRAVITY = -3;
    final int MAX_SPEED = 15;
    final int MIN_SPEED = 1;

    AnimationGame animSpriteMainPlayer;

    public MainPlayer(int maxScreenX, int maxScreenY, int minScreenY) {
        x = 20;
        y = 200;
        speed = 1;

        this.maxScreenX = maxScreenX;
        this.maxScreenY = maxScreenY - UtilResourse.spritePlayer.get(0).getHeight();
        animSpriteMainPlayer = new AnimationGame(speed, UtilResourse.spritePlayer.get(0),
                UtilResourse.spritePlayer.get(1),
                UtilResourse.spritePlayer.get(2),
                UtilResourse.spritePlayer.get(3));
    }

    public void update() {
        y-=speed + GRAVITY;
        if(y < minScreenY) y = minScreenY;
        if(y > maxScreenY) y = maxScreenY;
        animSpriteMainPlayer.runAnimation();
    }

    public void drawing(GraphicsFW graphicsFW) {
        animSpriteMainPlayer.drawAnimation(graphicsFW, x, y);
    }
}
